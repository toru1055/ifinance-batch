package jp.thotta.ifinance.batch.util;

import junit.framework.TestCase;
import org.jsoup.nodes.Document;

public class HttpGetTest extends TestCase {
    public void testGet() {
        String ok_url = "http://www.yahoo.co.jp/";
        String ng_url = "http://www.ngngng.co.jp/";
        Document ok = new HttpGet().get(ok_url);
        Document ng = new HttpGet().get(ng_url);
        assertEquals(ok.title(), "Yahoo! JAPAN");
        assertEquals(ng, null);
    }
}
