package jp.thotta.ifinance.batch.scraper.news;

import jp.thotta.ifinance.batch.scraper.NewsScraper;
import jp.thotta.ifinance.common.entity.News;
import junit.framework.TestCase;

import java.util.List;

public class NewsScraperRss2Test extends TestCase {
    public void testRss2() {
        String url = "http://rss.rssad.jp/rss/markezine/new/20/index.xml";
        //String url = "https://www.facebook.com/business/news/rss/";
        NewsScraper ns = new NewsScraperRss2();
        List<News> newsList = ns.getNewsList(url);
        assertTrue(newsList.size() > 0);
    }
}
