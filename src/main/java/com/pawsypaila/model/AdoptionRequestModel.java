package com.pawsypaila.model;

import java.util.Date;

public class AdoptionRequestModel {
	private int adoptionId;
    private int userId;
    private int petId;
    private String adoptionStatus;
    private String reason;
    private Date requestDate;

    public int getAdoptionId() { return adoptionId; }
    public void setAdoptionId(int adoptionId) { this.adoptionId = adoptionId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getPetId() { return petId; }
    public void setPetId(int petId) { this.petId = petId; }

    public String getAdoptionStatus() { return adoptionStatus; }
    public void setAdoptionStatus(String adoptionStatus) { this.adoptionStatus = adoptionStatus; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public Date getRequestDate() { return requestDate; }
    public void setRequestDate(Date requestDate) { this.requestDate = requestDate; }
}


