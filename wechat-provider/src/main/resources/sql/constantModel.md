query
===
SELECT * FROM t_wx_constant where 1=1 
@if(!isEmpty(constantName)){
    AND constant_name = #constantName#    
@}
@if(!isEmpty(constantValue)){
    AND constant_value = #constantValue#
@}
ORDER BY constant_value

query$count
===
SELECT COUNT(1) FROM t_wx_constant where 1=1 
@if(!isEmpty(constantName)){
    AND constant_name = #constantName#    
@}
@if(!isEmpty(constantValue)){
    AND constant_value = #constantValue#
@}
