package com.solvd.carina.demo;

import java.lang.invoke.MethodHandles;

import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.zebrunner.carina.core.IAbstractTest;
import com.solvd.carina.demo.api.DeleteEmployeeMethods;
import com.solvd.carina.demo.api.GetEmployeeMethods;
import com.solvd.carina.demo.api.GetEmployeesMethods;
import com.solvd.carina.demo.api.PostEmployeeMethods;
import com.solvd.carina.demo.api.PutEmployeeMethods;
import com.zebrunner.carina.api.apitools.validation.JsonComparatorContext;
import com.zebrunner.carina.api.apitools.validation.JsonCompareKeywords;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;

public class APITests implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test()
    @MethodOwner(owner = "aliscovsky")
    
    public void testGetEmployees() {
    	setCases("ANDK-368");
    	
        GetEmployeesMethods getEmployeesMethods = new GetEmployeesMethods();
        getEmployeesMethods.callAPIExpectSuccess();
        getEmployeesMethods.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey() + "data");
        getEmployeesMethods.validateResponseAgainstSchema("api/employees/_get/rs.schema");
    }
    
    @Test()
    @MethodOwner(owner = "aliscovsky")
    public void testGetEmployee() {
    	setCases("ANDK-369");
    	
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
    	setCases("ANDK-370");
    	
        PostEmployeeMethods api = new PostEmployeeMethods();
        api.setProperties("api/employees/employee/employee.properties");
        api.callAPIExpectSuccess();
        api.validateResponse();
    }
    
    @Test()
    @MethodOwner(owner = "aliscovsky")
    public void testDeleteEmployee() {
    	setCases("ANDK-371");
    	
    	String employeeId = "1";
    	
        DeleteEmployeeMethods deleteEmployeeMethods = new DeleteEmployeeMethods(employeeId);
        deleteEmployeeMethods.setProperties("api/employees/employee/employee.properties");
        deleteEmployeeMethods.callAPIExpectSuccess();
        deleteEmployeeMethods.validateResponse();
    }
    
    @Test()
    @MethodOwner(owner = "aliscovsky")
    public void testUpdateEmployee() {
    	setCases("ANDK-372");
    	
    	String employeeId = "1";
    	
        PutEmployeeMethods updateEmployeeMethods = new PutEmployeeMethods(employeeId);
        updateEmployeeMethods.setProperties("api/employees/employee/employee.properties");
        updateEmployeeMethods.callAPIExpectSuccess();
        
        JsonComparatorContext comparatorContext = JsonComparatorContext.context()
                .<Integer>withPredicate("idPredicate", id -> id == Integer.valueOf(employeeId).intValue());
        updateEmployeeMethods.validateResponse(comparatorContext);
        
        updateEmployeeMethods.validateResponse();
    }

}
