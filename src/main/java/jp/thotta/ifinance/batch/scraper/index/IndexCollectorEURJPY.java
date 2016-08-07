package jp.thotta.ifinance.batch.scraper.index;

import jp.thotta.ifinance.batch.scraper.BaseIndexCollector;

/**
 * Created by thotta on 2016/08/08.
 */
public class IndexCollectorEURJPY extends BaseIndexCollector {
    public static String INDEX_NAME = "EURJPY";
    static String classSourceUrl = "http://stocks.finance.yahoo.co.jp/stocks/detail/?code=EURJPY=X";
    static String classSelector = "td.stoksPrice";

    public IndexCollectorEURJPY() {
        super(classSourceUrl, classSelector);
    }
}
