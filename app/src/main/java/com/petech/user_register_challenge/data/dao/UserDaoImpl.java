package com.petech.user_register_challenge.data.dao;

import static com.petech.user_register_challenge.data.configuration.AppDatabaseHelper.USER_TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.petech.user_register_challenge.data.configuration.AppDatabaseHelper;
import com.petech.user_register_challenge.data.entity.UserEntity;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private AppDatabaseHelper helper;
    private SQLiteDatabase database;

    public UserDaoImpl(Context context) {
        helper = new AppDatabaseHelper(context);
        database = helper.getWritableDatabase();
    }

    @Override
    public long createUser(UserEntity user) {
        ContentValues contentUser = UserDaoUtil.getUserValues(user);

        return database.insertOrThrow(USER_TABLE_NAME, null, contentUser);
    }

    @Override
    public void updateUser(UserEntity user) {
        ContentValues contentUser = UserDaoUtil.getUserValues(user);

        database.update(
                USER_TABLE_NAME,
                contentUser,
                "_id = ?",
                new String[]{Integer.toString(user.get_id())}
        );
    }

    @Override
    public void deleteUser(UserEntity user) {
        database.delete(
                USER_TABLE_NAME,
                "_id = ?",
                new String[]{Integer.toString(user.get_id())}
        );
    }

    @Override
    public List<UserEntity> getAllUsers() {
        Cursor cursor = database.query(
                USER_TABLE_NAME,
                new String[]{"_id", "name", "password", "user_image", "address", "email", "born_date", "gender", "cpfcnpj"},
                null,
                null,
                null,
                null,
                null
        );
        List<UserEntity> usersList = new ArrayList();

        while (cursor.moveToNext()) {
            byte[] baseDecoded = Base64.getDecoder().decode(cursor.getString(3));
            String baseString = new String(baseDecoded);

            UserEntity userItem = new UserEntity.Builder()
                    .id(cursor.getInt(0))
                    .name(cursor.getString(1))
                    .password(cursor.getInt(2))
                    .userImage(baseString)
                    .address(cursor.getString(4))
                    .email(cursor.getString(5))
                    .bornDate(UserDaoUtil.convertStringIso8601ToLocalDate(cursor.getString(6)))
                    .gender(cursor.getInt(7) == 1)
                    .cpfCnpj(cursor.getString(8))
                    .build();

            usersList.add(userItem);
        }

        return usersList;
    }


}
