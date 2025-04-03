package process_state;

public interface ProcessState {
    void onDispatch();
    void onIORequest();
    void onIOComplete();
    void onTimeExpired();
}
