import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements Deque<T>{

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public static void main(String[] args) {
        Deque<Integer> ad = new ArrayDeque<>();
        ad.addFirst(1);
        ad.addFirst(2);
        ad.addFirst(3);
        ad.addLast(4);
        ad.addLast(5);
        ad.addLast(6);
        System.out.println(ad.toList());
    }

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int i = judgeFirst();
        int count = 0;
        while (count < size) {
            a[count] = items[i];
            if (i == items.length - 1) {
                i = 0;
            }else {
                i += 1;
            }
            count += 1;
        }
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    private int judgeFirst() {
        int first = 0;
        if (nextFirst == items.length - 1) {
            first = 0;
        }else {
            first = nextFirst + 1;
        }
        return first;

    }

    private int judgeLast() {
        int last = 0;
        if (nextLast == 0) {
            last = items.length - 1;
        }else {
            last = nextLast - 1;
        }
        return last;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(2 * size);
        }
        items[nextFirst] = x;
        size += 1;
        nextFirst = updateNextFirst();
    }

    private int updateNextFirst() {
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        }else {
            nextFirst -= 1;
        }
        return nextFirst;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(2 * size);
        }
        items[nextLast] = x;
        size += 1;
        nextLast = updateNextLast();
    }

    private int updateNextLast() {
        if (nextLast == items.length - 1) {
            nextLast = 0;
        }else {
            nextLast += 1;
        }
        return nextLast;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int i = judgeFirst();
        int count = 0;
        while (count < size) {
            returnList.add(items[i]);
            if (i == items.length - 1) {
                i = 0;
            }else {
                i += 1;
            }
            count += 1;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (nextFirst == 0 && nextLast == 1 && size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int first = judgeFirst();
        nextFirst = first;
        size -= 1;
        if (4 * size < items.length && size > 16) {
            resize(2 * size);
        }
        return items[first];
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int last = judgeLast();
        nextLast = last;
        size -= 1;
        if (4 * size < items.length && size > 16) {
            resize(2 * size);
        }
        return items[last];
    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        int i = judgeFirst();
        int count = 0;
        while (count != index) {
            if (i == items.length - 1) {
                i = 0;
            }else {
                i += 1;
            }
            count += 1;
        }
        return items[i];
    }
}
