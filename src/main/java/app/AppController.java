package main.java.app;

import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import main.java.app.NameGenerator;
import main.java.app.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AppController {

    //private static final String template = "Hello, %s!";
    //private final AtomicLong counter = new AtomicLong();
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private DatabaseService databaseService = new DatabaseService(log);
    
    @PostConstruct
    public void setUpDatabase() {
    	//do later
    }
    
    
    @RequestMapping("/nameGenerator")
    public @ResponseBody NameGenerator generatedName(
    		@RequestParam(value="name", defaultValue="World") String name,
    		@RequestParam(value="age", defaultValue="22") String age) throws Exception {
    	databaseService.runDb(jdbcTemplate);
    	String databaseName = databaseService.queryForEntry(jdbcTemplate, name);
        return new NameGenerator(name, Integer.parseInt(age), databaseName);
    }
    
    @RequestMapping("/test")
    public @ResponseBody NameGenerator generatedName() throws Exception {
    	databaseService.runDb(jdbcTemplate);
        return new NameGenerator("Work being done on DB", Integer.parseInt("200"), "Baratheon");
    }
}