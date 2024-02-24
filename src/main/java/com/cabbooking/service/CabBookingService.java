package com.cabbooking.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabbooking.entity.CabBooking;
import com.cabbooking.repository.CabBookingRepository;

@Service
public class CabBookingService {
	/*
	 * @Autowired private CabBookingRepository cabBookingRepository;
	 * 
	 * public void bookCab(CabBooking cabBooking) {
	 * 
	 * cabBooking.setBookingTime(new Date()); cabBookingRepository.save(cabBooking);
	 * 
	 * }
	 */
	 @Autowired
	    private CabBookingRepository cabBookingRepository;

	    public void bookCab(CabBooking cabBooking) {
	        // Assign a value to the identifier before persisting
	        cabBooking.getId(/* Assign a value here */);
	        cabBookingRepository.save(cabBooking);
	    }

	public double calculateFare(double distance) {
		// TODO Auto-generated method stub
		return distance * 10;
	}

}
