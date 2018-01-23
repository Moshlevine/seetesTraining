
//testing on play store
package seetest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.*;

public class PlaystoreTest extends Main {
	test test1 = new test();
	String device = test1.getter();
	String path;
	int failcount = 0;
	public String devicename = "";
	public String serialnumber = "";

	@Test(groups = { "seetest" })
	public void runtest() {
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

		client.setReporter("xml", path, "playstore test");
		client.launch("com.android.vending/.AssetBrowserActivity", false, true);
		client.click("NATIVE", "xpath=//*[@text='TOP CHARTS']", 0, 1);
		if (client.waitForElement("NATIVE", "xpath=//*[@id='toggle_switch_button' and @checked='false']", 0, 10000)) {
			client.click("NATIVE", "xpath=//*[@resource-id='com.android.vending:id/toggle_switch_button']", 0, 1);
		}
		String str0 = client.getTextIn("NATIVE", "xpath=//*[@text='1']", 0, "TEXT", "Right", 0, 0);
		String list_of_names;
		list_of_names = "Top 10 free apps: \n";
		list_of_names += "1-" + str0.split("\n")[0];
		String str1 = client.getTextIn("NATIVE", "xpath=//*[@text='2']", 0, "TEXT", "Right", 0, 0);
		list_of_names += "\n2-" + str1.split("\n")[0];
		String str2 = client.getTextIn("NATIVE", "xpath=//*[@text='3']", 0, "TEXT", "Right", 0, 0);
		list_of_names += "\n3-" + str2.split("\n")[1];

		String str3 = client.getTextIn("NATIVE", "xpath=//*[@text='4']", 0, "TEXT", "Right", 0, 0);
		list_of_names += "\n4-" + str3.split("\n")[0];
		if (client.swipeWhileNotFound("Down", 300, 0, "NATIVE", "xpath=//*[@text='7']", 0, 1000, 5, false)) {
			// If statement
		}
		String str4 = client.getTextIn("NATIVE", "xpath=//*[@text='5']", 0, "TEXT", "Right", 0, 0);
		list_of_names += "\n5-" + str4.split("\n")[1];

		String str5 = client.getTextIn("NATIVE", "xpath=//*[@text='6']", 0, "TEXT", "Right", 0, 0);
		list_of_names += "\n6-" + str5.split("\n")[0];

		String str6 = client.getTextIn("NATIVE", "xpath=//*[@text='7']", 0, "TEXT", "Right", 0, 0);
		list_of_names += "\n7-" + str6.split("\n")[0];
		if (client.isElementFound("NATIVE", "xpath=//*[@text='9']", 0)) {
			String str7 = client.getTextIn("NATIVE", "xpath=//*[@text='8']", 0, "TEXT", "Right", 0, 0);
			list_of_names += "\n8-" + str7.split("\n")[0];
		}

		else {
			client.swipe("Down", 200, 50);

			String str7 = client.getTextIn("NATIVE", "xpath=//*[@text='8']", 0, "TEXT", "Right", 0, 0);
			list_of_names += "\n8-" + str7.split("\n")[0];
		}

		String str8 = client.getTextIn("NATIVE", "xpath=//*[@text='9']", 0, "TEXT", "Right", 0, 0);
		list_of_names += "\n9-" + str8.split("\n")[0];

		if (client.swipeWhileNotFound("Down", 300, 50, "NATIVE", "xpath=//*[@text='11']", 0, 1000, 5, false)) {
			// If statement
		}
		String str9 = client.getTextIn("NATIVE", "xpath=//*[@text='10']", 0, "TEXT", "Right", 0, 0);
		list_of_names += "\n10-" + str9.split("\n")[0];

		System.out.println(list_of_names);

		client.click("default", "li_thumbnail_1", 0, 1);

		if (client.isElementFound("NATIVE", "xpath=//*[@text='INSTALL']", 0)) {
			client.click("NATIVE", "xpath=//*[@text='INSTALL']", 0, 1);
		}

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		File tempFile = new File("overall summary.txt");
		boolean exists = tempFile.exists();

		if (result.getStatus() == ITestResult.FAILURE) {
			// write to report of fail

						if (exists) {

							String failedstring = "the device " + this.devicename + " serial number " + this.serialnumber
									+ " has failed on the following test: Playstore test";
							BufferedWriter writer = null;

							writer = new BufferedWriter(new FileWriter("overall summary.txt", true));

							writer.append(' ');
							writer.newLine();
							writer.append(failedstring);
							writer.close();

						} else {
							System.out.println("in else exists");

							String failedstring = "the device " + this.devicename + " serial number " + this.serialnumber
									+ " has Failed on the following test: Playstore test";

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
						+ " has Passed on the following test: Playstore test";
				BufferedWriter writer = null;

				writer = new BufferedWriter(new FileWriter("overall summary.txt", true));

				writer.append(" ");
				writer.newLine();
				writer.append(success);
				writer.close();

			} else {
				System.out.println("in success else");

				String success = "the device " + this.devicename + " serial number " + this.serialnumber
						+ " has Passed on the following test: Playstore test";

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
