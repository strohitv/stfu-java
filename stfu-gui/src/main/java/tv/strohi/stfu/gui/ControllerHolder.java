package tv.strohi.stfu.gui;

import tv.strohi.stfu.gui.scenes.Controller;

public class ControllerHolder {
    private Controller holder = null;

    public Controller getHolder() {
        return holder;
    }

    public void setHolder(Controller holder) {
        this.holder = holder;
    }
}
