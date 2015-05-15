package com.IMaylatov.Recommend.webapp.Service.AuthorSong;

import com.IMaylatov.Recommend.webapp.DAO.Model.AuthorSong.AuthorSongDao;
import com.IMaylatov.Recommend.webapp.Model.AuthorSongGenre.AuthorSong;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 14.05.2015.
 */
@Service("AuthorSongService")
public class AuthorSongServiceImpl implements AuthorSongService {
    @Autowired
    private AuthorSongDao authorSongDao;

    @Override
    public List<AuthorSong> getAuthorByName(String authorName) {
        return authorSongDao.list(Restrictions.sqlRestriction(
                String.format("name like '%s%%'", authorName)));
    }
}
