package main.java.app;

public class NameGenerator {
	private final String name;
	private final int age;
	private final String generatedName;

	public NameGenerator(String name, int age) {
		this.age = age;
		this.name = name;
		this.generatedName = this.generateName(age, name);
	}

	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getGeneratedName() {
		return generatedName;
	}

	public String generateName(int age, String name) {

		String ageDependentWord = null;

		if (age < 40) {
			ageDependentWord = " the Mangler";
		} else {
			ageDependentWord = " the Giezer";
		}

		return "Your Game of Thrones name is " + name + ageDependentWord;
	}
}