package jp.thotta.ifinance.batch.scraper;

import java.util.List;

import jp.thotta.ifinance.common.entity.News;

public interface NewsScraper {
  public List<News> getNewsList(String url);
}
