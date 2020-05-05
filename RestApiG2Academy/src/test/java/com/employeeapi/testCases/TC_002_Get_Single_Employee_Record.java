package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_002_Get_Single_Employee_Record extends TestBase{
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void getEmployeeData()throws InterruptedException
	{
		logger.info("********** Started TC_002_Get_Single_Employee_Record **********");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET, "/employee/"+empID);
		
		Thread.sleep(3);
	}
	
	@Test
	void checkResponseBody() 
	{
		String responseBody=response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empID), true);
	}
	
	@Test
	void checkStatusCode() 
	{
		int statusCode=response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime() 
	{
		long responseTime=response.getTime();
		Assert.assertTrue(responseTime < 2000);
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
	void checkContentLength()
	{
		String contentLength=response.header("Content-Length");
		
		Assert.assertTrue(Integer.parseInt(contentLength)<1500);
	}
	
	@AfterClass
	void tearDown() 
	{
		logger.info("********** Finished TC_002_Get_Single_Employee_Record **********");
	}
}
