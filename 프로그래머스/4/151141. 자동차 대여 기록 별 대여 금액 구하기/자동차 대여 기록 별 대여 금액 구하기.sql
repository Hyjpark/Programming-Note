WITH rental_days AS (
    SELECT
        h.HISTORY_ID,
        c.CAR_ID,
        c.CAR_TYPE,
        c.DAILY_FEE,
        DATEDIFF(h.END_DATE, h.START_DATE) + 1 AS days
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY h
    JOIN CAR_RENTAL_COMPANY_CAR c
      ON h.CAR_ID = c.CAR_ID
    WHERE c.CAR_TYPE = '트럭'
),
discount_key AS (
    SELECT
        r.*,
        CASE
            WHEN r.days BETWEEN 7  AND 29 THEN '7일 이상'
            WHEN r.days BETWEEN 30 AND 89 THEN '30일 이상'
            WHEN r.days >= 90          THEN '90일 이상'
            ELSE NULL
        END AS duration_type
    FROM rental_days r
)
SELECT
    r.HISTORY_ID,
    ROUND(
        r.DAILY_FEE * r.days *
        (100 - IFNULL(p.DISCOUNT_RATE, 0)) / 100
    ) AS FEE
FROM discount_key r
LEFT JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN p
       ON p.CAR_TYPE = r.CAR_TYPE
      AND p.DURATION_TYPE = r.duration_type
ORDER BY FEE DESC, r.HISTORY_ID DESC;