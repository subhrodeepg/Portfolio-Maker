package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

//Contains the Ticker Symbol and the list of daily daya for the stock
public class Stock {
    private final ArrayList<DailyData> dailyDataList;
    private final String ticker;

    //REQUIRES: ticker name is have a length > 0;
    //EFFECTS: Has a list of the daily data information of the stock.
    // The stock can be initialized with empty arrayList. The ticker is the identifier for the stock.

    public Stock(ArrayList<DailyData> dailyData, String ticker) {
        this.dailyDataList = dailyData;
        this.ticker = ticker.toUpperCase(Locale.ROOT);
    }

    //EFFECTS: Returns entire dailyDataList
    public ArrayList<DailyData> getDailyData() {
        return dailyDataList;
    }

    //EFFECTS: Returns dailyDataList at position p (zero index)
    public DailyData getDailyDataAtPosition(int p) {
        return dailyDataList.get(p);
    }

    //EFFECTS: returns ticker symbol
    public String getTicker() {
        return ticker;
    }

    //REQUIRES: size of dailyData > 0
    //EFFECTS: returns the highest price the stock has reached
    public double maxPrice() {
        double max = Integer.MIN_VALUE;
        for (DailyData dailyData : dailyDataList) {
            if (dailyData.getMaxPrice() > max) {
                max = dailyData.getMaxPrice();
            }
        }
        return max;
    }

    //REQUIRES: size of dailyData > 0
    //EFFECTS: returns the lowest price the stock has reached
    public double minPrice() {
        double min = Integer.MAX_VALUE;
        for (DailyData dailyData : dailyDataList) {
            if (dailyData.getMinPrice() < min) {
                min = dailyData.getMinPrice();
            }
        }
        return min;
    }

    //REQUIRES: size of dailyData > 0
    //EFFECTS: Returns the average price of a stock by summing the total of closing pricing and dividing by the
    //number of days.
    public double avgPrice() {
        int days = 0;
        double closingTotal = 0;
        for (DailyData dailyData : dailyDataList) {
            closingTotal += dailyData.getClosingPrice();
            days++;
        }
        return closingTotal / days;
    }

    //REQUIRES: Valid dailyData
    //MODIFIES: this
    //EFFECTS: adds daily data to dailyDayaList if it is not already presents.
    // Returns true if added successfully or returns false.
    public boolean addDailyData(DailyData dailyDataPara) {
        for (DailyData dailyData : dailyDataList) {
            if (Objects.equals(dailyData.getDate(), dailyDataPara.getDate())) {
                return false;
            }
        }
        return dailyDataList.add(dailyDataPara);
    }

    //EFFECTS: returns size of dailyDataList;
    public int dailyDataLength() {
        return dailyDataList.size();
    }

    //REQUIRES: Date string in valid format of MM/DD/YYYY
    //EFFECTS: Returns true if date is present in dailyDataList, or returns false.
    public boolean dateSearch(String s) {
        for (DailyData dailyData : dailyDataList) {
            if (Objects.equals(dailyData.getDate(), s)) {
                return true;
            }
        }
        return false;
    }

    //This class was modeled after JsonSerializationDemo, the code can be found here:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    //MODIFIES: this
    //EFFECTS: Returns a json object from the stock object
    public JSONObject toJson() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for (DailyData dailyData : dailyDataList) {
            jsonArray.put(dailyData.toJson());
        }
        jsonObject.put("ticker", ticker);
        jsonObject.put("dateInfo", jsonArray);
        return jsonObject;
    }
}
