package model;

import exceptions.CategoryExistsException;
import org.json.JSONArray;
import org.json.JSONObject;

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
    public void addPortfolio(Portfolio portfolioToAdd) {
        portfolioList.add(portfolioToAdd);
        EventLog.getInstance().logEvent(new Event("New Portfolio has been created."));
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
    public void isContainsPortfolio(String category) throws CategoryExistsException {
        for (Portfolio portfolio : portfolioList) {
            if (Objects.equals(portfolio.getCategory(), category)) {
                throw new CategoryExistsException();
            }
        }
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

    //This class was modeled after JsonSerializationDemo, the code can be found here:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    //MODIFIES: this
    //EFFECTS: Initializes the iteration for creating a json object of portfolio list

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", "portfolioList");
        json.put("portfolios", portfoliosToJson());
        return json;
    }

    //This class was modeled after JsonSerializationDemo, the code can be found here:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    //MODIFIES: this
    //EFFECTS: Returns a json object from the portfolio object
    private JSONArray portfoliosToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Portfolio portfolio : portfolioList) {
            jsonArray.put(portfolio.toJson());
        }

        return jsonArray;
    }

}
