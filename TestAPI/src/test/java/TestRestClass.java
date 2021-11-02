import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestRestClass {
    @Test
    public void CheckGeorgeUser(){
        given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .body("data.find{it.first_name=='George'}.email", equalTo("george.bluth@reqres.in"));
    }
    @Test
    public void CheckMichaelSecondPage() {
        given()
                .params("page",2)
                .contentType(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(200)
                .body("data.find{it.first_name=='Michael'}.email", containsString("michael.lawson@reqres"));
    }

}

