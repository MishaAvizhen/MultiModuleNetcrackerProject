package com.netcracker.avizhen.services.config;

import com.netcracker.avizhen.persistence.config.PersistenceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Александр on 30.11.2016.
 */
@Configuration
@Import(PersistenceConfig.class)
@ComponentScan({"com.netcracker.avizhen.services"})
public class ServiceConfig {

}
