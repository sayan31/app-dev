package com.sayan.fullstack.demospringbootangular;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.sayan.fullstack.demospringbootangular.rest.ResourceConstants;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoSpringbootAngularApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReservationResourceTest {
	
	private static final Integer EXPECTED_ITEM_ID_FOR_GET = 1;
	
	@LocalServerPort
	private int port;

	@BeforeEach
	void setUp() throws Exception {
		RestAssured.port = Integer.valueOf(port);
        RestAssured.basePath = ResourceConstants.ROOM_RESERVATION_V1;
        RestAssured.baseURI = "http://localhost";
	}

	@Test
	void test() {
		//fail("Not yet implemented");
		given().when().get("/"+EXPECTED_ITEM_ID_FOR_GET).then().statusCode(200);
	}

}
