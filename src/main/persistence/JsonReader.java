package persistence;

import model.DailyData;
import model.Portfolio;
import model.PortfolioList;
import model.Stock;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

//This class was modeled after JsonSerializationDemo, the code can be found here:
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

//Reads portfolio list from json file
public class JsonReader {
    private String source;

    //EFFECTS: Constructs reader to read from json file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: Reads from a source file
    public PortfolioList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return initParse(jsonObject);

    }

    //EFFECTS: Initializes the parse and returns the saved portfolio list
    private PortfolioList initParse(JSONObject jsonObject) {
        PortfolioList savedPortfolioList = new PortfolioList(new ArrayList<Portfolio>());
        parsePortfolioList(savedPortfolioList, jsonObject);
        return savedPortfolioList;

    }

    //EFFECTS: Parses over the saved portfolio list
    private void parsePortfolioList(PortfolioList savedPortfolioList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("portfolios");
        for (Object json : jsonArray) {
            JSONObject nextPortfolio = (JSONObject) json;
            addPortfolio(savedPortfolioList, nextPortfolio);
        }
    }

    //MODIFIES: savedPortfolioList
    //EFFECTS: Iterates over saved saved portfolio
    private void addPortfolio(PortfolioList savedPortfolioList, JSONObject nextPortfolio) {
        JSONArray jsonArray = nextPortfolio.getJSONArray("stockInfo");
        Portfolio savedPortfolio = new Portfolio(new ArrayList<Stock>(), (String) nextPortfolio.get("category"));
        for (Object json : jsonArray) {
            JSONObject nextStock = (JSONObject) json;
            addStock(savedPortfolio, nextStock);
        }
        savedPortfolioList.addPortfolio(savedPortfolio);
    }

    //MODIFIES: savedPortfolio
    //EFFECTS: Iterates over saved stock
    private void addStock(Portfolio savedPortfolio, JSONObject nextStock) {
        JSONArray jsonArray = nextStock.getJSONArray("dateInfo");
        Stock savedStock = new Stock(new ArrayList<DailyData>(), (String) nextStock.get("ticker"));
        for (Object json : jsonArray) {
            JSONObject nextDailyData = (JSONObject) json;
            addDailyDate(savedStock, nextDailyData);
        }
        savedPortfolio.addStock(savedStock);

    }

    //MODIFIES: savedStock
    //EFFECTS: Adds dailyData to savedStock
    private void addDailyDate(Stock savedStock, JSONObject nextDailyData) {

        Double openingPrice = nextDailyData.getDouble("openingPrice");
        Double closingPrice = nextDailyData.getDouble("closingPrice");
        Double maxPrice = nextDailyData.getDouble("maxPrice");
        Double minPrice = nextDailyData.getDouble("minPrice");
        String date = nextDailyData.getString("date");
        DailyData savedDailyData = new DailyData(openingPrice, closingPrice, maxPrice, minPrice, date);
        savedStock.addDailyData(savedDailyData);

    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

}
