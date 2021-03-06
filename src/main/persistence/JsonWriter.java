package persistence;

import model.Event;
import model.EventLog;
import model.PortfolioList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//This class was modeled after JsonSerializationDemo, the code can be found here:
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Writes portfolio list to json file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of portfolio to file
    public void write(PortfolioList portfolioList) {
        JSONObject json = portfolioList.toJson();
        saveToFile(json.toString(TAB));
        EventLog.getInstance().logEvent(new Event("Portfolios and Stocks have been saved to a file."));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
