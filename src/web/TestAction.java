package web;

import java.util.ArrayList;

import dao.Follow;
import dao.Guser;
import ser.TestService;

public class TestAction {
	private TestService testService;

	public void test(){
		testService.test();
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
