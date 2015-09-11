package main.java.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseService {
	
	public void runDb(JdbcTemplate jdbcTemplate, Logger log) throws Exception {
		log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE MAPPEDNAMEPAIRS IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE MAPPEDNAMEPAIRS(" +
                "submitted_name VARCHAR(255), got_name VARCHAR(255))");

        // Split up the array of whole names into an array of first/last names
        // TODO: Change this to read from a text file later
        List<Object[]> splitUpNames = Arrays.asList("Ben Baratheon", "Anna Stark", "Laura Lanister", "Tom Tyrell").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // Use a Java 8 stream to print out each tuple of the list
        splitUpNames.forEach(name -> log.info(String.format("Inserting name match pair containing %s and %s", name[0], name[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO MAPPEDNAMEPAIRS (submitted_name, got_name) VALUES (?, ?)", splitUpNames);

        log.info("Querying for mapped name records where submitted_name = 'Ben':");
        jdbcTemplate.query("SELECT submitted_name, got_name FROM MAPPEDNAMEPAIRS WHERE submitted_name = ?", new Object[] { "Ben" },
                (rs, rowNum) -> new MappedNamePair(rs.getString("submitted_name"), rs.getString("got_name"))
        ).forEach(customer -> log.info(customer.toString()));
		
	}
}
