package com.lucifer.service.hfc;

import com.lucifer.dao.hfc.ArtistDao;
import com.lucifer.model.hfc.Artist;
import com.lucifer.model.hfc.ArtistRecommend;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liufx on 17/1/16.
 */
@Component
public class ArtistService {

    @Resource
    private ArtistDao artistDao;

    @Resource
    private PinYinService pinYinService;

    public void saveArtist(Artist artist) throws BadHanyuPinyinOutputFormatCombination {
        String namePy = pinYinService.toHanYuPinYin(artist.getName());
        artist.setNamePy(namePy);
        artistDao.insertArtist(artist);
    }

    public void updateArtist(Artist artist) throws BadHanyuPinyinOutputFormatCombination {
        String namePy = pinYinService.toHanYuPinYin(artist.getName());
        artist.setNamePy(namePy);
        artistDao.updateArtist(artist);
    }

    public List<ArtistRecommend> artistRecommendList(){
        List<ArtistRecommend> artistRecommendList = artistDao.artistRecommendList();
        for (ArtistRecommend artistRecommend:artistRecommendList) {
            Artist artist = artistDao.getArtist(artistRecommend.getArtistId());
            artistRecommend.setArtist(artist);
        }
        return artistRecommendList;
    }
}
