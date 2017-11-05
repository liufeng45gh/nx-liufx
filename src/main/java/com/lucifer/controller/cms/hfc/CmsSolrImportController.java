package com.lucifer.controller.cms.hfc;

import com.lucifer.dao.hfc.NewsDao;
import com.lucifer.model.hfc.News;
import com.lucifer.service.hfc.AppreciateSearchService;
import com.lucifer.service.hfc.ArtistSearchService;
import com.lucifer.service.hfc.NewsSearchService;
import com.lucifer.utils.Constant;
import com.lucifer.utils.PageInfoWriter;
import com.lucifer.utils.PageUtil;
import com.lucifer.utils.Result;
import org.apache.solr.client.solrj.SolrServerException;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */

//@Controller
//@RequestMapping("/cms")
public class CmsSolrImportController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private NewsSearchService newsSearchService;

    @Resource
    private ArtistSearchService artistSearchService;

    @Resource
    private AppreciateSearchService appreciateSearchService;



    @RequestMapping(value="/news/search/re-import",method = RequestMethod.POST)
    @ResponseBody
    public Result newsReImport() throws IOException, SolrServerException {
        logger.info("CmsSearchController newsReImport has been called");
        newsSearchService.reImport();
        return Result.ok();
    }

    @RequestMapping(value="/artist/search/re-import",method = RequestMethod.POST)
    @ResponseBody
    public Result artistReImport() throws IOException, SolrServerException {
        logger.info("CmsSearchController artistReImport has been called");
        artistSearchService.reImport();
        return Result.ok();
    }

    @RequestMapping(value="/appreciate/search/re-import",method = RequestMethod.POST)
    @ResponseBody
    public Result appreciateReImport() throws IOException, SolrServerException {
        logger.info("CmsSearchController appreciateReImport has been called");
        appreciateSearchService.reImport();
        return Result.ok();
    }


}
