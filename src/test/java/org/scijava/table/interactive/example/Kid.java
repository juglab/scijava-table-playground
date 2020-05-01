package org.scijava.table.interactive.example;

class Kid {
	private String name;
	private int age;

	Kid(String name, int age) {
		this.name = name;
		this.age = age;
	}
	String getName() {
		return name;
	}
	int getAge() {
		return age;
	}
}
