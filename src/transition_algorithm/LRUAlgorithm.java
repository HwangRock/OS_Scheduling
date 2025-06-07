package transition_algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUAlgorithm implements TransitionStrategy {

    private final int capacity;
    private int pageFaultCount = 0;
    private int replacementCount = 0;

    private LinkedHashMap<Integer, Integer> cache;

    public LRUAlgorithm(int capacity) {
        this.capacity = capacity;
        this.cache = createCache();
    }

    private LinkedHashMap<Integer, Integer> createCache() {
        return new LinkedHashMap<>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                if (size() > capacity) {
                    replacementCount++;
                    return true;
                }
                return false;
            }
        };
    }

    @Override
    public void accessPage(int pageNumber) {
        if (!cache.containsKey(pageNumber)) {
            pageFaultCount++;
        }
        cache.put(pageNumber, 1);
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
        cache = createCache();
        pageFaultCount = 0;
        replacementCount = 0;
    }
}
