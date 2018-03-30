package cn.edu.gdmec.android.boxuegu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Xml;
import android.widget.ImageView;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.activity.ExercisesBean;

/**
 * Created by asus on 2018/3/13.
 */

public class AnalysisUtils {
    /**
     * 从SharedPreferences中读取登录用户名
     */
    public static String readLoginUserName(Context context){
        SharedPreferences sp = context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        String userName = sp.getString("loginUserName","");
        return userName;
    }
    public static void setABCDEnable(boolean value, ImageView iv_a,ImageView iv_b,ImageView iv_c,ImageView iv_d){
        iv_a.setEnabled(value);
        iv_b.setEnabled(value);
        iv_c.setEnabled(value);
        iv_d.setEnabled(value);
    }
    /**
     * 解析每章的习题
     */
    public static List<ExercisesBean> getExercisesInfos(InputStream is) throws Exception {
        XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(is,"utf-8");
        List<ExercisesBean> exercisesInfos = null;
        ExercisesBean exercisesInfo = null;
        int type = pullParser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT){
            switch (type){
                case XmlPullParser.START_TAG:
                    if ("infos".equals(pullParser.getName())){
                        exercisesInfos = new ArrayList<ExercisesBean>();
                    }else  if ("exercises".equals(pullParser.getName())){
                        exercisesInfo = new ExercisesBean();
                        String ids = pullParser.getAttributeValue(0);
                        exercisesInfo.subjectId = Integer.parseInt(ids);
                    }else  if ("subject".equals(pullParser.getName())){

                        String subject = pullParser.nextText();
                        exercisesInfo.subject = subject;
                    }else  if ("a".equals(pullParser.getName())){

                        String a = pullParser.nextText();
                        exercisesInfo.a = a;
                    }else  if ("b".equals(pullParser.getName())){

                        String b = pullParser.nextText();
                        exercisesInfo.b = b;
                    }else  if ("c".equals(pullParser.getName())){

                        String c = pullParser.nextText();
                        exercisesInfo.c = c;
                    }else  if ("d".equals(pullParser.getName())){

                        String d = pullParser.nextText();
                        exercisesInfo.d = d;
                    }else  if ("answer".equals(pullParser.getName())){

                        String answer = pullParser.nextText();
                        exercisesInfo.answer = Integer.parseInt(answer);
                    }
                    break;
                    case XmlPullParser.END_TAG:
                        if ("exercises".equals(pullParser.getName())){
                            exercisesInfos.add(exercisesInfo);
                            exercisesInfo = null;
                        }
                        break;
            }
            type = pullParser.next();
        }
        return exercisesInfos;
    }


}
