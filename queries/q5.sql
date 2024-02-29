SELECT a.name AS ARTIST_NAME,
       COUNT(aa.name) AS NUM_ALIASES,
       STRING_AGG(aa.name, ', ' ORDER BY aa.name) AS COMMA_SEPARATED_LIST_OF_ALIASES
FROM artist a
    JOIN artist_alias aa ON a.id = aa.artist
WHERE a.name LIKE '%John'
    AND aa.name NOT LIKE '%John%'
GROUP BY a.name
ORDER BY artist_name;