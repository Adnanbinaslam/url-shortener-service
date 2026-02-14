package com.adnan.urlshortener.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adnan.urlshortener.entity.Url;

@Repository
public interface UrlShortenerRepository extends  JpaRepository<Url,Long>{
Optional<Url> findByShortCode(String shortCode);

Optional<Url> findByOriginalUrl(String originalUrl);
}
