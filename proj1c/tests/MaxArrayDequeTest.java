import deque.*;
import net.sf.saxon.functions.Minimax;
import org.junit.jupiter.api.*;

import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDequeTest {
    @Test
    public void testMax1() {
        Comparator<String> c = new StringComparator();
        MaxArrayDeque<String> d = new MaxArrayDeque<>(c);
        d.addLast("a");
        d.addLast("b");
        d.addLast("c");
        assertThat(d.max()).isEqualTo("c");
    }

    @Test
    public void testMax2() {
        Comparator<ArrayDeque> c = new DequeSizeComparator();
        ArrayDeque<Integer> d1 = new ArrayDeque<>();
        d1.addLast(1);
        ArrayDeque<Integer> d2 = new ArrayDeque<>();
        d2.addLast(1);
        d2.addLast(2);
        d2.addLast(3);
        ArrayDeque<Integer> d3 = new ArrayDeque<>();
        d3.addLast(1);
        d3.addLast(2);
        MaxArrayDeque<ArrayDeque> d = new MaxArrayDeque<>(c);
        d.addLast(d1);
        d.addLast(d2);
        d.addLast(d3);
        assertThat(d.max()).isEqualTo(d2);
    }

}
