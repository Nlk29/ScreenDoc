package org.ScreenDoc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


@SuppressWarnings("serial")
public class WelcomeSplash extends JFrame implements ActionListener
{
    private JLabel title;

    Font titleFont = new Font("Ariel", Font.BOLD + Font.ITALIC,40);

    WelcomeSplash()
    {
        // Using System theme
        String  style;
        style = javax.swing.UIManager.getSystemLookAndFeelClassName();
        try
        {
            UIManager.setLookAndFeel(style);
        } catch(Exception e)
        {
            style = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
        } finally
        {

        }


        setTitle("Welcome");
        setSize(450, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        title = new JLabel();
        title.setBounds(100, 50, 400, 50);
        title.setText("ScreenDoc");
        title.setFont(titleFont);
        panel.add(title);

        add(panel);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        return;
    }
}
