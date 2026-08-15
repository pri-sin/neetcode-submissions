class MedianFinder {
    //can have two heaps, a min heap and a max heap
    //we will add numbers to max heap and min heap and frequently rebalance it to ensure
    //that minheap either has 1 more than maxheap or both are equal
    //the smaller set of numbers stay in maxheap and the larger ones in minheap
    //the peak of maxheap gives the median in case of odd numbers and 
    //the peaks can be averaged in case of even

    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }
    
    public void addNum(int num) {
        // 1. Add to the appropriate heap
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        // 2. Rebalance heaps so that:
        // maxHeap.size() == minHeap.size() OR maxHeap.size() == minHeap.size() + 1
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }
    
    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }
        // Cast to double to prevent integer division truncation
        return (double) (minHeap.peek() + maxHeap.peek()) / 2.0;
    }
}
/*
Why We Add to maxHeap in That Condition
The core idea of the two-heap approach is to split our running stream of numbers cleanly into two halves:

The smaller half of the numbers goes into the maxHeap.

The larger half of the numbers goes into the minHeap.

Because the maxHeap always stores the smaller numbers, its root (accessed via peek()) represents the largest of the small numbers.

The condition works as follows:

num <= maxHeap.peek(): If the incoming number is less than or equal to the largest number in our smaller half, it naturally belongs in the smaller half. Therefore, we add it to the maxHeap.

maxHeap.isEmpty(): If the heap has no elements yet, the first number has to go somewhere, so we safely drop it into the maxHeap to start our collection.
*/