package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_005_Delete_Employee_Record extends TestBase{
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void deleteEmployee()throws InterruptedException
	{
		logger.info("********** Started TC_005_Delete_Employee_Record **********");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		
		response=httpRequest.request(Method.GET, "/employees");
		
		JsonPath jsonPathEvaluator=response.jsonPath();
		
		String empID=jsonPathEvaluator.get("[0].id");
		
		response=httpRequest.request(Method.DELETE, "/delete/"+empID);
		
		Thread.sleep(5);
	}
	
	@Test
	void checkResponseBody()
	{
		String responseBody=response.getBody().asString();
		Assert.assertEquals(responseBody.contains("successfully!delete records"), true);
		
		System.out.println(responseBody);
	}
	
	@Test
	void checkStatusCode() 
	{
		int statusCode=response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkStatusLine() 
	{
		String statusLine=response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType() 
	{
		String contentType=response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json;charset=utf-8");
	}
	
	@Test
	void checkServerType() 
	{
		String serverType=response.header("Server");
		Assert.assertEquals(serverType, "nginx/1.16.0");
	}
	
	@Test
	void checkContentEncoding() 
	{
		String contentEncoding=response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, "gzip");
	}
	
	@AfterClass
	void tearDown() 
	{
		logger.info("********** Finished TC_005_Delete_Employee_Record **********");
	}
}
