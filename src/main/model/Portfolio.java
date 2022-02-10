package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class Portfolio {
    private final ArrayList<Stock> stockList;
    private String category;


    public Portfolio(ArrayList<Stock> stockList, String category) {
        this.stockList = stockList;
        this.category = category;
    }

    public boolean addStock(Stock stockAdd) {
        if (this.isContainsStockTicker(stockAdd.getTicker())) {
            return false;
        } else {
            return stockList.add(stockAdd);
        }
    }

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

    public String getAllStockTickers() {
        String[] result = new String[stockList.size()];
        int i = 0;
        for (Stock stock : stockList) {
            result[i] = stock.getTicker();
            i++;
        }
        return String.join(", ", result);
    }


    public boolean isContainsStockTicker(String stockTicker) {
        for (Stock stock : stockList) {
            if (Objects.equals(stock.getTicker(), stockTicker)) {
                return true;
            }
        }
        return false;
    }

    public int getStockListLength() {
        return stockList.size();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String newCategory) {
        category = newCategory;
    }
}
