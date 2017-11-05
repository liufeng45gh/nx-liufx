package com.lucifer.dao.hfc;

import com.lucifer.dao.IBatisBaseDao;
import com.lucifer.model.hfc.IndexRecommend;
import com.lucifer.model.hfc.NewsRecommend;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by fx on 2017/1/31.
 */
@Component
public class IndexDao extends IBatisBaseDao {

    //-----------------------------------------------------------------------------------------------------------------------------//
    //下面是推荐新闻
    public List<IndexRecommend> newsIndexRecommendList(){
        return this.hfcSqlSession.selectList("newsIndexRecommendList");
    }

    public List<IndexRecommend> newsIndexRecommendListLimit6(){
        return this.hfcSqlSession.selectList("newsIndexRecommendListLimit6");
    }


    public IndexRecommend getNewsIndexRecommend(Long id) {
        return this.hfcSqlSession.selectOne("getNewsIndexRecommend",id);
    }

    public Integer updateNewsIndexRecommend(IndexRecommend indexRecommend){
        //newsRecommend.setTop(0f);
        return this.hfcSqlSession.update("updateNewsIndexRecommend",indexRecommend);
    }

    public Integer addNewsIndexRecommend(IndexRecommend indexRecommend){
        indexRecommend.setTop(0f);
        return this.hfcSqlSession.insert("addNewsIndexRecommend",indexRecommend);
    }

    public Integer deleteNewsIndexRecommend(Long id){
        return this.hfcSqlSession.delete("deleteNewsIndexRecommend",id);
    }

    //-------------------------------------------------------------------------------------------------------------------------------------//
    //下面是推荐艺术家
    public List<IndexRecommend> artistIndexRecommendList(){
        return this.hfcSqlSession.selectList("artistIndexRecommendList");
    }

    public List<IndexRecommend> artistIndexRecommendListLimit16(){
        return this.hfcSqlSession.selectList("artistIndexRecommendListLimit16");
    }


    public IndexRecommend getArtistIndexRecommend(Long id) {
        return this.hfcSqlSession.selectOne("getArtistIndexRecommend",id);
    }

    public Integer updateArtistIndexRecommend(IndexRecommend indexRecommend){
        //newsRecommend.setTop(0f);
        return this.hfcSqlSession.update("updateArtistIndexRecommend",indexRecommend);
    }

    public Integer addArtistIndexRecommend(IndexRecommend indexRecommend){
        indexRecommend.setTop(0f);
        return this.hfcSqlSession.insert("addArtistIndexRecommend",indexRecommend);
    }

    public Integer deleteArtistIndexRecommend(Long id){
        return this.hfcSqlSession.delete("deleteArtistIndexRecommend",id);
    }

    //-----------------------------------------------------------------------------------------------------------------------//
    //下面是推荐文化金融
    public List<IndexRecommend> financeIndexRecommendList(){
        return this.hfcSqlSession.selectList("financeIndexRecommendList");
    }

    public List<IndexRecommend> financeIndexRecommendListLimit3(){
        return this.hfcSqlSession.selectList("financeIndexRecommendListLimit3");
    }


    public IndexRecommend getFinanceIndexRecommend(Long id) {
        return this.hfcSqlSession.selectOne("getFinanceIndexRecommend",id);
    }

    public Integer updateFinanceIndexRecommend(IndexRecommend indexRecommend){
        //newsRecommend.setTop(0f);
        return this.hfcSqlSession.update("updateFinanceIndexRecommend",indexRecommend);
    }

    public Integer addFinanceIndexRecommend(IndexRecommend indexRecommend){
        indexRecommend.setTop(0f);
        return this.hfcSqlSession.insert("addFinanceIndexRecommend",indexRecommend);
    }

    public Integer deleteFinanceIndexRecommend(Long id){
        return this.hfcSqlSession.delete("deleteFinanceIndexRecommend",id);
    }

    //----------------------------------------------------------------------------------------------------------------------//
    //下面是研究报告

    public List<IndexRecommend> researchIndexRecommendList(){
        return this.hfcSqlSession.selectList("researchIndexRecommendList");
    }

    public List<IndexRecommend> researchIndexRecommendListLimit3(){
        return this.hfcSqlSession.selectList("researchIndexRecommendListLimit3");
    }


    public IndexRecommend getResearchIndexRecommend(Long id) {
        return this.hfcSqlSession.selectOne("getResearchIndexRecommend",id);
    }

    public Integer updateResearchIndexRecommend(IndexRecommend indexRecommend){
        //newsRecommend.setTop(0f);
        return this.hfcSqlSession.update("updateResearchIndexRecommend",indexRecommend);
    }

    public Integer addResearchIndexRecommend(IndexRecommend indexRecommend){
        indexRecommend.setTop(0f);
        return this.hfcSqlSession.insert("addResearchIndexRecommend",indexRecommend);
    }

    public Integer deleteResearchIndexRecommend(Long id){
        return this.hfcSqlSession.delete("deleteResearchIndexRecommend",id);
    }

    //---------------------------------------------------------------------------------------------------------------------//
    //下面是峰会
    public List<IndexRecommend> summitIndexRecommendList(){
        return this.hfcSqlSession.selectList("summitIndexRecommendList");
    }
    public IndexRecommend getSummitIndexRecommend(Long id) {
        return this.hfcSqlSession.selectOne("getSummitIndexRecommend",id);
    }

    public Integer updateSummitIndexRecommend(IndexRecommend indexRecommend){
        //newsRecommend.setTop(0f);
        return this.hfcSqlSession.update("updateSummitIndexRecommend",indexRecommend);
    }

    public Integer addSummitIndexRecommend(IndexRecommend indexRecommend){
        indexRecommend.setTop(0f);
        return this.hfcSqlSession.insert("addSummitIndexRecommend",indexRecommend);
    }

    public Integer deleteSummitIndexRecommend(Long id){
        return this.hfcSqlSession.delete("deleteSummitIndexRecommend",id);
    }

    //---------------------------------------------------------------------------------------------------------------------------------//
    //下面是会员活动

    public List<IndexRecommend> memberActivityIndexRecommendList(){
        return this.hfcSqlSession.selectList("memberActivityIndexRecommendList");
    }

    public List<IndexRecommend> memberActivityIndexRecommendListLimit4(){
        return this.hfcSqlSession.selectList("memberActivityIndexRecommendListLimit4");
    }
    public IndexRecommend getMemberActivityIndexRecommend(Long id) {
        return this.hfcSqlSession.selectOne("getMemberActivityIndexRecommend",id);
    }

    public Integer updateMemberActivityIndexRecommend(IndexRecommend indexRecommend){
        //newsRecommend.setTop(0f);
        return this.hfcSqlSession.update("updateMemberActivityIndexRecommend",indexRecommend);
    }

    public Integer addMemberActivityIndexRecommend(IndexRecommend indexRecommend){
        indexRecommend.setTop(0f);
        return this.hfcSqlSession.insert("addMemberActivityIndexRecommend",indexRecommend);
    }

    public Integer deleteMemberActivityIndexRecommend(Long id){
        return this.hfcSqlSession.delete("deleteMemberActivityIndexRecommend",id);
    }

    //--------海富映像部分--------
    public List<IndexRecommend> atlasIndexRecommendList(){
        return this.hfcSqlSession.selectList("atlasIndexRecommendList");
    }

    public List<IndexRecommend> atlasIndexRecommendListLimit12(){
        return this.hfcSqlSession.selectList("atlasIndexRecommendListLimit12");
    }

    public Integer addAtlasIndexRecommend(IndexRecommend indexRecommend){
        indexRecommend.setTop(0f);
        return this.hfcSqlSession.insert("addAtlasIndexRecommend",indexRecommend);
    }

    public IndexRecommend getAtlasIndexRecommend(Long id) {
        return this.hfcSqlSession.selectOne("getAtlasIndexRecommend",id);
    }

    public Integer updateAtlasIndexRecommend(IndexRecommend indexRecommend){
        //newsRecommend.setTop(0f);
        return this.hfcSqlSession.update("updateAtlasIndexRecommend",indexRecommend);
    }

    public Integer deleteAtlasIndexRecommend(Long id){
        return this.hfcSqlSession.delete("deleteAtlasIndexRecommend",id);
    }

}
