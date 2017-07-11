query
===
SELECT br.*,bud.BUD_NAME,com.COM_NAME FROM e_building_room br 
LEFT JOIN e_building bud ON br.bud_id = bud.bud_id 
LEFT JOIN e_community com ON com.com_id = bud.com_id
WHERE br.bud_id = #budId#
@if(authCode!='0'){
    AND br.auth_code LIKE #authCode+'%'#
@}
@if(!isEmpty(cellName)){
    AND br.cell_name = #cellName#
@}
@if(!isEmpty(floor)){
    AND br.floor = #floor#
@}
@if(!isEmpty(roomName)){
    AND br.room_name = #roomName#
@}
ORDER BY br.CELL_NAME,br.ROOM_NAME

query$count
===
SELECT COUNT(1) FROM e_building_room br 
LEFT JOIN e_building bud ON br.bud_id = bud.bud_id 
LEFT JOIN e_community com ON com.com_id = bud.com_id
WHERE br.bud_id = #budId#
@if(authCode!='0'){
    AND br.auth_code LIKE #authCode+'%'#
@}
@if(!isEmpty(cellName)){
    AND br.cell_name = #cellName#
@}
@if(!isEmpty(floor)){
    AND br.floor = #floor#
@}
@if(!isEmpty(roomName)){
    AND br.room_name = #roomName#
@}
ORDER BY br.CELL_NAME,br.ROOM_NAME