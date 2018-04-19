package com.BGAnimation.objectLayer;

import java.util.ArrayList;
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
    private ArrayList<Ticket> tickets; 
    
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
    
    public void addTicket(Ticket t) {
    	tickets.add(t);
    }
    
    public void removeTicket(Ticket t) {
    	tickets.remove(t);
    }
    
    public ArrayList<Ticket> getAllTickets() {
    	return tickets;
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
	
	public float getTotal() {
		return total;
	}
	
    public int getBookingId() {
    	return bookingId;
    }
    
    public String getCreditCard() {
    	return creditCard;
    }
    
    public int getUserId() {
    	return userId;
    }
    
    
}