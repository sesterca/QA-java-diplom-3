package ru.yandexpraktikum.stellarburgers.chrome;

import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.Before;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");
        System.setProperty("selenide.pageLoadTimeout", "30000");
        Configuration.timeout = 90000;
    }

    @After
    public void tearDown(){getWebDriver().quit();}
}
