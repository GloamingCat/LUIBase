package gson;

import java.lang.reflect.Type;

import lui.base.serialization.LObjectSerializer;

public class GObjectSerializer<T> extends LObjectSerializer<T> {
	
	protected final Type type;
	
	public GObjectSerializer(String path, Type type) {
		super(path + ".json");
		this.type = type;
	}

	@Override
	protected byte[] toByteArray(Object data) {
		return GGlobals.prettyGson.toJson(data, type).getBytes();
	}

	@Override
	protected T fromByteArray(byte[] bytes) {
		return GGlobals.prettyGson.fromJson(new String(bytes), type);
	}
	
	public Type getType() {
		return type;
	}

	@Override
	public void initialize() {
		data = GGlobals.prettyGson.fromJson("{}", type);
	}
	
}
