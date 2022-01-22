package test.automation.steps.RestAssuredExample;

import static io.restassured.RestAssured.given;
import static test.automation.framework.Rest.*;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import test.automation.framework.Config;

public class GoogleBooksAPISteps {

    private final String ENDPOINT_GET_BOOK_BY_ISBN = Config.getUrl() + "/books/v1/volumes";

    @Given("a book exists with \"([^\"]*)\" isbn")
    public void aBookExistsWithIsbn(String isbn){
        request = given().param("q", "isbn:" + isbn);
    }

    @When("a user retrieves the book by isbn")
    public void aUserRetrievesTheBookByIsbn(){
        response = request.when().get(ENDPOINT_GET_BOOK_BY_ISBN);
    }
}
