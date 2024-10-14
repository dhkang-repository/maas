package com.xmass.cloud.infrastructure.event;

import org.springframework.context.ApplicationEvent;

public class WebSocketDataEvent extends ApplicationEvent {
    private final UpbitResponse message;

    public WebSocketDataEvent(Object source, UpbitResponse message) {
        super(source);
        this.message = message;
    }

    public UpbitResponse getMessage() {
        return message;
    }
}
