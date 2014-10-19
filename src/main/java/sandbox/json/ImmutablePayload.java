package sandbox.json;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import javax.annotation.concurrent.Immutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;


/**
 * Immutable payload with a non-concrete ImmutableMap as its payload.
 *
 * @author jallen
 */
@Immutable
public class ImmutablePayload {

	private final ImmutableMap<String, String> payload;


	private ImmutablePayload(final Map<String, String> payload) {
		this.payload = ImmutableMap.copyOf(payload);
	}


	@JsonIgnore
	public static ImmutablePayload of() {
		return new ImmutablePayload(Maps.<String, String>newHashMap());
	}


	@JsonCreator
	public static ImmutablePayload fromMap(@JsonProperty("payload") final Map<String, String> payload) {
		checkNotNull(payload, "payload is null");
		return new ImmutablePayload(payload);
	}


	@JsonProperty("payload")
	public ImmutableMap<String, String> getPayload() {
		return payload;
	}


	@JsonIgnore
	public String getPayloadAttribute(final String attributeName) {
		return payload.containsKey(attributeName) ? payload.get(attributeName) : null;
	}


	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		final ImmutablePayload that = (ImmutablePayload)o;

		if (!payload.equals(that.payload)) {
			return false;
		}

		return true;
	}


	@Override
	public int hashCode() {
		return payload.hashCode();
	}


	@Override
	public String toString() {
		return "ImmutablePayload{" +
					"payload=" + payload +
					'}';
	}
}
