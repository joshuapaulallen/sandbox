package sandbox.json;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;


/**
 * Created by josh on 10/18/14.
 */
public class ImmutablePayloadSerializationTest {

	private static final boolean VERBOSE = true;


	@Test
	public void test() {
		final Map<String, String> map = ImmutableMap.<String, String>builder() //
					.put("abc", "123") //
					.put("def", "444") //
					.build();

		final ImmutablePayload payload = ImmutablePayload.fromMap(map);
		if (VERBOSE) {
			System.out.println("payload: " + payload);
		}

		final String payloadJson = JsonTools.toJson(payload);
		if (VERBOSE) {
			System.out.println("payloadJson: " + payloadJson);
		}

		final ImmutablePayload payloadDeserialized = JsonTools.fromJson(payloadJson, ImmutablePayload.class);
		if(VERBOSE) {
			System.out.println("deserialized payload: " + payloadDeserialized);
		}

		assertEquals(payload, payloadDeserialized);
	}
}
