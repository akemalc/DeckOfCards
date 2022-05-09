package API.step_definitions;

import API.pojo.BlackJack;
import io.cucumber.java.en.*;
import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.junit.Assert.*;

public class hometask_steps {

    String deck_id;
    Response drawCard;
    BlackJack blackJack;
    int totalCards;
    int numberOfDeck = 1;
    int dealtCards = 2;
    Integer remainingCards = 52 * numberOfDeck;

    @Given("Shuffle the Cards")
    public void shuffle_the_cards() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("deck_count",numberOfDeck)
                .get("new/shuffle/");

     assertEquals("Status code",response.statusCode(),200);
     assertEquals("Content Type",response.contentType(),"application/json");
     assertEquals("Success message",response.path("success"),true);
     assertEquals("Remaining Total Cards",response.path("remaining"),remainingCards);
     assertEquals("Shuffled message",response.path("shuffled"),true);

        deck_id = response.path("deck_id");
     totalCards = response.path("remaining");

    }
    @When("Draw a Card")
    public void draw_a_card() {
        drawCard = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("count",dealtCards)
                .get(deck_id+"/draw/");

        blackJack = drawCard.body().as(BlackJack.class);
        drawCard.prettyPrint();
    }
    @Then("Verify Cards Dealt")
    public void verify_cards_dealt() {
        assertEquals("Status code",drawCard.statusCode(),200);
        assertEquals("Content Type", drawCard.andReturn().contentType(),"application/json");
        assertEquals("Success message",blackJack.getSuccess(),true);
        assertEquals("Deck ID",blackJack.getDeckId(),deck_id);
        assertEquals("Number of Cards", blackJack.getCards().size(),dealtCards);
        assertEquals("Remaining Cards", blackJack.getRemaining(),(totalCards-dealtCards));
    }

}
