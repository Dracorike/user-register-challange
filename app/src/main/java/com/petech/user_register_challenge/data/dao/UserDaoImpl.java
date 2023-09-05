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

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class UserDaoImpl implements UserDao {
    private AppDatabaseHelper helper;
    private SQLiteDatabase database;

    @Inject
    public UserDaoImpl(@ApplicationContext Context context) {
        helper = new AppDatabaseHelper(context);
        database = helper.getWritableDatabase();
    }

    @Override
    public long createUser(UserEntity user) {
        ContentValues contentUser = UserDaoUtil.getUserValues(user);

        return database.insertOrThrow(USER_TABLE_NAME, null, contentUser);
    }

    @Override
    public int updateUser(UserEntity user) {
        ContentValues contentUser = UserDaoUtil.getUserValues(user);

        return database.update(
                USER_TABLE_NAME,
                contentUser,
                "_id = ?",
                new String[]{Integer.toString(user.get_id())}
        );
    }

    @Override
    public int deleteUser(int userId) {
        return database.delete(
                USER_TABLE_NAME,
                "_id = ?",
                new String[]{Integer.toString(userId)}
        );
    }

    @Override
    public List<UserEntity> getAllUsers() {
        Cursor cursor = database.query(
                USER_TABLE_NAME,
                new String[]{"_id", "name", "nick_name", "password", "user_image", "address", "email", "born_date", "gender", "cpfcnpj"},
                null,
                null,
                null,
                null,
                null
        );
        List<UserEntity> usersList = new ArrayList();

        while (cursor.moveToNext()) {
            byte[] baseDecoded = Base64.getDecoder().decode(cursor.getString(4));
            String baseString = new String(baseDecoded);

            UserEntity userItem = new UserEntity.Builder()
                    .id(cursor.getInt(0))
                    .name(cursor.getString(1))
                    .nickName(cursor.getString(2))
                    .password(cursor.getInt(3))
                    .userImage(baseString)
                    .address(cursor.getString(5))
                    .email(cursor.getString(6))
                    .bornDate(UserDaoUtil.convertStringIso8601ToLocalDate(cursor.getString(7)))
                    .gender(cursor.getInt(8) == 1)
                    .cpfCnpj(cursor.getString(9))
                    .build();

            usersList.add(userItem);
        }

        return usersList;
    }

    @Override
    public void closeDatabase() {
        database.close();
    }


}
