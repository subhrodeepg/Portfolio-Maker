package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    public void fileDoesNotExistTest() {
        JsonReader reader = new JsonReader("./data/fileDoesNotExist.json");
        try {
            PortfolioList portfolioList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void emptyPortfolioTest() {
        JsonReader reader = new JsonReader("./data/emptyPortfolio.json");
        try {
            PortfolioList portfolioList = reader.read();
            assertEquals(0, portfolioList.portfolioLength());
        } catch (IOException e) {
            fail("IOException Caught - Not expected");
        }
    }

    @Test
    public void portfolioTest() {
        JsonReader reader = new JsonReader("./data/portfolioTest.json");
        try {
            PortfolioList portfolioList = reader.read();
            assertEquals(2, portfolioList.portfolioLength());
            Portfolio portfolio = portfolioList.getPortfolio(0);
            assertEquals("technology", portfolio.getCategory());
            Stock stock = portfolio.getStock(0);
            assertEquals("AAPL", stock.getTicker());
            DailyData dailyData = stock.getDailyDataAtPosition(0);
            assertEquals("02/09/2022", dailyData.getDate());
            assertEquals(90, dailyData.getMinPrice());
            assertEquals(175, dailyData.getClosingPrice());
            assertEquals(180, dailyData.getMaxPrice());
            assertEquals(100, dailyData.getOpeningPrice());

        } catch (IOException e) {
            fail("IOException Caught - Not expected");
        }

    }


}
