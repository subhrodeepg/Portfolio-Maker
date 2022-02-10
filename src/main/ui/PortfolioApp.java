package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class PortfolioApp {
    private final PortfolioList portfolioList = new PortfolioList(new ArrayList<>());
    private Scanner input;


    public PortfolioApp() {
        runPortfolio();
    }

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
    }

    private void processCommand(String command) {
        if (command.equals("1")) {
            createPortfolio();
        } else if (command.equals("2")) {
            addStock();
        } else if (command.equals("3")) {
            deleteStock();
        } else if (command.equals("4")) {
            viewPortfolios();
        } else {
            System.out.println("Invalid input. Please try again.");
        }
    }

    private void viewPortfolios() {
        System.out.println(portfolioList.displayAllPortfoliosAndStocks());
    }

    private void deleteStock() {
        System.out.println("\nWhich portfolio do you want to add the stock to: ");
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

    @SuppressWarnings("methodlength")
    private void addStock() {
        System.out.println("\nWhich portfolio do you want to add the stock to: ");
        System.out.println("\n" + portfolioList.getAllCategories());
        System.out.println("\n Enter a number (Enter 0 to go back): ");
        int command;

        command = input.nextInt();
        if (command == 0) {
            return;
        }
        System.out.println("Enter a ticker symbol for a stock which will be include in the portfolio: ");
        String stockTicker = input.next();
        System.out.println("Enter the last opening price: ");
        int openingPrice = input.nextInt();
        System.out.println("Enter the last closing price: ");
        int closingPrice = input.nextInt();
        System.out.println("Enter the maximum price from the previous trading day: ");
        int maxPrice = input.nextInt();
        System.out.println("Enter the minimum price from the previous trading day: ");
        int minPrice = input.nextInt();
        System.out.println("Enter the date for the previous trading day (MM/DD/YYYY): ");
        String date = input.next();

        DailyData newDailyData = new DailyData(openingPrice,closingPrice, maxPrice, minPrice,date);
        Stock newStock = new Stock(new ArrayList<>(), stockTicker);
        newStock.addDailyData(newDailyData);
        Portfolio existingPortfolio = portfolioList.getPortfolio(command - 1);
        existingPortfolio.addStock(newStock);

        if (existingPortfolio.addStock(newStock)) {
            System.out.println("\nStock has been added successfully!");
        } else {
            System.out.println("\nStock cannot be added. Another stock with the same ticker exists. Please try again.");
        }
    }

    private void createPortfolio() {
        System.out.println("Enter the category for the portfolio: ");
        String category = input.next();
        System.out.println("Enter a ticker symbol for a stock which will be include in the portfolio: ");
        String stockTicker = input.next();
        System.out.println("Enter the last opening price: ");
        int openingPrice = input.nextInt();
        System.out.println("Enter the last closing price: ");
        int closingPrice = input.nextInt();
        System.out.println("Enter the maximum price from the previous trading day: ");
        int maxPrice = input.nextInt();
        System.out.println("Enter the maximum price from the previous trading day: ");
        int minPrice = input.nextInt();
        System.out.println("Enter the date for the previous trading day (MM/DD/YYYY): ");
        String date = input.next();

        DailyData newDailyData = new DailyData(openingPrice,closingPrice, maxPrice, minPrice,date);
        Stock newStock = new Stock(new ArrayList<>(), stockTicker);
        newStock.addDailyData(newDailyData);
        Portfolio newPortfolio = new Portfolio(new ArrayList<>(), category);
        newPortfolio.addStock(newStock);

        portfolioList.addPortfolio(newPortfolio);
    }

    private void displayOptions() {
        System.out.println("\nHello!");
        System.out.println("\nSelect an option from the list below:");
        System.out.println("\n1. Create a new portfolio.");
        System.out.println("\n2. Add a stock to an existing portfolio.");
        System.out.println("\n3. Delete a stock from an existing portfolio.");
        System.out.println("\n4. View all portfolios and stocks.");
        System.out.println("\nEnter here (enter 0 to exit): ");
    }

    private void init() {
        DailyData feb092022DailyData = new DailyData(100, 175, 180, 90, "02/09/2022");
        Stock aaplStock = new Stock(new ArrayList<>(), "AAPL");
        aaplStock.addDailyData(feb092022DailyData);
        Portfolio technologyPortfolio = new Portfolio(new ArrayList<>(), "technology");
        technologyPortfolio.addStock(aaplStock);

        portfolioList.addPortfolio(technologyPortfolio);
        input = new Scanner(System.in);

    }


}
