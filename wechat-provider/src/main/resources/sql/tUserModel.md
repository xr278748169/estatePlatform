query
===
SELECT * FROM t_user WHERE 1=1 
@if(!isEmpty(accountId)){
    AND ACCOUNT_ID = #accountId#
@}
ORDER BY FOCUS_TIME

query$count
===
SELECT COUNT(1) FROM t_user WHERE 1=1 
@if(!isEmpty(accountId)){
    AND ACCOUNT_ID = #accountId#
@}
ORDER BY FOCUS_TIME