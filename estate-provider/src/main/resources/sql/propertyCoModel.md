query
===
SELECT * FROM e_property_co WHERE 1=1 
@if(authCode!='0'){
    AND auth_code LIKE #authCode+'%'#
@}
ORDER BY pc_id

query$count
===
SELECT COUNT(1) FROM e_property_co WHERE 1=1 
@if(authCode!='0'){
    AND auth_code LIKE #authCode+'%'#
@}
