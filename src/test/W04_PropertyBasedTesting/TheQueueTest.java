package W04_PropertyBasedTesting;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.*;

public class TheQueueTest {
    private RequestService requestService;

    private TheQueue queue;

    private Map<String, Queue<String>> requests = new HashMap<>() {{
        put("OOP", new PriorityQueue<>() {
            {
                add("R1");
                add("R2");
            }
        });
        put("IDM", new PriorityQueue<>() {
            {
                add("R3");
                add("R4");
            }
        });
        put("CLP", new PriorityQueue<>());
    }};

    @BeforeEach
    public void setup(){
        requestService = Mockito.mock(RequestService.class);
        queue = new TheQueue(requestService);
    }


    @Test
    public void testGetNext() {
        Mockito.when(requestService.getRequestsByCourse()).thenReturn(requests);
        assertThrows(IllegalArgumentException.class,()->queue.getNext("CI"));
        assertThrows(NoSuchElementException.class,()->queue.getNext("CLP"));
        assertEquals("R1",queue.getNext("OOP"));
        assertEquals("R3",queue.getNext("IDM"));
        assertEquals("R2",queue.getNext("OOP"));
        assertEquals("R4",queue.getNext("IDM"));
    }

}