package sandbox.misc;

import org.junit.Test;


public class TryCatchFinally {

	@Test
	public void test() {
		System.out.println(tryFinally());
	}


	private int tryFinally() {
		try {
			return 1;
		} finally {
			return 0;
		}
	}
}
