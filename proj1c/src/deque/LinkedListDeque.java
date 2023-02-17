package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    public static void main(String[] args) {
        Deque<Integer> lld = new LinkedListDeque<>();
        lld.addFirst(1);
        lld.addFirst(2);
        lld.addLast(3);
        for (int x : lld) {
            System.out.print(x + " ");
        }
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private TNode wisPos;

        public LinkedListDequeIterator() {
            wisPos = new TNode();
            wisPos = sentinel.next;
        }

        public boolean hasNext() {
            return wisPos != sentinel;
        }

        public T next() {
            T returnItem = wisPos.item;
            wisPos = wisPos.next;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class TNode {
        private T item;
        private TNode prev;
        private TNode next;

        public TNode() {
            prev = null;
            next = null;
        }

        public TNode(T item, TNode prev, TNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

    }

    private TNode sentinel; // sentinel node make things common, no exception
    private int size;

    public LinkedListDeque() {
        sentinel = new TNode();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        // suggest draw a graph to understand the code below
        // create a new node and connect with the sentinel node
        // change sentinel.next to point at the new node
        // Don't forget change the old first node.prev to point at the new node (line 2)
        sentinel.next = new TNode(x, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        // same as addFirst
        // just change the direction from sentinel node
        sentinel.prev = new TNode(x, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (TNode a = sentinel.next; a != sentinel; a = a.next) {
            returnList.add(a.item);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel && sentinel.prev == sentinel) {
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
        TNode returnNode = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return returnNode.item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        TNode returnNode = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return returnNode.item;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        TNode i = sentinel.next;
        int count = 0;
        while(count != index) {
            i = i.next;
            count += 1;
        }
        return i.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        // Recursion must have a terminal condition which associated with the input parameter (index) .
        if (index == 0) {
            return sentinel.next.item;
        }
        sentinel = sentinel.next;
        return getRecursive(index - 1);
    }

}
