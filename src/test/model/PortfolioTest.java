package model;

import exceptions.StockAlreadyExistsException;
import exceptions.StockDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PortfolioTest {
    Portfolio portfolio;
    Stock stock;
    DailyData dailyData;

    @BeforeEach
    void run_before() {
        dailyData = new DailyData(100, 150, 175, 95, "01/08/2022");
        stock = new Stock(new ArrayList<>(), "AAPL");
        stock.addDailyData(dailyData);
        ArrayList<Stock> stockList = new ArrayList<>();
        stockList.add(stock);
        portfolio = new Portfolio(stockList, "technology");
    }

    @Test
    void addStockTest() {
        assertEquals(1, portfolio.getStockListLength());
        try {
            portfolio.confirmStockDoesNotExist("MSFT");
            DailyData dailyData1 = new
                    DailyData(90, 140, 165, 75, "01/09/2022");
            Stock stock1 = new Stock(new ArrayList<>(), "MSFT");
            stock1.addDailyData(dailyData1);
            portfolio.addStockToPortfolio(stock1);
        }  catch (StockAlreadyExistsException e) {
            fail("Exception Not Caught");
        }
        assertEquals(2, portfolio.getStockListLength());
        try {
            portfolio.confirmStockExists("MSFT");
        } catch (StockDoesNotExistException e) {
            fail("Exception Not Caught");
        }


    }

    @Test
    void deleteStockSuccessAtFirstPositionTest() {
        assertEquals(1, portfolio.getStockListLength());
        try {
            portfolio.confirmStockDoesNotExist("AAPL");
            fail();
        } catch (StockAlreadyExistsException e) {
            e.printStackTrace();
        }
        assertTrue(portfolio.deleteStock(stock));
        assertEquals(0, portfolio.getStockListLength());
        try {
            portfolio.confirmStockExists("AAPL");
            fail();
        } catch (StockDoesNotExistException e) {
            e.printStackTrace();
        }

    }

    @Test
    void deleteStockSuccessAtSecondPositionTest() {
        DailyData dailyData1 = new
                DailyData(90, 140, 165, 75, "01/09/2022");
        Stock stock1 = new Stock(new ArrayList<>(), "MSFT");
        stock1.addDailyData(dailyData1);
        portfolio.addStockToPortfolio(stock1);
        assertEquals(2, portfolio.getStockListLength());
        try{
            portfolio.confirmStockExists("MSFT");
        } catch (StockDoesNotExistException e) {
            fail();
        }

        assertTrue(portfolio.deleteStock(stock1));
        assertEquals(1, portfolio.getStockListLength());
        try {
            portfolio.confirmStockDoesNotExist("MSFT");
        } catch (StockAlreadyExistsException e) {
            fail();
        }

    }

    @Test
    void deleteStockFailureTest() {
        DailyData dailyData1 = new
                DailyData(90, 140, 165, 75, "01/09/2022");
        Stock stock1 = new Stock(new ArrayList<>(), "MSFT");
        stock1.addDailyData(dailyData1);
        try {
            portfolio.confirmStockDoesNotExist("MSFT");
        } catch (StockAlreadyExistsException e) {
            fail();
        }
        assertEquals(1, portfolio.getStockListLength());
        assertFalse(portfolio.deleteStock(stock1));
        assertEquals(1, portfolio.getStockListLength());
        try{
            portfolio.confirmStockDoesNotExist("MSFT");
        }catch (StockAlreadyExistsException e) {
            fail();
        }
    }

    @Test
    void deleteStockUsingTickerSuccessAtFirstPositionTest() {
        assertEquals(1, portfolio.getStockListLength());
        try {
            portfolio.confirmStockExists("AAPL");
        } catch (StockDoesNotExistException e) {
            fail();
        }

        assertTrue(portfolio.deleteStockUsingTicker("AAPL"));
        assertEquals(0, portfolio.getStockListLength());
        try{
            portfolio.confirmStockDoesNotExist("AAPL");
        } catch (StockAlreadyExistsException e) {
            fail();
        }

    }

    @Test
    void deleteStockUsingTickerSuccessAtSecondPositionTest() {
        DailyData dailyData1 = new
                DailyData(90, 140, 165, 75, "01/09/2022");
        Stock stock1 = new Stock(new ArrayList<>(), "MSFT");
        stock1.addDailyData(dailyData1);
        portfolio.addStockToPortfolio(stock1);
        assertEquals(2, portfolio.getStockListLength());
        try {
            portfolio.confirmStockExists("MSFT");
        } catch (StockDoesNotExistException e) {
            fail();
        }

        assertTrue(portfolio.deleteStockUsingTicker("MSFT"));
        assertEquals(1, portfolio.getStockListLength());
        try{
            portfolio.confirmStockDoesNotExist("MSFT");
        } catch (StockAlreadyExistsException e) {
            fail();
        }

    }

    @Test
    void deleteStockUsingTickerFailureTest() {
        assertEquals(1, portfolio.getStockListLength());
        try{
            portfolio.confirmStockDoesNotExist("MSFT");
        } catch (StockAlreadyExistsException e) {
            fail();
        }

        assertFalse(portfolio.deleteStockUsingTicker("MSFT"));
        assertEquals(1, portfolio.getStockListLength());

        try{
            portfolio.confirmStockDoesNotExist("MSFT");
        } catch (StockAlreadyExistsException e) {
            fail();
        }

    }

    @Test
    void getAllStockTickersTest() {
        DailyData dailyData1 = new
                DailyData(90, 140, 165, 75, "01/09/2022");
        Stock stock1 = new Stock(new ArrayList<>(), "MSFT");
        stock1.addDailyData(dailyData1);
        portfolio.addStockToPortfolio(stock1);
        assertEquals("technology: AAPL, MSFT", portfolio.getAllStockTickers());
    }

    @Test
    void getAndSetCategoryTest() {
        assertEquals("technology", portfolio.getCategory());
        portfolio.setCategory("business");
        assertEquals("business", portfolio.getCategory());
    }

    @Test
    void addStockFailureTest() {
        assertEquals(1, portfolio.getStockListLength());
        try{
            portfolio.confirmStockExists("AAPL");
        } catch (StockDoesNotExistException e) {
            fail();
        }

        portfolio.addStockToPortfolio(stock);
        assertEquals(2, portfolio.getStockListLength());
        try{
            portfolio.confirmStockExists("AAPL");
        } catch (StockDoesNotExistException e) {
            fail();
        }

    }
}
