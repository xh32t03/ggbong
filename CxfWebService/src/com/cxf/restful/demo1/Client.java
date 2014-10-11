package com.cxf.restful.demo1;

import org.apache.cxf.jaxrs.client.WebClient;

import com.cxf.restful.demo1.entity.Room;


public class Client {
	static WebClient client;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		client = WebClient.create("http://localhost:9999/");
		put();
	}

	static void get() {
		Room room = client.path("roomservice/room/001")
				.accept("application/xml").get(Room.class);
		System.out.println("get the room which id is:" + room.getId());
	}

	static void post() {
		Room room = new Room();
		room.setId("003");
		client.path("roomservice/room").accept("application/xml")
				.post(room, Room.class);
	}

	static void delete() {
		client.path("roomservice/room/002").accept("application/xml").delete();
	}

	static void put() {
		Room room = new Room();
		room.setId("007");
		client.path("roomservice/room/007").accept("application/xml").put(room);
	}
}