package jp.thotta.ifinance.batch.scraper;

import jp.thotta.ifinance.common.dao.CommonEntityManager;
import jp.thotta.ifinance.common.dao.MasterDataManager;
import jp.thotta.ifinance.common.dao.ScraperManager;
import jp.thotta.ifinance.common.entity.News;
import jp.thotta.ifinance.common.entity.Scraper;
import junit.framework.TestCase;

import java.util.List;

public class NewsScraperFactoryTest extends TestCase {
    MasterDataManager<Scraper> scraperManager =
            new MasterDataManager<Scraper>(Scraper.class);

    public void testBasicUsage() {
        assertTrue(NewsScraperFactory.initDatabase());
        String url = "http://suumo.jp/journal/?feed=rss22";
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
