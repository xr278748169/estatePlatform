deleteByRoleId
===
DELETE FROM p_role_res WHERE role_id = #roleId#

findByRoleId
===
SELECT rr.*,res.res_name,res.res_url 
FROM p_role_res rr LEFT JOIN p_res res 
ON res.res_id = rr.res_id 
WHERE rr.role_id = #roleId# 
@if(!isEmpty(isRoot)){
    or rr.ext2 = #isRoot#
@}else{
    and rr.ext2 IS NULL
@}