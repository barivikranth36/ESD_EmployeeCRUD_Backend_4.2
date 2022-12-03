package com.example.microservice;

import com.example.microservice.Util.CORSFilter;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class HelloApplication extends Application {
//    // Registering the CORSFilter class with the Jersey ResourceConfig
//            register(CORSFilter.class);
//
//    // Telling Jersey the CLASSPATH where the specified classes (in our case, CORSFilter) can be found
//    packages("com.academia.payment");
}