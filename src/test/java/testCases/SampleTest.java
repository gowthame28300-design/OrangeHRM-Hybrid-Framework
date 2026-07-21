package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import testBase.BaseClass;
import org.testng.annotations.Listeners;
import listeners.ListenerClass;

@Listeners(ListenerClass.class)
public class SampleTest extends BaseClass {



	@Test(retryAnalyzer = utilities.RetryAnalyzer.class)
	public void testFailure() {

	    login();

	    Assert.assertEquals("Dashboard", "Login");

	}}

