package W04_PropertyBasedTesting;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.*;
import java.util.stream.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

public class TodoApplicationTest {
    @Mock
    TodoService todoService;
    @Mock
    PersonService personService;
    TodoApplication todoApplication;
    @BeforeEach
    void setup(){
        todoService = Mockito.mock(TodoService.class);
        personService = Mockito.mock(PersonService.class);
        todoApplication = new TodoApplication(todoService,personService);
    }
    @Test
    public void testRetrieveTodos(){
        Mockito.when(personService.findUsernameById(1L)).thenReturn("User1");
//        Mockito.when(personService.findUsernameById(2L)).thenReturn("User2");
        Mockito.when(todoService.retrieveTodos("User1")).thenReturn(List.of("todo1", "todo2","todo3"));
//        Mockito.when(todoService.retrieveTodos("User2")).thenReturn(List.of("todo4","todo5","todo6"));
        assertEquals(List.of("todo1","todo2","todo3"),todoApplication.retrieveTodos(1L,"todo"));
        assertEquals(List.of("todo1"),todoApplication.retrieveTodos(1L,"1"));
        assertEquals(List.of(),todoApplication.retrieveTodos(1L,"test"));

    }

}