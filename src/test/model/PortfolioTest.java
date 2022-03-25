//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class PortfolioTest {
//    Portfolio portfolio;
//    Stock stock;
//    DailyData dailyData;
//
//    @BeforeEach
//    void run_before() {
//        dailyData = new DailyData(100, 150, 175, 95, "01/08/2022");
//        stock = new Stock(new ArrayList<>(), "AAPL");
//        stock.addDailyData(dailyData);
//        ArrayList<Stock> stockList = new ArrayList<>();
//        stockList.add(stock);
//        portfolio = new Portfolio(stockList, "technology");
//    }
//
//    @Test
//    void addStockTest() {
//        assertEquals(1, portfolio.getStockListLength());
//        assertFalse(portfolio.doesPortfolioContainStock("MSFT"));
//        DailyData dailyData1 = new
//                DailyData(90, 140, 165, 75, "01/09/2022");
//        Stock stock1 = new Stock(new ArrayList<>(), "MSFT");
//        stock1.addDailyData(dailyData1);
//        assertTrue(portfolio.addStock(stock1));
//        assertEquals(2, portfolio.getStockListLength());
//        assertTrue(portfolio.doesPortfolioContainStock("MSFT"));
//    }
//
//    @Test
//    void deleteStockSuccessAtFirstPositionTest() {
//        assertEquals(1, portfolio.getStockListLength());
//        assertTrue(portfolio.doesPortfolioContainStock("AAPL"));
//        assertTrue(portfolio.deleteStock(stock));
//        assertEquals(0, portfolio.getStockListLength());
//        assertFalse(portfolio.doesPortfolioContainStock("AAPL"));
//    }
//
//    @Test
//    void deleteStockSuccessAtSecondPositionTest() {
//        DailyData dailyData1 = new
//                DailyData(90, 140, 165, 75, "01/09/2022");
//        Stock stock1 = new Stock(new ArrayList<>(), "MSFT");
//        stock1.addDailyData(dailyData1);
//        assertTrue(portfolio.addStock(stock1));
//        assertEquals(2, portfolio.getStockListLength());
//        assertTrue(portfolio.doesPortfolioContainStock("MSFT"));
//        assertTrue(portfolio.deleteStock(stock1));
//        assertEquals(1, portfolio.getStockListLength());
//        assertFalse(portfolio.doesPortfolioContainStock("MSFT"));
//    }
//
//    @Test
//    void deleteStockFailureTest() {
//        DailyData dailyData1 = new
//                DailyData(90, 140, 165, 75, "01/09/2022");
//        Stock stock1 = new Stock(new ArrayList<>(), "MSFT");
//        stock1.addDailyData(dailyData1);
//        assertFalse(portfolio.doesPortfolioContainStock("MSFT"));
//        assertEquals(1, portfolio.getStockListLength());
//        assertFalse(portfolio.deleteStock(stock1));
//        assertEquals(1, portfolio.getStockListLength());
//        assertFalse(portfolio.doesPortfolioContainStock("MSFT"));
//    }
//
//    @Test
//    void deleteStockUsingTickerSuccessAtFirstPositionTest() {
//        assertEquals(1, portfolio.getStockListLength());
//        assertTrue(portfolio.doesPortfolioContainStock("AAPL"));
//        assertTrue(portfolio.deleteStockUsingTicker("AAPL"));
//        assertEquals(0, portfolio.getStockListLength());
//        assertFalse(portfolio.doesPortfolioContainStock("AAPL"));
//    }
//
//    @Test
//    void deleteStockUsingTickerSuccessAtSecondPositionTest() {
//        DailyData dailyData1 = new
//                DailyData(90, 140, 165, 75, "01/09/2022");
//        Stock stock1 = new Stock(new ArrayList<>(), "MSFT");
//        stock1.addDailyData(dailyData1);
//        assertTrue(portfolio.addStock(stock1));
//        assertEquals(2, portfolio.getStockListLength());
//        assertTrue(portfolio.doesPortfolioContainStock("MSFT"));
//        assertTrue(portfolio.deleteStockUsingTicker("MSFT"));
//        assertEquals(1, portfolio.getStockListLength());
//        assertFalse(portfolio.doesPortfolioContainStock("MSFT"));
//    }
//
//    @Test
//    void deleteStockUsingTickerFailureTest() {
//        assertEquals(1, portfolio.getStockListLength());
//        assertFalse(portfolio.doesPortfolioContainStock("MSFT"));
//        assertFalse(portfolio.deleteStockUsingTicker("MSFT"));
//        assertEquals(1, portfolio.getStockListLength());
//        assertFalse(portfolio.doesPortfolioContainStock("MSFT"));
//    }
//
//    @Test
//    void getAllStockTickersTest() {
//        DailyData dailyData1 = new
//                DailyData(90, 140, 165, 75, "01/09/2022");
//        Stock stock1 = new Stock(new ArrayList<>(), "MSFT");
//        stock1.addDailyData(dailyData1);
//        assertTrue(portfolio.addStock(stock1));
//        assertEquals("technology: AAPL, MSFT", portfolio.getAllStockTickers());
//    }
//
//    @Test
//    void getAndSetCategoryTest() {
//        assertEquals("technology", portfolio.getCategory());
//        portfolio.setCategory("business");
//        assertEquals("business", portfolio.getCategory());
//    }
//
//    @Test
//    void addStockFailureTest() {
//        assertEquals(1, portfolio.getStockListLength());
//        assertTrue(portfolio.doesPortfolioContainStock("AAPL"));
//        assertFalse(portfolio.addStock(stock));
//        assertEquals(1, portfolio.getStockListLength());
//        assertTrue(portfolio.doesPortfolioContainStock("AAPL"));
//    }
//}
