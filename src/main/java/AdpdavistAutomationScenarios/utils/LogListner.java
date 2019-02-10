package AdpdavistAutomationScenarios.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import AdpdavistAutomationScenarios.base.Base;


/*This class implements the ITestListener Interface and is used to generate the logs for all the different events which corresponds to the
 * execution of the testNg test*/

public class LogListner extends Base implements ITestListener {
	


	@Override
	public void onTestStart(ITestResult result) {
		
		log.info("Test Method Started: " +result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		 
		log.info("Test Method Success: " +result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		
		log.error("Test Method Failed: " +result.getName());
		log.error(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		
		log.info("Test Method Skipped: " +result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
//		 not necessary for this implementation
		
	}

     //	below mentioned methods are for test suite
	@Override
	public void onStart(ITestContext context)   
	{  
		
		log.info("Test Suit Started: " +context.getName());
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		log.info("Test Suit Finished: " +context.getName());
		
	}
	
	

}
