package com.assignment.imdb.tests;

import com.assignment.imdb.pages.IMDBHomePage;
import com.assignment.imdb.pages.IMDBSearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class IMDBVideoGameSearchTest extends RootClass {

    IMDBHomePage imdbHomePage;
    IMDBSearchResultsPage imdbSearchResultsPage;

    @BeforeTest
    public void initializeTest() {
        imdbHomePage = new IMDBHomePage();
        imdbSearchResultsPage = new IMDBSearchResultsPage();
    }

    @Test(priority = 1,
            testName = "Verify and Validate Opening IMDb Home Page.",
            description = "Verify and Validate Opening IMDb Home Page.")
    public void validateOpeningIMDBHomePage() {
        Assert.assertTrue(imdbHomePage.openHomePage(),
                "Unable to open IMDb Home Page. Hence, the Test has failed.");
    }

    @Test(priority = 2,
            testName = "Verify and Validate Searching a Video using a search string.",
            description = "Verify and Validate Searching a Video using a search string.",
            dependsOnMethods = {"validateOpeningIMDBHomePage"})
    public void validateSearchingUsingString() {
        Assert.assertTrue((!(imdbHomePage.searchFor(searchString)) && imdbSearchResultsPage.verifyPageTitle()),
                "Unable to search the Video using Search String. Hence, the Test has failed.");
    }

    @Test(priority = 3,
            testName = "Verify and validate if the Search Term is displayed as expected.",
            description = "Verify and validate if the Search Term is displayed as expected.",
            dependsOnMethods = {"validateSearchingUsingString"})
    public void validateSearchTerm() {
        Assert.assertTrue(imdbSearchResultsPage.verifySearchTerm(searchString),
                "Search Term is not displayed as expected. Hence, the Test has failed.");
    }

    @Test(priority = 4,
            testName = "Verify and validate if the video game search results are displayed on selecting Video Game filter.",
            description = "Verify and validate if the video game search results are displayed on selecting Video Game filter.",
            dependsOnMethods = {"validateSearchingUsingString"})
    public void validateFilteringSearchResultsByVideoGame() {
        imdbSearchResultsPage.filterByVideoGame();
        Assert.assertTrue((imdbSearchResultsPage.verifyVideoGameFilter() && imdbSearchResultsPage.verifySearchCategory()),
                "Unable to filter the Video Search Results by Video Game filter. Hence, the Test has failed.");
    }

    @AfterTest
    public void terminateTest()   {
        imdbHomePage = null;
        imdbSearchResultsPage = null;
    }
}