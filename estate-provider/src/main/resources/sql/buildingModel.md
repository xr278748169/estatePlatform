query
===
SELECT bu.*,com.COM_NAME FROM e_building bu 
LEFT JOIN e_community com ON bu.com_id = com.com_id
WHERE bu.com_id = #comId#
@if(authCode!='0'){
    AND bu.auth_code LIKE #authCode+'%'#
@}
@if(!isEmpty(budName)){
    AND bu.bud_name LIKE #'%'+budName+'%'#
@}
ORDER BY bu.bud_id

query$count
===
SELECT COUNT(1) FROM e_building bu 
LEFT JOIN e_community com ON bu.com_id = com.com_id
WHERE bu.com_id = #comId#
@if(authCode!='0'){
    AND bu.auth_code LIKE #authCode+'%'#
@}
@if(!isEmpty(budName)){
    AND bu.bud_name LIKE #'%'+budName+'%'#
@}
ORDER BY bu.bud_id