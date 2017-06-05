package com.allocadia.issues.dao;

import org.springframework.stereotype.Component;

import com.allocadia.issues.domainObject.Tag;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagDao {
    private static final Type TeamType = new TypeToken<List<JsonTeam>>() {
    }.getType();
    
    public List<Tag> getFromFile() {
        Gson gson = new Gson();
        JsonReader reader = null;
        
        try {
            reader = new JsonReader(new FileReader(this.getClass().getClassLoader().getResource("tags.json").getFile()));
        } catch (FileNotFoundException e) {
            return Collections.EMPTY_LIST;
        }
        
        List<JsonTeam> teams = gson.fromJson(reader, TeamType);
        
        return teams.stream().flatMap(
            team -> team.tags
                .stream()
                .map(t -> new Tag(t, team.team)))
            .collect(Collectors.toList());
    }
    
    private class JsonTeam {
        String team;
        List<String> tags;
    }
}
