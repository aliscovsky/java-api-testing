package com.solvd.carina.demo;

import java.lang.invoke.MethodHandles;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.zebrunner.carina.core.IAbstractTest;
import com.solvd.carina.demo.api.GetEmployeeMethods;
import com.solvd.carina.demo.api.GetEmployeesMethods;
import com.solvd.carina.demo.api.PostEmployeeMethods;
import com.solvd.carina.demo.api.PostUserMethod;
import com.zebrunner.carina.api.APIMethodPoller;
import com.zebrunner.carina.api.apitools.validation.JsonComparatorContext;
import com.zebrunner.carina.api.apitools.validation.JsonCompareKeywords;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.core.registrar.tag.Priority;
import com.zebrunner.carina.core.registrar.tag.TestPriority;

public class APITests implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test()
    @MethodOwner(owner = "aliscovsky")
    public void testGetEmployees() {
        GetEmployeesMethods getEmployeesMethods = new GetEmployeesMethods();
        getEmployeesMethods.callAPIExpectSuccess();
        getEmployeesMethods.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey() + "data");
        getEmployeesMethods.validateResponseAgainstSchema("api/employees/_get/rs.schema");
    }
    
    @Test()
    @MethodOwner(owner = "aliscovsky")
    public void testGetEmployee() {
    	String employeeId = "1";
    	
        GetEmployeeMethods getEmployeeMethod = new GetEmployeeMethods(employeeId);
        getEmployeeMethod.callAPIExpectSuccess();
        
        JsonComparatorContext comparatorContext = JsonComparatorContext.context()
                .<Integer>withPredicate("idPredicate", id -> id == Integer.valueOf(employeeId).intValue());
        getEmployeeMethod.validateResponse(comparatorContext);
        getEmployeeMethod.validateResponseAgainstSchema("api/employees/employee/_get/rs.schema");
    }
    
    @Test()
    @MethodOwner(owner = "aliscovsky")
    public void testCreateEmployee() throws Exception {
        LOGGER.info("test");
        setCases("");
        PostEmployeeMethods api = new PostEmployeeMethods();
        api.setProperties("api/employees/employee/employee.properties");
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

}
