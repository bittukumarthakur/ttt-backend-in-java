package com.example.demo;

import java.util.ArrayList;

public class Rooms {
    ArrayList<Room> rooms = new ArrayList<>();
    int currentRoomId = 1;

    public RoomInfo create(String hostName) {
        int roomId = currentRoomId;
        rooms.add(new Room(hostName, roomId, null));
        currentRoomId++;

        return new RoomInfo(roomId, hostName);
    }

    public Status getStatus(int roomId) {
        Room room = rooms.get(roomId - 1);
        if (room.ticTakToe() == null) {
            return new Status(null, null, null, null, false);
        }

        return room.ticTakToe().getStatus();
    }

    public boolean isValidRoomId(int roomId) {
        Room room = rooms.get(roomId - 1);
        return room != null && room.ticTakToe() == null;
    }

    public Status join(int roomId, String otherPlayerName) {
        Room room = rooms.get(roomId - 1);
        Player p1 = new Player(room.hostName(), "X");
        Player p2 = new Player(otherPlayerName, "O");
        Players players = new Players(p1, p2);
        TicTakToe ticTakToe = new TicTakToe(players);

        rooms.set(roomId - 1, new Room(room.hostName(), room.roomId(), ticTakToe));
        return ticTakToe.getStatus();
    }

    public void markPosition( int roomId, int position) throws Exception {
        Room room = this.getRoom(roomId);
        room.ticTakToe().mark(position);
    }

    private Room getRoom(int roomId) {
        return rooms.get(roomId - 1);
    }

    public String getCurrentPlayerName(int roomId) {
        Room room = getRoom(roomId);
        return room.ticTakToe().getStatus().currentPlayerName();
    }
}