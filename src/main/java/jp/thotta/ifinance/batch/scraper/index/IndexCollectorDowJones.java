package jp.thotta.ifinance.batch.scraper.index;

import jp.thotta.ifinance.batch.scraper.BaseIndexCollector;

/**
 * Created by thotta on 2016/08/06.
 */
public class IndexCollectorDowJones extends BaseIndexCollector {
    public static String INDEX_NAME = "DowJones";
    static String classSourceUrl = "http://stocks.finance.yahoo.co.jp/stocks/detail/?code=^DJI";
    static String classSelector = "td.stoksPrice:nth-child(3)";

    public IndexCollectorDowJones() {
        super(classSourceUrl, classSelector);
    }
}
