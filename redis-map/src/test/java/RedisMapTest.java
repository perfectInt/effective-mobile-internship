import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ru.effectivemobile.RedisMap;

import static org.junit.jupiter.api.Assertions.*;

public class RedisMapTest {

    RedisMap redisMap = new RedisMap();

    @AfterEach
    public void clear() {
        redisMap.clear();
    }

    @Test
    public void testPutAndGet() {
        redisMap.put("key1", "value1");
        redisMap.put("key2", "value2");
        redisMap.put("key3", "value3");

        assertEquals("value1", redisMap.get("key1"));
        assertEquals("value2", redisMap.get("key2"));
        assertEquals("value3", redisMap.get("key3"));
    }

    @Test
    public void testIsEmptyAndSize() {
        assertTrue(redisMap.isEmpty());

        redisMap.put("key1", "value1");
        assertFalse(redisMap.isEmpty());
        assertEquals(1, redisMap.size());
    }

    @Test
    public void testContainsKey() {
        redisMap.put("key1", "value1");
        assertTrue(redisMap.containsKey("key1"));
    }

    @Test
    public void testRemoveKey() {
        redisMap.put("key1", "value1");
        assertTrue(redisMap.containsKey("key1"));

        redisMap.remove("key1");
        assertFalse(redisMap.containsKey("key1"));
    }
}
