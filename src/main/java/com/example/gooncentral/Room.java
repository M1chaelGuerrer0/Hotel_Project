package com.example.gooncentral;

public class Room {
    private int room_Number=0;
    private String room_Type;
    private double  price_Per_Night;
    private int room_Capacity;
    private String availability;

    public Room(){}
    public Room(int room_Number, String room_Type, double  price_Per_Night, int room_Capacity, String availability){
        this.room_Number = room_Number;
        this.room_Type = room_Type;
        this.price_Per_Night = price_Per_Night;
        this.room_Capacity = room_Capacity;
        this.availability = availability;
    }
    // Setters
    public void setRoom_Type(String roomType){this.room_Type = roomType;}
    public void setPrice_Per_Night(double price_Per_Night){this.price_Per_Night = price_Per_Night;}
    public void setRoom_Capacity(int room_Capacity){this.room_Capacity = room_Capacity;}
    public void setAvailability(String availability){this.availability = availability;}
    public void setRoom_Number(int room_Number){this.room_Number = room_Number;};

    // Getters
    public String getRoom_Type(){return room_Type;}
    public double getPrice_Per_Night(){return price_Per_Night;}
    public int getRoom_Capacity(){return room_Capacity;}
    public String getAvailability(){return availability;}
    public int getRoom_Number() {return room_Number;}

    @Override
    public String toString() {
        return "\nroom_Number: " + this.room_Number +
                "\nroom_Type: " + this.room_Type +
                "\nprice_Per_Night: " + this.price_Per_Night +
                "\nroom_Capacity: "+ this.room_Capacity +
                "\navailability: " + this.availability + "\n";
    }
}
