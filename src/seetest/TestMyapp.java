package seetest;

import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//my app test
public class TestMyapp extends Main {
	test test1 = new test();
	String device = test1.getter();
	String path;
	int failcount = 0;
	public String devicename = "";
	public String serialnumber = "";

	@Test(groups = { "seetest" })
	public void runtest() throws FileNotFoundException {

		if (device.equals("")) {
			String device = client.waitForDevice("@os='android'", 300000);
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

		client.setReporter("xml", path, "my app test");

		if (client.uninstall("com.example.moshelevin.test4/.MainActivity")) {
		}
		if (client.install("com.example.moshelevin.test4/.MainActivity", true, false)) {
		}
		client.launch("com.example.moshelevin.test4/.MainActivity", true, true);

		if (client.isElementFound("NATIVE",
				"xpath=//*[@text=concat('Allow test4 to access this device', \"'\", 's location?')]", 0)) {
			client.click("NATIVE", "xpath=//*[@id='permission_allow_button']", 0, 1);
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

			client.elementSendText("NATIVE", "xpath=//*[@id='etname' ]", 0, csvUserName); // send the text from the csv
																							// file to "user name"
			client.elementSendText("NATIVE", "xpath=//*[@id='etPassword' ]", 0, csvPassword); // send the text from the
																								// csv file to
																								// "password"
			client.closeKeyboard();

			client.click("NATIVE", "xpath=//*[@id='btnlogin' ]", 0, 1);
			if (csvUserName.equals(csvPassword)) {
				break;
			} else {
				client.verifyElementFound("NATIVE", "xpath=//*[@id='alertTitle' ]", 0);

			}

			if (client.isElementFound("NATIVE", "xpath=//*[@id='alertTitle' ]", 0)) { // check if user name or password
																						// are not correct.

				client.click("NATIVE", "xpath=//*[@id='button2']", 0, 1);
			}

			else
				break;
		}

		inputStream.close(); // close the connection to the csv file

		client.click("NATIVE", "xpath=//*[@id='startbtn']", 0, 1);
		client.click("NATIVE", "xpath=//*[@id='button']", 0, 1);
		client.click("NATIVE", "xpath=//*[@id='button']", 0, 1);
		client.click("NATIVE", "xpath=//*[@id='button']", 0, 1);
		if (client.waitForElementToVanish("NATIVE", "id=button", 0, 3000)) {
		}
		client.deviceAction("Back");
		client.click("NATIVE", "xpath=//*[@id='startbtn']", 0, 1);
		client.click("NATIVE", "xpath=//*[@id='button']", 0, 1);
		client.click("NATIVE", "xpath=//*[@id='button']", 0, 1);
		client.click("NATIVE", "xpath=//*[@id='button']", 0, 1);
		if (client.waitForElementToVanish("NATIVE", "id=button", 0, 3000)) {
		}
		client.deviceAction("Back");
		client.click("NATIVE", "xpath=//*[@id='startbtn']", 0, 1);
		client.click("NATIVE", "xpath=//*[@id='button']", 0, 1);
		client.click("NATIVE", "xpath=//*[@id='button']", 0, 1);
		client.click("NATIVE", "xpath=//*[@id='button']", 0, 1);
		client.click("NATIVE", "xpath=//*[@id='button']", 0, 1);
		if (client.waitForElementToVanish("NATIVE", "xpath=//*[@id='button']", 0, 3000)) {
		}
		client.deviceAction("Back");
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		File tempFile = new File("overall summary.txt");
		boolean exists = tempFile.exists();

		if (result.getStatus() == ITestResult.FAILURE) {
			// write to report of fail

						if (exists) {

							String failedstring = "the device " + this.devicename + " serial number " + this.serialnumber
									+ " has failed on the following test: test my app test";
							BufferedWriter writer = null;

							writer = new BufferedWriter(new FileWriter("overall summary.txt", true));

							writer.append(' ');
							writer.newLine();
							writer.append(failedstring);
							writer.close();

						} else {
							System.out.println("in else exists");

							String failedstring = "the device " + this.devicename + " serial number " + this.serialnumber
									+ " has Failed on the following test: test my app test";

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
						+ " has Passed on the following test: test my app test";
				BufferedWriter writer = null;

				writer = new BufferedWriter(new FileWriter("overall summary.txt", true));

				writer.append(" ");
				writer.newLine();
				writer.append(success);
				writer.close();

			} else {
				System.out.println("in success else");

				String success = "the device " + this.devicename + " serial number " + this.serialnumber
						+ " has Passed on the following test: test my app test";

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
