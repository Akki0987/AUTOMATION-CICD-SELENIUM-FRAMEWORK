package base;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import ExtentReporterConfig.ExtentReporterConfiguration;

public class Listeners extends BaseTest implements ITestListener{

	ExtentReports extent = ExtentReporterConfiguration.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> tl = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result)
	{
		test = extent.createTest(result.getMethod().getMethodName());
		tl.set(test);
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		tl.get().pass(Status.PASS+ "Test : " + result.getMethod().getMethodName()+ " passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		tl.get().fail(Status.FAIL+"Test : " + result.getMethod().getMethodName()+ " passed"+ result.getThrowable());
		String path = null;
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
//		Take Screenshot 
		try {
			path = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		Attach to report 
		tl.get().addScreenCaptureFromPath(path);
	}
	
	@Override
	public void onTestSkipped(ITestResult result)
	{
		tl.get().skip(Status.SKIP + "Test "+ result.getMethod().getMethodName()+ " skipped");
	}
	
	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}
}