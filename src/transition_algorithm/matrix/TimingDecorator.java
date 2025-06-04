package transition_algorithm.matrix;

import transition_algorithm.TransitionStrategy;
import transition_algorithm.matrix.Decorator;

public class TimingDecorator extends Decorator {
    private long totalExecutionTime = 0;

    public TimingDecorator(TransitionStrategy strategy) {
        super(strategy);
    }

    @Override
    public void accessPage(int pageNumber) {
        long start = System.nanoTime();
        super.accessPage(pageNumber);
        long end = System.nanoTime();
        totalExecutionTime += (end - start);
    }

    public long getTotalExecutionTime() {
        return totalExecutionTime;
    }

    public double getAverageExecutionTime() {
        return totalExecutionTime / (double) Math.max(1, strategy.getReplacementCount() + strategy.getPageFaultCount());
    }
}
