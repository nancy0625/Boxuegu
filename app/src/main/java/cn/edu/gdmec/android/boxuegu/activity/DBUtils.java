package cn.edu.gdmec.android.boxuegu.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asus on 2018/3/26.
 */

public class DBUtils {
    private static DBUtils instance = null;
    private static SQLiteOpenHelper helper;
    private static SQLiteDatabase db;
    public DBUtils(Context context){
        helper = new SQLiteHelper(context);
        db = helper.getWritableDatabase();
    }
    public static DBUtils getInstance(Context context){
        if (instance == null){
            instance = new DBUtils(context);
        }
        return instance;
    }
    /**
     *保存個人資料信息
     */
    public void saveUserInfo(UserBean bean){
        ContentValues cv = new ContentValues();
        cv.put("userName",bean.userName);
        cv.put("nickName",bean.nickName);
        cv.put("sex",bean.sex);
        cv.put("signature",bean.signature);
        db.insert(SQLiteHelper.U_USERINFO,null,cv);
    }
    public UserBean getUserInfo(String userName){
        String sql = "SELECT * FROM "+SQLiteHelper.U_USERINFO+" WHERE userName=? ";
        Cursor cursor = db.rawQuery(sql,new String[]{userName});
        UserBean bean = null;
        while (cursor.moveToNext()){
            bean = new UserBean();
            bean.userName = cursor.getString(cursor.getColumnIndex("userName"));
            bean.nickName = cursor.getString(cursor.getColumnIndex("nickName"));
            bean.sex = cursor.getString(cursor.getColumnIndex("sex"));
            bean.signature = cursor.getString(cursor.getColumnIndex("signature"));


        }
        cursor.close();
        return bean;
    }
    /**
     * 修改個人資料
     */
    public void updateUserInfo(String key,String value,String userName){
        ContentValues cv = new ContentValues();
        cv.put(key,value);
        db.update(SQLiteHelper.U_USERINFO,cv,"userName=?",new String[]{userName});
    }
}
