package com.pawsypaila.model;

public class PaymentModel {
	private int paymentId;
    private String paymentMethod;
    private String paymentStatus;
    private double totalPaymentAmt;

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public double getTotalPaymentAmt() { return totalPaymentAmt; }
    public void setTotalPaymentAmt(double totalPaymentAmt) { this.totalPaymentAmt = totalPaymentAmt; }
}

