package com.flipkartTest.stepdefs;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageStepDefs {

    private static final String[] HEADERS = {"Phone Model","Price", "Ratings"};
    private List<PhoneDetails> phoneDetails;

    @Given("User has opened chrome browser and  navigated to flipkart homepage")
    public void user_has_opened_chrome_browser_and_navigated_to_flipkart_homepage() {
        CucumberHooks.driver.get(CucumberHooks.FLIPKART_HOMEPAGE_URL);
    }

    @Given("User has closed the login popup")
    public void user_has_closed_the_login_popup() {
        WebDriverWait wait = new WebDriverWait(CucumberHooks.driver, Duration.ofSeconds(10));
        WebElement loginPopupCloseButton = CucumberHooks.driver.findElement(By.xpath("//button[@class='_2AkmmA _29YdH8']"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='_2AkmmA _29YdH8']")));
        loginPopupCloseButton.click();
    }

    @When("On entering {string} in search field")
    public void on_entering_in_search_field(String string) {
        WebElement searchField = CucumberHooks.driver.findElement(By.xpath("//form[@action='/search']//input"));
        searchField.sendKeys("iphone");
    }

    @When("clicking on search icon")
    public void clicking_on_search_icon() {
        WebElement searchIcon = CucumberHooks.driver.findElement(By.xpath("//button[@type='submit']"));
        searchIcon.click();
    }


    @Then("User filters for phones greater than {int}")
    public void user_filters_for_phones_greater_than(Integer int1) {
        WebElement minmPrice = CucumberHooks.driver.findElement(By.xpath("//option[@value='Min']/parent::select/option[@value='30000']"));
        FluentWait waitToSort = new FluentWait(CucumberHooks.driver);
        waitToSort.withTimeout(Duration.ofSeconds(5));
        minmPrice.click();
        waitToSort.until(ExpectedConditions.elementToBeClickable(By.cssSelector("._1HmYoV._35HD7C")));
    }

    @When("Sorts the results by price in ascending order")
    public void sorts_the_results_by_price_in_ascending_order() {
        WebElement priceAscendingSortElement = CucumberHooks.driver.findElement(By.xpath("//div[text()='Price -- Low to High']"));
        FluentWait waitToSort = new FluentWait(CucumberHooks.driver);
        waitToSort.withTimeout(Duration.ofSeconds(5));
//        waitToSort.until(ExpectedConditions.elementToBeClickable(priceAscendingSortElement));
        waitToSort.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".t-0M7P._2doH3V")));
        priceAscendingSortElement.click();
    }

    @Then("It collects the list of phones whose price is less than {int}")
    public void it_collects_the_list_of_phones_whose_price_is_less_than(Integer int1) {
        FluentWait waitToSort = new FluentWait(CucumberHooks.driver);
        waitToSort.withTimeout(Duration.ofSeconds(5));
                waitToSort.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".t-0M7P._2doH3V")));
        List<PhoneDetails> phoneDetails = populatePhoneDetailsList();
        System.out.println(phoneDetails);

    }

    @NotNull
    private List<PhoneDetails> populatePhoneDetailsList() {
        phoneDetails = new ArrayList<>();
        List<WebElement> phoneNames = CucumberHooks.driver.findElements(By.cssSelector("._3wU53n"));
        List<WebElement> phonePrices = CucumberHooks.driver.findElements(By.cssSelector("._1vC4OE._2rQ-NK"));
        List<WebElement> phoneRatings = CucumberHooks.driver.findElements(By.xpath(
                "//span[@class='_38sUEc']/descendant::span[contains(text(),'Ratings')]"));
        for (int i = 0; i <phoneNames.size() ; i++) {
            Integer price = Integer.parseInt(phonePrices.get(i).getText().substring(1).replaceAll(",",""));
            if(price>40000)
                break;
            phoneDetails.add(new PhoneDetails(phoneNames.get(i).getText(), phonePrices.get(i).getText(), phoneRatings.get(i).getText()));
        }
        return phoneDetails;
    }

    @Then("Generate the data into csv")
    public void Generate_the_data_into_csv() throws IOException{
            FileWriter out = new FileWriter("Phone_Details.csv");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                .withHeader(HEADERS))) {
            for (PhoneDetails phoneDetail : phoneDetails) {
                printer.printRecord(phoneDetail);
            }
        }
    }

}
