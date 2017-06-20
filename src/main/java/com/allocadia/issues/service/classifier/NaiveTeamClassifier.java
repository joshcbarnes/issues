package com.allocadia.issues.service.classifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.allocadia.issues.dao.TagDao;
import com.allocadia.issues.domain.Tag;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class NaiveTeamClassifier {
    @Autowired
    private TagDao tagDao;
    
    public String classify(String title, String description) {
        Collection<Tag> tags = tagDao.getAll();
    
        Map<String, Double> titleScores = getScores(tags, Tokenizer.tokenize(title));
        Map<String, Double> descriptionScores = getScores(tags, Tokenizer.tokenize(description));
    
        Double maxScore = 0.0;
        String classification = "";
        for (String team: titleScores.keySet()) {
            Double score = titleScores.getOrDefault(team, 0.0) + descriptionScores.getOrDefault(team, 0.0);
            classification = score > maxScore ? team : classification;
            maxScore = Math.max(maxScore, score);
        }
        
        return classification;
    }
    
    private Map<String, Double> getScores(Collection<Tag> tags, Set<String> tokens) {
        Map<String, Double> scores = new HashMap<>();
        tags.stream()
            .filter(t -> tokens.contains(t.getName()))
            .forEach(t -> scores.put(t.getTeam(), scores.getOrDefault(t.getTeam(),0.0) + 1));
    
        return scores;
    }
}
