package com.adnan.urlshortener.service.Impl;


import org.springframework.stereotype.Component;

@Component
public class EncoderHelper {
    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

public String encode(Long id) {
    if (id == 0) return "0";

    StringBuilder result = new StringBuilder();

    while (id > 0) {
        int remainder = (int) (id % 62);
        result.append(BASE62.charAt(remainder));
        id = id / 62;
    }

    return result.reverse().toString();
}

}
