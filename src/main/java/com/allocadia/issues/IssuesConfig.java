package com.allocadia.issues;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.JiraRestClientFactory;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class IssuesConfig {

    @Bean
    public JiraRestClient jiraClient() throws URISyntaxException {
        JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        String username = System.getenv("ISSUES_JIRA_USERNAME");
        String password = System.getenv("ISSUES_JIRA_PASSWORD");
        URI jiraURI = new URI("https://allocadia.atlassian.net");
        return factory.createWithBasicHttpAuthentication(jiraURI, username, password);
    }
}
