package jp.thotta.ifinance.batch.scraper.index;

import jp.thotta.ifinance.batch.scraper.BaseIndexCollector;

/**
 * Created by thotta on 2016/08/08.
 */
public class IndexCollectorEURUSD extends BaseIndexCollector {
    public static String INDEX_NAME = "EURUSD";
    static String classSourceUrl = "http://stocks.finance.yahoo.co.jp/stocks/detail/?code=EURUSD=X";
    static String classSelector = "td.stoksPrice";

    public IndexCollectorEURUSD() {
        super(classSourceUrl, classSelector);
    }
}
