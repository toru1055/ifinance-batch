package jp.thotta.ifinance.batch.job;

import jp.thotta.ifinance.batch.scraper.IndexCollectorFactory;
import jp.thotta.ifinance.batch.scraper.NewsScraperFactory;
import jp.thotta.ifinance.common.dao.CommonEntityManager;

/**
 * Created by thotta on 2016/07/25.
 */
public class Initializer {
    public static void main(String[] args) {
        NewsScraperFactory.initDatabase();
        IndexCollectorFactory.initDatabase();
        CommonEntityManager.closeFactory();
    }
}
