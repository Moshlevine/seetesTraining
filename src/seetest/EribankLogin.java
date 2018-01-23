//eribank login test

package seetest;

import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EribankLogin extends Main {

	test test1 = new test();
	String device = test1.getter();
	public String path;
	int failcount = 0;
	public String devicename = "";
	public String serialnumber = "";

	@Test(groups = { "seetest" })

	public void runtest() throws FileNotFoundException {
		if (device.equals("")) {
			String device = client.waitForDevice("@os='android' or @os='ios' and @added='true'", 300000);
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

		client.setReporter("xml", path, "eribank login test");

		String os = client.getDeviceProperty("device.os");
		switch (os) {
		case "ANDROID":
			if (client.uninstall("com.experitest.ExperiBank")) {
				// If statement
			}
			if (client.install("com.experitest.ExperiBank/.LoginActivity", true, false)) {
				// If statement
			}
			client.launch("com.experitest.ExperiBank/.LoginActivity", true, true);

			break;

		case "IOS_APP":
			if (client.uninstall("com.experitest.ExperiBank")) {
				// If statement
			}
			if (client.install("com.experitest.ExperiBank", true, false)) {
				// If statement
			}
			client.launch("com.experitest.ExperiBank", true, true);

			break;
		}

		String csvUserName = null;
		String csvPassword = null;
		Scanner inputStream = new Scanner(new File("csvdata.csv"));
		inputStream.nextLine(); // ignore the first line - Headlines
		while (inputStream.hasNext()) {
			String data = inputStream.nextLine(); // Read line
			String[] values = data.split(","); // Split the line to an array
			csvUserName = values[0];
			csvPassword = values[1];

			client.elementSendText("NATIVE", "xpath=//*[@hint='Username' or @placeholder='Username']", 0, csvUserName); // send
																														// the
																														// text
																														// from
																														// the
																														// csv
																														// file
																														// to
																														// "user
																														// name"
			client.elementSendText("NATIVE", "xpath=//*[@placeholder='Password' or @id='passwordTextField']", 0,
					csvPassword); // send the text from the csv file to "password"
			client.click("NATIVE", "xpath=//*[@id='loginButton' or @accessibilityLabel='loginButton']", 0, 1);
			if (!"company".equals(csvPassword) && !"company".equals(csvUserName)) {
				client.verifyElementFound("NATIVE", "xpath=//*[@text='Invalid username or password!']", 0);
			}

			if (client.isElementFound("NATIVE", "xpath=//*[@text='Invalid username or password!']", 0)) { // check if
																											// user name
																											// or
																											// password
																											// are not
																											// correct.

				client.click("NATIVE", "xpath=//*[@text='Dismiss' or @text='Close']", 0, 1);
			}

			else
				break;
		}

		inputStream.close(); // close the connection to the csv file
		client.verifyElementFound("NATIVE",
				"xpath=//*[@id='makePaymentButton' or @accessibilityLabel='makePaymentButton']", 0);
		String str0 = client.getTextIn("NATIVE", "xpath=//*[@text='Make Payment']", 0, "TEXT", "Up", 0, 250);
		if ("IOS_APP".equals(os)) {
			str0 = str0.substring(18, str0.length() - 2);
			System.out.println(str0);

		}

		else {
			str0 = str0.substring(18, str0.length() - 2);
		}
		System.out.println(str0);
		double init_balance = Double.parseDouble(str0);
		System.out.println(init_balance);
		client.click("NATIVE", "xpath=//*[@id='makePaymentButton' or @accessibilityLabel='makePaymentButton']", 0, 1);
		if (client.waitForElement("NATIVE", "xpath=//*[@id='phoneTextField' or @placeholder='Phone']", 0, 10000)) {
			// If statement
		}
		client.elementSendText("NATIVE", "xpath=//*[@id='phoneTextField' or @placeholder='Phone']", 0, "052");
		client.elementSendText("NATIVE", "xpath=//*[@id='nameTextField' or @accessibilityLabel='Name']", 0, "dan");
		client.elementSendText("NATIVE", "xpath=//*[@id='amountTextField' or @accessibilityLabel='Amount']", 0, "35");
		String str1 = client.elementGetText("NATIVE", "xpath=//*[@id='amountTextField' or @class='UIFieldEditor']", 0);

		double entered_numb = Double.parseDouble(str1);
		double expected_out = init_balance - entered_numb;

		String expected_outstring = String.valueOf(expected_out);
		System.out.println(expected_outstring);

		client.click("NATIVE", "xpath=//*[@id='countryButton' or @accessibilityLabel='Select']", 0, 1);
		client.click("NATIVE", "xpath=//*[@text='Greenland']", 0, 1);
		client.click("NATIVE", "xpath=//*[@id='sendPaymentButton' or @accessibilityLabel='sendPaymentButton']", 0, 1);
		client.click("NATIVE", "xpath=//*[@id='button1' or @accessibilityLabel='Yes']", 0, 1);
		client.verifyIn("NATIVE", "xpath=//*[@text='Make Payment']", 0, "Up", "TEXT", expected_outstring, 0, 250);

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		File tempFile = new File("overall summary.txt");
		boolean exists = tempFile.exists();

		if (result.getStatus() == ITestResult.FAILURE) {
			// write to report of fail

			if (exists) {

				String failedstring = "the device " + this.devicename + " serial number " + this.serialnumber
						+ " has failed on the following test: Eribank Login test";
				BufferedWriter writer = null;

				writer = new BufferedWriter(new FileWriter("overall summary.txt", true));

				writer.append(' ');
				writer.newLine();
				writer.append(failedstring);
				writer.close();

			} else {
				System.out.println("in else exists");

				String failedstring = "the device " + this.devicename + " serial number " + this.serialnumber
						+ " has Failed on the following test: Eribank Login test";

				BufferedWriter writer = null;

				writer = new BufferedWriter(new FileWriter("overall summary.txt"));

				writer.write(failedstring);
				writer.close();

			}

			failcount++;
			System.out.println(failcount);

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
						+ " has Passed on the following test: Eribank Login test";
				BufferedWriter writer = null;

				writer = new BufferedWriter(new FileWriter("overall summary.txt", true));

				writer.append(" ");
				writer.newLine();
				writer.append(success);
				writer.close();

			} else {
				System.out.println("in success else");

				String success = "the device " + this.devicename + " serial number " + this.serialnumber
						+ " has Passed on the following test: Eribank Login test";

				BufferedWriter writer = null;
				writer = new BufferedWriter(new FileWriter("overall summary.txt"));
				writer.write(success);
				writer.close();
			}
			client.generateReport(false);

		}

		client.generateReport(false);
		client.releaseClient();

	}

}
