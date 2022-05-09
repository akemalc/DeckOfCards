package API;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    @BeforeAll
    static void setUp(){
        RestAssured.baseURI = "https://deckofcardsapi.com/api/deck";
    }
}
