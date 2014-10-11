package com.cxf.restful.demo1.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.cxf.restful.demo1.dao.RoomDAO;
import com.cxf.restful.demo1.entity.Person;
import com.cxf.restful.demo1.entity.Room;
import com.cxf.restful.demo1.entity.Rooms;

/**
 * http://localhost:9999/service 为入口，
   http://localhost:9999/service/room 为房间列表,
   http://localhost:9999/service/room/001/ 为001号房间的信息，
   http://localhost:9999/service/room/001/person 为在001号房间主的人的列表
 *
 * @Title:
 * @Description:
 * @Copyright:Copyright (c) 2012
 * @Company:SHERRY-PC
 * @Date:2012-6-25
 * @author joly
 * @version 1.0
 */
@Path("/service")
@Produces("application/xml")
public class RoomService {

	@GET
	@Path("/room/{id}")
	@Consumes("application/xml")
	public Room getRoom(@PathParam("id") String id) {
		System.out.println("get room by id= " + id);
		Room room = RoomDAO.getRoom(id);
		return room;
	}

	@GET
	@Path("/room")
	@Consumes("application/xml")
	public Rooms getAllRoom() {
		System.out.println("get all room");
		Rooms rooms = RoomDAO.getRooms();
		return rooms;
	}

	@POST
	@Path("/room")
	@Consumes("application/xml")
	public void addRoom(Room room) {
		System.out.println("add room which id is:" + room.getId());
		RoomDAO.addRoom(room);
	}

	@PUT
	@Path("/room/{id}")
	@Consumes("application/xml")
	public void updateRoom(@PathParam("id") String id, Room room) {
		System.out.println("update room which original id is:" + room.getId());
		RoomDAO.updateRoom(id, room);
	}

	@DELETE
	@Path("/room/{id}")
	@Consumes("application/xml")
	public void deleteRoom(@PathParam("id") String id) {
		System.out.println("remove room by id= " + id);
		RoomDAO.deleteRoom(id);
	}

	@POST
	@Path("/room/{id}")
	@Consumes("application/xml")
	public void addPerson(@PathParam("id") String id, Person person) {
		System.out.println("add person who's name is:" + person.getName());
		RoomDAO.addPerson(id, person);
	}

	@DELETE
	@Path("/room/{id}/{name}")
	@Consumes("application/xml")
	public void deletePerson(@PathParam("id") String id,
			@PathParam("name") String name) {
		System.out.println("remove person who's name is: " + name);
		RoomDAO.deletePerson(id, name);
	}
}
