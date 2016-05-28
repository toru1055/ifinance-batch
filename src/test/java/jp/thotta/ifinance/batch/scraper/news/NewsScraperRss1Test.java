package jp.thotta.ifinance.batch.scraper.news;

import jp.thotta.ifinance.batch.scraper.NewsScraper;
import jp.thotta.ifinance.common.entity.News;
import junit.framework.TestCase;

import java.util.List;

/**
 * Created by thotta on 2016/05/17.
 */
public class NewsScraperRss1Test extends TestCase {
    public void testGetNewsList() {
        String url = "http://www.re-port.net/rss/news.php";
        //String url = "http://rss.rssad.jp/rss/shukanjutaku/np/news.rdf";
        NewsScraper ns = new NewsScraperRss1();
        List<News> newsList = ns.getNewsList(url);
        assertTrue(newsList.size() > 0);
    }
}