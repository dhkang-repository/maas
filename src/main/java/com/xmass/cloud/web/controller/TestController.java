package com.xmass.cloud.web.controller;

import org.apache.logging.slf4j.Log4jMarker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/log")
    public String log() {
        LOGGER.trace("trace log");
        LOGGER.debug("debug log");
        LOGGER.info("info log");
        LOGGER.warn("warn log");
        LOGGER.error("error log");
        LOGGER.trace(MarkerFactory.getMarker("HTTP"), "trace log");
        LOGGER.debug(MarkerFactory.getMarker("HTTP"), "debug log");
        LOGGER.info(MarkerFactory.getMarker("HTTP"), "info log");
        LOGGER.warn(MarkerFactory.getMarker("HTTP"), "warn log");
        LOGGER.error(MarkerFactory.getMarker("HTTP"), "error log");
        return "log";
    }
}
