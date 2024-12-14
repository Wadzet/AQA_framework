package task_16;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.testng.Assert.assertEquals;

public class Task16TrelloAPITest {

    private static String BASE_URL = "https://api.trello.com/1";
    private static String apiKey;
    private static String token;
    private static String boardId;
    private static String listId;
    private static String cardId;

    @BeforeClass
    public void setup() {
        Dotenv dotenv = Dotenv.load();

        apiKey = dotenv.get("TRELLO_API_KEY");
        token = dotenv.get("TRELLO_TOKEN");
        boardId = dotenv.get("TRELLO_BOARD_ID");
        listId = dotenv.get("TRELLO_ID_LIST");

        RestAssured.baseURI = BASE_URL;
    }

    @Test(priority = 1)
    public void createCardTest() {
        cardId = given()
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .queryParam("idList", listId)
                .queryParam("name", "Task16 Test Card")
                .header("Content-Type", "application/json")
                .log().all()
                .when()
                .post("/cards")
                .then()
                .statusCode(200)
                .log().all()
                .extract().path("id");

        System.out.println("Created Card ID: " + cardId);
    }

    @Test(priority = 2)
    public void addMemberToCardTest() {
        String memberId = "670451fff4afb535b7bab9e8";

        given()
                .header("Content-Type", "application/json")
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .body("{ \"idMember\": \"" + memberId + "\" }")
                .log().all()
                .when()
                .post("/cards/" + cardId + "/idMembers")
                .then()
                .statusCode(200)
                .body("idMembers", hasItem(memberId))
                .log().all();
    }

    @Test(priority = 3)
    public void updateCardDescriptionTest() {
        String newDescription = "Updated description for Task16 card";

        given()
                .header("Content-Type", "application/json")
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .queryParam("desc", newDescription)
                .log().all()
                .when()
                .put("/cards/" + cardId)
                .then()
                .statusCode(200)
                .body("desc", org.hamcrest.Matchers.equalTo(newDescription))
                .log().all();
    }

    @Test(priority = 4)
    public void moveCardToAnotherListTest() {
        String newListId = "676011f00f2aa603129b088e";

        given()
                .header("Content-Type", "application/json")
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .queryParam("idList", newListId)
                .log().all()
                .when()
                .put("/cards/" + cardId)
                .then()
                .statusCode(200)
                .body("idList", org.hamcrest.Matchers.equalTo(newListId))
                .log().all();
    }
}

//V7.
//Create a new card on a board. Check that the API call returns a success status code and that the card is created with the name provided in the API call.
//Add a member to the card. Check that the API call returns a success status code and that the member is added to the card.
//Update the description of the card. Check that the API call returns a success status code and that the description of the card is updated as expected.
//Move the card to a different list on the board. Check that the API call returns a success status code and that the card is moved to the correct list.

//Make restAssured TC using scenario from Task_15.
//The same using any another API client.
//Add Request and Response clases for each unique endpoints.
//Validate Response Object using your own class comparator.
