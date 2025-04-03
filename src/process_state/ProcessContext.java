package process_state;

import process_state.state.Ready;

public class ProcessContext {
    private ProcessState state;

    public ProcessContext() {
        this.state = new Ready();
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

    public void dispatch() {
        state.onDispatch();
    }

    public void requestIO() {
        state.onIORequest();
    }

    public void completeIO() {
        state.onIOComplete();
    }

    public void timeExpired() {
        state.onTimeExpired();
    }
}