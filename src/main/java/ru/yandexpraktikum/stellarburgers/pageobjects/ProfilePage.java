package ru.yandexpraktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;

public class ProfilePage {

    public static final String PROFILE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    //кнопка Выйти
    @FindBy(how = How.XPATH, using = ".//li[button[text()='Выход']]")
    public SelenideElement buttonLogout;

    //ссылка StellarBurgers в хедере
    @FindBy(how = How.XPATH, using = ".//div[@class='AppHeader_header__logo__2D0X2']/a")
    public SelenideElement logoBurger;

    //кнопка Конструктор
    @FindBy(how = How.XPATH, using = ".//a[p[text()='Конструктор']]")
    public SelenideElement buttonConstructor;

    public LoginPage clickButtonLogout(){
        buttonLogout.shouldBe(Condition.and("can be clicked", visible, enabled), Duration.ofSeconds(3000)).click();
        return Selenide.page(LoginPage.class);}

    public void clickLogoBurger(){logoBurger.shouldBe(visible).click();}

    public void clickButtonConstructor(){
      buttonConstructor.shouldBe(Condition.and("can be clicked", visible, enabled), Duration.ofSeconds(3000)).click();}
}
