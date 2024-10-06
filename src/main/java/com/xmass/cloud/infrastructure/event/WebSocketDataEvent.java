package com.xmass.cloud.infrastructure.event;

import org.springframework.context.ApplicationEvent;

public class WebSocketDataEvent extends ApplicationEvent {
    private final String message;

    public WebSocketDataEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
