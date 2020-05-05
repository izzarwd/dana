package com.employeeapi.base;

import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID="10";
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		logger=Logger.getLogger("EmployeesRestAPI");
		PropertyConfigurator.configure("Log4j.properties");
	}
}
