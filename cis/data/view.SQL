
--用户收入视图
create or replace view v_user_income
as
select u.user_id,
       u.id_card,
       u.name,
       u.sex,
       u.birthday,
       u.address,
       u.career,
       u.level,
       u.status,       
       u.user_role,
       u.upline_user,        
       u.extend1,       
       u.extend2,     
       ui.income pre_month_income,
       ui.performance pre_month_performance
from t_user u
left join t_user_income ui
on ui.user_id = u.user_id and date_format(ui.income_date,'%Y-%m')=date_format(DATE_SUB(curdate(), INTERVAL 1 MONTH),'%Y-%m') 
--用户审核信息视图
create or replace view v_audit_info
as
select ai.user_id,
       ai.audit_admin,
       ai.audit_status,
       ai.register_date,
       ai.audit_date,       
       u.name,       
       u.id_card,      
       u.sex,       
       u.birthday,       
       u.address,       
       u.career,              
       u.extend1,       
       u.extend2,       
       u.status
from t_audit_info ai
left join t_user u
on ai.user_id = u.user_id
