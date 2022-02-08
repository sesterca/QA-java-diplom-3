package ru.yandexpraktikum.stellarburgers.yandex;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import ru.yandexpraktikum.stellarburgers.pageobjects.MainPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class SwitchOverSectionsInConstructorTests {

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");
        System.setProperty("selenide.timeout", "4000");
    }

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
