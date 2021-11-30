package com.Util;

import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CONSTANTS {

    private static String randomText() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int length = random.nextInt(30);
        if (length < 5) {
            length = 5;
        }
        ;
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final String SHOW_NAME = "showName";


    public static final class USER_DATA {
        public static final String USERID = "useID";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String NICKNAME = "nickname";
        public static final String AVATAR_TYPE = "avatarType";
    }

    public static final class DB_DATA {
        public static final String URL = "jdbc:mysql://localhost:3306/myblog?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false";
        public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
        public static final String USER = "root";
        public static final String PASSWORD = "user";
    }

    public static final class LOGIN_DATA {
        public static final String PASSWORD = "password";
        public static final String LOGIN_FAIL = "login_fail";
        public static final String LOGIN_SUCCESS = "login_success";
    }

    public static final class REGISTER_DATA {
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String REGISTER_ERROR = "register_error";
        public static final String REGISTER_SUCCESS = "register_success";
    }

    public static final class ARTICLES_DATA {
        public static final String TITLE = "title";
        public static final String CONTENT = "content";
        public static final String SUMMARY = "article-editor-html-code";
        //        public static final String OWNER_NAME = "ownerName";
        public static final String CREATE_DATE_MS = "create_date_ms";

        public static final String ARTICLE_ID = "articleID";
        public static final String SUPPORT_SKIM_TYPE = "support_skim_type";
        public static final String SUPPORT = "support";
        public static final String SKIM = "skim";
        public static final String NUM_CHANGE_TYPE = "num_change_type";
        public static final String INCREASE = "increase";
        public static final String DECREASE = "decrease";

        public static final String IS_DRAFT = "isDraft";

        public static final String MANAGE_ARTICLES = "manageArticles";     //文章是阅读传内容还是，编辑传内容。编辑传内容是不增加点击量的。
    }

    public static final class COMMENT_DATA {
        public static final String OWNER = "username";
        public static final String CONTENT = "comment-editor-html-code";
        public static final String ARTICLE_ID = "articleID";
        public static final String CREATE_TIME_MS = "createTime_ms";
    }

    public static final class IMAGE_UPLOAD {
        public static final String RELATIVE_ROOT = "/resource/img/";
        public static final String RELATIVE_PATH = RELATIVE_ROOT + "userAvatar";
        public static final String RELATIVE_TMP_PATH = RELATIVE_ROOT + "temp";
    }

    public static String getHtmlText(String content) {
        String pattern = "<.+?>";
        String[] split = content.split(pattern);
        StringBuilder res = new StringBuilder();
        for (String str : split) {
            if (!(str.length() == 0)) {
                res.append(str);
            }
        }
        return res.toString();
    }


    private static final int SUMMARY_WIDTH = 120;

    public static String getHtmlSummary(String content) {
        String pattern = "<.+?>";
        String[] split = content.split(pattern);
        StringBuilder res = new StringBuilder();
        for (String str : split) {
            if(res.length() >= SUMMARY_WIDTH){
                return res.toString();
            }
            if (!(str.length() == 0)) {
                res.append(str);
            }
        }
        return res.toString();
    }

}

