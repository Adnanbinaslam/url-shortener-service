package com.adnan.urlshortener.DTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UrlResponseDto {
    private Long id;

    private String shortUrl;
    
}