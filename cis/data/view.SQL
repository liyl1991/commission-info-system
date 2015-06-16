
--月收入统计视图
create or replace view v_income_month_count
as
select a.user_id,a.income_date,sum(a.income) income,sum(a.performance) performance
from t_user_income a
group by a.income_date,a.user_id


--用户收入视图
create or replace view v_user_income
as
select u.*,vi.income_date,vi.income,vi.performance
from t_user u
left join v_income_month_count vi
on u.user_id = vi.user_id
where u.user_role = 1; 
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

/*
--特殊提成配置视图
create or replace view v_special_setting
as
SELECT se.setting_id,st.proportion,st.rule_id ,se.user_id,st.setting_level,st.using_date,st.end_date,st.status
from t_special_setting se
left join t_income_setting st
on se.setting_id = st.setting_id;
--用户提成规则视图
create or replace view v_user_income_setting
as
select a.*,
       c.setting_id common_setting_id,
       c.proportion common_setting,
       c.setting_level,
       b.setting_id special_setting_id,
       b.proportion special_setting,b.user_id
from t_income_rule a 
left join t_income_setting c
on a.rule_id = c.rule_id and c.status = 1 and c.type = 1
left join v_special_setting b 
on a.rule_id = b.rule_id and c.setting_level = b.setting_level and b.status = 1;
*/