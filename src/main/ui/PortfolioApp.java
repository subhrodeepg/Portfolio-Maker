package ui;

import model.*;
import persistence.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

//portfolio console application
public class PortfolioApp {
    private static final String filePath = "./data/portfolioList.json";
    private PortfolioList portfolioList = new PortfolioList(new ArrayList<>());
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: Runs the portfolio application
    public PortfolioApp() {
        jsonWriter = new JsonWriter(filePath);
        jsonReader = new JsonReader(filePath);
        runPortfolio();
    }

    //MODIFIES: this
    //EFFECTS: Takes user input and processes it
    private void runPortfolio() {
        boolean keepRunning = true;

        init();

        String command;
        while (keepRunning) {
            displayOptions();
            command = input.next();

            if (command.equals("0")) {
                keepRunning = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThank you!");
    }

    //MODIFIES: this
    //EFFECTS: Calls the proper function according to user input
    private void processCommand(String command) {
        if (command.equals("1")) {
            createPortfolio();
        } else if (command.equals("2")) {
            addStock();
        } else if (command.equals("3")) {
            deleteStock();
        } else if (command.equals("4")) {
            viewPortfolios();
        } else if (command.equals("5")) {
            savePortfolioList();
        } else if (command.equals("6")) {
            loadPortfolioList();
        } else {
            System.out.println("Invalid input. Please try again.");
        }
    }

    //EFFECTS: Displays all the current portfolios and their stocks
    private void viewPortfolios() {
        System.out.println(portfolioList.displayAllPortfoliosAndStocks());
    }

    //MODIFIES: this
    //EFFECTS: Deletes a stock from a portfolio, if the stock exists. Or returns error message.
    private void deleteStock() {
        System.out.println("\nWhich portfolio do you want to delete the stock from: ");
        System.out.println("\n" + portfolioList.getAllCategories());
        System.out.println("\n Enter a number (Enter 0 to go back): ");
        int command;

        command = input.nextInt();
        if (command == 0) {
            return;
        }
        System.out.println("Enter a ticker symbol for a stock which will be deleted: ");
        String stockTicker = input.next();

        Portfolio existingPortfolio = portfolioList.getPortfolio(command - 1);

        if (existingPortfolio.deleteStockUsingTicker(stockTicker)) {
            System.out.println("\nStock has been deleted successfully!");
        } else {
            System.out.println("\nStock was not deleted.");
        }
    }

    //MODIFIES: this
    //EFFECTS: Creates a new stock in an existing portfolio, if the stock doesn't exist. Or returns error message.
    private void addStock() {
        System.out.println("\nWhich portfolio do you want to add the stock to: ");
        System.out.println("\n" + portfolioList.getAllCategories());
        System.out.println("\n Enter a number (Enter 0 to go back): ");
        int command;

        command = input.nextInt();
        if (command == 0) {
            return;
        }

        Stock newStock = createStock();
        Portfolio existingPortfolio = portfolioList.getPortfolio(command - 1);

        if (existingPortfolio.addStock(newStock)) {
            System.out.println("\nStock has been added successfully!");
        } else {
            System.out.println("\nStock cannot be added. Another stock with the same ticker exists. Please try again.");
        }
    }

    //MODIFIES: this
    //EFFECTS: Creates a new portfolio if the category doesn't exist. Or returns error message.
    private void createPortfolio() {
        System.out.println("Enter the category for the portfolio: ");
        String category = input.next();
        category = category.toLowerCase(Locale.ROOT);
        if (portfolioList.isContainsPortfolio(category)) {
            System.out.println("Portfolio cannot be created. A portfolio with the same category exists.");
            return;
        }
        Stock newStock = createStock();
        Portfolio newPortfolio = new Portfolio(new ArrayList<>(), category);
        newPortfolio.addStock(newStock);
        portfolioList.addPortfolio(newPortfolio);
        System.out.println("Portfolio has been created successfully!");
    }

    //MODIFIES: this
    //EFFECTS: Helper function to create a new stock.
    private Stock createStock() {
        System.out.println("Enter a ticker symbol for a stock which will be include in the portfolio: ");
        String stockTicker = input.next();
        System.out.println("Enter the last opening price: ");
        double openingPrice = input.nextDouble();
        System.out.println("Enter the last closing price: ");
        double closingPrice = input.nextDouble();
        System.out.println("Enter the maximum price from the previous trading day: ");
        double maxPrice = input.nextDouble();
        System.out.println("Enter the minimum price from the previous trading day: ");
        double minPrice = input.nextDouble();
        System.out.println("Enter the date for the previous trading day (MM/DD/YYYY): ");
        String date = input.next();

        DailyData newDailyData = new DailyData(openingPrice,closingPrice, maxPrice, minPrice,date);
        Stock newStock = new Stock(new ArrayList<>(), stockTicker);
        newStock.addDailyData(newDailyData);
        return newStock;
    }

    //EFFECTS: Displays the options for the user.
    private void displayOptions() {
        System.out.println("\nHello!");
        System.out.println("\nSelect an option from the list below:");
        System.out.println("\n1. Create a new portfolio.");
        System.out.println("\n2. Add a stock to an existing portfolio.");
        System.out.println("\n3. Delete a stock from an existing portfolio.");
        System.out.println("\n4. View all portfolios and stocks.");
        System.out.println("\n5. Save all portfolios and stocks.");
        System.out.println("\n6. Load all portfolios and stocks.");
        System.out.println("\nEnter here (enter 0 to exit): ");
    }

    //EFFECTS: Initializes a stock portfolio list with a technology portfolio containing AAPL stock
    private void init() {
        DailyData feb092022DailyData = new DailyData(100, 175, 180, 90, "02/09/2022");
        Stock aaplStock = new Stock(new ArrayList<>(), "AAPL");
        aaplStock.addDailyData(feb092022DailyData);
        Portfolio technologyPortfolio = new Portfolio(new ArrayList<>(), "technology");
        technologyPortfolio.addStock(aaplStock);

        portfolioList.addPortfolio(technologyPortfolio);
        input = new Scanner(System.in);

    }

    //This class was modeled after JsonSerializationDemo, the code can be found here:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: saves the portfolio list to file
    private void savePortfolioList() {
        try {
            jsonWriter.open();
            jsonWriter.write(portfolioList);
            jsonWriter.close();
            System.out.println("Saved portfolios to " + filePath);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + filePath);
        }
    }

    //This class was modeled after JsonSerializationDemo, the code can be found here:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    //EFFECTS: loads portfolio list from json file
    private void loadPortfolioList() {
        try {
            portfolioList = jsonReader.read();
            System.out.println("Loaded portfolios from " + filePath);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + filePath);
        }
    }


}
