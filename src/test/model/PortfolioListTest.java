//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import javax.sound.sampled.Port;
//import java.util.ArrayList;
//import java.util.Objects;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class PortfolioListTest {
//    PortfolioList portfolioList;
//    Portfolio portfolio;
//    Stock stock;
//    DailyData dailyData;
//
//    @BeforeEach
//    void run_before() {
//        dailyData = new DailyData(100, 150, 175, 95, "01/08/2022");
//        stock = new Stock(new ArrayList<>(), "AAPL");
//        stock.addDailyData(dailyData);
//        portfolio = new Portfolio(new ArrayList<>(), "technology");
//        portfolio.addStock(stock);
//        ArrayList<Portfolio> portfolioListArgument = new ArrayList<>();
//        portfolioListArgument.add(portfolio);
//        portfolioList = new PortfolioList(portfolioListArgument);
//    }
//
//    @Test
//    void addPortfolioSuccess() {
//        DailyData dailyData1 = new
//                DailyData(90, 250, 250, 97, "01/09/2022");
//        Stock stock1 = new Stock(new ArrayList<>(), "MSFT");
//        stock1.addDailyData(dailyData1);
//        Portfolio portfolio1 = new Portfolio(new ArrayList<>(), "business");
//        portfolio1.addStock(stock1);
////        ArrayList<Portfolio> portfolioListArgument1 = new ArrayList<>();
////        portfolioListArgument1.add(portfolio);
////        PortfolioList portfolioList1 = new PortfolioList(portfolioListArgument1);
//
//        assertEquals(1, portfolioList.portfolioLength());
//        assertFalse(portfolioList.isContainsPortfolio("business"));
//        assertTrue(portfolioList.addPortfolio(portfolio1));
//        assertEquals(2, portfolioList.portfolioLength());
//        assertTrue(portfolioList.isContainsPortfolio("business"));
//    }
//
//    @Test
//    void addPortfolioFailure() {
//        DailyData dailyData1 = new
//                DailyData(100, 150, 175, 95, "01/08/2022");
//        Stock stock1 = new Stock(new ArrayList<>(), "AAPL");
//        stock1.addDailyData(dailyData1);
//        Portfolio portfolio1 = new Portfolio(new ArrayList<>(), "technology");
//        portfolio1.addStock(stock1);
////        ArrayList<Portfolio> portfolioListArgument1 = new ArrayList<>();
////        portfolioListArgument1.add(portfolio);
////        PortfolioList portfolioList1 = new PortfolioList(portfolioListArgument1);
//
//        assertEquals(1, portfolioList.portfolioLength());
//        assertTrue(portfolioList.isContainsPortfolio("technology"));
//        assertFalse(portfolioList.addPortfolio(portfolio1));
//        assertEquals(1, portfolioList.portfolioLength());
//        assertTrue(portfolioList.isContainsPortfolio("technology"));
//    }
//
//    @Test
//    void deletePortfolioAtFirstPosition() {
//        assertEquals(1, portfolioList.portfolioLength());
//        assertTrue(portfolioList.isContainsPortfolio("technology"));
//        assertTrue(portfolioList.deletePortfolio("technology"));
//        assertEquals(0, portfolioList.portfolioLength());
//        assertFalse(portfolioList.deletePortfolio("technology"));
//    }
//
//    @Test
//    void deletePortfolioAtSecondPosition() {
//        DailyData dailyData1 = new
//                DailyData(90, 250, 250, 97, "01/09/2022");
//        Stock stock1 = new Stock(new ArrayList<>(), "MSFT");
//        stock1.addDailyData(dailyData1);
//        Portfolio portfolio1 = new Portfolio(new ArrayList<>(), "business");
//        portfolio1.addStock(stock1);
//
//        assertTrue(portfolioList.addPortfolio(portfolio1));
//        assertEquals(2, portfolioList.portfolioLength());
//        assertTrue(portfolioList.isContainsPortfolio("business"));
//        assertTrue(portfolioList.deletePortfolio("business"));
//        assertEquals(1, portfolioList.portfolioLength());
//        assertTrue(portfolioList.isContainsPortfolio("technology"));
//    }
//
//    @Test
//    void failureToDeletePortfolio() {
//        DailyData dailyData1 = new
//                DailyData(90, 250, 250, 97, "01/09/2022");
//        Stock stock1 = new Stock(new ArrayList<>(), "MSFT");
//        stock1.addDailyData(dailyData1);
//        Portfolio portfolio1 = new Portfolio(new ArrayList<>(), "business");
//        portfolio1.addStock(stock1);
//
//        assertTrue(portfolioList.addPortfolio(portfolio1));
//        assertEquals(2, portfolioList.portfolioLength());
//        assertFalse(portfolioList.isContainsPortfolio("energy"));
//        assertFalse(portfolioList.deletePortfolio("energy"));
//        assertEquals(2, portfolioList.portfolioLength());
//        assertFalse(portfolioList.isContainsPortfolio("energy"));
//    }
//
//    @Test
//    void displayAllPortfoliosAndStocks() {
//        DailyData dailyData1 = new
//                DailyData(90, 250, 250, 97, "01/09/2022");
//        Stock stock1 = new Stock(new ArrayList<>(), "MSFT");
//        stock1.addDailyData(dailyData1);
//        Portfolio portfolio1 = new Portfolio(new ArrayList<>(), "business");
//        portfolio1.addStock(stock1);
//
//        assertTrue(portfolioList.addPortfolio(portfolio1));
//        assertEquals(2, portfolioList.portfolioLength());
//        assertEquals("technology: AAPL\nbusiness: MSFT", portfolioList.displayAllPortfoliosAndStocks());
//    }
//
//    @Test
//    void getAllCategoriesTest() {
//        DailyData dailyData1 = new
//                DailyData(90, 250, 250, 97, "01/09/2022");
//        Stock stock1 = new Stock(new ArrayList<>(), "MSFT");
//        stock1.addDailyData(dailyData1);
//        Portfolio portfolio1 = new Portfolio(new ArrayList<>(), "business");
//        portfolio1.addStock(stock1);
//
//        assertTrue(portfolioList.addPortfolio(portfolio1));
//        assertEquals(2, portfolioList.portfolioLength());
//        assertEquals("1. technology, 2. business", portfolioList.getAllCategories());
//    }
//
//    @Test
//    void getPortfolio() {
//        DailyData dailyData1 = new
//                DailyData(90, 250, 250, 97, "01/09/2022");
//        Stock stock1 = new Stock(new ArrayList<>(), "MSFT");
//        stock1.addDailyData(dailyData1);
//        Portfolio portfolio1 = new Portfolio(new ArrayList<>(), "business");
//        portfolio1.addStock(stock1);
//
//        assertTrue(portfolioList.addPortfolio(portfolio1));
//        assertEquals(2, portfolioList.portfolioLength());
//        Portfolio portfolio2 = portfolioList.getPortfolio(1);
//        assertEquals(portfolio1, portfolio2);
//    }
//}
