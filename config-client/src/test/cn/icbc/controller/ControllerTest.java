package test.cn.icbc.controller;

import main.cn.icbc.ConfigClientApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;

/**
 * @author clyde lou
 */
@SpringBootTest(classes = ConfigClientApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest extends AbstractTestNGSpringContextTests {

    @org.testng.annotations.BeforeMethod
    public void setUp() throws Exception {
    }

    @org.testng.annotations.AfterMethod
    public void tearDown() throws Exception {
    }

    @org.testng.annotations.Test
    public void testHello() throws Exception {
        Assert.assertEquals("1", "1");
    }

    @org.testng.annotations.Test
    public void testSay() throws Exception {
        Assert.assertEquals("2", "2");
    }

}