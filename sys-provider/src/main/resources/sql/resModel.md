query
===
SELECT * FROM p_res WHERE 1=1
@if(!isEmpty(resType)){
    AND res_type = #resType#
@}
@if(!isEmpty(parentId)){
    AND parent_res_id = #parentId#
@}else{
    AND parent_res_id IS NULL
@}
ORDER BY order_num

query$count
===
SELECT COUNT(1) FROM p_res WHERE 1=1
@if(!isEmpty(resType)){
    AND res_type = #resType#
@}
@if(!isEmpty(parentId)){
    AND parent_res_id = #parentId#
@}else{
    AND parent_res_id IS NULL
@}
ORDER BY order_num

querySubRes
===
SELECT * FROM p_res WHERE 1=1
@if(!isEmpty(parentId)){
    AND parent_res_id = #parentId#
@}else{
    AND parent_res_id IS NULL
@}
ORDER BY order_num