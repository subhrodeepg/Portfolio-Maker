package model;

import java.util.LinkedList;

public class Stock {
    private final LinkedList<DailyData> dailyDataList;
    private final String ticker;

    public Stock(LinkedList<DailyData> dailyData, String ticker) {
        this.dailyDataList = dailyData;
        this.ticker = ticker;
    }

    public LinkedList<DailyData> getDailyData() {
        return dailyDataList;
    }

    public String getTicker() {
        return ticker;
    }

    //REQUIRES: size of dailyData > 0
    //EFFECTS: returns the highest price the stock has reached
    public int stockMax() {
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
    public int stockMin() {
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
    public int stockAvg() {
        int days = 0;
        int closingTotal = 0;
        for (DailyData dailyData : dailyDataList) {
            closingTotal += dailyData.getClosingPrice();
            days++;
        }
        return closingTotal / days;
    }



}
