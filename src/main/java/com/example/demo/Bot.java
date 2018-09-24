package com.example.demo;

import org.fluentd.logger.FluentLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class Bot {



    String host;
    int port;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private FluentLogger loggerF =null;

    public Bot(@Value("${fluentd.host}") String host,@Value("${fluentd.port}") int port) {
        this.host = host;
        this.port = port;
        loggerF=FluentLogger.getLogger("fluentd.test",host,port);
    }

    @PostConstruct
    @Scheduled(fixedDelay = 1000L)
    public void say(){
        Map map = initMap();
        long dt=System.currentTimeMillis();
        String message="hello!!!!!";
        map.put("message",message);
        loggerF.log("demoapp",map);
        logger.debug(message+" at "+dt);
    }

    private Map initMap() {
        HashMap<String,Object> map=new HashMap<>();
        map.put("level","debug");
        return map;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
