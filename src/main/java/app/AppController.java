package main.java.app;

import java.util.concurrent.atomic.AtomicLong;
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
    //private DatabaseService testDB = new DatabaseService();
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    @Autowired
    JdbcTemplate jdbcTemplate;


    @RequestMapping("/nameGenerator")
    public @ResponseBody NameGenerator generatedName(
    		@RequestParam(value="name", defaultValue="World") String name,
    		@RequestParam(value="age", defaultValue="22") String age) {
        return new NameGenerator(name, Integer.parseInt(age));
    }
    
    @RequestMapping("/test")
    public @ResponseBody NameGenerator generatedName() throws Exception {
    	DatabaseService.runDb(jdbcTemplate, log);
        return new NameGenerator("Work being done on DB", Integer.parseInt("200"));
    }
}