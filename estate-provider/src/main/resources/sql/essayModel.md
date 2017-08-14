query
===
SELECT * FROM r_essay WHERE 1=1
@if(!isEmpty(authCode)&&authCode!='0'){
    AND auth_code LIKE #authCode+'%'#
@}
@if(!isEmpty(title)){
    AND title LIKE #'%'+title+'%'#
@}
@if(!isEmpty(msgType)){
    AND msg_type = #msgType#
@}
@if(!isEmpty(msgType)){
    AND msg_type = #msgType#
@}
ORDER BY release_date DESC

query$count
===
SELECT COUNT(1) FROM r_essay WHERE 1=1
@if(!isEmpty(authCode)&&authCode!='0'){
    AND auth_code LIKE #authCode+'%'#
@}
@if(!isEmpty(title)){
    AND title LIKE #'%'+title+'%'#
@}
@if(!isEmpty(msgType)){
    AND msg_type = #msgType#
@}
ORDER BY release_date DESC