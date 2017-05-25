query
===
select u.*,o.org_name,g.group_name,pr.role_name,
pr.role_id from p_user u 
left join p_org o on u.org_id = o.org_id 
left join p_group g on u.group_id = g.group_id
left join p_user_role psr on psr.user_id = u.user_id 
left join p_role pr on pr.role_id = psr.role_id
where 1=1
@if(!isEmpty(loginName)){
    and u.login_name = #loginName#
@}
@if(!isEmpty(realName)){
    and u.real_name = #realName#
@}
order by u.user_id desc

query$count
===
select count(1) from p_user u 
left join p_org o on u.org_id = o.org_id 
left join p_group g on u.group_id = g.group_id
left join p_user_role psr on psr.user_id = u.user_id 
left join p_role pr on pr.role_id = psr.role_id
where 1=1
@if(!isEmpty(loginName)){
    and u.login_name = #loginName#
@}
@if(!isEmpty(realName)){
    and u.real_name = #realName#
@}
