package com.mash5.rest;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/mash5")
@Produces("application/json,application/xml")
@Consumes("application/json,application/xml")
public interface Mash5Interface {
	
	@POST
	public String getStringPost();
	
	@GET
	public String getStringGet();

	@GET
	@Path("/getMapVK")
	public Map<String, Object> getMapVK();
}
