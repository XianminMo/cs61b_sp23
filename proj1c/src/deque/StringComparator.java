package deque;

import java.util.Comparator;

public class StringComparator implements Comparator<String> {
    @Override
    public int compare(String d1, String d2) {
        return d1.compareTo(d2);
    }
}
