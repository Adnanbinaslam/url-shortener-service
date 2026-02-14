package com.adnan.urlshortener.service.Impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.adnan.urlshortener.DTO.UrlRequestDto;
import com.adnan.urlshortener.DTO.UrlResponseDto;
import com.adnan.urlshortener.entity.Url;
import com.adnan.urlshortener.exception.UrlNotFoundException;
import com.adnan.urlshortener.repository.UrlShortenerRepository;
import com.adnan.urlshortener.service.UrlService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final EncoderHelper encoderHelper;

    private final UrlShortenerRepository repository;

    private final ModelMapper modelMapper;

    @Override
    public UrlResponseDto createShortUrl(UrlRequestDto urlRequestDto) {

        Optional<Url> exists = repository.findByOriginalUrl(urlRequestDto.getOriginalUrl());

        Url url;
        if (exists.isPresent()) {// if the db has url previously
            url = exists.get();
        } else {
            
            url = modelMapper.map(urlRequestDto, Url.class);

            repository.save(url);

            Long id = url.getId();

            String shortCode = encoderHelper.encode(id);

            url.setShortCode(shortCode);

            repository.save(url);
        }

        return modelMapper.map(url, UrlResponseDto.class);

    }

    @Override
    public String getOriginalUrl(String shortCode) {
        Url url = repository.findByShortCode(shortCode).orElseThrow(() -> new UrlNotFoundException("Short URL not found: " + shortCode));

        Long currClick = url.getClickCount();
        currClick += 1;
        url.setClickCount(currClick);
        repository.save(url);

        return url.getOriginalUrl();

    }

}
