import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class APITests {

    @Test
    public void testGetLaunches() {
        Response response = given().
                filter(new RequestLoggingFilter()).
                filter(new ResponseLoggingFilter()).
                baseUri("https://api.spacexdata.com/v4").
                request().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body("").
                when().
                get("/launches/latest").
                then().
                statusCode(200).
                extract().response();

        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
        String image = response.path("links.patch.large");
        System.out.println("Image URL: " + image);
    }
}