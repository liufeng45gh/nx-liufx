package com.lucifer.dao.hfc;

import com.lucifer.dao.IBatisBaseDao;
import com.lucifer.model.hfc.Carousel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by fx on 2017/1/31.
 */
@Component
public class CarouseDao extends IBatisBaseDao{

    public List<Carousel> carouselList(){
        return this.hfcSqlSession.selectList("carouselList");
    }

    public Integer insertCarousel(Carousel carousel){
        return this.hfcSqlSession.insert("addCarousel",carousel);
    }

    public Integer deleteCarousel(Long id){
        return this.hfcSqlSession.delete("deleteCarousel",id);
    }

    public Carousel getCarousel(Long id){
        return this.hfcSqlSession.selectOne("getCarousel",id);
    }

    public Integer updateCarousel(Carousel carousel){
        return this.hfcSqlSession.update("updateCarousel",carousel);
    }


    public List<Carousel> newsCarouselList(){
        return this.hfcSqlSession.selectList("newsCarouselList");
    }

    public Integer insertNewsCarousel(Carousel carousel){
        return this.hfcSqlSession.insert("addNewsCarousel",carousel);
    }

    public Integer deleteNewsCarousel(Long id){
        return this.hfcSqlSession.delete("deleteNewsCarousel",id);
    }

    public Carousel getNewsCarousel(Long id){
        return this.hfcSqlSession.selectOne("getNewsCarousel",id);
    }

    public Integer updateNewsCarousel(Carousel carousel){
        return this.hfcSqlSession.update("updateNewsCarousel",carousel);
    }

    public Carousel firstNewsCarousel(){
        return this.hfcSqlSession.selectOne("firstNewsCarousel");
    }



}
