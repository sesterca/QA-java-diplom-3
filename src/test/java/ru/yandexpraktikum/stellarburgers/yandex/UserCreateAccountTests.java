package ru.yandexpraktikum.stellarburgers.yandex;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import ru.yandexpraktikum.stellarburgers.pageobjects.LoginPage;
import ru.yandexpraktikum.stellarburgers.pageobjects.RegisterPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserCreateAccountTests {

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");
        System.setProperty("selenide.timeout", "4000");}

    @Test
    @DisplayName("Cоздание пользователя с валидными данными")
    public void createUserAccountTest(){
        RegisterPage registerPage = open(RegisterPage.REGISTER_URL, RegisterPage.class);
        LoginPage loginPage = registerPage.setRegistrationForm();
        assertTrue(registerPage.isUserRegistrationSuccess());}

    @Test
    @DisplayName("Cоздание пользователя с невалидным паролем (вывод ошибки)")
    public void errorCreationUserWithInvalidPasswordTest(){
        RegisterPage registerPage = open(RegisterPage.REGISTER_URL, RegisterPage.class);
        registerPage.setRegistrationFormWithInvalidPassword();
        assertEquals("Некорректный пароль", registerPage.getErrorText());}
}