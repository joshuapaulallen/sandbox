package sandbox.misc;

public class Sandbox {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		System.out.println("before try 1");
		try {
			System.out.println("before try 2");
			try {
				System.out.println("in try 2");
				throw new Exception("dummy");
			} finally {
				System.out.println("finally 2");
			}
		} catch (final Exception e) {
			System.out.println("in catch 1");
		} finally {
			System.out.println("finally 1");
		}
		System.out.println("after try 1");

	}

}
