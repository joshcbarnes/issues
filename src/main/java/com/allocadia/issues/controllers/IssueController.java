package com.allocadia.issues.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.allocadia.api.dto.Issue;
import com.allocadia.issues.Service.IssuesService;

@RestController
public class IssueController {

    @Autowired
    private IssuesService issuesService;

    @RequestMapping(method = RequestMethod.POST, value = "/issues")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void create(@RequestBody Issue issue) {
    }
}
