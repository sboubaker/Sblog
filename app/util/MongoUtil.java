package util;

import org.bson.types.ObjectId;

import play.modules.morphia.MorphiaPlugin;

import com.google.code.morphia.Datastore;

public class MongoUtil {

	private static Datastore datastore=null;
	
	public static Datastore getDatastore(){
		if(datastore==null)
			datastore= MorphiaPlugin.ds();
		return datastore;
	}
	public static ObjectId formatToId(String str){
		return new ObjectId(str);
	}
}
