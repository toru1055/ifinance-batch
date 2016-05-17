package jp.thotta.ifinance.batch.scraper.news;

import jp.thotta.ifinance.batch.scraper.BaseNewsScraper;
import jp.thotta.ifinance.common.entity.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by thotta on 2016/05/18.
 */
public class NewsScraperAtom extends BaseNewsScraper {
    protected List<News> getNewsList(Document doc) {
        List<News> newsList = new ArrayList<News>();
        Elements items = doc.select("entry");
        for (Element item : items) {
            String title = item.select("title").text();
            String description = Jsoup.parse(item.select("summary").text()).text();
            String link = item.select("link").first().attr("abs:href");
            String pubDate = item.select("updated").text();
            Date announcedDate = parsePubDate(pubDate);
            News news = new News();
            news.setUrl(link);
            news.setTitle(title);
            news.setDescription(description);
            news.setAnnouncedDate(announcedDate);
            news.setCollectedDate(new Date());
            newsList.add(news);
        }
        return newsList;
    }

    protected SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    }
    // 参考
    // https://github.com/toru1055/ifinance/blob/master/src/main/java/jp/thotta/ifinance/collector/BaseCompanyNewsCollector.java#L180
}
