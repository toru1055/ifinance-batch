package jp.thotta.ifinance.batch.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.net.UnknownHostException;

public class XmlGet extends HttpGet {
    protected Document getDocument(String url, int timeout)
            throws UnknownHostException, Exception {
        return Jsoup.connect(url)
                .validateTLSCertificates(false)
                .timeout(timeout)
                .parser(Parser.xmlParser()).get();
    }
}
