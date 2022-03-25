package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class JTextFieldNew extends JTextField {
    private String hint;

    public JTextFieldNew(String hint) {
        this.setText(hint);
        this.hint = hint;
        this.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        this.setForeground(Color.GRAY);
        this.setSize(200, 40);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        setFocusAdapter();
    }

    private void setFocusAdapter() {
        this.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(hint)) {
                    setText("");
                    setForeground(Color.BLACK);
                } else {
                    setText(getText());
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().equals(hint) || getText().length() == 0) {
                    setText(hint);
                    setForeground(Color.GRAY);
                } else {
                    setText(getText());
                    setForeground(Color.BLACK);
                }
            }
        });
    }
}
