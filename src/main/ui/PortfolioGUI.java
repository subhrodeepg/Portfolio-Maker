package ui;

import exceptions.CategoryExistsException;
import exceptions.StockAlreadyExistsException;
import exceptions.StockDoesNotExistException;
import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

//Creates the Portfolio App GUI
public class PortfolioGUI extends JFrame {

    JPanel mainPanel;
    JPanel listPanel;
    JPanel container;
    JTextArea displayText;
    CardLayout cl;
    private PortfolioList portfolioList = new PortfolioList(new ArrayList<>());
    private static final String filePath = "./data/portfolioList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JTextField portfolioCategory;
    private JButtonNew buttonOne;
    private JTextField ticker;
    private JTextField openPrice;
    private JTextField closePrice;
    private JTextField minPrice;
    private JTextField maxPrice;
    private JTextField date;
    private JButtonNew buttonTwo;
    private String action;


    //EFFECTS: Runs the portfolio GUI
    public PortfolioGUI() throws IOException {
        super("Portfolio App");
        init();
        initializeGraphics();
    }

    //MODIFIES: this
    //EFFECTS: Initializes the graphics
    private void init() {
        jsonWriter = new JsonWriter(filePath);
        jsonReader = new JsonReader(filePath);

        DailyData feb092022DailyData = new DailyData(100, 175, 180, 90, "02/09/2022");
        Stock aaplStock = new Stock(new ArrayList<>(), "AAPL");
        aaplStock.addDailyData(feb092022DailyData);
        Portfolio technologyPortfolio = new Portfolio(new ArrayList<>(), "technology");
        technologyPortfolio.addStockToPortfolio(aaplStock);

        portfolioList.addPortfolio(technologyPortfolio);
    }

    //MODIFIES: this
    //EFFECTS: Adds image and welcome message
    private void initializeGraphics() throws IOException {
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog(EventLog.getInstance());
                System.exit(0);
            }
        });

        createMainPanel();
        setListPanel();
        createFields();

        getContainer();
        add(container);

        createPortfolioListButtons();

        setVisible(true);
    }


    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }

    //MODIFIES: this
    //EFFECTS: Creates the container and adds the 2 main panels
    public void getContainer() {
        container = new JPanel();
        cl = new CardLayout();
        container.setLayout(cl);
        container.add(mainPanel, "MainPanel");
        container.add(listPanel, "ListPanel");
    }

    //MODIFIES: this
    //EFFECTS: Creates the list panel
    private void setListPanel() {
        listPanel = new JPanel();
        listPanel.setLayout(null);
        listPanel.setBounds(0, 0, 400, 400);
        listPanel.setBackground(new Color(21, 29, 59));

        displayText = new JTextArea();
        displayText.setEditable(false);
        displayText.setText(portfolioList.displayAllPortfoliosAndStocks());

        JScrollPane portfolioListDisplay = new JScrollPane(displayText);
        portfolioListDisplay.setBounds(100, 100, 300, 300);
        portfolioListDisplay.setBackground(Color.WHITE);
        listPanel.add(portfolioListDisplay);
    }

    //MODIFIES: this
    //EFFECTS: Creates the fields
    private void createFields() {
        createPortfolioCategoryField();
        createButtonOne();
        createTickerField();
        createPriceFields();
        createAddStockButton();
    }

    //MODIFIES: this
    //EFFECTS:: Creates the portfolio category field
    private void createPortfolioCategoryField() {
        portfolioCategory = new JTextFieldNew("Portfolio");
        portfolioCategory.setLocation(420, 100);
        listPanel.add(portfolioCategory);
        portfolioCategory.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: Creates the main button
    private void createButtonOne() {
        buttonOne = new JButtonNew("");
        buttonOne.setSize(150, 40);
        buttonOne.setLocation(420, 150);
        listPanel.add(buttonOne);
        buttonOne.setVisible(false);

        updateButtonOne();
    }

    //MODIFIES: this
    //EFFECTS: Updates the button depending on the input
    private void updateButtonOne() {
        buttonOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                switch (action) {
                    case "add portfolio":
                        updateButtonOneAddPortfolio();
                        break;
                    case "add stock":
                        updateButtonOneAddStock();
                        break;
                    default:
                        updateButtonOneDeleteStock();
                }

            }
        });

    }

    //MODIFIES: this
    //EFFECTS: Updates the button when delete stock is selected
    private void updateButtonOneDeleteStock() {
        try {
            portfolioList.getPortfolio(Integer.parseInt(portfolioCategory.getText()) - 1);

            ticker.setVisible(true);

            buttonTwo.setText("Delete Stock");
            buttonTwo.setVisible(true);
        } catch (IndexOutOfBoundsException ex) {
            popPane("Portfolio Does Not Exist!");
        }
    }

    //MODIFIES: this
    //EFFECTS: Updates the button when add stock is selected
    private void updateButtonOneAddStock() {
        try {
            portfolioList.getPortfolio(Integer.parseInt(portfolioCategory.getText()) - 1);

            ticker.setVisible(true);
            openPrice.setVisible(true);
            closePrice.setVisible(true);
            maxPrice.setVisible(true);
            minPrice.setVisible(true);
            date.setVisible(true);
            buttonTwo.setText("Add Stock");
            buttonTwo.setVisible(true);
        } catch (IndexOutOfBoundsException ex) {
            popPane("Portfolio Does Not Exist!");
        }
    }

    //MODIFIES: this
    //EFFECTS: Updates the button when add portfolio is selected
    private void updateButtonOneAddPortfolio() {
        try {
            portfolioList.isContainsPortfolio(portfolioCategory.getText());

            ticker.setVisible(true);
            openPrice.setVisible(true);
            closePrice.setVisible(true);
            maxPrice.setVisible(true);
            minPrice.setVisible(true);
            date.setVisible(true);
            buttonTwo.setText("Add Stock");
            buttonTwo.setVisible(true);
        } catch (CategoryExistsException ex) {
            popPane(ex.getMessage());
        }
    }

    //MODIFIES: this
    //EFFECTS: Creates the ticker field
    private void createTickerField() {
        ticker = new JTextFieldNew("Ticker");
        ticker.setLocation(420, 200);
        listPanel.add(ticker);
        ticker.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: Creates the stock fields
    private void createPriceFields() {
        openPrice = new JTextFieldNew("Open price");
        openPrice.setLocation(420, 250);
        listPanel.add(openPrice);
        openPrice.setVisible(false);

        closePrice = new JTextFieldNew("Close price");
        closePrice.setLocation(420, 300);
        listPanel.add(closePrice);
        closePrice.setVisible(false);

        minPrice = new JTextFieldNew("Min price");
        minPrice.setLocation(420, 350);
        listPanel.add(minPrice);
        minPrice.setVisible(false);

        maxPrice = new JTextFieldNew("Max price");
        maxPrice.setLocation(420, 400);
        listPanel.add(maxPrice);
        maxPrice.setVisible(false);

        date = new JTextFieldNew("Date");
        date.setLocation(420, 450);
        listPanel.add(date);
        date.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: Creates add stock button
    private void createAddStockButton() {
        buttonTwo = new JButtonNew("");
        buttonTwo.setBounds(420, 500, 200, 40);
        listPanel.add(buttonTwo);
        buttonTwo.setVisible(false);

        updateAddStockButton();
    }

    //MODIFIES: this
    //EFFECTS:Updates add stock button
    private void updateAddStockButton() {
        buttonTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                DailyData newDailyData;
                Stock newStock;
                switch (action) {
                    case "add portfolio":
                        addPortfolio();
                        break;
                    case "add stock":
                        // we know portfolio exists
                        addStock();
                        break;
                    default:
                        deleteStock();
                }

            }
        });
    }

    //MODIFIES: this
    //EEFFCTS: Deletes stock from portfolio
    private void deleteStock() {
        try {
            // get portfolio
            Portfolio portfolio1 =
                    portfolioList.getPortfolio(Integer.parseInt(portfolioCategory.getText()) - 1);
            // check if ticker exists inside portfolio
            portfolio1.confirmStockExists(ticker.getText());
            // delete ticker from portfolio
            portfolio1.deleteStockUsingTicker(ticker.getText());
            // confirmation
            displayText.setText(portfolioList.displayAllPortfoliosAndStocks());
            repaint();
            revalidate();
            popPane("Stock has been deleted from portfolio");
            clearFields();
        } catch (StockDoesNotExistException ex) {
            popPane(ex.getMessage());
        }
    }

    //MODIFIES: this
    //EFFECTS: Add stock to the portfolio
    private void addStock() {
        DailyData newDailyData;
        Stock newStock;
        try {
            Portfolio portfolio =
                    portfolioList.getPortfolio(Integer.parseInt(portfolioCategory.getText()) - 1);

            portfolio.confirmStockDoesNotExist(ticker.getText());
            newDailyData = new DailyData(
                    Double.parseDouble(openPrice.getText()),
                    Double.parseDouble(closePrice.getText()),
                    Double.parseDouble(maxPrice.getText()),
                    Double.parseDouble(minPrice.getText()),
                    date.getText());

            newStock = new Stock(new ArrayList<>(), ticker.getText());
            newStock.addDailyData(newDailyData);
            portfolio.addStockToPortfolio(newStock);
            // confirmation
            displayText.setText(portfolioList.displayAllPortfoliosAndStocks());
            repaint();
            revalidate();
            popPane("Stock has been added to portfolio");
            clearFields();
        } catch (StockAlreadyExistsException ex) {
            popPane(ex.getMessage());
        }
    }

    //MODIFIES: this
    //EFFECTS: Add portfolio to portfolio list
    private void addPortfolio() {
        Stock newStock;
        DailyData newDailyData;
        // portfolio creation
        // create daily data
        newDailyData = new DailyData(
                Double.parseDouble(openPrice.getText()),
                Double.parseDouble(closePrice.getText()),
                Double.parseDouble(maxPrice.getText()),
                Double.parseDouble(minPrice.getText()),
                date.getText());
        // create new stock
        newStock = new Stock(new ArrayList<>(), ticker.getText());
        // add daily data to stock
        newStock.addDailyData(newDailyData);
        // create new portfolio
        Portfolio newPortfolio = new Portfolio(new ArrayList<>(), portfolioCategory.getText());
        // add stock to portfolio
        newPortfolio.addStockToPortfolio(newStock);
        // add stock to list
        portfolioList.addPortfolio(newPortfolio);
        // confirmation
        displayText.setText(portfolioList.displayAllPortfoliosAndStocks());
        repaint();
        revalidate();
        popPane("Portfolio and stock has been added!");
        clearFields();
        return;
    }

    //MODIFIES: this
    //EFFECTS: Abstract method for pop up pane
    private void popPane(String content) {
        JOptionPane.showMessageDialog(null, content, "Stock App", JOptionPane.WARNING_MESSAGE);
    }

    //MODIFIES: this
    //EFFECTS: Creates the buttons
    private void createPortfolioListButtons() {
        createMainAddPortfolioButton();
        createMainAddStockButton();
        createMainDeleteStockButton();

        createSaveButton();
        createLoadButton();
    }

    //MODIFIES: this
    //EFFECTS: Creates main add portffolio button
    private void createMainAddPortfolioButton() {
        JButtonNew mainAddPortfolioButton = new JButtonNew("Add Portfolio");
        mainAddPortfolioButton.setLocation(100, 500);
        listPanel.add(mainAddPortfolioButton);
        mainAddPortfolioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
                portfolioCategory.setVisible(true);
                buttonOne.setText("Add Portfolio");
                buttonOne.setVisible(true);
                action = "add portfolio";
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: Creates add stock
    private void createMainAddStockButton() {
        JButtonNew mainAddStockButton = new JButtonNew("Add Stock");
        mainAddStockButton.setLocation(200, 500);
        listPanel.add(mainAddStockButton);
        mainAddStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
                portfolioCategory.setVisible(true);
                buttonOne.setText("Enter Number");
                buttonOne.setVisible(true);
                action = "add stock";
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: Creates delete stock button
    private void createMainDeleteStockButton() {
        JButtonNew mainDeleteStockButton = new JButtonNew("Delete Stock");
        mainDeleteStockButton.setLocation(300, 500);
        listPanel.add(mainDeleteStockButton);
        mainDeleteStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
                portfolioCategory.setVisible(true);
                buttonOne.setText("Enter Number");
                buttonOne.setVisible(true);
                action = "delete stock";
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: Creates save button
    private void createSaveButton() {
        JButtonNew saveBtn = new JButtonNew("Save");
        saveBtn.setBounds(100, 445, 75, 35);
        listPanel.add(saveBtn);
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePortfolioList();
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: Creates load button
    private void createLoadButton() {
        JButtonNew loadBtn = new JButtonNew("Load");
        loadBtn.setBounds(175, 445, 75, 35);
        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPortfolioList();
            }
        });
        listPanel.add(loadBtn);
    }


    //MODIFIES: this
    //EFFECTS: Resets and hides all fields
    private void clearFields() {
        portfolioCategory.setText("Portfolio");
        portfolioCategory.setVisible(false);
        buttonOne.setVisible(false);
        ticker.setText("Ticker");
        ticker.setVisible(false);
        openPrice.setText("Open price");
        openPrice.setVisible(false);
        closePrice.setText("Close price");
        closePrice.setVisible(false);
        minPrice.setText("Min price");
        minPrice.setVisible(false);
        maxPrice.setText("Max price");
        maxPrice.setVisible(false);
        date.setText("Date");
        date.setVisible(false);
        buttonTwo.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: Creates the main panel
    private void createMainPanel() throws IOException {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 600, 400);
        mainPanel.setBackground(new Color(216, 33, 72));

        try {
            BufferedImage myPicture = ImageIO.read(new File("./data/portfolio_manager.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setBounds(150, 25, 500, 500);
            mainPanel.add(picLabel);
        } catch (IOException ex) {
            System.out.println("File not found");
        }

        JLabel welcomeMsg = new JLabel("Welcome!");
        welcomeMsg.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        welcomeMsg.setBounds(350, 400, 300, 100);
        mainPanel.add(welcomeMsg);

        welcomeMsg.setForeground(Color.WHITE);

        JButtonNew portolfiosBtn = new JButtonNew("View All Portfolios");
        portolfiosBtn.setBounds(330, 500, 150, 35);

//        mainPanel.add(welcomeMsg);

        mainPanel.add(portolfiosBtn);
        containerActionListener(portolfiosBtn, "ListPanel");
    }

    //MODIFIES: this
    //EFFECTS:Abstract function for action listener
    public void containerActionListener(JButtonNew button, String page) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(container, page);
            }
        });

    }

    //MODIFIES: this
    //EFFECTS: Loads portfolio list
    private void loadPortfolioList() {
        try {
            portfolioList = jsonReader.read();
            displayText.setText(portfolioList.displayAllPortfoliosAndStocks());
            JOptionPane.showMessageDialog(null, "Loaded portfolios from " + filePath);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Unable to read from file: " + filePath);
        }
    }

    //EFFECTS: Saves portfolio list
    private void savePortfolioList() {
        try {
            jsonWriter.open();
            jsonWriter.write(portfolioList);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,"Saved portfolios to " + filePath);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to write to file: " + filePath);
        }
    }
}
