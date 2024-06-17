package W08_AllTestingMethods;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class ReadingProgressTest {
    UserService userService;
    BookService bookService;
    EmailService emailService;
    ReadingProgress readingProgress;

    @BeforeEach
    public void setup() {
        userService = Mockito.mock(UserService.class);
        bookService = Mockito.mock(BookService.class);
        emailService = Mockito.mock(EmailService.class);
        readingProgress = new ReadingProgress(userService, bookService, emailService);
    }

    @Test
    public void testNotAuthorized() {
        Mockito.when(userService.isMarketingAuthorised(1)).thenReturn(false);
        readingProgress.readingProgress(1, 2, 50);
        Mockito.verify(bookService, Mockito.times(1)).updateLastVisualization(2, 50);
        Mockito.verify(emailService, Mockito.times(0)).sendKeepUpGoodWorkEmail(Mockito.anyInt());
        Mockito.verify(emailService, Mockito.times(0)).sendAlmostThereEmail(Mockito.anyInt());
        Mockito.verify(emailService, Mockito.times(0)).sendCongratsYouHaveMadeItEmail(Mockito.anyInt());
    }

    @Test
    public void testGoodWork() {
        Mockito.when(userService.isMarketingAuthorised(2)).thenReturn(true);
        readingProgress.readingProgress(2, 3, 10);
        Mockito.verify(bookService, Mockito.times(1)).updateLastVisualization(3, 10);
        Mockito.verify(emailService, Mockito.times(1)).sendKeepUpGoodWorkEmail(3);
        Mockito.verify(emailService, Mockito.times(0)).sendAlmostThereEmail(Mockito.anyInt());
        Mockito.verify(emailService, Mockito.times(0)).sendCongratsYouHaveMadeItEmail(Mockito.anyInt());
    }

    @Test
    public void testAlmostThere() {
        Mockito.when(userService.isMarketingAuthorised(3)).thenReturn(true);
        readingProgress.readingProgress(3, 4, 50);
        Mockito.verify(bookService, Mockito.times(1)).updateLastVisualization(4, 50);
        Mockito.verify(emailService, Mockito.times(0)).sendKeepUpGoodWorkEmail(Mockito.anyInt());
        Mockito.verify(emailService, Mockito.times(1)).sendAlmostThereEmail(4);
        Mockito.verify(emailService, Mockito.times(0)).sendCongratsYouHaveMadeItEmail(Mockito.anyInt());
    }

    @Test
    public void testCongrats() {
        Mockito.when(userService.isMarketingAuthorised(4)).thenReturn(true);
        readingProgress.readingProgress(4, 5, 99);
        Mockito.verify(bookService, Mockito.times(1)).updateLastVisualization(5, 99);
        Mockito.verify(emailService, Mockito.times(0)).sendKeepUpGoodWorkEmail(5);
        Mockito.verify(emailService, Mockito.times(0)).sendAlmostThereEmail(Mockito.anyInt());
        Mockito.verify(emailService, Mockito.times(1)).sendCongratsYouHaveMadeItEmail(Mockito.anyInt());
    }


}