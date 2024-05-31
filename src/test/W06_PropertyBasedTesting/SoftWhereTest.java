package W06_PropertyBasedTesting;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SoftWhereTest {
    static List<Trip> trips = new ArrayList<>() {{
        add(new Trip("Trip1", null, 10, 1));
        add(new Trip("Trip2", null, 20, 2));
        add(new Trip("Trip3", null, 30, 3));
        add(new Trip("Trip4", null, 40, 4));
    }};
    static List<Person> people = new ArrayList<>() {{
        add(new Person("Firstname1", "Middlename1", "Lastname1", LocalDate.now()));
        add(new Person("Firstname2", "Middlename2", "Lastname2", LocalDate.now()));
        add(new Person("Firstname3", "Middlename3", "Lastname3", LocalDate.now()));
        add(new Person("Firstname4", "Middlename4", "Lastname4", LocalDate.now()));
    }};
    static List<Reservation> reservations = new ArrayList<>() {{
        add(new Reservation(trips.get(0), people.subList(0, 1)));
        add(new Reservation(trips.get(0), people.subList(1, 3)));
        add(new Reservation(trips.get(1), people.subList(2, 3)));
        add(new Reservation(trips.get(2), people.subList(0, 3)));
        add(new Reservation(trips.get(2), people.subList(1, 2)));
    }};
    ReservationRepository reservationRepository;
    TripRepository tripRepository;
    SoftWhere softWhere;

    @BeforeEach
    void setup() {
        reservationRepository = Mockito.mock(ReservationRepository.class);
        tripRepository = Mockito.mock(TripRepository.class);
        softWhere = new SoftWhere(tripRepository, reservationRepository);
    }

    @Test
    public void testMakeReservation() {
        Mockito.when(tripRepository.getTripById(1L)).thenReturn(trips.get(0));
        Mockito.when(tripRepository.getTripById(2L)).thenReturn(trips.get(1));
        Mockito.when(tripRepository.getTripById(3L)).thenReturn(trips.get(2));
        Mockito.when(tripRepository.getTripById(4L)).thenReturn(trips.get(3));
        Mockito.when(tripRepository.getTripById(Mockito.longThat(x->x>4))).thenThrow(ElementNotFoundException.class);
        Mockito.when(reservationRepository.getAllReservationsByTrip(trips.get(0))).thenReturn(reservations.subList(0,2));
        Mockito.when(reservationRepository.getAllReservationsByTrip(trips.get(1))).thenReturn(reservations.subList(2,3));
        Mockito.when(reservationRepository.getAllReservationsByTrip(trips.get(2))).thenReturn(reservations.subList(3,5));
        Mockito.when(reservationRepository.getAllReservationsByTrip(trips.get(3))).thenReturn(List.of());
        assertFalse(softWhere.makeReservation(1L,people.subList(3,4)));
        Mockito.verify(reservationRepository,Mockito.times(0)).save(Mockito.any(Reservation.class));
        assertTrue(softWhere.makeReservation(2L,people.subList(2,3)));
        Mockito.verify(reservationRepository).save(Mockito.argThat(x->x.equals(new Reservation(trips.get(1),people.subList(2,3)))));
        assertFalse(softWhere.makeReservation(10L,people));
    }
}