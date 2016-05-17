package jp.thotta.ifinance.batch.scraper;

import jp.thotta.ifinance.common.dao.CommonEntityManager;
import jp.thotta.ifinance.common.dao.ScraperManager;
import jp.thotta.ifinance.common.entity.News;
import junit.framework.TestCase;

import java.util.List;

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
