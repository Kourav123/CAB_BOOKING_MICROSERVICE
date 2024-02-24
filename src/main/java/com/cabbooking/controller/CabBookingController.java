package com.cabbooking.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cabbooking.entity.CabBooking;
import com.cabbooking.repository.CabBookingRepository;
import com.cabbooking.service.CabBookingService;

@RestController
public class CabBookingController {

	private static final String CAB_BOOKED_SUCCESSFULLY = "Cab booked successfully!";

	@Autowired
	private CabBookingService cabBookingService;

	@Autowired
	private CabBookingRepository cabBookingRepository;

	@PostMapping(value = "/book", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
					MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String bookCab(@RequestParam String fromLocation, @RequestParam Integer typeOfCab,
			@RequestParam String toLocation) {
		CabBooking cabBooking = new CabBooking();
		cabBooking.setFromLocation(fromLocation);
		cabBooking.setToLocation(toLocation);
		cabBooking.setTypeOfCab(typeOfCab);
		cabBooking.setBookingTime(new Date());
		cabBookingService.bookCab(cabBooking);

		String response = "message", CAB_BOOKED_SUCCESSFULLY;

		return response;
	}

	@PostMapping(value = "/fare", consumes = { MediaType.ALL_VALUE
			 }, produces = { MediaType.APPLICATION_ATOM_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Double> calculateFare(@RequestParam("distance") double distance) {
		double fare = 0;
		if (distance != 0) {
			fare = cabBookingService.calculateFare(distance);
		}

		return ResponseEntity.ok().body(fare);
	}

	@GetMapping(value = "/bookings", consumes = { MediaType.ALL_VALUE}, produces = { MediaType.APPLICATION_ATOM_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<CabBooking>> getAllBookings() {
		List<CabBooking> booking = cabBookingRepository.findAll();

		return ResponseEntity.ok().body(booking);
	}
}
