package com.IMaylatov.Recommend.webapp.DAO.Model.Song.BlackList;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDaoImpl;
import com.IMaylatov.Recommend.webapp.Model.Song.BlackList;
import org.springframework.stereotype.Repository;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 17.05.2015.
 */
@Repository("BlackListDao")
public class BlackListDaoImpl extends GenericDaoImpl<BlackList, BlackList.PairKey> implements BlackListDao{
}
