SELECT RELEASE_NAME, RELEASE_YEAR
FROM (
         SELECT release.name AS RELEASE_NAME,
                release_info.date_year AS RELEASE_YEAR,
                ROW_NUMBER() OVER (PARTITION BY release.name ORDER BY release_info.date_year) AS row_num
         FROM release
                  JOIN release_info ON release.id = release_info.release
             AND release_info.date_year < 1970
                  JOIN area ON release_info.area = area.id
             AND area.name = 'United Kingdom'
                  JOIN medium ON medium.release = release.id
                  JOIN medium_format ON medium.format = medium_format.id
             AND medium_format.name = '12" Vinyl'
                  JOIN artist_credit ON release.artist_credit = artist_credit.id
             AND artist_credit.name = 'The Beatles'
     ) AS ranked_releases
WHERE row_num = 1
ORDER BY RELEASE_YEAR, RELEASE_NAME;
