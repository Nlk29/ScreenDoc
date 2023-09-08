package org.ScreenDoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PixelRepair extends JFrame
{
    boolean flash = true;
    int flashCount = 0;
    Color[] colors;

    public float speed;

    private Timer timer;

    PixelRepair(float speed, Color[] colors) {
        this.colors = colors;
        this.speed = speed;
        setSize(150, 110);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Add a WindowListener to handle window events
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowIconified(WindowEvent e) {
                // Prevent minimizing
                setExtendedState(JFrame.NORMAL);
            }

            @Override
            public void windowActivated(WindowEvent e) {
                // Make the frame always on top when activated
                setAlwaysOnTop(true);
                flash = true;
                flashCount = 0;
                startFlashing();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                // Disable always on top when deactivated
                setAlwaysOnTop(false);
                flash = false;
                stopFlashing();
            }
        });

        setVisible(true);
    }

    private void startFlashing() {
        int delay = (int) (60 / speed) * 10;
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().setBackground(colors[flashCount % colors.length]);
                repaint();
                flashCount++;
            }
        });
        timer.start();
    }

    private void stopFlashing() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
    }
}
