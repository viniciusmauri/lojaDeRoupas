package com.example.lojamarcao.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class RecursoCriadoEvent extends ApplicationEvent{

    private static final long serialVersionUID = 1L;

    private HttpServletResponse response;
    private Long cod;

    public RecursoCriadoEvent(Object source, HttpServletResponse response, Long codigo){
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