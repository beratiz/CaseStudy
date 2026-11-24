package de.amazon.stepdefinitions;

import static de.amazon.utilities.Driver.*;
import static de.amazon.utilities.ReusableMethods.waitFor;

import de.amazon.pages.BasketPage;
import de.amazon.pages.HomePage;
import de.amazon.pages.SearchResultPage;
import de.amazon.utilities.ConfigReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;

import java.util.List;


public class AmazonTestStepDef {

    HomePage homePage = new HomePage();
    SearchResultPage searchResultPage = new SearchResultPage();
    BasketPage basketPage = new BasketPage();

    @Given("As a new Amazon user, I am on the Amazon home page")
    public void as_a_new_amazon_user_i_am_on_the_amazon_home_page() {
        getDriver().get(ConfigReader.getProperty("amazon_url_de"));
        try {
            homePage.cookiesAcceptButton.click();
        } catch (NoSuchElementException e) {
        }
    }

    @And("I search products and add the cheapest one to basket")
    public void i_search_and_add_the_cheapest_one_to_basket(List<String> searchList) {

        for (String product : searchList) {
            homePage.searchProduct(product);
            searchResultPage.addToCartTheCheapestOne();
        }
    }

    @Given("I navigate to basket")
    public void i_navigate_to_basket() {
        new Actions(getDriver()).sendKeys(Keys.HOME).perform();
        homePage.menuBasket.click();
    }

    @Then("verify that basket calculates the result correctly")
    public void verify_that_basket_calculates_the_result_correctly() {
        String expectedResult = basketPage.calculateBasketResult();
        String actualResult = basketPage.getAmountOfBasket();
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Then("I navigate to checkout")
    public void i_navigate_to_checkout() {
        basketPage.toCheckoutButton.click();
        waitFor(2);

    }

    @Then("verify that user gets redirected to registration page")
    public void verify_user_gets_redirected_to_registration_page() {
        String expectedPageTitle = "Anmelden";
        String actualPageTitle = getDriver().getTitle();
        Assert.assertEquals(expectedPageTitle, actualPageTitle);
    }


}
