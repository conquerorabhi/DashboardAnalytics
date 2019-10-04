package com.poc.DashboardAnalytics;



import javax.ws.rs.*;

/**
 * Created by asingh on 8/5/19.
 */
@Path("/devices/{deviceId}")
public class DeviceResource {

    @GET
    @Produces("text/json")
    public String getDevices(@PathParam("deviceId") String deviceId){
        return null;
    }

    @POST
    @Consumes("test/json")
    public void createDevice(){

    }

}
