package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import DriverFactory.WebDriverFactory;
import PageObjects.SearchPage;

public class SearchPageSteps {

	public SearchPage users = new SearchPage(WebDriverFactory.getDriver());

	@Given("the user is on the E-commerce website")
	public void user_is_on_eCommerce_website() {
		
		WebDriverFactory.getDriver().get("https://www.ebay.com/");
	}

	@When("the user clicks on Shop By Category")
	public void the_user_clicks_on_shop_by_category() {
		
		users.clickingOnshopByCategoryBtn();
	}

	@When("the user selects Cell Phones and Accessories option")
	public void the_user_selects_cell_phones_and_accessories_option() {
		
		users.selectingPhonesAndSmartphones();
	}

	@When("the user applies the {string} filter and selects {string}")
	public void the_user_applies_the_filter_and_selects(String string, String string2) {
		
		users.applyConditionFilter("Condition", "New");
	}

	@When("the user applies the {string} filter with a range from {string} to {string}")
	public void the_user_applies_the_filter_with_a_range_from_to(String string, String fromPrice, String toPrice) {
		
		users.applyPriceFilter("Price", fromPrice, toPrice);
	}

	@Then("the applied {string} filter tag should be displayed and match {string}")
	public void the_applied_filter_tag_should_be_displayed_and_match(String filterByLocation, String location) {
		users.applyItemLocationFilter(filterByLocation, location);
		users.applyItemLocationFilter("Item Location","US Only");
	}

	@Then("the applied {string} filter tag should be displayed")
	public void the_applied_filter_tag_should_be_displayed(String string) {

		String actualConditionTag = users.getPhoneCondtionTagText();
		Assert.assertEquals(actualConditionTag, "New");

		String priceTageText = users.getPriceFilterTagText();
		Assert.assertEquals(priceTageText, "Price");
	}

	@Then("the applied {string} filter tag should be displayed and match")
	public void the_applied_filter_tag_should_be_displayed_and_match(String string) {

		String actualItemLocationTag = users.getItemLocationTagText();
		Assert.assertEquals(actualItemLocationTag, "Item Location");
	}

}
