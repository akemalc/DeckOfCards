package API;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class CardsTests extends TestBase{

    static String deck_id;
    static Response drawCard;
    static API.pojo.BlackJack blackJack;
    static int totalCards;
    static int numberOfDeck = 1;
    static int dealtCards = 2;
    Integer remainingCards = 52 * numberOfDeck;

    @Test
    @Order(1)
    @DisplayName("Shuffle the Cards")
    void shuffleCards(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("deck_count",numberOfDeck)
                .get("/new/shuffle/");

        assertEquals(200,response.statusCode(),"Status code");
        assertEquals("application/json",response.contentType(),"Content Type");
        assertEquals(true,response.path("success"),"Success message");
        assertEquals(remainingCards,response.path("remaining"),"Remaining Total Cards");
        assertEquals(true,response.path("shuffled"),"Shuffled message");

        deck_id = response.path("deck_id");
        totalCards = response.path("remaining");
    }

    @Test
    @Order(2)
    @DisplayName("Draw a Card")
    void draw2Cards(){
        drawCard = given().accept(ContentType.JSON)
                .and().queryParam("count",dealtCards)
                .get("/"+deck_id+"/draw/");

        assertEquals(200,drawCard.statusCode(),"Status code");
        assertEquals("application/json",drawCard.andReturn().contentType(),"Content Type");

        blackJack = drawCard.body().as(API.pojo.BlackJack.class);
        drawCard.prettyPrint();
    }

    @Test
    @Order(3)
    @DisplayName("Verify Cards Dealt")
    void dealtCards(){
        assertEquals(true,blackJack.getSuccess(),"Success message");
        assertEquals(deck_id, blackJack.getDeckId(),"Deck ID");
        assertEquals(dealtCards, blackJack.getCards().size(),"Number of Cards");
        assertEquals((totalCards-dealtCards), blackJack.getRemaining(),"Remaining Cards");
    }

}
