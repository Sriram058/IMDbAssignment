package com.assignment.imdb.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.SelenideWait;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class IMDBHomePage {
    private final String PAGE_URL = "https://www.imdb.com/";
    private final String PAGE_TITLE = "IMDb: Ratings, Reviews, and Where to Watch the Best Movies & TV Shows";

    private final SelenideElement searchBar = $(byName("q"));

    public boolean openHomePage() {
        open(PAGE_URL);
        return WebDriverRunner.getWebDriver().getTitle().equals(PAGE_TITLE);
    }

    public boolean searchFor(String searchString) {
        searchBar.val(searchString).pressEnter();
        SelenideWait selenideWait = new SelenideWait(WebDriverRunner.getWebDriver(), 3000L, 1000L);
        selenideWait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(PAGE_URL)));
        return WebDriverRunner.getWebDriver().getCurrentUrl().equals(PAGE_URL);
    }
}
