query
===
SELECT * FROM c_dictionary 
WHERE 1=1
@if(!isEmpty(dictTypeCode)){
    AND dict_type_code = #dictTypeCode#
@}
ORDER BY order_num asc

query$count
===
SELECT COUNT(1) FROM c_dictionary 
WHERE 1=1
@if(!isEmpty(dictTypeCode)){
    AND dict_type_code = #dictTypeCode#
@}
ORDER BY order_num asc