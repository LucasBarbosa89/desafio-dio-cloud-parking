package one.digitalinnovation.parking;

import io.restassured.RestAssured;
import one.digitalinnovation.parking.controller.DTO.ParkingDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.regex.MatchResult;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkTest extends AbstractContainer {

	@LocalServerPort
	private int randomPort;

	@BeforeEach
	public void setUpTest(){
		RestAssured.port = randomPort;
	}

	@Test
	void findAllResultCheck() {

		RestAssured.given().when().get("/parking").then().
				statusCode(HttpStatus.OK.value()).body("license[0]", Matchers.equalTo("OVO-1544"));
	}

	@Test
	void createResultCheck(){

		ParkingDTO dto = new ParkingDTO();
		dto.setColor("Amarelo");
		dto.setLicense("DMC-6602");
		dto.setModel("Fiat Toro");
		dto.setState("RJ");

		RestAssured.given().when().contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(dto).post("/parking")
				.then().statusCode(HttpStatus.CREATED.value()).body("license",Matchers.equalTo("DMC-6602"));
	}

}
