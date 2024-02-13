package ru.effectivemobile.shorturl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.effectivemobile.shorturl.model.UrlDto;
import ru.effectivemobile.shorturl.service.UrlService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/short-url")
public class UrlController {

    private final UrlService urlService;

    @PostMapping
    public String shortenUrl(@RequestBody UrlDto urlDto) {
        return urlService.createShortUrl(urlDto);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<?> redirectToUrl(@PathVariable String shortUrl) {
        String url = urlService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, url).build();
    }
}
