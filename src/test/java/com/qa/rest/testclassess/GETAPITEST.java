package com.qa.rest.testclassess;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.rest.restclient.RestClient;
import com.qa.rest.utill.CONSTANTS;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GETAPITEST {

	String baseURI = "http://demo4032024.mockable.io";
	String basePath = "/apitest";

	@Test
	public void validateStatusCodeTest() {
		Response response = RestClient.doGet("JSON", baseURI, basePath, true);
		int statusCode = response.getStatusCode();
		System.out.println("The status code is :: " + statusCode);
		Assert.assertEquals(statusCode, CONSTANTS.status_code);
	}

	@Test
	public void validateHeaderJsonResponse() {
		Response response = RestClient.doGet("JSON", baseURI, basePath, true);
		String jsonHeader = response.getHeader("Content-Type");
		Assert.assertEquals(jsonHeader, "application/json; charset=UTF-8");
	}

	@Test
	public void verifyCompanyData() {
		Response response = RestClient.doGet("JSON", baseURI, basePath, true);
		JsonPath js = response.jsonPath();
		Assert.assertEquals(js.getInt("employeeData[0].age"), CONSTANTS.age);
		Assert.assertEquals(js.getInt("status"), CONSTANTS.status_code);
		Assert.assertEquals(js.getString("employeeData[0].role"), CONSTANTS.role);
		Assert.assertEquals(js.getString("employeeData[0].dob"), CONSTANTS.dob);
		Assert.assertEquals(js.getString("message"), CONSTANTS.message);
	}

	@Test
	public void verifyCompanyName() {
		Response response = RestClient.doGet("JSON", baseURI, basePath, true);
		JsonPath js = response.jsonPath();
		Assert.assertEquals(js.getString("employeeData[0].company"), CONSTANTS.company_name, "fail");
	}

}
