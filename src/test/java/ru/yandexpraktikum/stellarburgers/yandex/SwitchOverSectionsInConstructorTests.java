package ru.yandexpraktikum.stellarburgers.yandex;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.yandexpraktikum.stellarburgers.pageobjects.MainPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class SwitchOverSectionsInConstructorTests extends BaseTest {

    @Test
    @DisplayName("Проверка перехода к разделу Булки")
    public void switchToBunsSectionTest(){
        MainPage mainPage = open(MainPage.MAIN_URL,MainPage.class)
                .clickLinkFillings()
                .clickLinkBuns();
        Assert.assertTrue(mainPage.sectionBuns.is(visible));}

    @Test
    @DisplayName("Проверка перехода к разделу Соусы")
    public void switchToSaucesSectionTest(){
        MainPage mainPage = open(MainPage.MAIN_URL,MainPage.class)
                .clickLinkSauces();
        Assert.assertTrue(mainPage.sectionSauces.is(visible));}

    @Test
    @DisplayName("Проверка перехода к разделу Начинки")
    public void switchToFillingsSectionTest(){
        MainPage mainPage = open(MainPage.MAIN_URL,MainPage.class)
                .clickLinkFillings();
        Assert.assertTrue(mainPage.sectionFillings.is(visible));}
}
