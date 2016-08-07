package jp.thotta.ifinance.batch.scraper;

import jp.thotta.ifinance.batch.scraper.index.*;
import jp.thotta.ifinance.common.dao.MasterDataManager;
import jp.thotta.ifinance.common.entity.MarketIndexCollector;
import jp.thotta.ifinance.common.entity.MarketIndexMaster;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thotta on 2016/07/27.
 */
public class IndexCollectorFactory {
    static MasterDataManager<MarketIndexCollector> miCollectorManager
            = new MasterDataManager<MarketIndexCollector>(MarketIndexCollector.class);
    static Map<String, IndexCollector> collectorMap = makeIndexCollectorMap();

    public static IndexCollector create(String collectorName) {
        return collectorMap.get(collectorName);
    }

    public static IndexCollector create(MarketIndexMaster miMaster) {
        if (miMaster != null && miMaster.getMarketIndexCollector() != null) {
            return collectorMap.get(miMaster.getMarketIndexCollector().getName());
        }
        return null;
    }

    public static boolean initDatabase() {
        for (String collectorName : collectorMap.keySet()) {
            MarketIndexCollector miCollector = new MarketIndexCollector(collectorName);
            miCollectorManager.add(miCollector);
        }
        return true;
    }

    static Map<String, IndexCollector> makeIndexCollectorMap() {
        Map<String, IndexCollector> m = new HashMap<String, IndexCollector>();
        m.put(IndexCollectorNikkei225.INDEX_NAME, new IndexCollectorNikkei225());
        m.put(IndexCollectorDowJones.INDEX_NAME, new IndexCollectorDowJones());
        m.put(IndexCollectorShanghaiStockExchange.INDEX_NAME, new IndexCollectorShanghaiStockExchange());
        m.put(IndexCollectorUSDJPY.INDEX_NAME, new IndexCollectorUSDJPY());
        m.put(IndexCollectorUSDCNY.INDEX_NAME, new IndexCollectorUSDCNY());
        m.put(IndexCollectorEURJPY.INDEX_NAME, new IndexCollectorEURJPY());
        m.put(IndexCollectorEURUSD.INDEX_NAME, new IndexCollectorEURUSD());
        m.put(IndexCollectorFTSE100.INDEX_NAME, new IndexCollectorFTSE100());
        return m;
    }
}
