query
===
SELECT com.*,pc.PC_NAME,
(SELECT COUNT(1) FROM e_owner WHERE com_id = com.com_id) AS OWN_NUM,
(SELECT COUNT(1) FROM e_owner WHERE com_id = com.com_id AND auth_state = '1') AS OWN_AUTH_NUM, 
(SELECT COUNT(1) FROM e_owner WHERE com_id = com.com_id AND auth_state = '0') AS APPLY_AUTH_NUM 
FROM e_community com 
LEFT JOIN e_property_co pc ON com.pc_id = pc.pc_id WHERE 1=1 
@if(!isEmpty(pcName)){
    AND pc.pc_name LIKE #'%'+pcName+'%'#
@}
@if(!isEmpty(comName)){
    AND com.com_name LIKE #'%'+comName+'%'#
@}
@if(authCode!='0'){
    AND com.auth_code LIKE #authCode+'%'#
@}
ORDER BY com.pc_id


query$count
===
SELECT COUNT(1) FROM e_community com 
LEFT JOIN e_property_co pc ON com.pc_id = pc.pc_id WHERE 1=1 
@if(!isEmpty(pcName)){
    AND pc.pc_name LIKE #'%'+pcName+'%'#
@}
@if(!isEmpty(comName)){
    AND com.com_name LIKE #'%'+comName+'%'#
@}
@if(authCode!='0'){
    AND com.auth_code LIKE #authCode+'%'#
@}