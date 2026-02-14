package com.adnan.urlshortener.controller;


import org.springframework.web.bind.annotation.RestController;

import com.adnan.urlshortener.DTO.UrlRequestDto;
import com.adnan.urlshortener.DTO.UrlResponseDto;
import com.adnan.urlshortener.service.Impl.UrlServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@CrossOrigin(origins = "*") 
@RequiredArgsConstructor
public class UrlController {

    private final UrlServiceImpl urlServiceImpl;
    
    @PostMapping("/create")
    public ResponseEntity<UrlResponseDto> postMethodName(@RequestBody UrlRequestDto request) {
        
        return ResponseEntity.ok(urlServiceImpl.createShortUrl(request));
    }
    @GetMapping("r/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {

        String originalUrl = urlServiceImpl.getOriginalUrl(shortCode);

        URI uri = URI.create(originalUrl);
        return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
    
    }
}
