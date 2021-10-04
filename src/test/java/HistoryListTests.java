import org.example.pubstones.util.datatyp.historylist.HistoryList;
import org.example.pubstones.util.datatyp.historylist.OutOfTimelineException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryListTests {
    private final int testInt1 = 1;
    private final int testInt2 = 2;
    private final int testInt3 = 3;

    @Test
    void testAppend() throws OutOfTimelineException {
        HistoryList<Integer> integerHistoryList = new HistoryList<>();

        assertThrows(OutOfTimelineException.class, integerHistoryList::getCurrent);

        integerHistoryList.append(this.testInt1);
        assertEquals(this.testInt1, (int) integerHistoryList.getCurrent());

        integerHistoryList.append(this.testInt2);
        integerHistoryList.append(this.testInt3);
        assertEquals(this.testInt3, (int) integerHistoryList.getCurrent());
    }

    @Test
    void testNavigation() throws OutOfTimelineException {
        HistoryList<Integer> integerHistoryList = new HistoryList<>();

        integerHistoryList.append(this.testInt1);
        integerHistoryList.append(this.testInt2);

        assertThrows(OutOfTimelineException.class, integerHistoryList::goNext);

        assertEquals(this.testInt1, (int) integerHistoryList.goPrevious());

        assertEquals(this.testInt2, (int) integerHistoryList.goNext());

        Integer getPreviousInteger = integerHistoryList.getPrevious();
        Integer goPreviousInteger = integerHistoryList.goPrevious();
        assertEquals(getPreviousInteger, goPreviousInteger);

        integerHistoryList.append(this.testInt3);

        assertEquals(this.testInt3, (int) integerHistoryList.getCurrent());

        assertEquals(this.testInt1, (int) integerHistoryList.goPrevious());
    }
}
