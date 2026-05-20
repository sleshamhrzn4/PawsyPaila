package com.pawsypaila.model;

import java.sql.Date;

public class DonationModel {
	private int donationId;
    private int userId;
    private double donationAmount;
    private Date donationDate;
    private String donationPaymentMethod;
    private String userName;

    public int getDonationId() { return donationId; }
    public void setDonationId(int donationId) { this.donationId = donationId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public double getDonationAmount() { return donationAmount; }
    public void setDonationAmount(double donationAmount) { this.donationAmount = donationAmount; }

    public Date getDonationDate() { return donationDate; }
    public void setDonationDate(Date donationDate) { this.donationDate = donationDate; }

    public String getDonationPaymentMethod() { return donationPaymentMethod; }
    public void setDonationPaymentMethod(String donationPaymentMethod) { this.donationPaymentMethod = donationPaymentMethod; }
    
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
}



