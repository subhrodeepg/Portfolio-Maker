package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

//Unit tests for the Event class
public class EventTest {
    private Event event;
    private Date date;


    @BeforeEach
    public void runBefore() {
        event = new Event("Stock has been added to portfolio.");
        date = Calendar.getInstance().getTime();
    }

    @Test
    public void testEvent() {
        assertEquals("Stock has been added to portfolio.", event.getDescription());
        assertEquals(date, event.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "Stock has been added to portfolio.",
                event.toString());
    }

    @Test
    public void hashCodeTest() {
        Event event2 = new Event("Stock has been added to portfolio.");
        assertTrue(event.equals(event2));
        assertEquals(event2.hashCode(), event.hashCode());
    }

    @Test
    public void equalsTest() {
        assertFalse(event.equals(null));
        final int i = 2;
        assertFalse(event.equals(i));
    }
}