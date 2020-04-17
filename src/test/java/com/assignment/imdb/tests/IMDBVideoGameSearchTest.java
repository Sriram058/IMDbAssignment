package com.assignment.imdb.tests;

import com.assignment.imdb.pages.IMDBHomePage;
import com.assignment.imdb.pages.IMDBSearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class IMDBVideoGameSearchTest extends RootClass {

    IMDBHomePage imdbHomePage;
    IMDBSearchResultsPage imdbSearchResultsPage;

    @BeforeTest
    public void startTest() {
        imdbHomePage = new IMDBHomePage();
        imdbSearchResultsPage = new IMDBSearchResultsPage();
    }

    @Test
    public void validateOpeningIMDBHomePage() {
        Assert.assertTrue(imdbHomePage.openHomePage());
    }

    @Test
    public void validateSearchingUsingString() {
        Assert.assertTrue(!(imdbHomePage.searchFor(searchString)) && imdbSearchResultsPage.verifyPageTitle());
    }

    @Test
    public void validateSearchTerm() {
        Assert.assertTrue(imdbSearchResultsPage.verifySearchTerm(searchString));
    }

    @Test
    public void validateFilteringSearchResultsByVideoGame() {
        imdbSearchResultsPage.filterByVideoGame();
        Assert.assertTrue(imdbSearchResultsPage.verifyVideoGameFilter() && imdbSearchResultsPage.verifySearchCategory());
    }
}