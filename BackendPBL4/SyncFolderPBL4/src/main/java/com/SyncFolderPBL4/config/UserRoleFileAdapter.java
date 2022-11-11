package com.SyncFolderPBL4.config;

import java.lang.reflect.Type;

import com.SyncFolderPBL4.model.entities.RoleID;
import com.SyncFolderPBL4.model.entities.UserRoleFileEntity;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class UserRoleFileAdapter implements JsonSerializer<UserRoleFileEntity>, JsonDeserializer<UserRoleFileEntity> {

	@Override
	public UserRoleFileEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		 
		final int userId = jsonObject.get("userId").getAsInt();
        final int fileId = jsonObject.get("fileId").getAsInt();
        final boolean readPermission = jsonObject.get("readPermission").getAsBoolean();
        final boolean updatePermission = jsonObject.get("updatePermission").getAsBoolean();
 
        final UserRoleFileEntity role = new UserRoleFileEntity();
        final RoleID roleId = new RoleID(userId,fileId);
        role.setRoleIds(roleId);
        role.setReadPermission(readPermission);
        role.setUpdatePermission(updatePermission);
        return role;
	}

	@Override
	public JsonElement serialize(UserRoleFileEntity src, Type typeOfSrc, JsonSerializationContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
