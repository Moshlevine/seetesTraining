package seetest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.*;

public class IOS_appstoretest extends Main {
	test test1 = new test();
	String device = test1.getter();
	String path;
	int failcount = 0;
	public String devicename = "";
	public String serialnumber = "";

	@Test(groups = { "seetest" })
	public void runtest() {

		if (device.equals("")) {
			String device = client.waitForDevice("@os='ios'", 300000);
		} else {
			client.setDevice(device);
		}

		devicename = client.getDeviceProperty("device.name");
		serialnumber = client.getDeviceProperty("device.sn");

		if (devicename.startsWith("ios_app:")) {
			devicename = devicename.substring(8, devicename.length());
		}
		String os = client.getDeviceProperty("device.os");

		long millis = System.currentTimeMillis();
		path = "RUN_" + millis + "\\" + devicename;

		client.setReporter("xml", path, "ios store test");
		client.launch("com.apple.AppStore", false, true);
		if (client.waitForElement("NATIVE", "xpath=//*[@text='Top Charts']", 0, 10000)) {
		}
		client.click("NATIVE", "xpath=//*[@text='Top Charts']", 0, 1);
		if (client.waitForElement("NATIVE", "xpath=//*[@text='Free']", 0, 10000)) {
		}
		client.click("NATIVE", "xpath=//*[@text='Free']", 0, 1);
		if (client.waitForElement("NATIVE", "xpath=//*[starts-with(@text, \"1,\")]", 0, 10000)) {

		}
		String str0 = client.elementGetText("NATIVE", "xpath=//*[starts-with(@text, \"1,\")]", 0);
		str0 = str0.substring(2, str0.length() - 42);

		String str1 = client.elementGetText("NATIVE", "xpath=//*[starts-with(@text, \"2,\")]", 0);
		str1 = str1.substring(2, str1.length() - 33);

		String str2 = client.elementGetText("NATIVE", "xpath=//*[starts-with(@text, \"3,\")]", 0);
		str2 = str2.substring(2, str2.length() - 59);

		String str3 = client.elementGetText("NATIVE", "xpath=//*[starts-with(@text, \"4,\")]", 0);
		str3 = str3.substring(2, str3.length() - 36);

		String str4 = client.elementGetText("NATIVE", "xpath=//*[starts-with(@text, \"5,\")]", 0);
		str4 = str4.substring(2, str4.length() - 42);

		if (client.swipeWhileNotFound("Down", 200, 2, "NATIVE", "xpath=//*[starts-with(@text, \"8,\")]", 0, 1000, 5,
				false)) {
			// If statement
		}
		String str5 = client.elementGetText("NATIVE", "xpath=//*[starts-with(@text, \"6,\")]", 0);
		str5 = str5.substring(2, str5.length() - 62);

		if (client.swipeWhileNotFound("Down", 500, 10, "NATIVE", "xpath=//*[starts-with(@text, \"10,\")]", 0, 1000, 5,
				false)) {
			// If statement
		}
		String str6 = client.elementGetText("NATIVE", "xpath=//*[starts-with(@text, \"7,\")]", 0);
		str6 = str6.substring(2, str6.length() - 50);

		String str7 = client.elementGetText("NATIVE", "xpath=//*[starts-with(@text, \"8,\")]", 0);
		str7 = str7.substring(2, str7.length() - 44);

		String str8 = client.elementGetText("NATIVE", "xpath=//*[starts-with(@text, \"9,\")]", 0);
		str8 = str8.substring(2, str8.length() - 41);

		String str9 = client.elementGetText("NATIVE", "xpath=//*[starts-with(@text, \"10,\")]", 0);
		str9 = str9.substring(2, str9.length() - 62);
		client.click("default", "Face", 0, 1);
		client.click("NATIVE", "xpath=//*[@text='Download']", 0, 1);
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		File tempFile = new File("overall summary.txt");
		boolean exists = tempFile.exists();

		if (result.getStatus() == ITestResult.FAILURE) {
			// write to report of fail

			if (exists) {

				String failedstring = "the device " + this.devicename + " serial number " + this.serialnumber
						+ " has failed on the following test: IOS app store test";
				BufferedWriter writer = null;

				writer = new BufferedWriter(new FileWriter("overall summary.txt", true));

				writer.append(' ');
				writer.newLine();
				writer.append(failedstring);
				writer.close();

			} else {
				System.out.println("in else exists");

				String failedstring = "the device " + this.devicename + " serial number " + this.serialnumber
						+ " has Failed on the following test: IOS app store test";

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
						+ " has Passed on the following test: IOS app store test";
				BufferedWriter writer = null;

				writer = new BufferedWriter(new FileWriter("overall summary.txt", true));

				writer.append(" ");
				writer.newLine();
				writer.append(success);
				writer.close();

			} else {
				System.out.println("in success else");

				String success = "the device " + this.devicename + " serial number " + this.serialnumber
						+ " has Passed on the following test: IOS app store test";

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
