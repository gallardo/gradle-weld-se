package app;

import javax.inject.Singleton;

@Singleton
public class HelloWorld {
	public String doSalute() {
		return "Hi World!";
	}
}
