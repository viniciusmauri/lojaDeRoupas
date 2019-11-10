package com.example.lojamarcao.event.listener;

import com.example.lojamarcao.event.RecursoCriadoEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent>{

    @Override
    public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent){

        HttpServletResponse response = recursoCriadoEvent.getResponse();
        Long cod = recursoCriadoEvent.getCod();

        AdicionarHeader(response, cod);
    }

    private void AdicionarHeader(HttpServletResponse response, Long cod) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{cod}")
                .buildAndExpand(cod).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}