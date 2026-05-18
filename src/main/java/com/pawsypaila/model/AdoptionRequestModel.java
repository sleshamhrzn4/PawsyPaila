package com.pawsypaila.model;



public class AdoptionRequestModel {
	private int adoptionId;
    private int userId;
    private int petId;
    private String adoptionStatus;
    private String fullName;
    private String petName;

    public int getAdoptionId() { return adoptionId; }
    public void setAdoptionId(int adoptionId) { this.adoptionId = adoptionId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getPetId() { return petId; }
    public void setPetId(int petId) { this.petId = petId; }

    public String getAdoptionStatus() { return adoptionStatus; }
    public void setAdoptionStatus(String adoptionStatus) { this.adoptionStatus = adoptionStatus; }


    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
 
    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }
}


