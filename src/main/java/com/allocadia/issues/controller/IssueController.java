package com.allocadia.issues.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.allocadia.api.dto.Issue;
import com.allocadia.issues.service.IssueService;

@RestController
public class IssueController {

    @Autowired
    private IssueService issuesService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/issues")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody Issue issue) {
        issuesService.createIssue(issue.getTitle(), issue.getDescription(), issue.getPriority());
    }
}
