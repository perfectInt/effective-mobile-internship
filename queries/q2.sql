SELECT release.name AS RELEASE_NAME, artist_credit.name AS ARTIST_NAME, release_info.date_year AS RELEASE_YEAR
FROM release
         JOIN artist_credit ON release.artist_credit = artist_credit.id
         JOIN release_info ON release.id = release_info.release
    AND release_info.date_year IS NOT NULL
         JOIN medium ON medium.release = release.id
         JOIN medium_format ON medium.format = medium_format.id
    AND medium_format.name = 'Cassette'
ORDER BY release_info.date_year DESC, release_info.date_month DESC, release_info.date_day DESC, RELEASE_NAME, ARTIST_NAME
LIMIT 10;