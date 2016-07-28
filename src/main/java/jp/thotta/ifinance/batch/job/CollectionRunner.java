package jp.thotta.ifinance.batch.job;

import jp.thotta.ifinance.batch.scraper.IndexCollector;
import jp.thotta.ifinance.batch.scraper.IndexCollectorFactory;
import jp.thotta.ifinance.batch.scraper.NewsScraper;
import jp.thotta.ifinance.batch.scraper.NewsScraperFactory;
import jp.thotta.ifinance.common.dao.*;
import jp.thotta.ifinance.common.entity.DailyMarketIndex;
import jp.thotta.ifinance.common.entity.MarketIndexMaster;
import jp.thotta.ifinance.common.entity.News;
import jp.thotta.ifinance.common.entity.Subscription;

import java.util.*;

public class CollectionRunner {
    SubscriptionManager subscriptionManager;
    NewsManager newsManager;
    MarketIndexMasterManager miMasterManager;
    DailyMarketIndexManager dailyMarketIndexManager;

    public CollectionRunner() {
        subscriptionManager = new SubscriptionManager();
        newsManager = new NewsManager();
        miMasterManager = new MarketIndexMasterManager();
        dailyMarketIndexManager = new DailyMarketIndexManager();
    }

    public void executeNewsSubscription() {
        List<Subscription> subscriptions = subscriptionManager.selectAll();
        for (Subscription subscription : subscriptions) {
            if (subscription.isReadable() && subscription.isActive()) {
                System.out.println(
                        "Start reading subscription: " + subscription.getName());
                NewsScraper ns = NewsScraperFactory.create(subscription);
                try {
                    Collection<News> newsList = deduplicateByTitle(
                            ns.getNewsList(subscription.getUrl())
                    );
                    subscription.setLastReadDate();
                    subscriptionManager.update(subscription);
                    for (News news : newsList) {
                        news.setSubscription(subscription);
                        if (subscription.getFixedIndustry() != null) {
                            news.addIndustry(subscription.getFixedIndustry());
                        }
                        newsManager.add(news);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Skip for interval: " + subscription.getName());
            }
        }
    }

    public void executeMarketIndexCollection() {
        List<MarketIndexMaster> miMasterList = miMasterManager.selectAll();
        for (MarketIndexMaster miMaster : miMasterList) {
            if (miMaster.isReadable() && miMaster.isActive()) {
                System.out.println(
                        "Start reading market index: " + miMaster.getName()
                );
                IndexCollector collector = IndexCollectorFactory.create(miMaster);
                Double val = collector.getCurrentValue();
                if (val != null) {
                    DailyMarketIndex dailyMarketIndex = dailyMarketIndexManager.findToday(miMaster);
                    if (dailyMarketIndex == null) {
                        dailyMarketIndex = new DailyMarketIndex(miMaster, new Date());
                        dailyMarketIndex.createPrice(val);
                        dailyMarketIndexManager.add(dailyMarketIndex);
                    } else {
                        dailyMarketIndex.updatePrice(val);
                        dailyMarketIndexManager.update(dailyMarketIndex);
                    }
                }
            } else {
                System.out.println("Skip for interval: " + miMaster.getName());
            }
        }
    }

    Collection<News> deduplicateByTitle(List<News> newsList) {
        Map<String, News> newsMap = new HashMap<String, News>();
        for (News news : newsList) {
            newsMap.put(news.getTitle(), news);
        }
        return newsMap.values();
    }

    public void execDaemon() {
        int count = 1;
        while (true) {
            System.out.println("[" + (count++) + "] Execute " + this.getClass().getSimpleName());
            executeNewsSubscription();
            executeMarketIndexCollection();
            System.out.println("End execution.");
            System.out.println();
            try {
                Thread.sleep(10000);
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) {
        String mode = "batch";
        if (args.length > 0) {
            mode = args[0];
        }
        if ("batch".equals(mode)) {
            System.out.println("Batch mode was started.");
            CollectionRunner runner = new CollectionRunner();
            runner.executeNewsSubscription();
            runner.executeMarketIndexCollection();
        } else if ("daemon".equals(mode)) {
            System.out.println("Daemon mode was started.");
            new CollectionRunner().execDaemon();
        } else {
            System.out.println("Syntax Error. Args = batch|daemon");
        }
        CommonEntityManager.closeFactory();
    }


}
