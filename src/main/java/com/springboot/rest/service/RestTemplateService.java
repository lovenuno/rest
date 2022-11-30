package com.springboot.rest.service;

import com.springboot.rest.dto.MemberDto;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    public String getName(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("api/v1/crud-api")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity(uri,String.class);

        return responseEntity.getBody();
    }

    public String getNameWithPathVariable(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/v1/crud-api/{name}")
                .encode()
                .build()
                .expand("Flature")
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        return responseEntity.getBody();
    }

    public String getNameWithParameter(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/v1/crud-api/param")
                .queryParam("name", "Flature")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        return responseEntity.getBody();
    }

    public ResponseEntity<MemberDto> postWithParamAndBody(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/v1/crud-api")
                .queryParam("name", "Flature")
                .queryParam("email", "nuno08@gmail.com")
                .queryParam("organization", "Wikibooks")
                .encode()
                .build()
                .toUri();

        MemberDto memberDto = new MemberDto();
        memberDto.setName("flature!!");
        memberDto.setEmail("nuno08@gmail.com");
        memberDto.setOrganization("Around hub Studio");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDto> responseEntity =
                restTemplate.postForEntity(uri, memberDto, MemberDto.class);

        return responseEntity;
    }

    public ResponseEntity<MemberDto> postWithHeader(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/v1/crud-api/add-header")
                .encode()
                .build()
                .toUri();

        MemberDto memberDto = new MemberDto();
        memberDto.setName("flature");
        memberDto.setEmail("nuno08@gmail.com");
        memberDto.setOrganization("Around hub Studio");

        RequestEntity<MemberDto> requestEntity = RequestEntity
                .post(uri)
                .header("my-header", "Wikibooks API")
                .body(memberDto);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDto> responseEntity = restTemplate.exchange(
                requestEntity, MemberDto.class
        );

        return responseEntity;

    }
}
