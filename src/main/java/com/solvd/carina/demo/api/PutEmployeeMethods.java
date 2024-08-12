package com.solvd.carina.demo.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.config.Configuration;

@Endpoint(url = "${base_url}/api/v1/update/${employee_id}", methodType = HttpMethodType.PUT)
@RequestTemplatePath(path = "api/employees/employee/_put/rq.json")
@ResponseTemplatePath(path = "api/employees/employee/_put/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class PutEmployeeMethods extends AbstractApiMethodV2 {

    public PutEmployeeMethods(String employeeId) {
        replaceUrlPlaceholder("base_url", Configuration.getRequired("employees_api_url"));
        replaceUrlPlaceholder("employee_id", employeeId);
    }
}