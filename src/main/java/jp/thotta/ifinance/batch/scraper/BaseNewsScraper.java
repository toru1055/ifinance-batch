package jp.thotta.ifinance.batch.scraper;

import jp.thotta.ifinance.batch.util.XmlGet;
import jp.thotta.ifinance.common.entity.News;
import org.jsoup.nodes.Document;

import java.util.List;

public abstract class BaseNewsScraper implements NewsScraper {
    public List<News> getNewsList(String url) {
        Document doc = new XmlGet().get(url);
        return getNewsList(doc);
    }

    abstract protected List<News> getNewsList(Document doc);
}
