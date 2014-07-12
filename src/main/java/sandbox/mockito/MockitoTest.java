package sandbox.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.ImmutableSet;


public class MockitoTest {

	@InjectMocks
	private final Workbench workbench = new Workbench();

	@Mock
	private Palette palette;

	@Mock
	private Color red;

	@Mock
	private Color green;

	@Mock
	private Color blue;


	@Before
	public void setup() {
		// needed to make mock annotations work
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void test_directMock() {
		final Color yellow = mock(Color.class);
		when(yellow.getName()).thenReturn("Yellow!");
		assertEquals("Yellow!", yellow.getName());
	}


	@Test
	public void test_annotationMock() {
		when(red.getName()).thenReturn("Red!");
		assertEquals("Red!", red.getName());
	}


	@Test
	public void test_injectMocks() {
		when(palette.getColors()).thenReturn(ImmutableSet.of(new Color("cyan"), new Color("magenta"), new Color("yellow")));
		final Set<Color> workbenchColors = workbench.getColors();
		for (final Color color : workbenchColors) {
			System.out.println(color.getName());
		}
	}

	private class Workbench {

		public Set<Color> getColors() {
			return palette.getColors();
		}
	}

	private class Palette {

		public Set<Color> getColors() {
			return ImmutableSet.of(new Color("red"), new Color("blue"), new Color("green"));
		}

	}


	private class Color {

		private final String name;


		public Color(final String name) {
			this.name = name;
		}


		public String getName() {
			return name;
		}

	}
}
