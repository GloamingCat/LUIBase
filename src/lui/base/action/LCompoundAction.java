package lui.base.action;

public class LCompoundAction implements LAction {

    private final LAction a1;
    private final LAction a2;

    public LCompoundAction(LAction a1, LAction a2) {
        this.a1 = a1;
        this.a2 = a2;
    }

    @Override
    public void undo() {
        a1.undo();
        a2.undo();
    }

    @Override
    public void redo() {
        a1.redo();
        a2.redo();
    }

    @Override
    public String toString() {
        return a1 + "\n" + a2;
    }

}
