package heap;

import java.util.*;

public class SmallestRangeCoveringElementsFromKLists {

    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Element> minHeap = new PriorityQueue<>();

        int maxVal = Integer.MIN_VALUE;
        int rangeStart = 0;
        int rangeEnd = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            int value = nums.get(i).get(0);
            Element element = new Element(value, i, 0);
            minHeap.add(element);
            if (value > maxVal) {
                maxVal = value;
            }
        }

        while (minHeap.size() == nums.size()) {
            Element minElement = minHeap.poll();
            int minVal = minElement.value;
            // update range
            if (maxVal - minVal < rangeEnd - rangeStart) {
                rangeStart = minVal;
                rangeEnd = maxVal;
            }
            int nextIndex = minElement.elementIndex + 1;
            List<Integer> currList = nums.get(minElement.listIndex);
            int sizeOfCurrentList = currList.size();
            if (nextIndex < sizeOfCurrentList) {
                int nextVal = currList.get(nextIndex);
                Element nextElement = new Element(nextVal, minElement.listIndex, nextIndex);
                minHeap.add(nextElement);
                if (nextVal > maxVal) {
                    maxVal = nextVal;
                }
            } else {
                break;
            }
        }

        return new int[]{rangeStart, rangeEnd};
    }

    static class Element implements Comparable<Element> {
        Integer value;
        Integer listIndex;
        Integer elementIndex;

        public Element(Integer value, Integer listIndex, Integer elementIndex) {
            this.value = value;
            this.listIndex = listIndex;
            this.elementIndex = elementIndex;
        }

        @Override
        public int compareTo(Element o) {
            return Integer.compare(this.value, o.value);
        }
    }
}
