package com.cxf.restful.demo1;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

import com.cxf.restful.demo1.entity.Person;
import com.cxf.restful.demo1.entity.Room;
import com.cxf.restful.demo1.entity.Rooms;
import com.cxf.restful.demo1.service.RoomService;

public class Server {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		RoomService service = new RoomService();

		// Service instance
		JAXRSServerFactoryBean restServer = new JAXRSServerFactoryBean();
		restServer.setResourceClasses(Room.class, Person.class, Rooms.class);
		restServer.setServiceBean(service);
		restServer.setAddress("http://localhost:9999/");
		restServer.create();
	}
}
