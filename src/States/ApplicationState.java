package States;

import Container.MyFrame;

public class ApplicationState {
    private MyFrame _frame;

    public String currentState;

    public ApplicationState() {
        this.currentState = "Playing";
    }
}
