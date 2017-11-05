package com.lucifer.service.hfc;

import com.lucifer.dao.hfc.ArtistDao;
import com.lucifer.dao.hfc.NewsDao;
import com.lucifer.exception.ArgumentException;
import com.lucifer.model.hfc.Artist;
import com.lucifer.model.hfc.News;
import com.lucifer.utils.PageInfoWriter;
import com.lucifer.utils.StringHelper;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * Created by liufx on 17/2/9.
 */
@Component
public class ArtistSearchService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${solr.url.artist}")
    private String solrServerUrl;

    @Resource
    private ArtistDao artistDao;

    private HttpSolrClient httpSolrClient;

    @PostConstruct
    private void init(){
        httpSolrClient = new HttpSolrClient.Builder(solrServerUrl).build();
    }

    public void reImport() throws IOException, SolrServerException {
        logger.info("----UserSearchService reImport has been called------");

        clearSolr();

        Date updatedAt = this.getSolrMaxUpdatedAt();

        while (true){
            List<Artist> artistList = artistDao.artistListOrderByUpdatedAt(updatedAt,1000);
            logger.info("artistList size: "+artistList.size());
            if (artistList.size() == 0) {
                break;
            }

            Collection<SolrInputDocument> docList = new ArrayList<SolrInputDocument>();
            List<String> deleteIdList =new ArrayList<String>();
            for (Artist artist : artistList) {
                if (artist.getIsDeleted().equals(0)) {
                    SolrInputDocument doc1 = this.parse(artist);
                    docList.add(doc1);
                } else {
                    deleteIdList.add(artist.getId().toString());
                }
            }
            if (docList.size() > 0) {
                httpSolrClient.add(docList);
                httpSolrClient.commit();
                logger.info("httpSolrClient.commit()");
            }

            if (deleteIdList.size()>0) {
                httpSolrClient.deleteById(deleteIdList);
                httpSolrClient.commit();
                logger.info("delete httpSolrClient.commit()");
            }

            updatedAt = artistList.get(artistList.size() - 1).getUpdatedAt();
            logger.info("change updatedAt to: "+ updatedAt.toString());
        }

    }

    /**
     * 清除solr里的所有数据
     * @throws IOException
     * @throws SolrServerException
     */
    private void clearSolr() throws IOException, SolrServerException {
        httpSolrClient.deleteByQuery("*:*");
        httpSolrClient.commit();
    }

    private Date getSolrMaxUpdatedAt() throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.addSort("updatedAt", SolrQuery.ORDER.desc);
        query.setRows(1);
        QueryResponse rsp = httpSolrClient.query(query);
        SolrDocumentList docs = rsp.getResults();
        logger.info("docs.size(): "+ docs.size());
        if (docs.size() > 0) {
            SolrDocument document = docs.get(0);
            Date updatedAt = (Date) document.getFieldValue("updatedAt");
            logger.info("updatedAt:" + updatedAt);
            // updated_at=new Date(updated_at.getTime());
            logger.info("solar back updatedAt is: " + updatedAt);
            return updatedAt;
        }

        return null;
    }

    private SolrInputDocument parse(Artist artist){
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", artist.getId());
        doc.addField("name", artist.getName());
        doc.addField("tag", artist.getTag());
        doc.addField("updatedAt", artist.getUpdatedAt());
        doc.addField("avatar", artist.getAvatar());
        return doc;
    }

    public void updateArtist(Artist artist) throws IOException, SolrServerException, ArgumentException {
        if (null == artist.getId()) {
            throw new ArgumentException("id 不能为空");
        }
        if (StringHelper.isEmpty(artist.getName())) {
            throw new ArgumentException("name 不能为空");
        }
        SolrInputDocument doc1 = this.parse(artist);
        httpSolrClient.add(doc1);
        httpSolrClient.commit();
    }

    public PageInfoWriter searchList(String text, Integer page, Integer rows) throws IOException, JSONException {
        Integer offset = (page - 1) * rows;
        Map<String,Object> resultMap = new HashMap<>();
        SolrQuery query = new SolrQuery();
        logger.info("text is :" + text);
        text = text.replaceAll("\\u002A","");
        text = text.replaceAll("&","");
        text = text.replaceAll("\\u003F","");
        PageInfoWriter pageInfo = PageInfoWriter.create(page, rows, 0);
        if (StringHelper.isEmpty(text)) {
            //query.setQuery("*:*");

            //pageInfo.writeToGateway(resultMap);
            pageInfo.setDataList(new ArrayList<>());
            pageInfo.setAllRecordCount(0);
            return pageInfo;
        } else {
            query.setQuery(text);
        }

//        query.setQuery("*:*");
//        query.addFilterQuery("nickName:*"+text+"*");
        query.setHighlight(true);
        query.setParam("hl.fl", "name,tag");


        query.setRows(rows);
        query.setStart(offset);
        QueryResponse rsp = null;
        List<Artist> artistList = new ArrayList<Artist>();
        try{
            rsp = httpSolrClient.query(query);

        }catch (Exception e){
            e.printStackTrace();

            return pageInfo;
        }

        Long numberFound = rsp.getResults().getNumFound();

        SolrDocumentList docs = rsp.getResults();
        Map<String, Map<String, List<String>>> highMap = rsp.getHighlighting();

        String lowerCaseText = text.toLowerCase();

        //List<String> segments = this.segment(lowerCaseText);

        logger.info("docs.size(): "+ docs.size());
        for (int i = 0; i < docs.size(); i++) {

            SolrDocument doc = docs.get(i);


            logger.info("resumeDoc.getFieldValue(\"title\"): "+doc.getFieldValue("title"));

            Artist artist = this.docToObject(doc);
            //user = userService.getUserInfoById(user.getId());

            artistList.add(artist);
            this.setHighlighting(artist,highMap);
//            if (!this.isCorrectHighlighting(lowerCaseText,user.getNickName())) {
//                String highlightingNickName = this.highlightingNickName(segments,user.getNickName());
//                user.setNickName(highlightingNickName);
//            }
//            String highlightingNickName = new HighlightingUtil().process(user.getNickName(),lowerCaseText);
//            user.setNickName(highlightingNickName);

        }


        pageInfo.setDataList(artistList);
        pageInfo.setAllRecordCount(numberFound.intValue());

        return pageInfo;
    }




    private Artist docToObject(SolrDocument doc){
        Artist artist = new Artist();
        artist.setId((Long)doc.getFieldValue("id"));
        artist.setName((String)doc.getFieldValue("name"));
        artist.setAvatar((String)doc.getFieldValue("avatar"));
        artist.setUpdatedAt((Date) doc.getFieldValue("updatedAt"));
        artist.setTag((String)doc.getFieldValue("tag"));
        //news.setPublishAt((Date) doc.getFieldValue("publishAt"));
        return artist;
    }

    private void setHighlighting(Artist artist , Map<String, Map<String, List<String>>> highMap ){
        Map<String,List<String>> highMap2 = highMap.get(artist.getId().toString());
        logger.info("highMap2 is "+highMap2);
        if (null != highMap2) {
            List<String> listString = highMap2.get("name");
            logger.info("listString is "+listString);
            if(null != listString&& listString.size()>0) {
                String highString =  listString.get(0);
                logger.info("name is "+highString);
                artist.setName(highString);
            }

            List<String> listTag = highMap2.get("tag");
            logger.info("listTag is "+listTag);
            if(null != listTag&& listTag.size()>0) {
                String highString =  listTag.get(0);
                logger.info("tag is "+highString);
                artist.setTag(highString);
            }
        }

    }

    private Boolean isCorrectHighlighting(String param,String highLightingString){
        Integer startOffset =  highLightingString.indexOf("<em>");
        if (startOffset == -1) {
            return false;
        }
        Integer endOffset = highLightingString.indexOf("</em>");
        if (endOffset == -1) {
            return false;
        }
        String emString =  highLightingString.substring(startOffset+"<em>".length(),endOffset);
        if (param.indexOf(emString.toLowerCase()) != -1) {
            return true;
        }
        return false;
    }

    private List<String> segment(String sentence) {
        //System.out.println("sentence is : "+ sentence);
        //System.out.println("sentence length is : "+ sentence.length());
        List<String> segTokenList = new ArrayList<String>();
        int maxWordLength = sentence.length();

        for (int wordLength = maxWordLength; wordLength>0; wordLength --) {
            for (int offset = 0; offset<sentence.length()-wordLength+1;offset++) {
                String word = sentence.substring(offset,offset+wordLength);
                //System.out.println("word is : "+ word);
                //System.out.println("offset: "+ offset+"  wordLength: "+ wordLength);
                //char[]  charArray = Utility.STRING_CHAR_ARRAY;
                //SegToken segToken = new SegToken(word.toCharArray(),offset,offset+wordLength,WordType.CHINESE_WORD,wordLength);
                segTokenList.add(word);
            }
        }
        return segTokenList;
    }

    private String highlightingNickName(List<String> segments,String nickName){
        nickName = nickName.replaceAll("<em>","");
        nickName = nickName.replaceAll("</em>","");
        String lowerCaseNickName = nickName.toLowerCase();
        for (int i = 0; i < segments.size(); i++) {
            int index = lowerCaseNickName.indexOf(segments.get(i));
            if (index != -1) {
                String piPeiText = nickName.substring(index, index + segments.get(i).length());
                String highlightingNickName = nickName.replaceAll(piPeiText, "<em>" + piPeiText + "</em>");
                return highlightingNickName;
            }
        }
        return nickName;
    }

}
