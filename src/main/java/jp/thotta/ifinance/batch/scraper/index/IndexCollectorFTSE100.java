package jp.thotta.ifinance.batch.scraper.index;

import jp.thotta.ifinance.batch.scraper.BaseIndexCollector;

/**
 * Created by thotta on 2016/08/08.
 */
public class IndexCollectorFTSE100 extends BaseIndexCollector {
    public static String INDEX_NAME = "FTSE100";
    static String classSourceUrl = "http://stocks.finance.yahoo.co.jp/stocks/detail/?code=^FTSE";
    static String classSelector = "td.stoksPrice:nth-child(3)";

    public IndexCollectorFTSE100() {
        super(classSourceUrl, classSelector);
    }
}
