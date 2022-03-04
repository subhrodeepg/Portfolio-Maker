package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.util.ArrayList;

import javax.sound.sampled.Port;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//This class was modeled after JsonSerializationDemo, the code can be found here:
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonWriterTest {
    @Test
    public void fileDoesNotExistTest() {
        try {
            JsonWriter writer = new JsonWriter("./data/my/fileDoesNotExist.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void writeEmptyPortfolioTest() {
        try {
            PortfolioList portfolioList = new PortfolioList(new ArrayList<Portfolio>());
            JsonWriter jsonWriter = new JsonWriter("./data/emptyPortfolio.json");
            jsonWriter.open();
            jsonWriter.write(portfolioList);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader("./data/emptyPortfolio.json");
            PortfolioList resultPortfolio = jsonReader.read();
            assertEquals(0, resultPortfolio.portfolioLength());
        } catch (IOException e) {
            fail("IO Exception was caught");
        }
    }

    @Test
    public void writePortfolioTest() {
        try {
            PortfolioList portfolioList = new PortfolioList(new ArrayList<Portfolio>());
            DailyData feb092022DailyData = new DailyData(100, 175,
                    180, 90, "02/09/2022");
            Stock aaplStock = new Stock(new ArrayList<>(), "AAPL");
            aaplStock.addDailyData(feb092022DailyData);
            Portfolio technologyPortfolio = new Portfolio(new ArrayList<>(), "technology");
            technologyPortfolio.addStock(aaplStock);
            portfolioList.addPortfolio(technologyPortfolio);

            JsonWriter jsonWriter = new JsonWriter("./data/emptyPortfolio.json");
            jsonWriter.open();
            jsonWriter.write(portfolioList);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader("./data/emptyPortfolio.json");
            PortfolioList resultPortfolioList = jsonReader.read();
            assertEquals(1, resultPortfolioList.portfolioLength());
            Portfolio portfolioResult = portfolioList.getPortfolio(0);
            assertEquals("technology", portfolioResult.getCategory());
            Stock stock = portfolioResult.getStock(0);
            assertEquals("AAPL", stock.getTicker());
            DailyData dailyData = stock.getDailyDataAtPosition(0);
            assertEquals("02/09/2022", dailyData.getDate());
            assertEquals(90, dailyData.getMinPrice());
            assertEquals(175, dailyData.getClosingPrice());
            assertEquals(180, dailyData.getMaxPrice());
            assertEquals(100, dailyData.getOpeningPrice());
        } catch (IOException e) {
            fail("IO Exception was caught");
        }
    }
}
