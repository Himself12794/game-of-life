package com.himself12794.main;

import com.himself12794.main.things.Stuff;
import com.himself12794.main.things.Thing;

public class Main {

	public static void main(String...args) {
		for (String arg : args) {
			System.out.println(arg);
		}
		Test.doSomeStuff();
		Thing thing1 = new Thing("Name", "value");
		Stuff thing = new Stuff("name", "value");
		System.out.println(thing);
		thing1.setName("booger");
		System.out.println(thing1);
	}
	
}
