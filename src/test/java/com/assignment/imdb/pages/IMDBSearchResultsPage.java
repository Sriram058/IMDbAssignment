package com.assignment.imdb.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class IMDBSearchResultsPage {
    private final String PAGE_TITLE = "Find - IMDb";
    private final String FILTER_VIDEO_GAME = "Video Game";
    private final String CATEGORY_VIDEO_GAME = "Search category: Video Game Titles";

    private final SelenideElement searchTerm = $("span.findSearchTerm");
    private final SelenideElement videoGameFilter = $("ul.findTitleSubfilterList").$(By.tagName("li"),3).$(By.tagName("a"));
    private final SelenideElement activeFilterInContext = $(By.cssSelector("li.filterActive"), 0);
    private final SelenideElement activeCategoryInContext = $("div#findSubHeader");

    public boolean verifyPageTitle(){
        return WebDriverRunner.getWebDriver().getTitle().equals(PAGE_TITLE);
    }

    public boolean verifySearchTerm(String searchString) {
        searchString = String.format("\"%s\"", searchString);
        return searchTerm.innerText().equals(searchString);
    }
    public void filterByVideoGame() {
        videoGameFilter.click();
    }

    public boolean verifyVideoGameFilter() {
        return activeFilterInContext.text().equals(FILTER_VIDEO_GAME);
    }

    public boolean verifySearchCategory() {
        return activeCategoryInContext.text().equals(CATEGORY_VIDEO_GAME);
    }
}
