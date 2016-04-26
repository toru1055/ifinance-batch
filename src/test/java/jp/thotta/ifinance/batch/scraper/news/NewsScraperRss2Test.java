package jp.thotta.ifinance.batch.scraper.news;

import java.util.List;
import junit.framework.TestCase;

import jp.thotta.ifinance.common.entity.News;
import jp.thotta.ifinance.batch.scraper.NewsScraper;

public class NewsScraperRss2Test extends TestCase {
  public void testRss2() {
    String url = "http://rss.rssad.jp/rss/markezine/new/20/index.xml";
    //String url = "https://www.facebook.com/business/news/rss/";
    NewsScraper ns = new NewsScraperRss2();
    List<News> newsList = ns.getNewsList(url);
    assertTrue(newsList.size() > 0);
  }
}
