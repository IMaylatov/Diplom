package com.IMaylatov.Recommend.Service.Model.Song;

import com.IMaylatov.Recommend.Model.Song;
import com.IMaylatov.Recommend.Service.Generic.GenericServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by Liggoriya on 24.03.2015.
 */

/**
 * Класс предоставляющий службы для работы с сущностью Song
 */
@Service("SongService")
public class SongServiceImpl extends GenericServiceImpl<Song, Long> implements SongService{
}
