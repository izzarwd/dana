package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC_001_Get_All_Employees extends TestBase{
	
	@BeforeClass
	void getAllEmployees()throws InterruptedException
	{
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET, "/employees");
		
		Thread.sleep(3);
	}
	
	@Test
	void checkResponseBody() 
	{
		logger.info("********** Checking Response Body **********");
		
		String responseBody=response.getBody().asString();
		logger.info("response body ==> "+responseBody);
		Assert.assertTrue(responseBody != null);
	}
	
	@Test
	void checkStatusCode() 
	{
		logger.info("********** Checking Status Code **********");
		
		int statusCode=response.getStatusCode();
		logger.info("status code ==> "+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime() 
	{
		logger.info("********** Checking Response Time **********");
		
		long responseTime=response.getTime();
		logger.info("response time is ==> "+responseTime);
		Assert.assertTrue(responseTime < 3000);
	}
	
	@Test
	void checkStatusLine() 
	{
		logger.info("********** Checking Status Line **********");
		
		String statusLine=response.getStatusLine();
		logger.info("status line ==> "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType() 
	{
		logger.info("********** Checking Content Type **********");
		
		String contentType=response.header("Content-Type");
		logger.info("content type is ==> "+contentType);
		Assert.assertEquals(contentType, "application/json;charset=utf-8");
	}
	
	@Test
	void checkServerType() 
	{
		logger.info("********** Checking Server Type **********");
		
		String serverType=response.header("Server");
		logger.info("server type is ==> "+serverType);
		Assert.assertEquals(serverType, "nginx/1.16.0");
	}
	
	@Test
	void checkContentEncoding() 
	{
		logger.info("********** Checking Content Encoding **********");
		
		String contentEncoding=response.header("Content-Encoding");
		logger.info("content encoding is ==> "+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}
	
	@Test
	void checkContentLength() 
	{
		logger.info("********** Checking Content Length **********");
		
		String contentLength=response.header("Content-Length");
		logger.info("content length is ==> "+contentLength);
		
		if(Integer.parseInt(contentLength)<100)
			logger.warning("Content length is less than 100");
		
		Assert.assertTrue(Integer.parseInt(contentLength)>100);
	}
	
	@Test
	void checkCookies() 
	{
		logger.info("********** Checking Cookies **********");
		
		String cookie=response.getCookie("PHPSESSID");
		System.out.println(cookie);
	}
	
	@AfterClass
	void tearDown() 
	{
		logger.info("********** Finished TC_001_Get_All_Employees **********");
	}
}
