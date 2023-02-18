package deque;

import java.util.Comparator;

public class DequeSizeComparator implements Comparator<ArrayDeque> {
    @Override
    public int compare(ArrayDeque d1, ArrayDeque d2) {
        return d1.size() - d2.size();
    }
}
