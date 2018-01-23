using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using experitestClient;

namespace Experitest
{
    [TestClass]
    public class _123
    {
        private string host = "localhost";
        private int port = 8889;
        private string projectBaseDirectory = "C:\\Users\\moshe.levin\\workspace\\tutorial";
        protected Client client = null;
        
        [TestInitialize()]
        public void SetupTest()
        {
            client = new Client(host, port, true);
            client.SetProjectBaseDirectory(projectBaseDirectory);
            client.SetReporter("xml", "reports", "123");
        }

        [TestMethod]
        public void Test_123()
        {
            client.SetDevice("adb:LG-H850");
            client.Launch("com.experitest.ExperiBank/.LoginActivity", true, true);
            client.ElementSendText("default", "Username", 0, "sdf");
            client.ElementSendText("default", "Password", 0, "dds");
            client.Click("default", "Login", 0, 1);
            client.Click("default", "Close", 0, 1);
            client.VerifyElementFound("NATIVE", "", 0);
            string str0 = client.ElementGetText("NATIVE", "xpath=//*[@text='25']", 0);
        }

        [TestCleanup()]
        public void TearDown()
        {
        // Generates a report of the test case.
        // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
        client.GenerateReport(false);
        // Releases the client so that other clients can approach the agent in the near future. 
        client.ReleaseClient();
        }
    }
}