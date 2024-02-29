import org.junit.jupiter.api.Test;
import ru.effectivemobile.RingBuffer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RingBufferTest {
    @Test
    public void testRingBuffer() {
        RingBuffer<Integer> buffer = new RingBuffer<>(5);

        assertEquals(0, buffer.size());

        buffer.put(1);
        buffer.put(2);
        buffer.put(3);

        assertEquals(3, buffer.size());

        assertEquals(1, buffer.poll());
        assertEquals(2, buffer.poll());
        assertEquals(3, buffer.poll());

        assertEquals(0, buffer.size());
    }

    @Test
    public void testWriterAndReader() {
        RingBuffer<Integer> buffer = new RingBuffer<>();
        Integer val = null;
        val = buffer.poll();
        assertEquals(null, val);
        val = buffer.poll();
        assertEquals(null, val);
        buffer.put(10);
        val = buffer.poll();
        assertEquals(10, val);
    }
}
