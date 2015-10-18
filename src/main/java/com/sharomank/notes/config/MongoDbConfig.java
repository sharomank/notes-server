package com.sharomank.notes.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.sharomank.notes.converter.LocalDateTimeToStringConverter;
import com.sharomank.notes.converter.LocalDateToStringConverter;
import com.sharomank.notes.converter.StringToLocalDateConverter;
import com.sharomank.notes.converter.StringToLocalDateTimeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

@Configuration
@EnableMongoRepositories(basePackages = "com.sharomank.notes.repository")
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
        //Don't forget about use UserCredentials for accessing to MongoDB in real projects
        return new MongoClient(mongoDbHost, Integer.parseInt(mongoDbPort));
    }

    @Override
    public CustomConversions customConversions() {
        //Manually register JSR-310 converters to support JDK 8 date/time types,
        //because used spring-data-mongodb version is lower than 1.7
        //see https://jira.spring.io/browse/DATAMONGO-1102
        return new CustomConversions(Arrays.asList(
                new LocalDateToStringConverter(),
                new LocalDateTimeToStringConverter(),
                new StringToLocalDateConverter(),
                new StringToLocalDateTimeConverter()
        ));
    }
}
