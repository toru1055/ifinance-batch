package jp.thotta.ifinance.batch.scraper.index;

import jp.thotta.ifinance.batch.scraper.IndexCollector;
import junit.framework.TestCase;

/**
 * Created by thotta on 2016/08/05.
 */
public class IndexCollectorNikkei225Test extends TestCase {
    public void testGetCurrentValue() {
        IndexCollector indexCollector = new IndexCollectorNikkei225();
        Double val = indexCollector.getCurrentValue();
        assertTrue(val != null);
        assertTrue(val > 0.0);
    }
}