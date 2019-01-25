import com.example.DemoApplication;
import com.example.domain.Book;
import com.jayway.restassured.RestAssured;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.Response;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class },
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LiveTest {

    private static final String API_ROOT = "http://localhost:8080/api/books";

    private Book createRandomBook() {
        Book book = new Book();
        book.setTitle(randomAlphabetic(10));
        book.setAuthor(randomAlphabetic(15));
        return book;
    }

    private String createBookAsUri(Book book) {
        Response response = (Response) RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(book)
                .post(API_ROOT);
        return API_ROOT + "/" + (((com.jayway.restassured.response.Response) response).jsonPath().get("id"));
    }


    @Test
    public void whenGetAllBooks_thenOK() {
        Response response = (Response) RestAssured.get(API_ROOT);

        assertEquals(HttpStatus.OK.value(), ((com.jayway.restassured.response.Response) response).getStatusCode());

    }

    @Test
    public void whenGetBooksByTitle_thenOK() {
        Book book = createRandomBook();
        createBookAsUri(book);
        Response response = (Response) RestAssured.get(
                API_ROOT + "/title/" + book.getTitle());

        assertEquals(HttpStatus.OK.value(), ((com.jayway.restassured.response.Response) response).getStatusCode());
        assertEquals(book.getTitle(), ((com.jayway.restassured.response.Response) response).jsonPath().get("title"));
    }

    @Test
    public void whenGetNotExistBookById_thenNotFound() {
        Response response = (Response) RestAssured.get(API_ROOT + "/" + randomNumeric(4));

        assertEquals(HttpStatus.NOT_FOUND.value(), ((com.jayway.restassured.response.Response) response).getStatusCode());
    }
}
