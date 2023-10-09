package com.tyss;

public final class ImmutableClass {
	
	private final String firstName;
	
	private final String lastName;

	public ImmutableClass(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.err.println("Collected");
		super.finalize();
	}

}
