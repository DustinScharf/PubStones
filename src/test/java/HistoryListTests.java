import org.example.pubstones.util.HistoryList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryListTests {
    private final int testInt1 = 1;
    private final int testInt2 = 2;
    private final int testInt3 = 3;

    @Test
    void testAppend() {
        HistoryList<Integer> integerHistoryList = new HistoryList<>();

        assertThrows(NullPointerException.class, integerHistoryList::getCurrent);

        integerHistoryList.append(this.testInt1);
        assertEquals(this.testInt1, (int) integerHistoryList.getCurrent());

        integerHistoryList.append(this.testInt2);
        integerHistoryList.append(this.testInt3);
        assertEquals(this.testInt3, (int) integerHistoryList.getCurrent());
    }

    @Test
    void testNavigation() {
        HistoryList<Integer> integerHistoryList = new HistoryList<>();

        integerHistoryList.append(this.testInt1);
        integerHistoryList.append(this.testInt2);

        assertEquals(this.testInt1, (int) integerHistoryList.goPrevious());

        assertEquals(this.testInt2, (int) integerHistoryList.goNext());

        integerHistoryList.goPrevious();

        integerHistoryList.append(this.testInt3);

        assertEquals(this.testInt3, (int) integerHistoryList.getCurrent());

        assertEquals(this.testInt1, (int) integerHistoryList.goPrevious());
    }
}
