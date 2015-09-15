package main.java.app;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.app.NameGenerator;
import main.java.app.DatabaseService;
import main.java.app.TextFileParser;

@Controller
public class AppController {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private DatabaseService databaseService = new DatabaseService(log);
    private TextFileParser textFileParser = new TextFileParser("/Users/reneeslamka/SpringBootLearningProject/ListNames.txt", log);
    
    
    @PostConstruct
    public void setUpDatabase() {
    	//do later
    }
    
    //add proper exception/error checking
    @RequestMapping("/nameGenerator")
    public @ResponseBody NameGenerator generatedName(
    		@RequestParam(value="name", defaultValue="World") String name,
    		@RequestParam(value="age", defaultValue="22") String age) throws Exception {
    	
    	//get GoT names from text file
    	String listNames[] = textFileParser.readFile();
    	//insert list of names into database
    	databaseService.runDb(jdbcTemplate, listNames);
    	//query for match against submitted name
    	String databaseGoTName = databaseService.queryForEntry(jdbcTemplate, name);
    	//return user info to be displayed by web page as JSON object
        return new NameGenerator(name, Integer.parseInt(age), databaseGoTName);
    }
    
    //test method
    @RequestMapping("/parseNamesTest")
    public @ResponseBody NameGenerator parseNames() throws IOException {
    	String[] list = textFileParser.readFile();
    	String name = new String("");
    	if (list[0] != null) {
    		name = list[4];
    	} else {
    		name = "Ben";
    	}
    	return new NameGenerator(name, 44, "Brienne");
	}
}