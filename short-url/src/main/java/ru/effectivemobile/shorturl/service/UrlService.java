package ru.effectivemobile.shorturl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.effectivemobile.shorturl.exceptions.NotFoundException;
import ru.effectivemobile.shorturl.model.Url;
import ru.effectivemobile.shorturl.model.UrlDto;
import ru.effectivemobile.shorturl.repository.UrlRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    public String getOriginalUrl(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl).orElseThrow(NotFoundException::new);
        return url.getOriginalUrl();
    }

    @Transactional
    public String createShortUrl(UrlDto urlDto) {
        String shortUrl = UUID.randomUUID().toString().substring(0, 4);
        Url url = new Url();
        if (urlDto.getOriginalUrl().startsWith("http://") || urlDto.getOriginalUrl().startsWith("https://"))
            url.setOriginalUrl(urlDto.getOriginalUrl());
        else
            url.setOriginalUrl("http://" + urlDto.getOriginalUrl());
        url.setShortUrl(shortUrl);
        return urlRepository.save(url).getShortUrl();
    }
}
