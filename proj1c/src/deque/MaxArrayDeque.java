package deque;

import java.util.Comparator;
import java.util.DuplicateFormatFlagsException;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> c;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.c = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        int i = judgeFirst();
        int max = judgeNext(i);
        while (get(i) != null) {
            if (this.c.compare(get(i), get(max)) > 0) {
                max = i;
            }
            i = judgeNext(i);
        }
        return get(max);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        int i = judgeFirst();
        int max = judgeNext(i);
        while (get(i) != null) {
            if (c.compare(get(i), get(max)) > 0) {
                max = i;
            }
            i = judgeNext(i);
        }
        return get(max);
    }

}
