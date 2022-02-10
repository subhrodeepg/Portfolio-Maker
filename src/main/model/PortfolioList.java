package model;

import java.util.ArrayList;
import java.util.Objects;

public class PortfolioList {
    private final ArrayList<Portfolio> portfolioList;

    public PortfolioList(ArrayList<Portfolio> portfolioList) {
        this.portfolioList = portfolioList;
    }

    public boolean addPortfolio(Portfolio portfolioToAdd) {
        if (this.isContainsPortfolio(portfolioToAdd.getCategory())) {
            return false;
        } else {
            return portfolioList.add(portfolioToAdd);
        }
    }

    public boolean deletePortfolio(String categoryToDelete) {
        int i = 0;
        for (Portfolio portfolio : portfolioList) {
            if (Objects.equals(portfolio.getCategory(), categoryToDelete)) {
                portfolioList.remove(i);
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean isContainsPortfolio(String category) {
        for (Portfolio portfolio : portfolioList) {
            if (Objects.equals(portfolio.getCategory(), category)) {
                return true;
            }
        }
        return false;
    }

    public String displayAllPortfoliosAndStocks() {
        String[] result = new String[portfolioList.size()];
        int i = 0;
        for (Portfolio portfolio : portfolioList) {
            result[i] = portfolio.getAllStockTickers();
            i++;
        }
        return String.join("\n", result);
    }

    public int portfolioLength() {
        return portfolioList.size();
    }

}
