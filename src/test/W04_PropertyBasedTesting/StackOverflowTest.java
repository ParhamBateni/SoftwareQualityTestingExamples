package W04_PropertyBasedTesting;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class StackOverflowTest {

    UserRepository userRepository;
    Scoring scoring;
    StackOverflow stackOverflow;

    User user1 = new User("U1", 1, false);
    User user2 = new User("U2", 10, true);
    User user3 = new User("U3", 100, true);

    Post post1 = new Post(user1, "post1");
    Post post2 = new Post(user2, "post2");
    Post post3 = new Post(user3, "post3");

    @BeforeEach
    void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        scoring = Mockito.mock(Scoring.class);
        stackOverflow = new StackOverflow(userRepository, scoring);
    }

    @Test
    public void test() {
        Mockito.when(scoring.pointsForJedi()).thenReturn(5);
        Mockito.when(scoring.pointsForNormalUser()).thenReturn(1);
        stackOverflow.downvote(post1, user2);
        Mockito.verify(userRepository).update(user1);
        assertEquals(-4, user1.getPoints());
        stackOverflow.downvote(post2, user1);
        Mockito.verify(userRepository).update(user2);
        assertEquals(9, user2.getPoints());

    }
}