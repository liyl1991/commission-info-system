
--  Create Tables 
CREATE TABLE t_income_rule
(
	rule_id INTEGER NOT NULL COMMENT '主键',
	detail_content VARCHAR(255) NOT NULL COMMENT '规则内容',
	PRIMARY KEY (rule_id)
)  COMMENT='提成规则表'
;
--输入8种规则名
insert into t_income_rule values(100,'BX');
insert into t_income_rule values(101,'BCX');
insert into t_income_rule values(102,'BDX');
insert into t_income_rule values(103,'BEX');
insert into t_income_rule values(104,'BCDX');
insert into t_income_rule values(105,'BCEX');
insert into t_income_rule values(106,'BDEX');
insert into t_income_rule values(107,'BCDEX');

CREATE TABLE t_income_setting
(
	setting_id INTEGER  AUTO_INCREMENT COMMENT '主键',
	type INTEGER NOT NULL DEFAULT 1 COMMENT '类型：1、常规，2、特殊',
	proportion NUMERIC(4,4) NOT NULL COMMENT '比例',
	rule_id INTEGER NOT NULL,
	setting_level VARCHAR(2) NOT NULL,
	using_date DATETIME COMMENT '使用开始时间',
	end_date DATETIME COMMENT '弃用时间',
	status INTEGER DEFAULT 1 COMMENT '状态：1、使用，2、弃用',
	PRIMARY KEY (setting_id)
) 
;

insert into t_income_setting values(101,1,'0.3',100,'B',str_to_date('01/01/2000', '%m/%d/%Y'),null,1);
insert into t_income_setting values(102,1,'0.25',101,'C',str_to_date('01/01/2000', '%m/%d/%Y'),null,1);
insert into t_income_setting values(103,1,'0.2',102,'D',str_to_date('01/01/2000', '%m/%d/%Y'),null,1);
insert into t_income_setting values(104,1,'0.15',103,'E',str_to_date('01/01/2000', '%m/%d/%Y'),null,1);
insert into t_income_setting values(105,1,'0.05',104,'C',str_to_date('01/01/2000', '%m/%d/%Y'),null,1);
insert into t_income_setting values(106,1,'0.20',104,'D',str_to_date('01/01/2000', '%m/%d/%Y'),null,1);
insert into t_income_setting values(107,1,'0.1',105,'C',str_to_date('01/01/2000', '%m/%d/%Y'),null,1);
insert into t_income_setting values(108,1,'0.15',105,'E',str_to_date('01/01/2000', '%m/%d/%Y'),null,1);
insert into t_income_setting values(109,1,'0.1',106,'D',str_to_date('01/01/2000', '%m/%d/%Y'),null,1);
insert into t_income_setting values(110,1,'0.15',106,'E',str_to_date('01/01/2000', '%m/%d/%Y'),null,1);
insert into t_income_setting values(111,1,'0.05',107,'C',str_to_date('01/01/2000', '%m/%d/%Y'),null,1);
insert into t_income_setting values(112,1,'0.05',107,'D',str_to_date('01/01/2000', '%m/%d/%Y'),null,1);
insert into t_income_setting values(113,1,'0.15',107,'E',str_to_date('01/01/2000', '%m/%d/%Y'),null,1);
--基础提成比例 初始30%
insert into t_income_setting values(114,1,'0.3',0,'0',str_to_date('01/01/2000', '%m/%d/%Y'),null,1);

CREATE TABLE t_reach_setting
(
	reach_id INTEGER AUTO_INCREMENT COMMENT '主键',
	type INTEGER NOT NULL DEFAULT 1 COMMENT '类型：1、常规，2、特殊',
	reach_performance NUMERIC(12,2) NOT NULL COMMENT '达标值',
	setting_level VARCHAR(2) NOT NULL COMMENT '配置等级',
	using_date DATETIME COMMENT '使用开始时间',
	end_date DATETIME COMMENT '弃用时间',
	status INTEGER DEFAULT 1 COMMENT '状态：1、使用，2、弃用',
	PRIMARY KEY (reach_id)
)  COMMENT='业绩达标配置'
;


--输入3种达标规则名
insert into t_reach_setting values(101,1,15000,'C',str_to_date('01/01/2000', '%m/%d/%Y'),null,1);
insert into t_reach_setting values(102,1,10000,'D',str_to_date('01/01/2000', '%m/%d/%Y'),null,1);
insert into t_reach_setting values(103,1,5000,'E',str_to_date('01/01/2000', '%m/%d/%Y'),null,1);

CREATE TABLE t_special_setting
(
	user_id INTEGER NOT NULL COMMENT '用户id',
	setting_id INTEGER NOT NULL,
	type INTEGER DEFAULT 1 COMMENT '类型：1、提成比例，2、达标指数',
	PRIMARY KEY (user_id, setting_id)
)  COMMENT='特殊提成配置表'
;

create or replace view v_special_setting
as
SELECT se.setting_id,st.proportion,st.rule_id ,se.user_id,st.setting_level,st.using_date,st.end_date,st.status
from t_special_setting se
left join t_income_setting st
on se.setting_id = st.setting_id;

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

--重建t_user_income
--  Drop Tables, Stored Procedures and Views 
DROP TABLE IF EXISTS t_user_income
;

--  Create Tables 
CREATE TABLE t_user_income
(
	income_id INTEGER AUTO_INCREMENT COMMENT '收入信息id',
	user_id INTEGER COMMENT '用户id',
	income_date DATE COMMENT '月份',
	income NUMERIC(12,3) COMMENT '收入',
	performance NUMERIC(12,3) COMMENT '业绩',
	setting_id INTEGER COMMENT '使用提成规则的id',
	from_income_id INTEGER COMMENT '提成来源id',
	PRIMARY KEY (income_id)
)  COMMENT='用户收入信息表'
;

create or replace view v_user_income
as
select u.*,vi.income_date,vi.income,vi.performance
from t_user u
left join v_income_month_count vi
on u.user_id = vi.user_id
where u.user_role = 1