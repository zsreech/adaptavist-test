package AdpdavistAutomationScenarios.utils;

import java.io.File;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdpdavistAutomationScenarios.base.Base;

/*This Class is used to generate the testNG Extent Report by Extending the IReporter interface of TestNG. */
public class ExtentReportNG extends Base  implements IReporter{
	
private ExtentReports extent;
	

	public void generateReport(List<XmlSuite> xmlsuites, List<ISuite> suites ,String outputDirectory) {
		// TODO Auto-generated method stub
		
		Date date = new Date();
		Timestamp stamp = new Timestamp(date.getTime());
		String timeStamp = stamp.toString();
		
		//Setting the location to generate the testNG reports 
		extent = new ExtentReports(prop.getProperty("reportFilePath")+ File.separator+"FinalExtentReport_"+timeStamp+".html",true);
		
		
		for(ISuite suite:suites) {
		Map<String,ISuiteResult> result=suite.getResults();
		
		
		for(ISuiteResult r : result.values()) {
			ITestContext context =r.getTestContext();
			
			buildTestNodes(context.getPassedTests(), LogStatus.PASS);
			buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
			buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
		}
	}
		extent.flush();
		extent.close();
	}
		
		private void buildTestNodes(IResultMap tests,LogStatus status) {
			ExtentTest test;
			
			if(tests.size()>0) {
			for(ITestResult result: tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());
				
				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));
				
				for(String group:result.getMethod().getGroups()) {
					test.assignCategory(group);
				}
				
				if(result.getThrowable()!= null) {
					test.log(status, result.getThrowable());
				}else {
					test.log(status, "Test"+status.toString().toLowerCase()+"ed");
					
				}
				
				extent.endTest(test);
				
			}
			}
		}

		//Fetching the timestamp to append to the fileName
		private Date getTime(long millis) {
			// TODO Auto-generated method stub
			Calendar calendar=Calendar.getInstance();
			calendar.setTimeInMillis(millis);
			return calendar.getTime();
		}

}
