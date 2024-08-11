package com.solvd.carina.demo.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.config.Configuration;

@Endpoint(url = "${base_url}/api/v1/create", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "api/employees/employee/_post/rq.json")
@ResponseTemplatePath(path = "api/employees/employee/_post/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class PostEmployeeMethods extends AbstractApiMethodV2 {

    public PostEmployeeMethods() {
        super("api/employees/employee/_post/rq.json", "api/employees/employee/_post/rs.json", "api/employees/employee/employee.properties");
        replaceUrlPlaceholder("base_url", Configuration.getRequired("employees_api_url"));
    }
}
