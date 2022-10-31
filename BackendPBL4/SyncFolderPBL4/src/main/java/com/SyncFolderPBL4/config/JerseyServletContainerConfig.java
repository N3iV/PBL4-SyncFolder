package com.SyncFolderPBL4.config;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
 
public class JerseyServletContainerConfig extends ResourceConfig {
    public JerseyServletContainerConfig() {
        packages("com.SyncFolderPBL4.controller.api");
        register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), Level.INFO,
                LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));
        register(JacksonFeature.class); 
        register(MultiPartFeature.class); 
    }
}
