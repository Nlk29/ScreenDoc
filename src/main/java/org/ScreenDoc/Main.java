package org.ScreenDoc;


import jdk.nashorn.internal.ir.SetSplitState;

public class Main
{
    static ConfigScreen configScreen;

    static StateManager stateManager;

    public static void main(String[] args)
    {
        stateManager = new StateManager();

        WelcomeSplash splash = new WelcomeSplash();

        configScreen = new ConfigScreen(stateManager);

        stateManager.configScreen = configScreen;

        // Waiting
        try
        {
            Thread.sleep(200);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }

        splash.setVisible(false);

        stateManager.SetState(0);
    }

}