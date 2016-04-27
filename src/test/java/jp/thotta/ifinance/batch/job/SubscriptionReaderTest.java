package jp.thotta.ifinance.batch.job;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import jp.thotta.ifinance.common.entity.*;
import jp.thotta.ifinance.common.dao.*;
import jp.thotta.ifinance.batch.scraper.news.*;
import jp.thotta.ifinance.batch.scraper.NewsScraper;
import jp.thotta.ifinance.batch.scraper.NewsScraperFactory;

import junit.framework.TestCase;

public class SubscriptionReaderTest extends TestCase {
  SubscriptionManager subscriptionManager = new SubscriptionManager();
  IndustryManager industryManager = new IndustryManager();
  NewsManager newsManager = new NewsManager();
  ScraperManager scraperManager = new ScraperManager();
  SubscriptionReader sr = new SubscriptionReader();

  @Override
  protected void setUp() {
    NewsScraperFactory.initDatabase();
    Industry industry = new Industry("ネット広告");
    industryManager.add(industry);
    Subscription s = new Subscription();
    s.setName("Markezine");
    s.setUrl("http://rss.rssad.jp/rss/markezine/new/20/index.xml");
    s.setPrivateFlag(false);
    s.setScraper(scraperManager.findByName("Rss2"));
    s.setFixedIndustry(industry);
    subscriptionManager.add(s);
  }

  public void testBasic() {
    sr.execOnce();
    News news= newsManager.find(1L);
    assertTrue(news != null);
    assertTrue(news.getTitle().length() > 0);
  }

  @Override
  protected void tearDown() {
    CommonEntityManager.closeFactory();
  }
}
