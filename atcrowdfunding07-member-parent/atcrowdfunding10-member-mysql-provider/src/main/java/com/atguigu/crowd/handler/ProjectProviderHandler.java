package com.atguigu.crowd.handler;

import com.atguigu.crowd.service.api.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dec
 */


@RestController
public class ProjectProviderHandler {
    @Autowired
    private ProjectService projectService;

}
