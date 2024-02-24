package com.cabbooking.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.cabbooking.entity.CabBooking;
import com.cabbooking.repository.CabBookingRepository;

@SpringBootTest
public class CabBookingServiceTest {

    @SpyBean
    private CabBookingRepository cabBookingRepository;

    @Autowired
    private CabBookingService cabBookingService;


    @Test
    public void testBookCab() {
        // Prepare test data
        CabBooking cabBooking = new CabBooking();
        cabBooking.setFromLocation("Location A");
        cabBooking.setToLocation("Location B");
        cabBooking.setTypeOfCab(1);
        cabBooking.setBookingTime(new Date());
        // Capture the argument passed to save method
        ArgumentCaptor<CabBooking> argument = ArgumentCaptor.forClass(CabBooking.class);

        // Invoke the method to be tested
        cabBookingService.bookCab(cabBooking);

        // Verify that save method was called with the correct argument
        verify(cabBookingRepository).save(argument.capture());
        CabBooking capturedCabBooking = argument.getValue();

        // Assertions
        assertNotNull(capturedCabBooking.getBookingTime());
        assertEquals("Location A", capturedCabBooking.getFromLocation());
        assertEquals("Location B", capturedCabBooking.getToLocation());
        assertEquals(1, capturedCabBooking.getTypeOfCab());
    }

    @Test
    public void testCalculateFare() {
        // Test with distance = 5
        double distance = 5;
        assertEquals(50, cabBookingService.calculateFare(distance));
        
        // Test with distance = 0
        assertEquals(0, cabBookingService.calculateFare(0));
        
        // Test with distance = -1
        assertEquals(-10, cabBookingService.calculateFare(-1));
    }
}
