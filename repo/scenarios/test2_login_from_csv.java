//package <set your test package>;
import com.experitest.client.*;
import org.junit.*;
/**
 *
*/
public class test2loginfromcsv {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\moshe.levin\\workspace\\tutorial";
    protected Client client = null;

    @Before
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "test2loginfromcsv");
    }

    @Test
    public void testtest2loginfromcsv(){
        client.setDevice("adb:Samsung Note 3");
        client.launch("com.experitest.ExperiBank/.LoginActivity", true, true);
        client.elementSendText("NATIVE", "hint=Username", 0, "company");
        client.elementSendText("NATIVE", "hint=Password", 0, "company");
        client.click("NATIVE", "text=Login", 0, 1);
        client.verifyElementFound("NATIVE", "id=makePaymentButton", 0);
        String str0 = client.getTextIn("default", "Make Payment", 0, "TEXT", "Up", 0, 250);
        client.click("default", "Make Payment", 0, 1);
        if(client.waitForElement("default", "Phone", 0, 10000)){
            // If statement
        }
        client.elementSendText("default", "Phone", 0, "052");
        client.elementSendText("default", "Name", 0, "dan");
        client.elementSendText("default", "Amount", 0, "35");
        client.click("default", "Select", 0, 1);
        client.click("default", "Greenland", 0, 1);
        client.click("default", "Send Payment", 0, 1);
        client.click("default", "Yes", 0, 1);
        client.verifyIn("default", "Make Payment", 0, "Up", "TEXT", "30.00$", 0, 250);
    }

    @After
    public void tearDown(){
        // Generates a report of the test case.
        // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
        client.generateReport(false);
        // Releases the client so that other clients can approach the agent in the near future. 
        client.releaseClient();
    }
}
