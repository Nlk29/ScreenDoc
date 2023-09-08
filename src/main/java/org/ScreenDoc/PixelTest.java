package org.ScreenDoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;

public class PixelTest extends JFrame
{
    Color colors[] = {Color.BLACK, Color.WHITE, Color.RED, Color.GREEN, Color.BLUE};

    boolean displayColors[];

    int currentColor = 0;


    void DisplayColor()
    {
        if(currentColor > colors.length - 1)
        {
            setCursor(DEFAULT_CURSOR);
            dispose();
            return;
        }

        // Skipping disabled colors
        if(!displayColors[currentColor])
        {
            currentColor++;
        }

        getContentPane().setBackground(colors[currentColor]);
        currentColor++;
    }

    PixelTest(boolean[] displayColors)
    {
        this.displayColors = displayColors;

        // Hiding cursor
        Point blankPoint = new Point(0, 0);
        Cursor blankCursor = getToolkit().createCustomCursor(new java.awt.image.BufferedImage(1, 1, java.awt.image.BufferedImage.TYPE_INT_ARGB), blankPoint, "blankCursor");
        setCursor(blankCursor);

        // Making fullscreen window
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        
        DisplayColor();

        addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
                    // Close the window when the spacebar is pressed
                    dispose();
                }

                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                {
                    // Close the window when the spacebar is pressed
                    DisplayColor();
                }
            }
        });
    }

    PixelTest()
    {
        this.displayColors = new boolean[]{true, true, true, true, true};

        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        DisplayColor();

        addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
                    // Close the window when the spacebar is pressed
                    dispose();
                }

                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                {
                    // Close the window when the spacebar is pressed
                    DisplayColor();
                }
            }
        });
    }
}
