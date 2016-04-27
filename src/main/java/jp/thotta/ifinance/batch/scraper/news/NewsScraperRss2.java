package jp.thotta.ifinance.batch.scraper.news;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jp.thotta.ifinance.common.entity.News;
import jp.thotta.ifinance.batch.scraper.NewsScraper;
import jp.thotta.ifinance.batch.scraper.BaseNewsScraper;

public class NewsScraperRss2 extends BaseNewsScraper {
  SimpleDateFormat f
    = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

  @Override
  protected List<News> getNewsList(Document doc) {
    List<News> newsList = new ArrayList<News>();
    Elements items = doc.select("item");
    for(Element item : items) {
      String title = item.select("title").text();
      String description = Jsoup.parse(item.select("description").text()).text();
      String link = item.select("link").text();
      String pubdate = item.select("pubdate").text();
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

  Date parsePubDate(String pubdate) {
    try {
      Date d = f.parse(pubdate);
      return d;
    } catch(Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
