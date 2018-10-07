package com.mohammadreza.mirali.energyconsumption;

import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by mmirali on 07/10/2018.
 */
@Named("FractionService")
@Path("/")
public class FractionService {



    @GET
    @Path("/test")
    public void transactions()
    {
        System.out.println("Thisi iiii");
    }
}
