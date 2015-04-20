alter table t_audit_info change aduit_date audit_date date;
alter table t_audit_info MODIFY COLUMN audit_status decimal(2,0) COMMENT '审批状态：1、通过，2、驳回，3、等待';
alter table t_user MODIFY COLUMN status decimal(2,0) COMMENT '状态：1、正常、2、不可用、3、待审核';
--新增审批视图
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