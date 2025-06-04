package transition_algorithm.matrix;

import transition_algorithm.TransitionStrategy;

public abstract class Decorator implements TransitionStrategy {
    protected final TransitionStrategy strategy;

    public Decorator(TransitionStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void accessPage(int pageNumber) {
        strategy.accessPage(pageNumber);
    }

    @Override
    public int getPageFaultCount() {
        return strategy.getPageFaultCount();
    }

    @Override
    public int getReplacementCount() {
        return strategy.getReplacementCount();
    }

    @Override
    public void reset() {
        strategy.reset();
    }
}
