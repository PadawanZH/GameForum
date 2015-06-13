package web;

import java.util.ArrayList;

import dao.Follow;
import dao.Guser;
import ser.TestService;

public class TestAction {
	private TestService testService;

	public void test(){
		testService.test();
		Guser u1 = new Guser("1");
		Guser u2 = new Guser("2");
		Follow f1 = new Follow(u1, u2);
		ArrayList<Follow> list = new ArrayList<Follow>();
		list.add(f1);
		Follow f2 = new Follow(u1, u2);
		System.out.println("TestAction.test() : contains ? : "+ list.contains(f2) + " and ==? : " + (f1 == f2));
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
