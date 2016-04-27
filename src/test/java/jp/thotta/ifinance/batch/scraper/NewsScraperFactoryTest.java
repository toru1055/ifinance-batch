package jp.thotta.ifinance.batch.scraper;

import java.util.List;
import junit.framework.TestCase;

import jp.thotta.ifinance.common.entity.*;
import jp.thotta.ifinance.common.dao.*;
import jp.thotta.ifinance.batch.scraper.news.*;

public class NewsScraperFactoryTest extends TestCase {
  ScraperManager scraperManager = new ScraperManager();

  public void testBasicUsage() {
    assertTrue(NewsScraperFactory.initDatabase());
    String url = "https://www.facebook.com/business/news/rss/";
    NewsScraper ns = NewsScraperFactory.create("Rss2");
    assertTrue(ns != null);
    List<News> newsList = ns.getNewsList(url);
    assertTrue(newsList.size() > 0);
  }

  @Override
  protected void tearDown() {
    CommonEntityManager.closeFactory();
  }
}
