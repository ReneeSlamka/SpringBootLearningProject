package main.java.app;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NameGenerator {
	private final String name;
	private final int age;
	private final String matchedName;
	private final String generatedName;
    private static final Logger log = LoggerFactory.getLogger(Application.class);

	public NameGenerator(String name, int age, String matchedName) {
		this.age = age;
		this.name = name;
		this.matchedName = matchedName;
		this.generatedName = this.generateName(name, age, matchedName);
	}

	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getMatchedName() {
		return matchedName;
	}
	
	public String getGeneratedName() {
		return generatedName;
	}

	public String generateName(String name, int age, String matchedName) {

		String ageDependentWord = null;

		if (age < 40) {
			ageDependentWord = " the Mangler";
		} else {
			ageDependentWord = " the Geezer";
		}

		return "Your Game of Thrones name is " + matchedName + ageDependentWord;
	}
}