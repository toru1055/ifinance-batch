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
 * Created by thotta on 2016/05/17.
 */
public class NewsScraperRss1 extends BaseNewsScraper {

    @Override
    protected List<News> getNewsList(Document doc) {
        List<News> newsList = new ArrayList<News>();
        Elements items = doc.select("item");
        for (Element item : items) {
            String title = item.select("title").text();
            String description = Jsoup.parse(item.select("description").text()).text();
            String link = item.select("link").text();
            String pubdate = item.select("dc|date").text();
            Date announcedDate = parsePubDate(pubdate);
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

    @Override
    protected SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    }
}
