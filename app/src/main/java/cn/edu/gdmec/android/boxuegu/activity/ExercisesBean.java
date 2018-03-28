package cn.edu.gdmec.android.boxuegu.activity;

/**
 * Created by asus on 2018/3/28.
 */

public class ExercisesBean {
    public int id;//每章习题Id
    public String title;//每章习题标题
    public String content;//习题数目
    public int background;
    public int subjectId;
    public String subject;
    public String a;
    public String b;
    public String c;//选项c
    public String d;
    public int answer;//正确答案
    /**
     * select为0表示所选项是对的，1表示选中的A选项是错的，2表示选中的B选项是错的，3表示选中的C选项是错的，4表示所选中的D选项是错的
     *
     *
     */
    public int select;

}
