package cn.haohao.cis.income.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserIncomeInputObj {
	
	private Float income;
	private Integer userId;
	private Integer ruleId;
	private Integer year;
	private Integer month;
	public Float getIncome() {
		return income;
	}
	public void setIncome(Float income) {
		this.income = income;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			return sdf.parse(this.year + "/" + this.month + "/01");
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	public Integer getRuleId() {
		return ruleId;
	}
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
}
