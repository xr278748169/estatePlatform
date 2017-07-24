query
===
SELECT * FROM r_essay WHERE 1=1
@if(authCode!='0'){
    AND auth_code LIKE #authCode+'%'#
@}
ORDER BY release_date

query$count
===
SELECT COUNT(1) FROM r_essay WHERE 1=1
@if(authCode!='0'){
    AND auth_code LIKE #authCode+'%'#
@}
ORDER BY release_date