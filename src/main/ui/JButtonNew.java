package ui;

import javax.swing.*;
import java.awt.*;

public class JButtonNew extends JButton {
    public JButtonNew(String s) {
        this.setText(s);
        this.setSize(100, 40);
        this.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.setForeground(Color.WHITE);
        this.setBackground(Color.BLUE);
        this.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.WHITE));
    }
}
