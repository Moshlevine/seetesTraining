//package <set your test package>;
import com.experitest.client.*;
import org.junit.*;
/**
 *
*/
public class _123 {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\moshe.levin\\workspace\\tutorial";
    protected Client client = null;

    @Before
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "123");
    }

    @Test
    public void test_123(){
        client.setDevice("adb:LG-H850");
        client.launch("com.experitest.ExperiBank/.LoginActivity", true, true);
        client.elementSendText("default", "Username", 0, "sdf");
        client.elementSendText("default", "Password", 0, "dds");
        client.click("default", "Login", 0, 1);
        client.click("default", "Close", 0, 1);
        client.verifyElementFound("NATIVE", "", 0);
        String str0 = client.elementGetText("NATIVE", "xpath=//*[@text='25']", 0);
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
