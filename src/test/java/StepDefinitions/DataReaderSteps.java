package StepDefinitions;

import java.util.List;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class DataReaderSteps {
	@Given("user on the homepage")
	public void user_on_the_homepage() {
	   System.out.println("User is on Home Page ");
	}

	@Given("user follows {string}")
	public void user_follows(String string) {
		System.out.println("User Follows : "+string);
	}

	@When("user fills {string} with {string}")
	public void user_fills_with(String string, String string2) {
		System.out.println("User fillls :"+string +""+string2 );
	}

	@When("user clicks {string}")
	public void user_clicks(String string) {
		System.out.println("User clicks  :"+string);
	}

	@When("user enters the following details")
	public void user_enters_the_following_details(io.cucumber.datatable.DataTable dataTable) {
		/*
		 * List<List<String>> data = dataTable.asLists();
		 * System.out.println("First Name : "+data.get(0)+ "First Name : "+data.get(0));
		 * System.out.println("First Name : "+data.get(1)+ "First Name : "+data.get(1));
		 */
		Map<String, String> map = dataTable.asMap();
		System.out.println(map.get(0));

	
	}
}
