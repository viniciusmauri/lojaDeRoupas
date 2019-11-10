package com.example.lojamarcao.event;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class RecursoCriadoEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private HttpServletResponse response;
    private Long cod;

    public RecursoCriadoEvent(Object source, HttpServletResponse response, Long cod) {
        super(source);
        this.response = response;
        this.cod = cod;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public Long getCod() {
        return cod;
    }
}