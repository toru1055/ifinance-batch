package jp.thotta.ifinance.batch.scraper.index;

import jp.thotta.ifinance.batch.scraper.BaseIndexCollector;

/**
 * Created by thotta on 2016/08/08.
 */
public class IndexCollectorUSDJPY extends BaseIndexCollector {
    public static String INDEX_NAME = "USDJPY";
    static String classSourceUrl = "http://stocks.finance.yahoo.co.jp/stocks/detail/?code=USDJPY=X";
    static String classSelector = "td.stoksPrice";

    public IndexCollectorUSDJPY() {
        super(classSourceUrl, classSelector);
    }
}
