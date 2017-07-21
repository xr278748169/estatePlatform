query
===
SELECT ow.*,com.COM_NAME,bu.BUD_NAME,br.CELL_NAME,br.FLOOR,br.ROOM_NAME FROM e_owner ow 
LEFT JOIN e_building_room br ON br.bur_id = ow.bur_id 
LEFT JOIN e_building bu ON bu.BUD_ID = ow.BUD_ID 
LEFT JOIN e_community com ON com.com_id = ow.com_id WHERE 1=1
@if(authCode!='0'){
    AND ow.auth_code LIKE #authCode+'%'#
@}
@if(!isEmpty(comId)){
    AND ow.com_id = #comId#
@}
@if(!isEmpty(budId)){
    AND ow.bud_id = #budId#
@}
@if(!isEmpty(burId)){
    AND ow.bur_id = #burId#
@}
@if(!isEmpty(roomName)){
    AND br.room_name = #roomName#
@}
@if(!isEmpty(ownType)){
    AND ow.own_type = #ownType#
@}
@if(!isEmpty(ownName)){
    AND ow.own_name LIKE #'%'+ownName+'%'#
@}
@if(!isEmpty(idNumber)){
    AND ow.id_number LIKE #'%'+idNumber+'%'#
@}
@if(!isEmpty(telephone)){
    AND ow.telephone LIKE #'%'+telephone+'%'#
@}
@if(!isEmpty(ownState)){
    AND ow.own_state = #ownState#
@}
@if(!isEmpty(authState)){
    AND ow.auth_state = #authState#
@}
ORDER BY br.CELL_NAME,br.ROOM_NAME

query$count
===
SELECT COUNT(1) FROM e_owner ow 
LEFT JOIN e_building_room br ON br.bur_id = ow.bur_id 
LEFT JOIN e_building bu ON bu.BUD_ID = ow.BUD_ID 
LEFT JOIN e_community com ON com.com_id = ow.com_id WHERE 1=1
@if(authCode!='0'){
    AND ow.auth_code LIKE #authCode+'%'#
@}
@if(!isEmpty(comId)){
    AND ow.com_id = #comId#
@}
@if(!isEmpty(budId)){
    AND ow.bud_id = #budId#
@}
@if(!isEmpty(burId)){
    AND ow.bur_id = #burId#
@}
@if(!isEmpty(roomName)){
    AND br.room_name = #roomName#
@}
@if(!isEmpty(ownType)){
    AND ow.own_type = #ownType#
@}
@if(!isEmpty(ownName)){
    AND ow.own_name LIKE #'%'+ownName+'%'#
@}
@if(!isEmpty(idNumber)){
    AND ow.id_number LIKE #'%'+idNumber+'%'#
@}
@if(!isEmpty(telephone)){
    AND ow.telephone LIKE #'%'+telephone+'%'#
@}
@if(!isEmpty(ownState)){
    AND ow.own_state = #ownState#
@}
@if(!isEmpty(authState)){
    AND ow.auth_state = #authState#
@}
ORDER BY br.CELL_NAME,br.ROOM_NAME