package com.cabbooking.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cab_booking")
public class CabBooking {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String fromLocation;
	    private String toLocation;
	    private int typeOfCab;
	    private Date bookingTime;

	    // Constructors, getters, and setters

	    public CabBooking() {
	        // Default constructor required by JPA
	    }

	    public CabBooking(String fromLocation, String toLocation, int typeOfCab) {
	        this.fromLocation = fromLocation;
	        this.toLocation = toLocation;
	        this.typeOfCab = typeOfCab;
	        this.bookingTime = new Date(); // Set the booking time to the current date/time
	    }

	    // Getters and setters

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getFromLocation() {
	        return fromLocation;
	    }

	    public void setFromLocation(String fromLocation) {
	        this.fromLocation = fromLocation;
	    }

	    public String getToLocation() {
	        return toLocation;
	    }

	    public void setToLocation(String toLocation) {
	        this.toLocation = toLocation;
	    }

	    public int getTypeOfCab() {
	        return typeOfCab;
	    }

	    public void setTypeOfCab(int typeOfCab) {
	        this.typeOfCab = typeOfCab;
	    }

	    public Date getBookingTime() {
	        return bookingTime;
	    }

	    public void setBookingTime(Date bookingTime) {
	        this.bookingTime = bookingTime;
	    }
}

