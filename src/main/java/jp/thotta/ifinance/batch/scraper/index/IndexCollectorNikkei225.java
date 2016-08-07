package jp.thotta.ifinance.batch.scraper.index;

import jp.thotta.ifinance.batch.scraper.BaseIndexCollector;
import jp.thotta.ifinance.batch.scraper.IndexCollector;
import jp.thotta.ifinance.batch.util.HttpGet;
import org.jsoup.nodes.Document;

/**
 * Created by thotta on 2016/08/04.
 */
public class IndexCollectorNikkei225 extends BaseIndexCollector {
    public static String INDEX_NAME = "Nikkei225";
    static String classSourceUrl = "http://stocks.finance.yahoo.co.jp/stocks/detail/?code=998407.O";
    static String classSelector = "td.stoksPrice:nth-child(3)";

    public IndexCollectorNikkei225() {
        super(classSourceUrl, classSelector);
    }
}
