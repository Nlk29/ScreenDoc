package org.ScreenDoc;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.Style;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class ConfigScreen extends JFrame implements ActionListener, ChangeListener
{
    static boolean pixelTestEnable = true;
    static boolean pixelRepairEnable = true;
    static boolean monitorQualityTestEnable = false;

    int state = 0;

    private JPanel panel;

    private JLabel title;


    // Main Buttons

    private JButton pixelTestButton;
    private JButton repairButton;
    private JButton monitorQualityTestButton;


    // Pixel test config

    PixelTest test;

    private JLabel selectColorsLabel;

    private JCheckBox testBlackCheck;
    private JCheckBox testWhiteCheck;
    private JCheckBox testRedCheck;
    private JCheckBox testBlueCheck;
    private JCheckBox testGreenCheck;

    private JButton pixelTestStartButton;

    private boolean testBlackEnable = true;
    private boolean testWhiteEnable = true;
    private boolean testRedEnable = true;
    private boolean testBlueEnable = true;
    private boolean testGreenEnable = true;


    // Pixel repair config

    PixelRepair repair;

    JSlider repairSpeedSlider;
    JLabel repairSpeedLabel;
    JButton repairStartButton;

    Color[][] repairColors = {
            {Color.RED, Color.GREEN, Color.BLUE},
            {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.CYAN},
            {Color.YELLOW, Color.MAGENTA, Color.CYAN},
            {Color.WHITE, Color.BLACK},
            {Color.WHITE, Color.BLACK, Color.RED, Color.GREEN, Color.BLUE},
            {Color.WHITE, Color.BLACK, Color.YELLOW, Color.MAGENTA, Color.CYAN},
            {Color.WHITE, Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.CYAN},
    };

    JRadioButton color1;
    JRadioButton color2;
    JRadioButton color3;
    JRadioButton color4;
    JRadioButton color5;
    JRadioButton color6;
    JRadioButton color7;
    ButtonGroup selectRepairColors;

    float repairSpeed = 3f;

    Font titleFont = new Font("Ariel", Font.BOLD,20);

    ConfigScreen(StateManager stateManager)
    {

        // Using System theme
        String  style;
        style = UIManager.getSystemLookAndFeelClassName();
        try
        {
            UIManager.setLookAndFeel(style);
        } catch(Exception e)
        {
            style = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
        } finally
        {

        }



        setTitle("Config");
        setSize(300, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);

        // Drawing title
        title = new JLabel();
        title.setBounds(10, 10, 400, 50);
        title.setText("ScreenDoc");
        title.setFont(titleFont);
        panel.add(title);

        DrawMainMenu();

        add(panel);
        setLocationRelativeTo(null); // Center the window

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void DrawMainMenu()
    {
        // Draw main buttons

        pixelTestButton = new JButton();
        pixelTestButton.setBounds(80, 60, 120, 25);
        pixelTestButton.setText("Dead Pixel Test");
        pixelTestButton.addActionListener(this);
        pixelTestButton.setActionCommand("pixelTest");
        pixelTestButton.setEnabled(pixelTestEnable);
        panel.add(pixelTestButton);

        repairButton = new JButton();
        repairButton.setBounds(80, 90, 120, 25);
        repairButton.setText("Dead Pixel Repair");
        repairButton.addActionListener(this);
        repairButton.setActionCommand("pixelRepair");
        repairButton.setEnabled(pixelRepairEnable);
        panel.add(repairButton);

        monitorQualityTestButton = new JButton();
        monitorQualityTestButton.setBounds(80, 120, 120, 25);
        monitorQualityTestButton.setText("Test Monitor Quality");
        monitorQualityTestButton.addActionListener(this);
        monitorQualityTestButton.setActionCommand("monitorTest");
        monitorQualityTestButton.setEnabled(monitorQualityTestEnable);
        panel.add(monitorQualityTestButton);



        // Draw pixel test config

        selectColorsLabel = new JLabel();
        selectColorsLabel.setBounds(35, 150, 150, 12);
        selectColorsLabel.setText("Colors to test:\0");
        selectColorsLabel.setVisible(false);
        panel.add(selectColorsLabel);

        testBlackCheck = new JCheckBox();
        testBlackCheck.setBounds(55, 170, 120, 15);
        testBlackCheck.setText("Black");
        testBlackCheck.setSelected(testBlackEnable);
        testBlackCheck.setVisible(false);
        testBlackCheck.addActionListener(this);
        testBlackCheck.setActionCommand("testBlack");
        panel.add(testBlackCheck);

        testWhiteCheck = new JCheckBox();
        testWhiteCheck.setBounds(55, 190, 120, 15);
        testWhiteCheck.setText("White");
        testWhiteCheck.setSelected(testWhiteEnable);
        testWhiteCheck.setVisible(false);
        testWhiteCheck.addActionListener(this);
        panel.add(testWhiteCheck);

        testRedCheck = new JCheckBox();
        testRedCheck.setBounds(55, 210, 120, 15);
        testRedCheck.setText("Red");
        testRedCheck.setSelected(testRedEnable);
        testRedCheck.setVisible(false);
        testRedCheck.addActionListener(this);
        panel.add(testRedCheck);

        testGreenCheck = new JCheckBox();
        testGreenCheck.setBounds(55, 230, 120, 15);
        testGreenCheck.setText("Green");
        testGreenCheck.setSelected(testGreenEnable);
        testGreenCheck.setVisible(false);
        testGreenCheck.addActionListener(this);
        panel.add(testGreenCheck);

        testBlueCheck = new JCheckBox();
        testBlueCheck.setBounds(55, 250, 120, 15);
        testBlueCheck.setText("Blue");
        testBlueCheck.setSelected(testBlueEnable);
        testBlueCheck.setVisible(false);
        testBlueCheck.addActionListener(this);
        panel.add(testBlueCheck);

        pixelTestStartButton = new JButton();
        pixelTestStartButton.setBounds(80, 280, 120, 25);
        pixelTestStartButton.setText("Start");
        pixelTestStartButton.addActionListener(this);
        pixelTestStartButton.setActionCommand("pixelTestStart");
        pixelTestStartButton.setToolTipText("Use space for next color, esc to exit");
        pixelTestStartButton.setVisible(false);
        panel.add(pixelTestStartButton);



        // Draw pixel repair config

        repairSpeedLabel = new JLabel();
        repairSpeedLabel.setBounds(70, 155, 150, 12);
        repairSpeedLabel.setText("Speed\0");
        repairSpeedLabel.setVisible(false);
        panel.add(repairSpeedLabel);

        repairSpeedSlider = new JSlider();
        repairSpeedSlider.setBounds(70, 170, 140, 35);
        repairSpeedSlider.setMajorTickSpacing(8);
        repairSpeedSlider.setMinorTickSpacing(3);
        repairSpeedSlider.addChangeListener(this);
        repairSpeedSlider.setValue((int)(repairSpeed * 10));
        repairSpeedSlider.setPaintTicks(true);
        repairSpeedSlider.setPaintLabels(false);
        repairSpeedSlider.setVisible(false);
        panel.add(repairSpeedSlider);

        selectRepairColors = new ButtonGroup();

        color1 = new JRadioButton("Pattern 1");
        color1.setBounds(70, 210, 220, 25);
        color1.setActionCommand("0");
        color1.setVisible(false);
        selectRepairColors.add(color1);
        panel.add(color1);

        color2 = new JRadioButton("Pattern 2");
        color2.setBounds(70, 230, 220, 25);
        color2.setActionCommand("1");
        color2.setVisible(false);
        selectRepairColors.add(color2);
        panel.add(color2);

        color3 = new JRadioButton("Pattern 3");
        color3.setBounds(70, 250, 220, 25);
        color3.setActionCommand("2");
        color3.setVisible(false);
        selectRepairColors.add(color3);
        panel.add(color3);

        color4 = new JRadioButton("Pattern 4");
        color4.setBounds(70, 270, 220, 25);
        color4.setActionCommand("3");
        color4.setVisible(false);
        selectRepairColors.add(color4);
        panel.add(color4);

        color5 = new JRadioButton("Pattern 5");
        color5.setBounds(70, 290, 220, 25);
        color5.setActionCommand("4");
        color5.setVisible(false);
        selectRepairColors.add(color5);
        panel.add(color5);

        color6 = new JRadioButton("Pattern 6");
        color6.setBounds(70, 310, 220, 25);
        color6.setActionCommand("5");
        color6.setVisible(false);
        selectRepairColors.add(color6);
        panel.add(color6);

        color7 = new JRadioButton("Pattern 7");
        color7.setBounds(70, 330, 250, 25);
        color7.setActionCommand("6");
        color7.setVisible(false);
        selectRepairColors.add(color7);
        panel.add(color7);

        repairStartButton = new JButton();
        repairStartButton.setBounds(80, 360, 120, 25);
        repairStartButton.setText("Start");
        repairStartButton.addActionListener(this);
        repairStartButton.setActionCommand("pixelRepairStart");
        repairStartButton.setToolTipText("Start flashing treatment");
        repairStartButton.setVisible(false);
        panel.add(repairStartButton);

    }

    void DrawPixelTestUI()
    {
        state = 1;

        // Enabling pixel test ui

        selectColorsLabel.setVisible(true);
        testBlackCheck.setVisible(true);
        testWhiteCheck.setVisible(true);
        testRedCheck.setVisible(true);
        testBlueCheck.setVisible(true);
        testGreenCheck.setVisible(true);
        pixelTestStartButton.setVisible(true);


        // Disabling pixel repair ui

        repairSpeedSlider.setVisible(false);
        repairSpeedLabel.setVisible(false);
        repairStartButton.setVisible(false);
        color1.setVisible(false);
        color2.setVisible(false);
        color3.setVisible(false);
        color4.setVisible(false);
        color5.setVisible(false);
        color6.setVisible(false);
        color7.setVisible(false);

        panel.repaint();
    }

    void DrawPixelRepairUI()
    {
        state = 2;

        // Disabling pixel test ui

        selectColorsLabel.setVisible(false);
        testBlackCheck.setVisible(false);
        testWhiteCheck.setVisible(false);
        testRedCheck.setVisible(false);
        testBlueCheck.setVisible(false);
        testGreenCheck.setVisible(false);
        pixelTestStartButton.setVisible(false);

        
        // Enabling pixel repair ui

        repairSpeedSlider.setVisible(true);
        repairSpeedLabel.setVisible(true);
        repairStartButton.setVisible(true);
        color1.setVisible(true);
        color2.setVisible(true);
        color3.setVisible(true);
        color4.setVisible(true);
        color5.setVisible(true);
        color6.setVisible(true);
        color7.setVisible(true);

        panel.repaint();
    }

    void StartPixelTest()
    {
        JOptionPane.showMessageDialog(this,
                "The selected colors will now be displayed on your screen. You can then search for dead pixels.\n" +
                        "\n" +
                        "Space: next color\n" +
                        "Esc: exit",
                        "Instructions",
                        JOptionPane.INFORMATION_MESSAGE
                );

        boolean displayColors[] = {testBlackEnable, testWhiteEnable, testRedEnable, testGreenEnable, testBlueEnable};
        test = new PixelTest(displayColors);
    }

    void StartPixelRepair()
    {
        if(selectRepairColors.getSelection() == null)
        {
            // Playing error sound
            Runnable sound2 =
                    (Runnable)Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");

            if(sound2 != null) sound2.run();

            JOptionPane.showMessageDialog(this,
                    "Please select a flash pattern.",
                    "No pattern selected",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this,
                "You can now drag the flashing window over the malfunctioning pixels to repair them.\n" +
                        "You can also try to carefully massage the malfunctioning pixels."
                );

        repair = new PixelRepair(repairSpeed, repairColors[Integer.parseInt(selectRepairColors.getSelection().getActionCommand())]);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch(e.getActionCommand())
        {

            // State machine for tool selection
            case "pixelTest":
                if(state != 1)
                {
                    DrawPixelTestUI();
                }
                break;
            case "pixelRepair":
                if(state != 2)
                {
                    DrawPixelRepairUI();
                }
                break;

            // Updating pixel test checkboxes
            case "testBlack":
                testBlackEnable = testBlackCheck.isSelected();
                break;
            case "testWhite":
                testWhiteEnable = testWhiteCheck.isSelected();
                break;
            case "testRed":
                testRedEnable = testRedCheck.isSelected();
                break;
            case "testBlue":
                testBlueEnable = testBlueCheck.isSelected();
                break;

            // Peripheral
            case "pixelTestStart":
                StartPixelTest();
                break;
            case "pixelRepairStart":
                StartPixelRepair();
                break;
            default:
                throw new StringIndexOutOfBoundsException("Action Command " + e.getActionCommand() + " invalid");
        }
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        repairSpeed = ((float)repairSpeedSlider.getValue()) / 10f;
        if(repair != null)
        {
            repair.speed = repairSpeed;
        }
    }
}
