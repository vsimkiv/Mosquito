package com.softserve.mosquito.configs;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

@Configuration
@EnableMongoRepositories(basePackages = "com.softserve.mosquito.repo.api")
@PropertySource({"classpath:mongodb.properties"})
public class MongoConfig {

    @Autowired
    private Environment env;

    @Bean
    public Mongo mongo() throws Exception {
        String mongoUser = env.getProperty("mongoUser");
        String databaseName = env.getProperty("databaseName");
        String mongoPass = env.getProperty("mongoPass");
        String mongoHost = env.getProperty("mongoHost");
        int mongoPort = Integer.parseInt(env.getProperty("mongoPort"));

        MongoCredential credential = MongoCredential.createCredential(
                mongoUser, databaseName, mongoPass.toCharArray());
        ServerAddress serverAddress = new ServerAddress(mongoHost, mongoPort);

        return new MongoClient(serverAddress, Arrays.asList(credential));
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), env.getProperty("databaseName"));
    }
}