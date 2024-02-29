WITH ElvisArtists AS (
    SELECT a.name AS ARTIST_NAME,
           EXTRACT(MONTH FROM to_timestamp(ri.date_month::text, 'MM')) AS RELEASE_MONTH,
           COUNT(*) AS NUM_RELEASES
    FROM artist_credit ac
             JOIN artist_credit_name acn ON ac.id = acn.artist_credit
             JOIN artist a ON acn.artist = a.id
             JOIN artist_type at ON a.type = at.id
             JOIN release r ON ac.id = r.artist_credit
             JOIN release_info ri ON r.id = ri.release
    WHERE at.name = 'Person' AND a.name LIKE 'Elvis%'
      AND ri.date_month IS NOT NULL
    GROUP BY a.name, EXTRACT(MONTH FROM to_timestamp(ri.date_month::text, 'MM'))
),
     MaxReleases AS (
         SELECT ARTIST_NAME,
                RELEASE_MONTH,
                NUM_RELEASES,
                ROW_NUMBER() OVER (PARTITION BY ARTIST_NAME ORDER BY NUM_RELEASES DESC, RELEASE_MONTH DESC) AS rn
         FROM ElvisArtists
     )
SELECT ARTIST_NAME, RELEASE_MONTH, NUM_RELEASES
FROM MaxReleases
WHERE rn = 1
ORDER BY NUM_RELEASES DESC, ARTIST_NAME;
