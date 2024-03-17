package com.nhnacademy.edu.springframework.dooray;

import com.nhn.dooray.client.DoorayHook;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class DoorayHookSender {
    private RestTemplate resttemplate;
    private String url;

    public DoorayHookSender(RestTemplate resttemplate, String url) {
        this.resttemplate = resttemplate;
        this.url = url;
    }

    public boolean send(DoorayHook doorayHook) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<DoorayHook> entity = new HttpEntity(doorayHook, headers);
        ResponseEntity<String> exchange = this.resttemplate.exchange(this.url, HttpMethod.POST, entity, String.class, new Object[0]);
        return true;
    }
}
