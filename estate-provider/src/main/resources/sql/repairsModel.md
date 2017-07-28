query
===
SELECT rp.*,com.COM_NAME,bu.BUD_NAME,br.CELL_NAME,br.FLOOR,
br.ROOM_NAME,ow.OWN_NAME,ow.TELEPHONE,ow.ID_NUMBER FROM e_repairs rp 
LEFT JOIN e_owner ow ON ow.own_id = rp.own_id
LEFT JOIN e_building_room br ON br.bur_id = ow.bur_id
LEFT JOIN e_building bu ON bu.bud_id = ow.bud_id
LEFT JOIN e_community com ON com.com_id = rp.com_id WHERE 1=1 
@if(authCode!='0'){
    AND rp.auth_code LIKE #authCode+'%'#
@}
@if(!isEmpty(comId)){
    AND rp.comId = #comId#
@}
@if(!isEmpty(state)){
    AND rp.state = #state#
@}
@if(!isEmpty(title)){
    AND rp.title LIKE #'%'+title+'%'#
@}
@if(!isEmpty(ownName)){
    AND ow.own_name LIKE #'%'+ownName+'%'#
@}
@if(!isEmpty(telephone)){
    AND ow.telephone LIKE #'%'+telephone+'%'#
@}
ORDER BY rp.RE_DATE DESC

query$count
===
SELECT COUNT(1) FROM e_repairs rp 
LEFT JOIN e_owner ow ON ow.own_id = rp.own_id
LEFT JOIN e_building_room br ON br.bur_id = ow.bur_id
LEFT JOIN e_building bu ON bu.bud_id = ow.bud_id
LEFT JOIN e_community com ON com.com_id = rp.com_id WHERE 1=1 
@if(authCode!='0'){
    AND rp.auth_code LIKE #authCode+'%'#
@}
@if(!isEmpty(comId)){
    AND rp.comId = #comId#
@}
@if(!isEmpty(state)){
    AND rp.state = #state#
@}
@if(!isEmpty(title)){
    AND rp.title LIKE #'%'+title+'%'#
@}
@if(!isEmpty(ownName)){
    AND ow.own_name LIKE #'%'+ownName+'%'#
@}
@if(!isEmpty(telephone)){
    AND ow.telephone LIKE #'%'+telephone+'%'#
@}
ORDER BY rp.RE_DATE DESC

selectById
===
SELECT rp.*,com.COM_NAME,bu.BUD_NAME,br.CELL_NAME,br.FLOOR,
br.ROOM_NAME,ow.OWN_NAME,ow.TELEPHONE,ow.ID_NUMBER FROM e_repairs rp 
LEFT JOIN e_owner ow ON ow.own_id = rp.own_id
LEFT JOIN e_building_room br ON br.bur_id = ow.bur_id
LEFT JOIN e_building bu ON bu.bud_id = ow.bud_id
LEFT JOIN e_community com ON com.com_id = rp.com_id 
WHERE rp.re_id = #reId#