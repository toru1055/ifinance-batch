package jp.thotta.ifinance.batch.scraper;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import jp.thotta.ifinance.common.entity.*;
import jp.thotta.ifinance.common.dao.*;
import jp.thotta.ifinance.batch.scraper.news.*;

public class NewsScraperFactory {
  static ScraperManager scraperManager = new ScraperManager();
  static Map<String, NewsScraper> scraperMap = makeScraperMap();

  public static NewsScraper create(String scraperName) {
    return scraperMap.get(scraperName);
  }

  public static NewsScraper create(Integer scraperId) {
    Scraper scraper = scraperManager.find(scraperId);
    if(scraper != null) {
      return scraperMap.get(scraper.getName());
    }
    return null;
  }

  public static NewsScraper create(Subscription subscription) {
    if(subscription != null && subscription.getScraper() != null) {
      return scraperMap.get(subscription.getScraper().getName());
    }
    return null;
  }

  public static boolean initDatabase() {
    List<Scraper> scrapers = new ArrayList<Scraper>();
    for(String scraperName : scraperMap.keySet()) {
      Scraper scraper = new Scraper(scraperName);
      scrapers.add(scraper);
    }
    return scraperManager._import(scrapers);
  }

  static Map<String, NewsScraper> makeScraperMap() {
    Map<String, NewsScraper> m = new HashMap<String, NewsScraper>();
    m.put("Rss2", new NewsScraperRss2());
    return m;
  }

  public static void main(String[] args) {
    initDatabase();
  }
}
