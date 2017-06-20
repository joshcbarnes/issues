package com.allocadia.issues.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.allocadia.api.dto.Priority;
import com.allocadia.issues.service.classifier.NaiveTeamClassifier;

@Component
public class IssueService {
    
    @Autowired
    private NaiveTeamClassifier naiveTeamClassifier;
    
    
    public String createIssue(String title, String description, Priority priority) {
        String team = naiveTeamClassifier.classify("Export", description);

        // TODO should return url to issue created;
        
        System.out.print(team);
        return team;
    }
}
