package com.springboot.journalApp.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration
{
    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Override
    protected String getDatabaseName()
    {
        // This strictly forces Spring to use the "journal" database
        return "journal";
    }

    @Override
    public MongoClient mongoClient()
    {
        // This connects to your local MongoDB instance on the standard port
        return MongoClients.create(uri);
    }

    @Override
    protected boolean autoIndexCreation()
    {
        return true;
    }
}