package API.step_definitions;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {
    @Before
    public void setUp(){
        RestAssured.baseURI = "https://deckofcardsapi.com/api/deck/";
    }
}
