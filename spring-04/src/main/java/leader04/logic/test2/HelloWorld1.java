package leader04.logic.test2;

import annotation.ZAutoWrite;
import annotation.ZComponent;

@ZComponent
public class HelloWorld1 {
	@ZAutoWrite
	private HelloWorld1Value value;

	public void sayHello() {
		value.hello();
	}
}
