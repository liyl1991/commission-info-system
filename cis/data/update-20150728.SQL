CREATE OR REPLACE VIEW v_user_income
AS
SELECT
	u.user_id AS user_id,
	u.id_card AS id_card,
	u. NAME AS NAME,
	u.password AS password,
	u.sex AS sex,
	u.birthday AS birthday,
	u.address AS address,
	u.career AS career,
	u.level AS level,
	u.status AS status,
	u.user_role AS user_role,
	u.upline_user AS upline_user,
	u.extend1 AS extend1,
	u.extend2 AS extend2,
	vi.income_date AS income_date,
	vi.income AS income,
	vi.performance AS performance,
	rs.reach_performance AS reach_performance
FROM
	t_user u
LEFT JOIN v_income_month_count vi ON u.user_id = vi.user_id
LEFT JOIN t_reach_setting rs ON (
	vi.income_date >= rs.using_date
	AND (
		vi.income_date < rs.end_date
		OR ISNULL(rs.end_date)
	)
	AND rs.setting_level = u. LEVEL
)
WHERE
	u.user_role = 1