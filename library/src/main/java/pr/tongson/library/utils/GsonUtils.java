package pr.tongson.library.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Email:289286298@qq.com
 * @Author Tongson
 * @Date 2020/03/18
 * @Version V1.0.0
 * @Since
 * @Description Gson
 */
public class GsonUtils {
    private static final Gson gson;

    static {
        gson = new GsonBuilder().registerTypeAdapter(Integer.class, new JsonDeserializer<Integer>() {

            @Override
            public Integer deserialize(JsonElement json, Type type, JsonDeserializationContext arg2) throws JsonParseException {
                try {
                    return json.getAsInt();
                } catch (Exception e) {
                    return -1;
                }
            }
        }).registerTypeAdapter(Long.class, new JsonDeserializer<Long>() {

            @Override
            public Long deserialize(JsonElement json, Type type, JsonDeserializationContext arg2) throws JsonParseException {
                try {
                    return json.getAsLong();
                } catch (Exception e) {
                    return (long) -1;
                }
            }
        }).registerTypeAdapter(Float.class, new JsonDeserializer<Float>() {

            @Override
            public Float deserialize(JsonElement json, Type type, JsonDeserializationContext arg2) throws JsonParseException {
                try {
                    return json.getAsFloat();
                } catch (Exception e) {
                    return (float) -1;
                }
            }
        }).registerTypeAdapter(Double.class, new JsonDeserializer<Double>() {

            @Override
            public Double deserialize(JsonElement json, Type type, JsonDeserializationContext arg2) throws JsonParseException {
                try {
                    return json.getAsDouble();
                } catch (Exception e) {
                    return (double) -1;
                }
            }
        }).serializeNulls().setPrettyPrinting().create();
    }

    private GsonUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * @return 返回Gson
     */
    public static Gson getGson() {
        return gson;
    }

    /**
     * JsonObject string to Json pr.tongson.module_usersystem.bean.
     *
     * @param json  json string
     * @param clazz json pr.tongson.module_usersystem.bean
     * @param <T>   json pr.tongson.module_usersystem.bean class
     * @return json pr.tongson.module_usersystem.bean or null.
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return gson.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * JsonObject string to Json pr.tongson.module_usersystem.bean.
     *
     * @param json
     * @param type {@Link Type}
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Type type) {
        try {
            return gson.fromJson(json, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Json pr.tongson.module_usersystem.bean to json string.
     *
     * @param json json pr.tongson.module_usersystem.bean.
     * @return json string.
     */
    public static String toJson(Object json) {
        return gson.toJson(json);
    }


    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public static String GsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * 转成list
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> List<T> GsonToList(String gsonString, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(gsonString).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }

    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }
}
