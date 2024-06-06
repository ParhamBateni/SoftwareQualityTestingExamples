package W07_IntegrationTesting;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;

import static org.junit.Assert.*;

public class MyHealthTest {
    AuthService authService;
    LabResultRepository labResultRepository;
    Log log;
    MyHealth myHealth;

    @BeforeEach
    public void setup(){
        authService = Mockito.mock(AuthService.class);
        labResultRepository = Mockito.mock(LabResultRepository.class);
        log = Mockito.mock(Log.class);
        myHealth = new MyHealth(authService,labResultRepository,log);
    }

    @Test
    public void testIllegalAccess(){
        Mockito.when(authService.canViewLabResult(1,1)).thenReturn(false);
        assertNull(myHealth.getLabResult(1,1));
        Mockito.verify(log).warn("1 tried to illegally access lab result 1");
        Mockito.verify(labResultRepository,Mockito.times(0)).getLabResultById(1);
    }
    @Test
    public void testResultDoesNotExist(){
        Mockito.when(authService.canViewLabResult(1,1)).thenReturn(true);
        Mockito.when(labResultRepository.getLabResultById(1)).thenReturn(null);
        assertNull(myHealth.getLabResult(1,1));
        Mockito.verify(log).warn("Lab result 1 was requested but doesn't exist.");
    }
    @Test
    public void testResultExists(){
        Mockito.when(authService.canViewLabResult(1,1)).thenReturn(true);
        Mockito.when(labResultRepository.getLabResultById(1)).thenReturn("result1");
        assertEquals("result1",myHealth.getLabResult(1,1));
        Mockito.verify(log,Mockito.times(0)).warn(Mockito.anyString());
    }

}