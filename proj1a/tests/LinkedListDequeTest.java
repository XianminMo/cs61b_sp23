import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    @DisplayName("LinkedListDeque has no fields besides nodes and primitives")
    void noNonTrivialFields() {
        Class<?> nodeClass = NodeChecker.getNodeClass(LinkedListDeque.class, true);
        List<Field> badFields = Reflection.getFields(LinkedListDeque.class)
                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(nodeClass) || f.isSynthetic()))
                .toList();

        assertWithMessage("Found fields that are not nodes or primitives").that(badFields).isEmpty();
    }

    @Test
    /** In this test, we have three different assert statements that verify that addFirst works correctly. */
    public void addFirstTestBasic() {
        Deque<String> lld1 = new LinkedListDeque<>();

        lld1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(lld1.toList()).containsExactly("back").inOrder();

        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
    }

    @Test
    /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
     *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
    public void addLastTestBasic() {
        Deque<String> lld1 = new LinkedListDeque<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        Deque<Integer> lld1 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }

    @Test
    /** Test the method isEmpty. */
    public void testIsEmpty() {
        Deque<Integer> lld = new LinkedListDeque<>();
        assertThat(lld.isEmpty()).isTrue();
        lld.addLast(1);
        assertThat(lld.isEmpty()).isFalse();
        lld.addLast(2);
        assertThat(lld.isEmpty()).isFalse();
    }

    @Test
    /** Test the method size. */
    public void testSize() {
        Deque<Integer> lld = new LinkedListDeque<>();
        assertThat(lld.size()).isEqualTo(0);
        lld.addLast(1);
        assertThat(lld.size()).isEqualTo(1);
    }

    @Test
    /** Test the method get. */
    public void testGet() {
        Deque<Integer> lld = new LinkedListDeque<>();
        assertThat(lld.get(1)).isEqualTo(null);
        lld.addLast(1);
        lld.addLast(2);
        lld.addLast(3);
        assertThat(lld.get(3)).isEqualTo(null);
        assertThat(lld.get(2)).isEqualTo(3);
        assertThat(lld.get(-1)).isEqualTo(null);
        assertThat(lld.get(3000)).isEqualTo(null);
    }

    @Test
    /** Test the method get. */
    public void testGetRecursive() {
        Deque<Integer> lld = new LinkedListDeque<>();
        assertThat(lld.getRecursive(1)).isEqualTo(null);
        lld.addLast(1);
        lld.addLast(2);
        lld.addLast(3);
        assertThat(lld.getRecursive(3)).isEqualTo(null);
        assertThat(lld.getRecursive(2)).isEqualTo(3);
        assertThat(lld.getRecursive(-1)).isEqualTo(null);
        assertThat(lld.getRecursive(3000)).isEqualTo(null);
    }

    @Test
    /** Test two methods removeFirst and removeLast. */
    public void testRemoveFirstAndRemoveLast() {
        Deque<Integer> lld = new LinkedListDeque<>();
        assertThat(lld.removeFirst()).isEqualTo(null);
        assertThat(lld.removeLast()).isEqualTo(null);
        lld.addLast(1); // [1]
        lld.addLast(2); // [1 2]
        lld.addFirst(5); // [5 1 2]
        lld.addFirst(6); // [6 5 1 2]
        int first = lld.removeFirst(); // [5 1 2] , 6
        int last = lld.removeLast(); // [5 1] , 2
        assertThat(lld.toList()).containsExactly(5, 1).inOrder();
        assertThat(first).isEqualTo(6);
        assertThat(last).isEqualTo(2);
    }
}