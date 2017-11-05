package com.lucifer.service.hfc;

import com.lucifer.dao.hfc.*;
import com.lucifer.model.hfc.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fx on 2017/1/31.
 */
@Component
public class IndexService {

    @Resource
    private IndexDao indexDao;

    @Resource
    private NewsDao newsDao;

    @Resource
    private ArtistDao artistDao;

    @Resource
    private CulturalFinanceDao culturalFinanceDao;

    @Resource
    private ResearchReportDao researchReportDao;

    @Resource
    private SummitDao summitDao;

    @Resource
    private MemberActivityDao memberActivityDao;

    @Resource
    private AtlasDao atlasDao;

    public List<IndexRecommend> newsIndexRecommendList(){
        List<IndexRecommend> indexRecommendList = indexDao.newsIndexRecommendList();
        for(IndexRecommend indexRecommend:indexRecommendList){
            News news = newsDao.getNews(indexRecommend.getTargetId());
            indexRecommend.setTarget(news);
        }
        return indexRecommendList;
    }

    public List<IndexRecommend> newsIndexRecommendListLimit6(){
        List<IndexRecommend> indexRecommendList = indexDao.newsIndexRecommendListLimit6();
        for(IndexRecommend indexRecommend:indexRecommendList){
            News news = newsDao.getNews(indexRecommend.getTargetId());
            indexRecommend.setTarget(news);
        }
        return indexRecommendList;
    }

    public List<IndexRecommend> artistIndexRecommendList(){
        List<IndexRecommend> indexRecommendList = indexDao.artistIndexRecommendList();
        for(IndexRecommend indexRecommend:indexRecommendList){
            Artist artist = artistDao.getArtist(indexRecommend.getTargetId());
            indexRecommend.setTarget(artist);
        }
        return indexRecommendList;
    }

    public List<IndexRecommend> artistIndexRecommendListLimit16(){
        List<IndexRecommend> indexRecommendList = indexDao.artistIndexRecommendListLimit16();
        for(IndexRecommend indexRecommend:indexRecommendList){
            Artist artist = artistDao.getArtist(indexRecommend.getTargetId());
            indexRecommend.setTarget(artist);
        }
        return indexRecommendList;
    }

    public List<IndexRecommend> financeIndexRecommendList(){
        List<IndexRecommend> indexRecommendList = indexDao.financeIndexRecommendList();
        for(IndexRecommend indexRecommend:indexRecommendList){
            CulturalFinance target = culturalFinanceDao.getCulturalFinance(indexRecommend.getTargetId());
            indexRecommend.setTarget(target);
        }
        return indexRecommendList;
    }

    public List<IndexRecommend> financeIndexRecommendListLimit3(){
        List<IndexRecommend> indexRecommendList = indexDao.financeIndexRecommendListLimit3();
        for(IndexRecommend indexRecommend:indexRecommendList){
            CulturalFinance target = culturalFinanceDao.getCulturalFinance(indexRecommend.getTargetId());
            indexRecommend.setTarget(target);
        }
        return indexRecommendList;
    }

    public List<IndexRecommend> researchIndexRecommendList(){
        List<IndexRecommend> indexRecommendList = indexDao.researchIndexRecommendList();
        for(IndexRecommend indexRecommend:indexRecommendList){
            ResearchReport target = researchReportDao.getResearchReport(indexRecommend.getTargetId());
            indexRecommend.setTarget(target);
        }
        return indexRecommendList;
    }

    public List<IndexRecommend> researchIndexRecommendListLimit3(){
        List<IndexRecommend> indexRecommendList = indexDao.researchIndexRecommendListLimit3();
        for(IndexRecommend indexRecommend:indexRecommendList){
            ResearchReport target = researchReportDao.getResearchReport(indexRecommend.getTargetId());
            indexRecommend.setTarget(target);
        }
        return indexRecommendList;
    }

    public List<IndexRecommend> summitIndexRecommendList(){
        List<IndexRecommend> indexRecommendList = indexDao.summitIndexRecommendList();
        for(IndexRecommend indexRecommend:indexRecommendList){
            Summit target = summitDao.getSummit(indexRecommend.getTargetId());
            indexRecommend.setTarget(target);
        }
        return indexRecommendList;
    }

    public List<IndexRecommend> memberActivityIndexRecommendList(){
        List<IndexRecommend> indexRecommendList = indexDao.memberActivityIndexRecommendList();
        for(IndexRecommend indexRecommend:indexRecommendList){
            MemberActivity target = memberActivityDao.getMemberActivity(indexRecommend.getTargetId());
            indexRecommend.setTarget(target);
        }
        return indexRecommendList;
    }

    public List<IndexRecommend> memberActivityIndexRecommendListLimit4(){
        List<IndexRecommend> indexRecommendList = indexDao.memberActivityIndexRecommendListLimit4();
        for(IndexRecommend indexRecommend:indexRecommendList){
            MemberActivity target = memberActivityDao.getMemberActivity(indexRecommend.getTargetId());
            indexRecommend.setTarget(target);
        }
        return indexRecommendList;
    }

    public List<IndexRecommend> atlasIndexRecommendList(){
        List<IndexRecommend> indexRecommendList = indexDao.atlasIndexRecommendList();
        for(IndexRecommend indexRecommend:indexRecommendList){
            Atlas atlas = atlasDao.getAtlas(indexRecommend.getTargetId());
            indexRecommend.setTarget(atlas);
        }
        return indexRecommendList;
    }

    public List<IndexRecommend> atlasIndexRecommendListLimit12(){
        List<IndexRecommend> indexRecommendList = indexDao.atlasIndexRecommendListLimit12();
        for(IndexRecommend indexRecommend:indexRecommendList){
            Atlas atlas = atlasDao.getAtlas(indexRecommend.getTargetId());
            indexRecommend.setTarget(atlas);
        }
        return indexRecommendList;
    }


}
