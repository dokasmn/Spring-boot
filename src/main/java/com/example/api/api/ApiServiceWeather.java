package com.example.api.api;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.Proxy;
import java.net.InetSocketAddress;

@Service
public class ApiServiceWeather {
    public String getWeather(){
        String url = "https://api.open-meteo.com/v1/forecast?"+
                "latitude=35&" +
                "longitude=139&"+
                "hourly=temperature";

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP,
                new InetSocketAddress("proxy.br.bosch.com",80));
        factory.setProxy(proxy);

        RestTemplate restTemplate = new RestTemplate(factory);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}
