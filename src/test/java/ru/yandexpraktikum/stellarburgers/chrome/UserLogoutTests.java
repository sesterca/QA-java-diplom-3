package ru.yandexpraktikum.stellarburgers.chrome;

import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import ru.yandexpraktikum.stellarburgers.com.UserOperations;
import ru.yandexpraktikum.stellarburgers.pageobjects.LoginPage;
import ru.yandexpraktikum.stellarburgers.pageobjects.MainPage;
import ru.yandexpraktikum.stellarburgers.pageobjects.ProfilePage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class UserLogoutTests {

    private UserOperations userOperations;
    public static String userEmail;
    public static String userPassword;

    @Before
    public void createUser(){
        userOperations = new UserOperations();
        Map<String, String> userData = userOperations.register();
        userEmail = userData.get("email");
        userPassword = userData.get("password");
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");}

    @After
    public void deleteUser(){userOperations.delete();}

    @Test
    @DisplayName("Выход по кнопке Выйти в личном кабинете")
    public void userLogoutOnClickLogoutButtonAccountPageTest(){
        MainPage mainPage = open(LoginPage.ACCOUNT_URL, LoginPage.class)
                .setLoginForm(userEmail, userPassword);
        Selenide.sleep(4000);
        ProfilePage profilePage = mainPage.clickButtonAccountAfterLogin();
        Selenide.sleep(4000);
        profilePage.clickButtonLogout();
        Selenide.sleep(4000);
        Assert.assertEquals(url(), LoginPage.ACCOUNT_URL);
    }
}
