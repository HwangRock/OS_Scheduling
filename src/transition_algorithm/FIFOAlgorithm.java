package transition_algorithm;

import java.util.*;

public class FIFOAlgorithm implements TransitionStrategy {

    private final int capacity;
    private int pageFaultCount;
    private int replacementCount;

    private Queue<Integer> queue;
    private Set<Integer> inCache;

    public FIFOAlgorithm(int capacity) {
        this.capacity = capacity;
        reset();
    }

    @Override
    public void accessPage(int pageNumber) {
        if (capacity == 0) return;

        if (inCache.contains(pageNumber)) {
            return;
        }

        pageFaultCount++;

        if (queue.size() == capacity) {
            int removed = queue.poll();
            inCache.remove(removed);
            replacementCount++;
        }

        queue.offer(pageNumber);
        inCache.add(pageNumber);
    }

    @Override
    public int getPageFaultCount() {
        return pageFaultCount;
    }

    @Override
    public int getReplacementCount() {
        return replacementCount;
    }

    @Override
    public void reset() {
        pageFaultCount = 0;
        replacementCount = 0;
        queue = new LinkedList<>();
        inCache = new HashSet<>();
    }
}
