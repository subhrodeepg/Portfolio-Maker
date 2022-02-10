package model;

import java.util.ArrayList;
import java.util.Objects;

//PortfolioList contains a list of all the portfolios
public class PortfolioList {
    private final ArrayList<Portfolio> portfolioList;

    //EFFECTS: Sets portfolioList to portfolioList parameter. Can initialized with empty array list.
    public PortfolioList(ArrayList<Portfolio> portfolioList) {
        this.portfolioList = portfolioList;
    }

    //REQUIRES: valid portfolio
    //MODIFIES: this
    //EFFECTS: adds portfolio to portfolio list
    public boolean addPortfolio(Portfolio portfolioToAdd) {
        if (this.isContainsPortfolio(portfolioToAdd.getCategory())) {
            return false;
        } else {
            return portfolioList.add(portfolioToAdd);
        }
    }

    //MODIFIES: this
    //EFFECTS: deletes portfolio using category
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

    //EFFECTS: Returns true if portfolio is in portfolio list, using category. Or returns false.
    public boolean isContainsPortfolio(String category) {
        for (Portfolio portfolio : portfolioList) {
            if (Objects.equals(portfolio.getCategory(), category)) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: Returns string with all portfolios and their stocks
    public String displayAllPortfoliosAndStocks() {
        String[] result = new String[portfolioList.size()];
        int i = 0;
        for (Portfolio portfolio : portfolioList) {
            result[i] = portfolio.getAllStockTickers();
            i++;
        }
        return String.join("\n", result);
    }

    //EFFECTS: Returns portfolio length
    public int portfolioLength() {
        return portfolioList.size();
    }

    //EFFECTS: Returns all portfolio categories
    public String getAllCategories() {
        String[] result = new String[portfolioList.size()];
        int i = 0;
        for (Portfolio portfolio : portfolioList) {
            result[i] = Integer.toString(i + 1) + ". " + portfolio.getCategory();
            i++;
        }
        return String.join(", ", result);
    }

    //EFFECTS: Returns portfolio using zero index from portfolio list
    public Portfolio getPortfolio(Integer index) {
        return portfolioList.get(index);
    }

}
