package sandbox.powermock;

import java.util.Set;

import com.google.common.collect.ImmutableSet;


public final class ColorTools {

	private ColorTools() {}


	public static Set<Color> getColors() {
		return ImmutableSet.of(new Color("red"), new Color("blue"), new Color("green"));
	}

}
