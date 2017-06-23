query
===
SELECT * FROM t_account WHERE 1=1
@if(authCode!='0'){
    AND auth_code LIKE #authCode+'%'#
@}
@if(!isEmpty(accountName)){
    AND account_name = #accountName#
@}
ORDER BY account_id

query$count
===
SELECT COUNT(1) FROM t_account WHERE 1=1
@if(authCode!='0'){
    AND auth_code LIKE #authCode+'%'#
@}
@if(!isEmpty(accountName)){
    AND account_name = #accountName#
@}
