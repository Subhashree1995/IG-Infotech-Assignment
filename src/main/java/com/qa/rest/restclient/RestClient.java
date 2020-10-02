package com.qa.rest.restclient;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
	
	public static Response doGet(String contentType,String baseURI,String basePath,boolean log) {
		if(setBaseURI(baseURI)) {
			RequestSpecification request=createRequest(contentType, log);
			return getResponse("GET", request, basePath);
		}
		return null;
		
	}
	private static boolean setBaseURI(String baseURI) {
		if(baseURI==null || baseURI.isEmpty()) {
			return false;
		}
		try {
			RestAssured.baseURI=baseURI;
			return true;
		}catch(Exception e) {
			System.out.println("Some exception occured while putting the baseURI");
			return false;
		}
	}
	private static RequestSpecification createRequest(String contentType,boolean log) {
		RequestSpecification request;
		if(log) {
			request=RestAssured.given().log().all();
		}else {
			request=RestAssured.given();
		}
		if(contentType!=null) {
			if(contentType.equalsIgnoreCase("JSON")) {
				request.contentType(ContentType.JSON);
			}else if(contentType.equalsIgnoreCase("XML")){
				request.contentType(ContentType.XML);
			}else if(contentType.equalsIgnoreCase("TEXT")) {
				request.contentType(ContentType.TEXT);
			}
		}
		return request;
		
	}
	private static Response getResponse(String httpmethod,RequestSpecification request,String basePath) {
		return executeApi(httpmethod, request, basePath);
	}
	private static Response executeApi(String httpmethod,RequestSpecification request,String basePath) {
		Response response=null;
		switch (httpmethod) {
		case "GET":
			response=request.get(basePath);
			break;
		case "POST":
			response=request.post(basePath);
			break;
		case "PUT":
			response=request.put(basePath);
			break;
		case "DELETE":
			response=request.delete(basePath);
			break;

		default:
			System.out.println("Please enter correct htttp method");
			break;
		}
		return response;
	}

}
