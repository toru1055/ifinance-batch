package jp.thotta.ifinance.batch.scraper.news;

import jp.thotta.ifinance.batch.scraper.NewsScraper;
import jp.thotta.ifinance.common.entity.News;
import junit.framework.TestCase;

import java.util.List;

/**
 * Created by thotta on 2016/05/18.
 */
public class NewsScraperAtomTest extends TestCase {
    public void testAtom() {
        String url = "http://www.iecoco-sumaino-smartnet.com/files/blog/rss/RSS_BLOG_100.rdf";
        NewsScraper ns = new NewsScraperAtom();
        List<News> newsList = ns.getNewsList(url);
        assertTrue(newsList.size() > 0);
    }
}