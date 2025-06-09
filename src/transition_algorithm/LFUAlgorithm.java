package transition_algorithm;

import java.util.*;

public class LFUAlgorithm implements TransitionStrategy {

    private final int capacity;
    private int pageFaultCount;
    private int replacementCount;

    private Map<Integer, Integer> pageToFreq;
    private Map<Integer, LinkedHashSet<Integer>> freqToPages;
    private int minFreq;

    public LFUAlgorithm(int capacity) {
        this.capacity = capacity;
        reset();
    }

    @Override
    public void accessPage(int pageNumber) {
        if (capacity == 0) return;

        if (pageToFreq.containsKey(pageNumber)) {
            int freq = pageToFreq.get(pageNumber);
            pageToFreq.put(pageNumber, freq + 1);

            freqToPages.get(freq).remove(pageNumber);
            if (freqToPages.get(freq).isEmpty()) {
                freqToPages.remove(freq);
                if (minFreq == freq) minFreq++;
            }

            freqToPages.computeIfAbsent(freq + 1, k -> new LinkedHashSet<>()).add(pageNumber);
        }
        else {
            pageFaultCount++;

            if (pageToFreq.size() == capacity) {
                LinkedHashSet<Integer> lfuPages = freqToPages.get(minFreq);
                int evict = lfuPages.iterator().next(); // 가장 오래된 페이지
                lfuPages.remove(evict);
                if (lfuPages.isEmpty()) {
                    freqToPages.remove(minFreq);
                }
                pageToFreq.remove(evict);
                replacementCount++;
            }

            pageToFreq.put(pageNumber, 1);
            freqToPages.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(pageNumber);
            minFreq = 1; 
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
        minFreq = 0;
        pageToFreq = new HashMap<>();
        freqToPages = new HashMap<>();
    }
}
