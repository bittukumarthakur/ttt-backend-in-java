package com.example.demo.Exceptions;

public class InvalidRoomIdException extends Exception{
    public InvalidRoomIdException(int roomId) {
        super("Room ID: " + roomId + " is invalid");
    }

}
