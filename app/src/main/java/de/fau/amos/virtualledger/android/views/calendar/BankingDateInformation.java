package de.fau.amos.virtualledger.android.views.calendar;

import java.util.List;

import de.fau.amos.virtualledger.dtos.Booking;
import hirondelle.date4j.DateTime;

/**
 * Created by Georg on 15.06.2017.
 */

public class BankingDateInformation {

    private DateTime dateTime;
    private double amount;
    private List<Booking> bookingList;

    public BankingDateInformation(DateTime dateTime, double amount, List<Booking> bookingList) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.bookingList = bookingList;
    }

    public double getAmount() {
        return amount;
    }

    public double getAmountDelta() {
        double amountDelta = 0.0;
        for (Booking booking : bookingList) {
            amountDelta += booking.getAmount();
        }
        return amountDelta;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}