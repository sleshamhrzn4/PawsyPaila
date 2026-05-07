package com.pawsypaila.model;

public class PetModel {
	private int petId;
    private String petName;
    private String petType;
    private String breed;
    private String petDesc;

    public int getPetId() { return petId; }
    public void setPetId(int petId) { this.petId = petId; }

    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }

    public String getPetType() { return petType; }
    public void setPetType(String petType) { this.petType = petType; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public String getPetDesc() { return petDesc; }
    public void setPetDesc(String petDesc) { this.petDesc = petDesc; }

}

