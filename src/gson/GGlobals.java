package gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

public interface GGlobals {

	Gson prettyGson = new GsonBuilder().
			setPrettyPrinting().
			disableHtmlEscaping().
			create();
	Gson gson = new GsonBuilder().create();
	JsonParser json = new JsonParser();
	
}
