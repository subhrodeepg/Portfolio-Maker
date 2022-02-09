package model;

public class DailyData {
    private int openingPrice;
    private int closingPrice;
    private int maxPrice;
    private int minPrice;
    private String date;

    public DailyData(int openingPrice, int closingPrice, int maxPrice, int minPrice, String date) {
        this.openingPrice = openingPrice;
        this.closingPrice = closingPrice;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.date = date;
    }

    //EFFECTS: Returns the maximum price the stock reached for the day.
    public int getMaxPrice() {
        return maxPrice;
    }

    //EFFECTS: Returns the minimum price the stock reached for the day.
    public int getMinPrice() {
        return minPrice;
    }

    public int getOpeningPrice() {
        return openingPrice;
    }

    public int getClosingPrice() {
        return closingPrice;
    }

    public String getDate() {
        return date;
    }

    public boolean setMaxPrice(int maxPrice) {
        if (maxPrice > this.maxPrice) {
            this.maxPrice = maxPrice;
            return true;
        } else {
            return false;
        }

    }

    public boolean setMinPrice(int minPrice) {
        if (minPrice < this.minPrice) {
            this.minPrice = minPrice;
            return true;
        } else {
            return false;
        }
    }

    public void setOpeningPrice(int openingPrice) {
        this.openingPrice = openingPrice;
    }

    public void setClosingPrice(int closingPrice) {
        this.closingPrice = closingPrice;
    }

    public void setDate(String date) {
        this.date = date;
    }



}
