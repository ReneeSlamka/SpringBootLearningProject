package main.java.app;

import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseService {
	
	public Logger log;
	
	
	public DatabaseService(Logger log) {
		this.log = log;
	}
	
	public void runDb(JdbcTemplate jdbcTemplate, String[] textEntries) throws Exception {

        // Split up the array of whole names into an array of first/last names
        // TODO: Change this to read from a text file later
		//String[] listNames = {"Ben Baratheon", "Anna Stark", "Laura Lanister", "Tom Tyrell"};
        List<Object[]> splitUpNames = Arrays.asList(textEntries).stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());
        
		createTable(jdbcTemplate);
		insertTableEntries(jdbcTemplate, splitUpNames);
	}
	
	public void createTable(JdbcTemplate jdbcTemplate) {
		log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE MAPPEDNAMEPAIRS IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE MAPPEDNAMEPAIRS(" +
                "submitted_name VARCHAR(255), got_name VARCHAR(255))");
	}
	
	public void insertTableEntries(JdbcTemplate jdbcTemplate, List<Object[]> databaseEntries) {
		// Use a Java 8 stream to print out each tuple of the list
		if (databaseEntries.size() > 0) {
			databaseEntries.forEach(entry -> log.info(String.format("Inserting name match pair containing %s and %s", entry[0], entry[1])));
		}
        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO MAPPEDNAMEPAIRS (submitted_name, got_name) VALUES (?, ?)", databaseEntries);
	}
	
	public String queryForEntry(JdbcTemplate jdbcTemplate, String searchTerm) {
		log.info("Querying for mapped name records where submitted_name = " + searchTerm + ":");
		
		ArrayList<String> matches = new ArrayList<String>(); 
		
        jdbcTemplate.query("SELECT submitted_name, got_name FROM MAPPEDNAMEPAIRS WHERE submitted_name = ?", new Object[] { searchTerm },
                (rs, rowNum) -> new MappedNamePair(rs.getString("submitted_name"), rs.getString("got_name"))
        ).forEach(customer -> matches.add(customer.getGameOfThronesName()));
        
        if (matches.size() > 0) {
        	return matches.get(0);
        } else {
        	return new String("Unknown Monster");
        }
	}
}
