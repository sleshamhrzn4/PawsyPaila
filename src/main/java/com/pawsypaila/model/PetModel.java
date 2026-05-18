package com.pawsypaila.model;

public class PetModel {
	private int petId;
    private String petName;
    private int petAge;
    private String petType;
    private String petGender;
    private String petDesc;
    private String petImage;

    

    public int getPetId() { return petId; }
    public void setPetId(int petId) { this.petId = petId; }

    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }

    public int getPetAge() { return petAge; }
    public void setPetAge(int petAge) { this.petAge = petAge; }

    public String getPetType() { return petType; }
    public void setPetType(String petType) { this.petType = petType; }
    
    public String getPetGender() { return petGender; }
    public void setPetGender(String petGender) { this.petGender = petGender; }

    public String getPetDesc() { return petDesc; }
    public void setPetDesc(String petDesc) { this.petDesc = petDesc; }

    public String getPetImage() { return petImage; }
    public void setPetImage(String petImage) { this.petImage = petImage; }
}


