package model;

import org.json.JSONObject;

//DailyData contains opening price, closing price, maximum price the stock reached, and minimum price the stock
// reached for the date.
public class DailyData {
    private double openingPrice;
    private double closingPrice;
    private double maxPrice;
    private double minPrice;
    private String date;

    //REQUIRES: Non-negative prices for opening price, closing price, maximum price and minimum price.
    //Date must be in MM/DD/YYYY format.
    //EFFECTS: Opening price is set to opening price for the date. Closing price is set to closing price for the date.
    //Maximum price is set to maximum price for the date. Minimum price is set to minimum price for the date.
    //Date is set to the date for the all prices.
    public DailyData(double openingPrice, double closingPrice, double maxPrice, double minPrice, String date) {
        this.openingPrice = openingPrice;
        this.closingPrice = closingPrice;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.date = date;
    }

    //EFFECTS: Returns the maximum price the stock reached for the day.
    public double getMaxPrice() {
        return maxPrice;
    }

    //EFFECTS: Returns the minimum price the stock reached for the day.
    public double getMinPrice() {
        return minPrice;
    }

    //EFFECTS: Returns the opening price for the day.
    public double getOpeningPrice() {
        return openingPrice;
    }

    //EFFECTS: Returns the closing price for the day.
    public double getClosingPrice() {
        return closingPrice;
    }

    //EFFECTS: Returns the date.
    public String getDate() {
        return date;
    }

    //MODIFIES: this
    //EFFECTS: Changes the maximum price if the price is higher than the current maximum.
    public boolean setMaxPrice(double maxPrice) {
        if (maxPrice > this.maxPrice) {
            this.maxPrice = maxPrice;
            return true;
        } else {
            return false;
        }

    }

    //MODIFIES: this
    //EFFECTS: Changes the minimum price if the price is lower than the current maximum.
    public boolean setMinPrice(double minPrice) {
        if (minPrice < this.minPrice) {
            this.minPrice = minPrice;
            return true;
        } else {
            return false;
        }
    }

    //MODIFIES: this
    //EFFECTS: Changes the opening price.
    public void setOpeningPrice(double openingPrice) {
        this.openingPrice = openingPrice;
    }

    //MODIFIES: this
    //EFFECTS: Changes the closing price.
    public void setClosingPrice(double closingPrice) {
        this.closingPrice = closingPrice;
    }

    //MODIFIES: this
    //EFFECTS: Changes the date.
    public void setDate(String date) {
        this.date = date;
    }

    //This method was modeled after JsonSerializationDemo, the code can be found here:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    //MODIFIES: this
    //EFFECTS: Returns a json object from the dailyData object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", date);
        json.put("openingPrice", openingPrice);
        json.put("closingPrice", closingPrice);
        json.put("maxPrice", maxPrice);
        json.put("minPrice", minPrice);

        return json;
    }

    
}
