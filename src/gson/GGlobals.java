package gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

public interface GGlobals {

	final Gson prettyGson = new GsonBuilder().
			setPrettyPrinting().
			disableHtmlEscaping().
			create();
	final Gson gson = new GsonBuilder().create();
	final JsonParser json = new JsonParser();
	
}
