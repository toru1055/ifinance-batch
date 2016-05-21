package jp.thotta.ifinance.batch.job;

import jp.thotta.ifinance.batch.scraper.NewsScraper;
import jp.thotta.ifinance.batch.scraper.NewsScraperFactory;
import jp.thotta.ifinance.common.dao.CommonEntityManager;
import jp.thotta.ifinance.common.dao.NewsManager;
import jp.thotta.ifinance.common.dao.SubscriptionManager;
import jp.thotta.ifinance.common.entity.News;
import jp.thotta.ifinance.common.entity.Subscription;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionReader {
    SubscriptionManager subscriptionManager;
    NewsManager newsManager;

    public SubscriptionReader() {
        subscriptionManager = new SubscriptionManager();
        newsManager = new NewsManager();
    }

    public void execOnce() {
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

    Collection<News> deduplicateByTitle(List<News> newsList) {
        Map<String, News> newsMap = new HashMap<String, News>();
        for(News news : newsList) {
            newsMap.put(news.getTitle(), news);
        }
        return newsMap.values();
    }

    public void execDaemon() {
        int count = 1;
        while (true) {
            System.out.println("[" + (count++) + "] Execute " + this.getClass().getSimpleName());
            execOnce();
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
            new SubscriptionReader().execOnce();
        } else if ("daemon".equals(mode)) {
            System.out.println("Daemon mode was started.");
            new SubscriptionReader().execDaemon();
        } else {
            System.out.println("Syntax Error. Args = batch|daemon");
        }
        CommonEntityManager.closeFactory();
    }

}
