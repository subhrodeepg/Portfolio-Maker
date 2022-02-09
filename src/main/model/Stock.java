package model;

import java.util.ArrayList;
import java.util.Objects;

public class Stock {
    private final ArrayList<DailyData> dailyDataList;
    private final String ticker;

    public Stock(ArrayList<DailyData> dailyData, String ticker) {
        this.dailyDataList = dailyData;
        this.ticker = ticker;
    }

    public ArrayList<DailyData> getDailyData() {
        return dailyDataList;
    }

    public DailyData getDailyDataAtPosition(int p) {
        return dailyDataList.get(p);
    }

    public String getTicker() {
        return ticker;
    }

    //REQUIRES: size of dailyData > 0
    //EFFECTS: returns the highest price the stock has reached
    public int maxPrice() {
        int max = Integer.MIN_VALUE;
        for (DailyData dailyData : dailyDataList) {
            if (dailyData.getMaxPrice() > max) {
                max = dailyData.getMaxPrice();
            }
        }
        return max;
    }

    //REQUIRES: size of dailyData > 0
    //EFFECTS: returns the lowest price the stock has reached
    public int minPrice() {
        int min = Integer.MAX_VALUE;
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
    public int avgPrice() {
        int days = 0;
        int closingTotal = 0;
        for (DailyData dailyData : dailyDataList) {
            closingTotal += dailyData.getClosingPrice();
            days++;
        }
        return closingTotal / days;
    }

    public boolean dailyDataAdd(DailyData dailyDataPara) {
        for (DailyData dailyData : dailyDataList) {
            if (Objects.equals(dailyData.getDate(), dailyDataPara.getDate())) {
                return false;
            }
        }
        return dailyDataList.add(dailyDataPara);
    }

    public int dailyDataLength() {
        return dailyDataList.size();
    }

    public boolean dateSearch(String s) {
        for (DailyData dailyData : dailyDataList) {
            if (Objects.equals(dailyData.getDate(), s)) {
                return true;
            }
        }
        return false;
    }



}
