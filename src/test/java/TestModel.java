import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TestModel {
    @Test
    public void testCalcIncome() {
        assertEquals(Model.calcIncome(10, 10, 10, 10), 1000, 0);
        assertEquals(Model.calcIncome(3, 3, 1, 1), 9, 0);
        assertEquals(Model.calcIncome(29, 2, 2, 2), 116, 0);

        assertEquals(Model.calcIncome(10, 10, 10, 8), 900, 0);
        assertEquals(Model.calcIncome(11, 10, 10, 8), 980, 0);
    }

    @Test
    public void testGetTicketPrice() {
        assertEquals(Model.getTicketPrice(10, 10, 10, 10, 1), 10, 0);
        assertEquals(Model.getTicketPrice(10, 10, 10, 8, 1), 10, 0);
        assertEquals(Model.getTicketPrice(9, 9, 10, 8, 9), 8, 0);
        assertEquals(Model.getTicketPrice(9, 9, 8, 10, 9), 10, 0);

        assertEquals(Model.getTicketPrice(11, 10, 10, 8, 11), 8, 0);
        assertEquals(Model.getTicketPrice(11, 10, 10, 8, 6), 8, 0);
        assertEquals(Model.getTicketPrice(11, 10, 10, 8, 5), 10, 0);

        assertEquals(Model.getTicketPrice(2, 10, 10, 8, 1), 10, 0);
        assertEquals(Model.getTicketPrice(2, 10, 10, 8, 2), 10, 0);
    }
}
