package jp.thotta.ifinance.batch.scraper;

import jp.thotta.ifinance.batch.util.HttpGet;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

/**
 * Created by thotta on 2016/08/04.
 */
public class BaseIndexCollector implements IndexCollector {
    String sourceUrl;
    String selector;

    public BaseIndexCollector(String sourceUrl, String selector) {
        this.sourceUrl = sourceUrl;
        this.selector = selector;
    }

    public Double getCurrentValue() {
        Double val = null;
        Document doc = new HttpGet().get(sourceUrl);
        if (doc != null) {
            String valString = doc.select(selector).text();
            if (!valString.equals("---")) {
                val = Double.parseDouble(valString.replace(",", ""));
            }
        }
        return val;
    }
}
