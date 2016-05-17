package jp.thotta.ifinance.batch.scraper;

import jp.thotta.ifinance.common.entity.News;

import java.util.List;

public interface NewsScraper {
    public List<News> getNewsList(String url);
}
