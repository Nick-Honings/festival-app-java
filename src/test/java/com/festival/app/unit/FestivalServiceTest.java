package com.festival.app.unit;

import com.festival.app.model.Festival;
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
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FestivalServiceTest {

    @Mock
    private FestivalRepository festivalRepository;

    @InjectMocks
    private FestivalService festivalService;

    static List<Festival> festivals;

    @BeforeAll
    static void init(){
        festivals = new ArrayList<Festival>();
        Festival f1 = new Festival();
        Festival f2 = new Festival();
        Festival f3 = new Festival();

        f1.setName("Defqon");
        f1.setDate(new Date(2020-5-6));
        f2.setName("Pinkpop");
        f2.setDate(new Date(2020-6-4));
        f3.setName("Lowlands");
        f3.setDate(new Date(2020-4-6));
        festivals.add(f1);
        festivals.add(f2);
        festivals.add(f3);
    }

    @Test
    void getAllFestivals(){
        // Arrange
        when(festivalRepository.findAll()).thenReturn(festivals);

        // Act
        List<Festival> result = festivalService.getAll();

        // Assert
        assertEquals(3, result.size());
        verify(festivalRepository, times(1)).findAll();
    }

    @Test
    void addFestival_ShouldWork() {
        // Arrange
        Festival expected = new Festival();
        expected.setName("Defqon");
        when(festivalRepository.save(any(Festival.class))).thenReturn(expected);

        // Act
        Festival result = festivalService.addEvent(expected);

        // Assert
        assertEquals(expected, result);
        verify(festivalRepository, times(1)).save(any(Festival.class));
    }

    @Test
    void removeFestival_ShouldWork(){
        // Arrange
        Festival expected = festivals.get(0);
        ReflectionTestUtils.setField(expected, "id", (long)1);
        var x = expected.getId();

        // Act
        festivalService.deleteById((long) 1);

        // Assert
        verify(festivalRepository).deleteById(any());

    }
}
