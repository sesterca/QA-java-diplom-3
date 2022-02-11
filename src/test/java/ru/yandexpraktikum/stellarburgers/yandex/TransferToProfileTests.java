package ru.yandexpraktikum.stellarburgers.yandex;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandexpraktikum.stellarburgers.com.UserOperations;
import ru.yandexpraktikum.stellarburgers.pageobjects.LoginPage;
import ru.yandexpraktikum.stellarburgers.pageobjects.ProfilePage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;

public class TransferToProfileTests extends BaseTest {

    private UserOperations userOperations;
    public String userEmail;
    public String userPassword;

    @Before
    public void registerUser(){
        userOperations = new UserOperations();
        Map<String, String> userData = userOperations.register();
        userEmail = userData.get("email");
        userPassword = userData.get("password");}

    @After
    public void deleteUser(){
        userOperations.delete();}

    @Test
    @DisplayName("Переход по клику на Личный кабинет с главной страницы (авторизованный пользователь)")
    public void transferToProfilePageOnClickButtonAccountTest(){
        ProfilePage profilePage = open(LoginPage.ACCOUNT_URL, LoginPage.class)
                .setLoginForm(userEmail, userPassword)
                .clickButtonAccountAfterLogin();
        Assert.assertTrue(profilePage.isProfilePageLoaded());}
}
