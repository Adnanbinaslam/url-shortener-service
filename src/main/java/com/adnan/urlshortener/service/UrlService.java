package com.adnan.urlshortener.service;

import com.adnan.urlshortener.DTO.UrlRequestDto;
import com.adnan.urlshortener.DTO.UrlResponseDto;

public interface UrlService {

    UrlResponseDto createShortUrl(UrlRequestDto url);

    String getOriginalUrl(String shortCode);

    
}
