package de.amazon.pages;

import de.amazon.utilities.Driver;
import de.amazon.utilities.ReusableMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchResultPage {
    public SearchResultPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "s-result-sort-select")
    public WebElement resultSortSelect;

    @FindBy(xpath = "//*[@data-component-type=\"s-search-result\"]//img")
    public WebElement firstProductOfSearchResult;


    @FindBy(name = "submit.addToCart")
    public WebElement addToCheapestOne;


    /**
     *Adds the cheapest product to cart
     */
    public void addToCartTheCheapestOne() {
        Select options = new Select(resultSortSelect);
        options.selectByIndex(1);
        ReusableMethods.waitForClickablility(firstProductOfSearchResult,5);
        addToCheapestOne.click();
    }
}
