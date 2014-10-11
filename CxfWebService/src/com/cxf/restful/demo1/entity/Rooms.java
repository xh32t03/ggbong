package com.cxf.restful.demo1.entity;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.cxf.restful.demo1.dao.RoomDAO;

@XmlRootElement(name = "rooms")
public class Rooms {
	Map<String, Room> rooms;

	public Rooms() {
		rooms = RoomDAO.getMapOfRooms();
	}

	public Map<String, Room> getRooms() {
		return rooms;
	}

	public void setRooms(Map<String, Room> rooms) {
		this.rooms = rooms;
	}
}
