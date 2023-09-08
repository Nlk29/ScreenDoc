package org.ScreenDoc;

public class StateManager
{
     ConfigScreen configScreen;

    public void SetState(int state)
    {
        switch(state)
        {
            case 0:
                configScreen.setVisible(true);
                break;
            default:
                throw new IndexOutOfBoundsException("invalid state");
        }
    }
}
