package com.allocadia.issues.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.util.concurrent.Promise;

@Component
public class JiraGateway {
    
    @Autowired
    private JiraRestClient jiraClient;
    
    public Issue test() {
        Promise<Issue> issuePromise = jiraClient.getIssueClient().getIssue("ASD-16001");
        return issuePromise.claim();
    }
}
