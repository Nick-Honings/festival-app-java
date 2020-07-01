package com.festival.app.unit;

import com.festival.app.model.Event;
import com.festival.app.repository.FestivalRepository;
import com.festival.app.service.FestivalService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private FestivalRepository festivalRepository;

    @InjectMocks
    private FestivalService festivalService;

    static List<Event> events;

    @BeforeAll
    static void init(){
        events = new ArrayList<Event>();
        Event f1 = new Event();
        Event f2 = new Event();
        Event f3 = new Event();

        f1.setName("Defqon");
        f1.setDate(new Date(2020-5-6));
        f2.setName("Pinkpop");
        f2.setDate(new Date(2020-6-4));
        f3.setName("Lowlands");
        f3.setDate(new Date(2020-4-6));
        events.add(f1);
        events.add(f2);
        events.add(f3);
    }

    @Test
    void getAllFestivals(){
        // Arrange
        when(festivalRepository.findAll()).thenReturn(events);

        // Act
        List<Event> result = festivalService.getAll();

        // Assert
        assertEquals(3, result.size());
        verify(festivalRepository, times(1)).findAll();
    }

    @Test
    void addFestival_ShouldWork() {
        // Arrange
        Event expected = new Event();
        expected.setName("Defqon");
        when(festivalRepository.save(any(Event.class))).thenReturn(expected);

        // Act
        Event result = festivalService.addEvent(expected);

        // Assert
        assertEquals(expected, result);
        verify(festivalRepository, times(1)).save(any(Event.class));
    }

    @Test
    void removeFestival_ShouldWork(){
        // Arrange
        Event expected = events.get(0);
        ReflectionTestUtils.setField(expected, "id", (long)1);
        var x = expected.getId();

        // Act
        festivalService.deleteById((long) 1);

        // Assert
        verify(festivalRepository).deleteById(any());

    }
}
