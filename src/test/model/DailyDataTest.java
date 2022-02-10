package model;
import model.DailyData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DailyDataTest {
    DailyData dailyData;

    @BeforeEach
    void setup() {
        dailyData = new DailyData(100.50, 150, 150, 90, "01/07/2022");
    }

    @Test
    void getMaxPriceTest() {
        assertEquals(150, dailyData.getMaxPrice());
    }

    @Test
    void getMinPriceTest() {
        assertEquals(90, dailyData.getMinPrice());
    }

    @Test
    void getOpeningPriceTest() {
        assertEquals(100.50, dailyData.getOpeningPrice());
    }

    @Test
    void getClosingPriceTest() {
        assertEquals(150, dailyData.getClosingPrice());
    }

    @Test
    void getDateTest() {
        assertEquals("01/07/2022", dailyData.getDate());
    }

    @Test
    void setMaxPriceSuccessTest(){
        assertTrue(dailyData.setMaxPrice(1000));
        assertEquals(1000, dailyData.getMaxPrice());
        assertTrue(dailyData.setMaxPrice(11000));
        assertEquals(11000, dailyData.getMaxPrice());
    }

    @Test
    void setMaxPriceBoundaryFailureTest(){
        assertFalse(dailyData.setMaxPrice(150));
        assertEquals(150, dailyData.getMaxPrice());
    }

    @Test
    void setMaxPriceLessThanFailureTest(){
        assertFalse(dailyData.setMaxPrice(100));
        assertEquals(150, dailyData.getMaxPrice());
    }

    @Test
    void setMinPriceSuccessTest(){
        assertTrue(dailyData.setMinPrice(80));
        assertEquals(80, dailyData.getMinPrice());
        assertTrue(dailyData.setMinPrice(70));
        assertEquals(70, dailyData.getMinPrice());
    }

    @Test
    void setMinPriceBoundaryFailureTest(){
        assertFalse(dailyData.setMinPrice(90));
        assertEquals(90, dailyData.getMinPrice());
    }

    @Test
    void setMinPriceMoreThanFailureTest(){
        assertFalse(dailyData.setMaxPrice(100));
        assertEquals(90, dailyData.getMinPrice());
    }

    @Test
    void setOpeningPriceTest(){
        dailyData.setOpeningPrice(1000);
        assertEquals(1000, dailyData.getOpeningPrice());
        dailyData.setOpeningPrice(1200);
        assertEquals(1200, dailyData.getOpeningPrice());
    }

    @Test
    void setClosingPriceTest(){
        dailyData.setClosingPrice(1000);
        assertEquals(1000, dailyData.getClosingPrice());
        dailyData.setClosingPrice(1200);
        assertEquals(1200, dailyData.getClosingPrice());
    }

    @Test
    void setDateTest(){
        dailyData.setDate("01/08/2022");
        assertEquals("01/08/2022", dailyData.getDate());
        dailyData.setDate("01/09/2022");
        assertEquals("01/09/2022", dailyData.getDate());
    }



}