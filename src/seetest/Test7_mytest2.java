package seetest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.*;

public class Test7_mytest2 extends Main {
	test test1 = new test();
	String device = test1.getter();

	String path;
	int failcount = 0;
	public String devicename = "";
	public String serialnumber = "";

	@Test(groups = { "seetest" })
	public void runtest() {
		if (device.equals("")) {
			String str0 = client.waitForDevice("@os='android'", 300000);
		} else {
			client.setDevice(device);
		}
		devicename = client.getDeviceProperty("device.name");
		serialnumber = client.getDeviceProperty("device.sn");

		if (devicename.startsWith("adb:")) {
			devicename = devicename.substring(4, devicename.length());
		}
		if (devicename.startsWith("ios_app:")) {
			devicename = devicename.substring(8, devicename.length());
		}

		long millis = System.currentTimeMillis();
		path = "RUN_" + millis + "\\" + devicename;

		client.setReporter("xml", path, "my third test");
		String str1 = client.getDevicesInformation();
		String str2 = client.getDeviceProperty("device.os");
		if (client.getNetworkConnection("wifi")) {
		}
		if (client.uninstall("com.ralksoft.sellerprofitcalculator/.MainActivity")) {
		}
		if (client.install("com.ralksoft.sellerprofitcalculator/.MainActivity", true, false)) {
		}
		client.launch("com.ralksoft.sellerprofitcalculator/.MainActivity", false, true);
		String str3 = client.getCounter("cpu");
		String str4 = client.getMonitorsData("");
		client.startMonitor("");
		client.click("NATIVE", "xpath=//*[@text='Fixed price']", 0, 1);
		client.setLanguage("LANG_ENG");
		client.verifyElementNotFound("NATIVE", "xpath=//*[@text='Auction starting price:']", 0);
		client.click("NATIVE", "xpath=//*[@text='Auction']", 0, 1);
		if (client.isElementFound("NATIVE", "xpath=//*[@text='Auction starting price:']", 0)) {
		}
		client.clearDeviceLog();
		if (client.applicationClose("")) {
		}
		client.applicationClearData("com.ralksoft.sellerprofitcalculator");

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		File tempFile = new File("overall summary.txt");
		boolean exists = tempFile.exists();

		if (result.getStatus() == ITestResult.FAILURE) {
			// write to report of fail

						if (exists) {

							String failedstring = "the device " + this.devicename + " serial number " + this.serialnumber
									+ " has failed on the following test: test 7 my test";
							BufferedWriter writer = null;

							writer = new BufferedWriter(new FileWriter("overall summary.txt", true));

							writer.append(' ');
							writer.newLine();
							writer.append(failedstring);
							writer.close();

						} else {
							System.out.println("in else exists");

							String failedstring = "the device " + this.devicename + " serial number " + this.serialnumber
									+ " has Failed on the following test: test 7 my test";

							BufferedWriter writer = null;

							writer = new BufferedWriter(new FileWriter("overall summary.txt"));

							writer.write(failedstring);
							writer.close();

						}
			failcount++;

			while (failcount < 3) {
				try {
					if (failcount == 2) {
						client.reboot(400000);
						failcount++;

					}
					runtest();

				} catch (Exception e) {
					failcount++;
					client.generateReport(false);
					client.collectSupportData(path, "", client.getDeviceProperty("device.name"), "", "", "");

				}
			}

		}
		if (result.getStatus() == ITestResult.SUCCESS) {
			// write to report

			if (exists) {
				System.out.println("in success exists");

				String success = "the device " + this.devicename + " serial number " + this.serialnumber
						+ " has Passed on the following test: test 7 my test";
				BufferedWriter writer = null;

				writer = new BufferedWriter(new FileWriter("overall summary.txt", true));

				writer.append(" ");
				writer.newLine();
				writer.append(success);
				writer.close();

			} else {
				System.out.println("in success else");

				String success = "the device " + this.devicename + " serial number " + this.serialnumber
						+ " has Passed on the following test: test 7 my test";

				BufferedWriter writer = null;
				writer = new BufferedWriter(new FileWriter("overall summary.txt"));
				writer.write(success);
				writer.close();
			}

		}
		client.generateReport(false);
		client.releaseClient();

	}
}
