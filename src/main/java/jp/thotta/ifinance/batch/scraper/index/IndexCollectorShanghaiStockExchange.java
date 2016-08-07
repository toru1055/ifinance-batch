package jp.thotta.ifinance.batch.scraper.index;

import jp.thotta.ifinance.batch.scraper.BaseIndexCollector;

/**
 * Created by thotta on 2016/08/08.
 */
public class IndexCollectorShanghaiStockExchange extends BaseIndexCollector {
    public static String INDEX_NAME = "ShanghaiStockExchange";
    static String classSourceUrl = "http://stocks.finance.yahoo.co.jp/stocks/detail/?code=000001.SS";
    static String classSelector = "td.stoksPrice:nth-child(3)";

    public IndexCollectorShanghaiStockExchange() {
        super(classSourceUrl, classSelector);
    }
}
