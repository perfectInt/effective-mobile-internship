package ru.effectivemobile.shorturl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.effectivemobile.shorturl.model.Url;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByShortUrl(String shortUrl);
}
