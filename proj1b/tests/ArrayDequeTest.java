import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {
    @Test
    public void testToListAddFirstAndAddLast() {
        /* ad means ArrayDeque */
        Deque<Integer> ad = new ArrayDeque<>();
        ad.addFirst(1); // [1]
        ad.addFirst(2); // [2, 1]
        ad.addFirst(3); // [3, 2, 1]
        ad.addLast(4); // [3, 2, 1, 4]
        ad.addLast(5); // [3, 2, 1, 4, 5]
        ad.addLast(6); // [3, 2, 1, 4, 5, 6]
        assertThat(ad.toList()).containsExactly(3, 2, 1, 4, 5, 6).inOrder();
    }

    @Test
    public void testIsEmpty() {
        Deque<Integer> ad = new ArrayDeque<>();
        assertThat(ad.isEmpty()).isTrue();
        ad.addLast(1);
        assertThat(ad.isEmpty()).isFalse();
        ad.addLast(2);
        assertThat(ad.isEmpty()).isFalse();
    }

    @Test
    /** Test the method size. */
    public void testSize() {
        Deque<Integer> ad = new ArrayDeque<>();
        assertThat(ad.size()).isEqualTo(0);
        ad.addLast(1);
        assertThat(ad.size()).isEqualTo(1);
    }

    @Test
    /** Test the method get. */
    public void testGet() {
        Deque<Integer> ad = new ArrayDeque<>();
        assertThat(ad.get(1)).isEqualTo(null);
        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);
        assertThat(ad.get(3)).isEqualTo(null);
        assertThat(ad.get(2)).isEqualTo(3);
        assertThat(ad.get(-1)).isEqualTo(null);
        assertThat(ad.get(3000)).isEqualTo(null);
    }

    @Test
    /** Test two methods removeFirst and removeLast. */
    public void testRemoveFirstAndRemoveLast() {
        Deque<Integer> ad = new ArrayDeque<>();
        assertThat(ad.removeFirst()).isEqualTo(null);
        assertThat(ad.removeLast()).isEqualTo(null);
        ad.addLast(1); // [1]
        ad.addLast(2); // [1 2]
        ad.addFirst(5); // [5 1 2]
        ad.addFirst(6); // [6 5 1 2]
        int first = ad.removeFirst(); // [5 1 2] , 6
        int last = ad.removeLast(); // [5 1] , 2
        assertThat(ad.toList()).containsExactly(5, 1).inOrder();
        assertThat(first).isEqualTo(6);
        assertThat(last).isEqualTo(2);
    }
}
