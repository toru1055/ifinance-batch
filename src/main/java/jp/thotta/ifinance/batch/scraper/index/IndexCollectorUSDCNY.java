package jp.thotta.ifinance.batch.scraper.index;

import jp.thotta.ifinance.batch.scraper.BaseIndexCollector;

/**
 * Created by thotta on 2016/08/08.
 */
public class IndexCollectorUSDCNY extends BaseIndexCollector {
    public static String INDEX_NAME = "USDCNY";
    static String classSourceUrl = "http://stocks.finance.yahoo.co.jp/stocks/detail/?code=USDCNY=X";
    static String classSelector = "td.stoksPrice";

    public IndexCollectorUSDCNY() {
        super(classSourceUrl, classSelector);
    }
}
