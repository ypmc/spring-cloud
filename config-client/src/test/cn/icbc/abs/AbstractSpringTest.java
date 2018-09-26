package test.cn.icbc.abs;

import main.cn.icbc.ConfigClientApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author clyde lou
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ConfigClientApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebAppConfiguration
public class AbstractSpringTest {
}
