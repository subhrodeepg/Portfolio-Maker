package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

public class StockTest {
    Stock stock;
    @BeforeEach
    void run_before(){
        DailyData dailyData = new
                DailyData(100, 150, 180, 90, "01/07/2022");
        stock = new Stock(new ArrayList<>(), "AAPL");
        stock.dailyDataAdd(dailyData);
    }

    @Test
    void addListTest(){
        DailyData dailyData1 = new
                DailyData(90, 160, 190, 90, "01/08/2022");
        assertEquals(1, stock.dailyDataLength());
        assertFalse(stock.dateSearch("01/08/2022"));
        assertTrue(stock.dailyDataAdd(dailyData1));
        assertEquals(2, stock.dailyDataLength());
        assertTrue(stock.dateSearch("01/08/2022"));
    }

    @Test
    void maxPriceTest() {
        assertEquals(180, stock.maxPrice());
        DailyData dailyData1 = new
                DailyData(90, 160, 190, 90, "01/08/2022");
        assertTrue(stock.dailyDataAdd(dailyData1));
        DailyData dailyData2 = new
                DailyData(90, 160, 180, 90, "01/09/2022");
        assertTrue(stock.dailyDataAdd(dailyData2));
        assertEquals(190, stock.maxPrice());
    }

    @Test
    void minPriceTest() {
        assertEquals(90, stock.minPrice());
        DailyData dailyData1 = new
                DailyData(90, 160, 190, 80, "01/08/2022");
        assertTrue(stock.dailyDataAdd(dailyData1));
        assertEquals(80, stock.minPrice());
        DailyData dailyData2 = new
                DailyData(120, 160, 190, 100, "01/09/2022");
        assertTrue(stock.dailyDataAdd(dailyData2));
        assertEquals(80, stock.minPrice());
    }

    @Test
    void avgPrice() {
        assertEquals(150, stock.avgPrice());
        DailyData dailyData1 = new
                DailyData(90, 160, 190, 80, "01/08/2022");
        assertTrue(stock.dailyDataAdd(dailyData1));
        assertEquals(155, stock.avgPrice());
    }

    @Test
    void dateSearch() {
        assertTrue(stock.dateSearch("01/07/2022"));
        assertFalse(stock.dateSearch("01/08/2022"));
        DailyData dailyData1 = new
                DailyData(90, 160, 190, 80, "01/08/2022");
        assertTrue(stock.dailyDataAdd(dailyData1));
        assertTrue(stock.dateSearch("01/08/2022"));
    }

    @Test
    void dailyDataAddFailure() {
        DailyData dailyData1 = new
                DailyData(100, 150, 180, 90, "01/07/2022");
        assertFalse(stock.dailyDataAdd(dailyData1));
        DailyData dailyData2 = new
                DailyData(90, 160, 190, 80, "01/08/2022");
        assertTrue(stock.dailyDataAdd(dailyData2));

    }

    @Test
    void getTickerTest() {
        assertEquals("AAPL", stock.getTicker());
    }

    @Test
    void getDailyDataAtPositionTest() {
        DailyData dailyData;
        dailyData = stock.getDailyDataAtPosition(0);
        assertEquals(100, dailyData.getOpeningPrice());
        assertEquals(150, dailyData.getClosingPrice());
        assertEquals(180, dailyData.getMaxPrice());
        assertEquals(90, dailyData.getMinPrice());
        assertEquals("01/07/2022", dailyData.getDate());

        DailyData dailyData1 = new
                DailyData(90, 160, 190, 80, "01/08/2022");

        assertTrue(stock.dailyDataAdd(dailyData1));
        dailyData = stock.getDailyDataAtPosition(1);
        assertEquals(90, dailyData.getOpeningPrice());
        assertEquals(160, dailyData.getClosingPrice());
        assertEquals(190, dailyData.getMaxPrice());
        assertEquals(80, dailyData.getMinPrice());
        assertEquals("01/08/2022", dailyData.getDate());

    }

    @Test
    void getDailyDataTest() {
        ArrayList<DailyData> dailyDataList1 = new ArrayList<>();
        DailyData dailyData1 = new
                DailyData(100, 150, 180, 90, "01/07/2022");
        DailyData dailyData2 = new
                DailyData(90, 160, 190, 80, "01/08/2022");
        dailyDataList1.add(dailyData1);
        dailyDataList1.add(dailyData2);

        DailyData dailyData3 = new
                DailyData(90, 160, 190, 80, "01/08/2022");
        assertTrue(stock.dailyDataAdd(dailyData3));
        ArrayList<DailyData> dailyDataListFromStock = stock.getDailyData();

        for(int i = 0; i < stock.dailyDataLength(); i++) {
            DailyData dailyDataFromStock;
            DailyData dailyDataFromTestStock;
            dailyDataFromStock = dailyDataListFromStock.get(i);
            dailyDataFromTestStock = dailyDataList1.get(i);

            assertEquals(dailyDataFromTestStock.getOpeningPrice(), dailyDataFromStock.getOpeningPrice());
            assertEquals(dailyDataFromTestStock.getClosingPrice(), dailyDataFromStock.getClosingPrice());
            assertEquals(dailyDataFromTestStock.getMaxPrice(), dailyDataFromStock.getMaxPrice());
            assertEquals(dailyDataFromTestStock.getMinPrice(), dailyDataFromStock.getMinPrice());
            assertEquals(dailyDataFromTestStock.getDate(), dailyDataFromStock.getDate());
        }

    }
}
