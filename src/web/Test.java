package web;

import java.util.List;

import dao.TUser;
import ser.TestService;

public class Test {
	TestService testService;
	public String test() {
		/* NAME��PASSWORDΪ���򷵻ص�¼ҳ�棬������֤��¼ */
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
