import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;


public class TestModel {
    private Model model;

    @Before
    public void initModel() {
        model = new Model();
    }

    @Test
    public void testCalcIncome() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m = model.getClass().getDeclaredMethod("calcTotalIncome", int.class, int.class, double.class, double.class);
        m.setAccessible(true);

        assertEquals((double) m.invoke(model, 10, 10, 10, 10), 1000, 0);
        assertEquals((double) m.invoke(model, 3, 3, 1, 1), 9, 0);
        assertEquals((double) m.invoke(model, 29, 2, 2, 2), 116, 0);

        assertEquals((double) m.invoke(model, 10, 10, 10, 8), 900, 0);
        assertEquals((double) m.invoke(model, 11, 10, 10, 8), 980, 0);
    }

    @Test
    public void testGetTicketPrice() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m = model.getClass().getDeclaredMethod("calcTicketPrice", int.class, int.class, double.class, double.class, int.class);
        m.setAccessible(true);

        assertEquals((double) m.invoke(model, 10, 10, 10, 10, 1), 10, 0);
        assertEquals((double) m.invoke(model, 10, 10, 10, 8, 1), 10, 0);
        assertEquals((double) m.invoke(model, 9, 9, 10, 8, 9), 8, 0);
        assertEquals((double) m.invoke(model, 9, 9, 8, 10, 9), 10, 0);

        assertEquals((double) m.invoke(model, 11, 10, 10, 8, 11), 8, 0);
        assertEquals((double) m.invoke(model, 11, 10, 10, 8, 6), 8, 0);
        assertEquals((double) m.invoke(model, 11, 10, 10, 8, 5), 10, 0);

        assertEquals((double) m.invoke(model, 2, 10, 10, 8, 1), 10, 0);
        assertEquals((double) m.invoke(model, 2, 10, 10, 8, 2), 10, 0);
    }
}
