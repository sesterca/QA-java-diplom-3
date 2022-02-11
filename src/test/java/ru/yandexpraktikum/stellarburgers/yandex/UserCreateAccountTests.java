package ru.yandexpraktikum.stellarburgers.yandex;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.yandexpraktikum.stellarburgers.pageobjects.LoginPage;
import ru.yandexpraktikum.stellarburgers.pageobjects.RegisterPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserCreateAccountTests extends BaseTest {

    @Test
    @DisplayName("Создание пользователя с валидными данными")
    public void createUserAccountTest(){
        LoginPage loginPage = open(RegisterPage.REGISTER_URL, RegisterPage.class)
                .setRegistrationForm();
        assertTrue(loginPage.isLoginPageLoaded());}

    @Test
    @DisplayName("Создание пользователя с невалидным паролем (вывод ошибки)")
    public void errorCreationUserWithInvalidPasswordTest(){
        RegisterPage registerPage = open(RegisterPage.REGISTER_URL, RegisterPage.class)
                .setRegistrationFormWithInvalidPassword();
        assertEquals("Некорректный пароль", registerPage.getErrorText());}
}