package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

//Portfolio contains a list of all the stocks that have been added, and a category name.
public class Portfolio {
    private final ArrayList<Stock> stockList;
    private String category;


    //REQUIRES: category string length > 0
    //EFFECTS:stockList parameter is set to the stockList field. Can be initialized with empty arrayList.
    //category parameter is set to category field.
    public Portfolio(ArrayList<Stock> stockList, String category) {
        this.stockList = stockList;
        this.category = category;
    }

    //REQUIRES: Valid stock
    //MODIFIES: this
    //EFFECTS: Adds a new stock to stock list
    public boolean addStock(Stock stockAdd) {
        if (this.isContainsStockTicker(stockAdd.getTicker())) {
            return false;
        } else {
            return stockList.add(stockAdd);
        }
    }

    public Stock getStock(int index) {
        return stockList.get(index);
    }

    //REQUIRES: Valid stock
    //MODIFIES: this
    //EFFECTS: Deletes a stock from stock list
    public boolean deleteStock(Stock stockDelete) {
        int i = 0;
        for (Stock stock : stockList) {
            if (Objects.equals(stock.getTicker(), stockDelete.getTicker())) {
                stockList.remove(i);
                return true;
            }
            i++;
        }
        return false;
    }

    //REQUIRES: Ticker symbol in capital letters
    //MODIFIES: this
    //EFFECTS: Deletes a stock from stocklist using ticker symbol
    public boolean deleteStockUsingTicker(String stockTicker) {
        int i = 0;
        for (Stock stock : stockList) {
            if (Objects.equals(stock.getTicker(), stockTicker)) {
                stockList.remove(i);
                return true;
            }
            i++;
        }
        return false;
    }

    //EFFECTS: Returns all ticker symbols in stock list
    public String getAllStockTickers() {
        String[] result = new String[stockList.size()];
        int i = 0;
        for (Stock stock : stockList) {
            result[i] = stock.getTicker();
            i++;
        }
        return this.getCategory() + ": " + String.join(", ", result);
    }


    //EFFECTS: Returns true if stock ticker is in stock list
    public boolean isContainsStockTicker(String stockTicker) {
        for (Stock stock : stockList) {
            if (Objects.equals(stock.getTicker(), stockTicker)) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: Returns stock list length
    public int getStockListLength() {
        return stockList.size();
    }

    //EFFECTS: Returns category of the portfolio
    public String getCategory() {
        return category;
    }

    //MODIFIES: this
    //EFFECTS: Sets new category
    public void setCategory(String newCategory) {
        category = newCategory;
    }

    //This class was modeled after JsonSerializationDemo, the code can be found here:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    //MODIFIES: this
    //EFFECTS: Returns a json object from the portfolio object
    public JSONObject toJson() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        for (Stock stock : stockList) {
            jsonArray.put(stock.toJson());
        }
        jsonObject.put("category", category);
        jsonObject.put("stockInfo", jsonArray);

        return jsonObject;
    }
}
