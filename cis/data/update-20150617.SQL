alter table t_user_income add column from_downline int COMMENT '来源下线';
--下级提成信息统计
create or replace view v_user_from_downline
as
select ui.user_id,ui.income_date,ui.from_downline,sum(ui.income) income,sum(ui.performance) performance
from t_user_income ui
group by ui.user_id,ui.income_date,ui.from_downline

create or replace view v_user_from_downline_detail
as
select v.*, u1.name, u1.level, u2.name downline_name,u2.level downline_level
from v_user_from_downline v
left join t_user u1
on v.user_id = u1.user_id
left join t_user u2
on v.from_downline = u2.user_id;