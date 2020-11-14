package com.sol.problem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.model.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class ProblemApplicationTests {
	@LocalServerPort
	private int assignedPort;

	private final String httpUrl = "http://127.0.0.1:";
	ObjectMapper om=new ObjectMapper();
	@Test
	void successResponsePrb1() throws UnirestException, JsonProcessingException {

		final HttpResponse<String> response = Unirest.get(httpUrl + assignedPort + "/api/problem1?inputStr=Category:Humanities&pageId=100411")
				.asString();
		Response res=om.readValue(response.getBody(), Response.class);
		assertTrue(!res.getTerms().isEmpty() && !res.getRelations().isEmpty());
	}

	@Test
	void negativeResponseInputStringMissingPrb1() throws UnirestException, JsonProcessingException {

		final HttpResponse<String> response = Unirest.get(httpUrl + assignedPort + "/api/problem1?inputStr=&pageId=100411")
				.asString();
		Response res=om.readValue(response.getBody(), Response.class);
		assertTrue(res.getStatus()==400 && res.getMessage().equalsIgnoreCase("Mandatory parameter(s) missing") && res.getError().equalsIgnoreCase("Bad Request"));
	}

	@Test
	void successResponsePrb2() throws UnirestException, JsonProcessingException {

		final HttpResponse<String> response = Unirest.get(httpUrl + assignedPort + "/api/problem2?inputStr=Category:Anthropologists&cmlimit=11")
				.asString();
		Response res=om.readValue(response.getBody(), Response.class);
		assertTrue(!res.getTerms().isEmpty() && !res.getRelations().isEmpty());
	}

	@Test
	void negativeResponseMandInputMissingPrb2() throws UnirestException, JsonProcessingException {

		final HttpResponse<String> response = Unirest.get(httpUrl + assignedPort + "/api/problem2?inputStr=Category:Anthropologists&cmlimit=")
				.asString();
		Response res=om.readValue(response.getBody(), Response.class);
		assertTrue(res.getStatus()==400 && res.getMessage().equalsIgnoreCase("Mandatory parameter(s) missing") && res.getError().equalsIgnoreCase("Bad Request"));
	}

}
