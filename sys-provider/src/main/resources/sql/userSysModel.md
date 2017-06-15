deleteByUserId
===
DELETE FROM p_user_sys WHERE user_id = #userId#

findByUserId
===
SELECT us.*,s.sys_url,s.sys_name FROM p_user_sys us 
LEFT JOIN p_sys s ON s.sys_id = us.sys_id 
WHERE us.user_id = #userId#