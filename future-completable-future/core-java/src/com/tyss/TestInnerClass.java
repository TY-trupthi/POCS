package com.tyss;

import com.tyss.InnerClass.SubClass;

public class TestInnerClass {
	
	public static void main(String[] args) {
		InnerClass innerClass = new InnerClass();
		SubClass subClass = innerClass.new SubClass();
		subClass.checkInnerClass();
	}

}
