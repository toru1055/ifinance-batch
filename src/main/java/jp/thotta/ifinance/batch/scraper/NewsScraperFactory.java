package jp.thotta.ifinance.batch.scraper;

import jp.thotta.ifinance.batch.scraper.news.NewsScraperAtom;
import jp.thotta.ifinance.batch.scraper.news.NewsScraperRss1;
import jp.thotta.ifinance.batch.scraper.news.NewsScraperRss2;
import jp.thotta.ifinance.common.dao.MasterDataManager;
import jp.thotta.ifinance.common.entity.Scraper;
import jp.thotta.ifinance.common.entity.Subscription;

import java.util.HashMap;
import java.util.Map;

/*
 * Initialize部分をjobに移す
 * SubscriptionReaderも名前を変えて、index_collectorも動かす
 * パッケージ構成をもうちょっと考え直したい
 * scraper/
 *      - IndexCollector(interface)
 *      - IndexCollectorFactory
 *      - IndexCollectorBase
 *      - index/
 *          - NikkeiAverageCollector
 */
public class NewsScraperFactory {
    static MasterDataManager<Scraper> scraperManager = new MasterDataManager<Scraper>(Scraper.class);
    static Map<String, NewsScraper> scraperMap = makeScraperMap();

    public static NewsScraper create(String scraperName) {
        return scraperMap.get(scraperName);
    }

    public static NewsScraper create(Integer scraperId) {
        Scraper scraper = scraperManager.find(scraperId);
        if (scraper != null) {
            return scraperMap.get(scraper.getName());
        }
        return null;
    }

    public static NewsScraper create(Subscription subscription) {
        if (subscription != null && subscription.getScraper() != null) {
            return scraperMap.get(subscription.getScraper().getName());
        }
        return null;
    }

    public static boolean initDatabase() {
        for (String scraperName : scraperMap.keySet()) {
            Scraper scraper = new Scraper(scraperName);
            scraperManager.add(scraper);
        }
        return true;
    }

    static Map<String, NewsScraper> makeScraperMap() {
        Map<String, NewsScraper> m = new HashMap<String, NewsScraper>();
        m.put("Rss2", new NewsScraperRss2());
        m.put("Rss1", new NewsScraperRss1());
        m.put("Atom", new NewsScraperAtom());
        return m;
    }
}
