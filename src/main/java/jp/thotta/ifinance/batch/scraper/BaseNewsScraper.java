package jp.thotta.ifinance.batch.scraper;

import java.util.List;
import org.jsoup.nodes.Document;

import jp.thotta.ifinance.common.entity.News;
import jp.thotta.ifinance.batch.util.XmlGet;

public abstract class BaseNewsScraper implements NewsScraper {
  public List<News> getNewsList(String url) {
    Document doc = new XmlGet().get(url);
    return getNewsList(doc);
  }

  abstract protected List<News> getNewsList(Document doc);
}
