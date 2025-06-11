package transition_algorithm;

import java.util.*;

public class NURAlgorithm implements TransitionStrategy {

    private static class Page {
        int number;
        boolean R, M;

        Page(int number, boolean modified) {
            this.number = number;
            this.R = true;
            this.M = modified;
        }

        int getClassValue() {
            if (!R && !M) return 0;
            if (!R && M)  return 1;
            if (R && !M)  return 2;
            return 3;
        }
    }

    private final int capacity;
    private int pageFaultCount = 0;
    private int replacementCount = 0;

    private List<Page> cache;
    private Map<Integer, Page> pageMap;

    public NURAlgorithm(int capacity) {
        this.capacity = capacity;
        reset();
    }

    @Override
    public void accessPage(int pageNumber) {
        accessPage(pageNumber, false);
    }

    public void accessPage(int pageNumber, boolean isWrite) {
        if (capacity == 0) return;

        if (pageMap.containsKey(pageNumber)) {
            Page p = pageMap.get(pageNumber);
            p.R = true;
            if (isWrite) p.M = true;
            return;
        }

        pageFaultCount++;

        if (cache.size() == capacity) {
            Page toRemove = null;
            int minClass = 4;

            for (Page p : cache) {
                int cls = p.getClassValue();
                if (cls < minClass) {
                    minClass = cls;
                    toRemove = p;
                    if (cls == 0) break;
                }
            }

            cache.remove(toRemove);
            pageMap.remove(toRemove.number);
            replacementCount++;
        }

        Page newPage = new Page(pageNumber, isWrite);
        cache.add(newPage);
        pageMap.put(pageNumber, newPage);
    }
    
    public void resetReferencedBits() {
        for (Page p : cache) {
            p.R = false;
        }
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
        cache = new LinkedList<>();
        pageMap = new HashMap<>();
    }
}
