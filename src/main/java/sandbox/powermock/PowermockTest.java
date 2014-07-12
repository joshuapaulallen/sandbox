package sandbox.powermock;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.common.collect.ImmutableSet;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ColorTools.class})
public class PowermockTest {

	@Test
	public void test_withoutMocking() {
		final Set<Color> actualColors = getColors();
		for (final Color color : actualColors) {
			System.out.println(color.getName());
		}
	}

	@Test
	public void test_mockStatic() {
		mockStatic(ColorTools.class);
		final Set<Color> alternateColors = ImmutableSet.of(new Color("cyan"), new Color("magenta"), new Color("yellow"));
		when(ColorTools.getColors()).thenReturn(alternateColors);

		final Set<Color> actualColors = getColors();
		for (final Color color : actualColors) {
			System.out.println(color.getName());
		}
	}


	private Set<Color> getColors() {
		return ColorTools.getColors();
	}

}
