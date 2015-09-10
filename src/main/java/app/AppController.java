package main.java.app;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import main.java.app.NameGenerator;

@Controller
public class AppController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/nameGenerator")
    public @ResponseBody NameGenerator generatedName(
    		@RequestParam(value="name", defaultValue="World") String name,
    		@RequestParam(value="age", defaultValue="22") String age) {
        return new NameGenerator(name, age);
    }
}