package web;

import java.util.Date;

import ser.UserService;
import dao.Guser;


public class ChangeInfoAction {
	UserService userService;
	String year,month,day;
	String nullString = new String("");
	/**
	 * <b><font color="red">guser的account是必须传的值，前端使用表单隐藏hidden域传值</font></b>
	 */
	Guser guser;
	public String changeInfo(){
		System.out.println(guser.toString());
		if(!year.equals(nullString) && !month.equals(nullString) && !day.equals(nullString)){
			System.out.println(year+" "+month+" "+day+" ");
			Date birthday = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
			guser.setBirthday(birthday);
		}
		return userService.changeInfo(guser);
	}
	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}
	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}
	/**
	 * @return the guser
	 */
	public Guser getGuser() {
		return guser;
	}
	/**
	 * @param guser the guser to set
	 */
	public void setGuser(Guser guser) {
		this.guser = guser;
	}
}
