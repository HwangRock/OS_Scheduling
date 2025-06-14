package transition_algorithm;

import java.util.*;

public class CLOCKAlgorithm implements TransitionStrategy {

    private static class Page {
        int number;
        boolean referenced;

        Page(int number) {
            this.number = number;
            this.referenced = true;
        }
    }

    private final int capacity;
    private int pageFaultCount = 0;
    private int replacementCount = 0;

    private List<Page> buffer;
    private Map<Integer, Page> pageMap;
    private int hand; // 시계 바늘 위치

    public CLOCKAlgorithm(int capacity) {
        this.capacity = capacity;
        reset();
    }

    @Override
    public void accessPage(int pageNumber) {
        if (capacity == 0) return;

        // 캐시에 존재 → R 비트만 갱신
        if (pageMap.containsKey(pageNumber)) {
            Page page = pageMap.get(pageNumber);
            page.referenced = true;
            return;
        }

        pageFaultCount++;

        if (buffer.size() < capacity) {
            Page newPage = new Page(pageNumber);
            buffer.add(newPage);
            pageMap.put(pageNumber, newPage);
            return;
        }

        // 교체 대상 찾기 (Clock 방식)
        while (true) {
            Page current = buffer.get(hand);
            if (!current.referenced) {
                pageMap.remove(current.number);
                Page newPage = new Page(pageNumber);
                buffer.set(hand, newPage);
                pageMap.put(pageNumber, newPage);
                replacementCount++;
                hand = (hand + 1) % capacity;
                break;
            } else {
                current.referenced = false;
                hand = (hand + 1) % capacity;
            }
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
        buffer = new ArrayList<>(capacity);
        pageMap = new HashMap<>();
        hand = 0;
    }
}
