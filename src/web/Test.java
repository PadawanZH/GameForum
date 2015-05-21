package web;

import java.util.List;

import dao.TUser;
import ser.TestService;

public class Test {
	TestService testService;
	public String test() {
		/* NAME和PASSWORD为空则返回登录页面，否则验证登录 */
		List<TUser> list = testService.Test();
		
		System.out.println(list.get(0).getStuId());
		return "test";
	}
	/**
	 * @return the testService
	 */
	public TestService getTestService() {
		return testService;
	}
	/**
	 * @param testService the testService to set
	 */
	public void setTestService(TestService testService) {
		this.testService = testService;
	}
}
