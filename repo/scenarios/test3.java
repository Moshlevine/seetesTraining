//package <set your test package>;
import com.experitest.client.*;
import org.junit.*;
/**
 *
*/
public class test3 {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\moshe.levin\\workspace\\tutorial";
    protected Client client = null;

    @Before
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "test3");
    }

    @Test
    public void testtest3(){
        client.setDevice("adb:LG-H850");
        client.launch("com.example.moshelevin.test4/.MainActivity", true, true);
        client.elementSendText("NATIVE", "xpath=//*[@id='etname']", 0, "avav");
        client.elementSendText("default", "Password_1", 0, "avav");
        client.closeKeyboard();
        client.click("default", "LOGIN_1", 0, 1);
        client.click("default", "Start", 0, 1);
        client.click("default", "button", 0, 1);
        client.click("default", "button", 0, 1);
        client.click("default", "button", 0, 1);
        client.click("default", "element 1", 0, 1);
        client.click("default", "element 2", 0, 1);
        if(client.waitForElement("default", "element 3", 0, 10000)){
            // If statement
        }
        client.click("default", "element 3", 0, 1);
        client.click("default", "Start", 0, 1);
        client.click("default", "button", 0, 1);
        client.click("default", "button", 0, 1);
        client.click("default", "button", 0, 1);
        client.click("default", "element 4", 0, 1);
        client.click("default", "element 3", 0, 1);
        client.click("default", "Start", 0, 1);
        client.click("default", "button", 0, 1);
        client.click("default", "element 5", 0, 1);
        client.click("default", "button", 0, 1);
        client.click("default", "element 6", 0, 1);
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
