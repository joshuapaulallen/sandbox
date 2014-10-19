package sandbox.json;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;


/**
 * Created by josh on 10/18/14.
 */
public class JsonTools {

	private static final ObjectMapper MAPPER = getMapper();


	private static ObjectMapper getMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new GuavaModule());
		return mapper;
	}


	/**
	 * Converts a POJO to its JSON equivalent.
	 *
	 * @param <T>  The type of the POJO
	 * @param pojo The POJO to convert, which may not be null
	 * @return The resulting JSON, which will not be null or empty
	 */
	public static final <T> String toJson(final T pojo) {
		try {
			checkNotNull(pojo, "pojo is null");
			final String json = MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).writeValueAsString(pojo);
			checkState(!Strings.isNullOrEmpty(json), "marshaled json is null or empty for pojo:[" + pojo + "]");
			return json;
		} catch (final Exception e) {
			throw Throwables.propagate(e);
		}
	}


	/**
	 * Converts a JSON string to its POJO equivalent.
	 *
	 * @param <T>           The type of the POJO
	 * @param json          The JSON string to convert, which may not be null or empty
	 * @param classForTypeT A class for type T
	 * @return The resulting POJO, which will not be null
	 */
	public static <T> T fromJson(final String json, final Class<T> classForTypeT) {
		try {
			final String trimmedJson = checkNotNull(json, "json is null").trim();
			checkState(!Strings.isNullOrEmpty(trimmedJson), "json is empty");
			final T pojo = MAPPER.readValue(trimmedJson, classForTypeT);
			return checkNotNull(pojo, "pojo is null");
		} catch (final Exception e) {
			throw Throwables.propagate(e);
		}
	}
}
