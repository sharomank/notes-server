package com.sharomank.progress.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.sharomank.progress.repository")
@PropertySource("classpath:db.properties")
public class MongoDbConfig extends AbstractMongoConfiguration {
    @Value("${mongodb.host}")
    private String mongoDbHost;
    @Value("${mongodb.port}")
    private String mongoDbPort;
    @Value("${mongodb.name}")
    private String mongoDbName;

    @Override
    protected String getDatabaseName() {
        return mongoDbName;
    }

    @Override
    public Mongo mongo() throws Exception {
        //TODO need add UserCredentials for accessing to MongoDB
        return new MongoClient(mongoDbHost, Integer.parseInt(mongoDbPort));
    }
}
