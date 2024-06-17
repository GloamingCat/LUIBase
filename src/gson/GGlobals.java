package gson;

import com.google.gson.*;
import lui.base.data.LDataList;
import lui.base.data.LDataTree;

import java.util.ArrayList;
import java.util.function.Function;

public interface GGlobals {

	Gson prettyGson = new GsonBuilder().
			setPrettyPrinting().
			disableHtmlEscaping().
			create();
	Gson gson = new GsonBuilder().create();
	JsonParser json = new JsonParser();

	static <T> String encodeJsonList(ArrayList<T> array, Function<T, String> elementEncoder) {
		StringBuilder json = new StringBuilder().append("[");
		for (T p : array) {
			json.append(",").append(elementEncoder.apply(p));
		}
		json.deleteCharAt(1).append("]");
		return json.toString();
	}

	static <T> LDataList<T> decodeJsonList(String str, Function<String, T> elementDecoder) {
		LDataList<T> list = new LDataList<>();
		JsonArray array = (JsonArray) json.parse(str);
		for (JsonElement element : array) {
			list.add(elementDecoder.apply(element.toString()));
		}
		return list;
	}

	static <T> LDataTree<T> decodeJsonTree(String str, Function<String, T> elementDecoder) {
		LDataTree<T> root = new LDataTree<>();
		JsonObject tree = (JsonObject) GGlobals.json.parse(str);
		root.data = elementDecoder.apply(tree.get("data").toString());
		JsonArray children = tree.get("children").getAsJsonArray();
		for (JsonElement element : children) {
			LDataTree<T> child = decodeJsonTree(element.toString(), elementDecoder);
			child.setParent(root);
		}
		return root;
	}

}
