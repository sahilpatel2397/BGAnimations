package com.BGAnimation.objectLayer;

import java.util.Date;

public class BookingOrder{
    private int bookingId;
    private Date date;
    private int numTickets;
    private String promoCode;
    private float subTotal;
    private float tax;
    private float total;
    private String creditCard;
    private int userId;
    
    public BookingOrder(){
        
    }
    public BookingOrder(int bookingId, Date date, int numTickets, String promoCode, float subTotal, float tax, float total, String creditcard, int userId){
    	this.bookingId = bookingId;
    	this.date = date;
    	this.numTickets = numTickets;
    	this.promoCode = promoCode;
    	this.subTotal = subTotal;
    	this.tax = tax;
    	this.total = total;
    	this.creditCard = creditcard;
    	this.userId = userId;
    	
    }
	public Date getBookingDate() {
		return date;
	}
	public int getNumTicketsInOrder() {
		return numTickets;
	}
	public String getPromoCode() {
		return promoCode;
	}
	public float getSubtotal() {
		return subTotal;
	}
	public float getTax() {
		return tax;
	}
    public int getBookingId() {
    	return bookingId;
    }
}