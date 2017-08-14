query
===
SELECT su.*,com.COM_NAME,bu.BUD_NAME,br.CELL_NAME,br.FLOOR,
br.ROOM_NAME,ow.OWN_NAME,ow.TELEPHONE,ow.ID_NUMBER FROM e_suit su 
LEFT JOIN e_owner ow ON ow.own_id = su.own_id
LEFT JOIN e_building_room br ON br.bur_id = ow.bur_id
LEFT JOIN e_building bu ON bu.bud_id = ow.bud_id
LEFT JOIN e_community com ON com.com_id = su.com_id WHERE 1=1 
@if(!isEmpty(authCode)&&authCode!='0'){
    AND su.auth_code LIKE #authCode+'%'#
@}
@if(!isEmpty(comId)){
    AND su.comId = #comId#
@}
@if(!isEmpty(state)){
    AND su.state = #state#
@}
@if(!isEmpty(title)){
    AND su.title LIKE #'%'+title+'%'#
@}
@if(!isEmpty(ownId)){
    AND su.own_id = #ownId#
@}
@if(!isEmpty(ownName)){
    AND ow.own_name LIKE #'%'+ownName+'%'#
@}
@if(!isEmpty(telephone)){
    AND ow.telephone LIKE #'%'+telephone+'%'#
@}
ORDER BY su.ST_DATE DESC

query$count
===
SELECT COUNT(1) FROM e_suit su 
LEFT JOIN e_owner ow ON ow.own_id = su.own_id
LEFT JOIN e_building_room br ON br.bur_id = ow.bur_id
LEFT JOIN e_building bu ON bu.bud_id = ow.bud_id
LEFT JOIN e_community com ON com.com_id = su.com_id WHERE 1=1 
@if(!isEmpty(authCode)&&authCode!='0'){
    AND su.auth_code LIKE #authCode+'%'#
@}
@if(!isEmpty(comId)){
    AND su.comId = #comId#
@}
@if(!isEmpty(state)){
    AND su.state = #state#
@}
@if(!isEmpty(title)){
    AND su.title LIKE #'%'+title+'%'#
@}
@if(!isEmpty(ownId)){
    AND su.own_id = #ownId#
@}
@if(!isEmpty(ownName)){
    AND ow.own_name LIKE #'%'+ownName+'%'#
@}
@if(!isEmpty(telephone)){
    AND ow.telephone LIKE #'%'+telephone+'%'#
@}
ORDER BY su.ST_DATE DESC


selectById
===
SELECT su.*,com.COM_NAME,bu.BUD_NAME,br.CELL_NAME,br.FLOOR,
br.ROOM_NAME,ow.OWN_NAME,ow.TELEPHONE,ow.ID_NUMBER FROM e_suit su 
LEFT JOIN e_owner ow ON ow.own_id = su.own_id
LEFT JOIN e_building_room br ON br.bur_id = ow.bur_id
LEFT JOIN e_building bu ON bu.bud_id = ow.bud_id
LEFT JOIN e_community com ON com.com_id = su.com_id
WHERE su.st_id = #stId#
