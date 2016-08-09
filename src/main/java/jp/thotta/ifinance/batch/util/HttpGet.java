package jp.thotta.ifinance.batch.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.UnknownHostException;

// TODO: Singletonにして、連続して同じドメインにアクセスする場合に制御
public class HttpGet {
    private static final int SLEEP_TIME = 1000;
    private static final int RETRY_NUM = 2;

    public Document get(String url) {
        return get(url, 9000);
    }

    public Document get(String url, int timeout) {
        for (int i = 0; i <= RETRY_NUM; i++) {
            if (i > 0) {
                System.out.print("Retrying[" + i + "], ");
                //sleep();
            }
            sleep();
            System.out.println("[HttpGet] " + url);
            try {
                Document d = getDocument(url, timeout);
                return d;
            } catch (UnknownHostException e) {
                System.err.println(
                        "java.net.UnknownHostException: " +
                                e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected Document getDocument(String url, int timeout)
            throws UnknownHostException, Exception {
        return Jsoup.connect(url)
                .validateTLSCertificates(false)
                .timeout(timeout)
                .get();
    }

    private static void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
        }
    }
}
