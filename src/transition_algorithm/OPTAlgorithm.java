package transition_algorithm;

import java.util.*;

public class OPTAlgorithm implements TransitionStrategy {

    private final int capacity;
    private int[] futureAccesses;
    private int currentIndex;
    private int pageFaultCount;
    private int replacementCount;
    private List<Integer> cache;

    public OPTAlgorithm(int capacity) {
        this.capacity = capacity;
        reset();
    }

    public void setFutureAccesses(int[] accesses) {
        this.futureAccesses = accesses;
        this.currentIndex = 0;
    }

    @Override
    public void accessPage(int pageNumber) {
        if (cache.contains(pageNumber)) {
            currentIndex++;
            return;
        }

        pageFaultCount++;

        if (cache.size() < capacity) {
            cache.add(pageNumber);
            currentIndex++;
            return;
        }

        int farthest = -1;
        int idxToReplace = -1;

        for (int i = 0; i < cache.size(); i++) {
            int page = cache.get(i);
            boolean found = false;

            for (int j = currentIndex + 1; j < futureAccesses.length; j++) {
                if (futureAccesses[j] == page) {
                    if (j > farthest) {
                        farthest = j;
                        idxToReplace = i;
                    }
                    found = true;
                    break;
                }
            }

            if (!found) {
                idxToReplace = i;
                break;
            }
        }

        cache.set(idxToReplace, pageNumber);
        replacementCount++;
        currentIndex++;
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
        this.cache = new ArrayList<>();
        this.futureAccesses = null;
        this.currentIndex = 0;
        this.pageFaultCount = 0;
        this.replacementCount = 0;
    }
}
