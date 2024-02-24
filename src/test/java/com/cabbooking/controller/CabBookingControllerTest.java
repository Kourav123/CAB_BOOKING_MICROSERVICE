package com.cabbooking.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cabbooking.entity.CabBooking;
import com.cabbooking.repository.CabBookingRepository;
import com.cabbooking.service.CabBookingService;

@WebMvcTest(controllers = CabBookingController.class)
@AutoConfigureMockMvc
public class CabBookingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CabBookingService cabBookingService;

	@MockBean
	private CabBookingRepository cabBookingRepository;

	@Test
	public void testBookCab() throws Exception {
		String fromLocation = "Location A";
		Integer typeOfCab = 1;
		String toLocation = "Location B";
		String expectedResponse = "message";

		mockMvc.perform(post("/book").param("fromLocation", fromLocation).param("typeOfCab", String.valueOf(typeOfCab))
				.param("toLocation", toLocation).contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk()).andExpect(content().string(expectedResponse));

		// Verify that the service method was called with the correct parameters
		verify(cabBookingService).bookCab(any());
	}

	@Test
	public void testGetAllBookings() throws Exception {
		// Prepare mock data
		CabBooking cab = new CabBooking("Location A", "Location B", 1);
		cab.setId(12L);
		cab.setBookingTime(new Date());
		

		List<CabBooking> bookings = new ArrayList<>();
		bookings.add(cab);

		// Mock repository method
		when(cabBookingRepository.findAll()).thenReturn(bookings);

		// Perform the test
				mockMvc.perform(get("/bookings")
				        .contentType(MediaType.APPLICATION_ATOM_XML_VALUE))
				        .andExpect(status().isOk())
				        .andExpect(content().contentType(MediaType.APPLICATION_ATOM_XML_VALUE))
				        .andExpect(xpath("/List/item/id").string("12"))
				        .andExpect(xpath("/List/item/fromLocation").string("Location A"))
				        .andExpect(xpath("/List/item/toLocation").string("Location B"))
				        .andExpect(xpath("/List/item/typeOfCab").string("1"));
	}

	@Test
	public void testCalculateFare() throws Exception {
        when(cabBookingService.calculateFare(10.0)).thenReturn(50.0); // example fare

		// Perform the request and assert the response
		mockMvc.perform(post("/fare").param("distance", "10.0").contentType(MediaType.APPLICATION_ATOM_XML))

				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_ATOM_XML)) // Expect the																					// be JSON
				.andExpect(content().string("<Double>50.0</Double>")) // Assuming the calculated fare is 50.0 for a distance of 10.0
				.andDo(result -> System.out.println("Response Body: " + result.getResponse().getContentAsString()));
	}
}
