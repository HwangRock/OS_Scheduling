package transition_algorithm.matrix;

import transition_algorithm.TransitionStrategy;

public class HitTrackingDecorator extends Decorator {
    private int hitCount = 0;

    public HitTrackingDecorator(TransitionStrategy strategy) {
        super(strategy);
    }

    @Override
    public void accessPage(int pageNumber) {
        int before = strategy.getPageFaultCount();
        super.accessPage(pageNumber);
        int after = strategy.getPageFaultCount();

        if (before == after) hitCount++;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void reset() {
        super.reset();
        hitCount = 0;
    }
}
