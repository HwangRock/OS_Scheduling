package transition_algorithm;

public class PageTransition {
    private final TransitionStrategy strategy;

    public PageTransition(TransitionStrategy strategy) {
        this.strategy = strategy;
    }

    public void run(int[] pageSequence) {
        for (int page : pageSequence) {
            strategy.accessPage(page);
        }
    }

    public void printResult() {
        System.out.println("총 페이지 폴트: " + strategy.getPageFaultCount());
        System.out.println("총 교체 횟수: " + strategy.getReplacementCount());
    }

    public void reset() {
        strategy.reset();
    }
}
