package com.cabbooking.entity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CabBookingTests {
	 	
	@InjectMocks
	private CabBooking cabBooking;

	@BeforeEach
    public void setUp() {
        cabBooking = new CabBooking("Bangalore", "Chennai", 1); // Initialize with sample data
    }
	    

	@Test
	public void testIdGeneration() {
		cabBooking.setId(12L);
		assertNotNull(cabBooking.getId());
	}

	@Test
	public void testFromLocation() {
		cabBooking.setFromLocation("Bangalore");
		assertEquals("Bangalore", cabBooking.getFromLocation(), "From location should be set correctly");
	}

	@Test
	public void testToLocation() {
		cabBooking.setToLocation("Chennai");
		assertEquals("Chennai", cabBooking.getToLocation(), "To location should be set correctly");
	}

	@Test
	public void testTypeOfCab() {
		cabBooking.setTypeOfCab(1);
		assertEquals(1, cabBooking.getTypeOfCab(), "Type of cab should be set correctly");
	}

	@Test
	public void testBookingTime() {
		Date bookingTime = new Date();
		cabBooking.setBookingTime(bookingTime);
		assertEquals(bookingTime, cabBooking.getBookingTime(), "Booking time should be set correctly");
	}

}
