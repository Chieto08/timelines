package com.project.timelines.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Followers {
    
    private String _sourceId;
    private String _destinationId;
    public static List<Followers> follows = new ArrayList<Followers>(Arrays.asList());

	public Followers(
        String sourceId,
        String destinationId
    ){
        _sourceId = sourceId;
        _destinationId = destinationId; 
    }

    public String getSourceId(){
        return _sourceId;
    }

    public String getDestinationId(){
        return _destinationId;
    }

    @Override
    public String toString(){
        return "{ Source Id: "+ _sourceId + " Destination Id: " + _destinationId + " }";
    }

}
