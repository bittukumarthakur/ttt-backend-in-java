package com.example.demo;

import com.example.demo.Exceptions.InvalidRoomIdException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class TikTacToeController {
    private final Rooms rooms = new Rooms();

    @GetMapping("/echo")
    public String echo() {
        return "service is up";
    }

    @GetMapping("/mark")
    public Status markPosition(@RequestParam() int position, @CookieValue(name = "user-name") String userName, @CookieValue(name = "room-id") int roomId) throws Exception {
        System.out.println("marked  " + position + "position" + "by " + userName);
        if (!rooms.getCurrentPlayerName(roomId).equals(userName)) throw new Exception("It not you turn");
        rooms.markPosition(roomId, position);


        return rooms.getStatus(roomId);
    }

    @PostMapping("/join-room")
    public Status joinRoom(@RequestBody RoomInfo roomInfo, HttpServletResponse response) throws InvalidRoomIdException {
        if (rooms.isValidRoomId(roomInfo.roomId())) {
            Cookie userNameCookie = new Cookie("user-name", String.valueOf(roomInfo.userName()));
            Cookie roomIdCookie = new Cookie("room-id", String.valueOf(roomInfo.roomId()));

            response.addCookie(userNameCookie);
            response.addCookie(roomIdCookie);

            System.out.println("Room Id: " + roomInfo.roomId() + " | Joined: " + roomInfo.userName());
            return rooms.join(roomInfo.roomId(), roomInfo.userName());
        }

        throw new InvalidRoomIdException(roomInfo.roomId());
    }

    @GetMapping("/create-room")
    public RoomInfo createRoom(HttpServletResponse response, @RequestParam() String hostName) {
        RoomInfo roominfo = rooms.create(hostName);
        int roomId = roominfo.roomId();

        Cookie userNameCookie = new Cookie("user-name", hostName);
        Cookie roomIdCookie = new Cookie("room-id", String.valueOf(roomId));

        response.addCookie(userNameCookie);
        response.addCookie(roomIdCookie);

        System.out.println("room: " + roomId + "is created by " + hostName);
        return roominfo;
    }

    @GetMapping("/status")
    public Status status(@CookieValue(name = "room-id") String roomId) {
        return rooms.getStatus(Integer.parseInt(roomId));

    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidMoveException(Exception exception) {
        return exception.getMessage();
    }
}
