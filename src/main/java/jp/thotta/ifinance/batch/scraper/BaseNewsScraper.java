package jp.thotta.ifinance.batch.scraper;

import jp.thotta.ifinance.batch.util.XmlGet;
import jp.thotta.ifinance.common.entity.News;
import org.jsoup.nodes.Document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class BaseNewsScraper implements NewsScraper {
    public List<News> getNewsList(String url) {
        Document doc = new XmlGet().get(url);
        return getNewsList(doc);
    }

    protected Date parsePubDate(String pubdate) {
        SimpleDateFormat f = this.getDateFormat();
        try {
            Date d = f.parse(pubdate);
            return d;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    abstract protected List<News> getNewsList(Document doc);

    abstract protected SimpleDateFormat getDateFormat();
}
