SELECT CONCAT(start_decade, 's') AS DECADE,
       COUNT(*) AS NUM_ARTIST_GROUP
FROM (
    SELECT FLOOR(a.begin_date_year / 10) * 10 AS start_decade
    FROM artist a
        JOIN artist_type at ON a.type = at.id
        JOIN area ar ON a.area = ar.id
    WHERE a.begin_date_year >= 1900
        AND a.begin_date_year <= 2023
        AND at.name = 'Group'
        AND ar.name = 'United States'
    ) AS decades
GROUP BY start_decade
ORDER BY start_decade;
