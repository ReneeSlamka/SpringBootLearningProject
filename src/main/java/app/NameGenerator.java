package app;

public class NameGenerator {
	private final int age = 40;
	private final String name;
	private final String generatedName;

	public NameGenerator(String name) {
		//this.age = age;
		this.name = name;
		this.generatedName = "Test " + name;//this.generateName(age, name);
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
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