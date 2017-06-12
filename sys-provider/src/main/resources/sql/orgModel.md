query
===
SELECT * FROM p_org WHERE 1=1
@if(!isEmpty(parentOrgId)){
   AND PARENT_ORG_ID = #parentOrgId#
@}
ORDER BY AUTH_CODE

query$count
===
SELECT COUNT(1) FROM p_org WHERE 1=1
@if(!isEmpty(parentOrgId)){
   AND PARENT_ORG_ID = #parentOrgId#
@}