package com.SyncFolderPBL4.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
 
public class JerseyServletContainerConfig extends ResourceConfig {
    public JerseyServletContainerConfig() {
        packages("com.SyncFolderPBL4.controller.api");
        register(JacksonFeature.class); 
        register(MultiPartFeature.class); 
    }
}
