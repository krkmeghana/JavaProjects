package com.example.JobPosting;

import java.util.*;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class searchRepo {
	@Autowired	
	MongoClient mongoClient;
	@Autowired
	MongoConverter converter;
	
	public List<Post> findByText(String txt){
	
	MongoDatabase database = mongoClient.getDatabase("SpringDemo");
	MongoCollection<Document> collection = database.getCollection("JobPost");

	AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
	    new Document("index", "default")
	            .append("text", 
	    new Document("query", txt)
	                .append("path", Arrays.asList("desc", "techs", "profile")))), 
	    new Document("$limit", 10L), 
	    new Document("$sort", 
	    new Document("exp", 1L))));
	
	List<Post> posts = new ArrayList<Post>();
	result.forEach(doc -> posts.add(converter.read(Post.class, doc)));
	
	return posts;
}
	
	
}
