package transition_algorithm;

public interface TransitionStrategy {
    void accessPage(int pageNumber);
    int getPageFaultCount();
    int getReplacementCount();
    void reset();
}
