SELECT
 CAR_TYPE,
 COUNT(*) CARS
FROM CAR_RENTAL_COMPANY_CAR 
WHERE options like '%통풍시트%' OR options like '%열선시트%' OR options like '%가죽시트%'
GROUP BY CAR_TYPE
ORDER BY CAR_TYPE