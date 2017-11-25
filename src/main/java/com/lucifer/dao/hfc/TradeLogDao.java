package com.lucifer.dao.hfc;

import com.lucifer.dao.IBatisBaseDao;
import com.lucifer.model.hfc.TradeLog;

/**
 * Created by Administrator on 2017/11/25.
 */
public class TradeLogDao extends IBatisBaseDao {

    public Integer insert(TradeLog tradeLog){
        return this.hfcSqlSession.insert("insertTradeLog",tradeLog);
    }
}
