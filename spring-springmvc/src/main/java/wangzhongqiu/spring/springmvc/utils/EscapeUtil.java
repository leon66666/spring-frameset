package wangzhongqiu.spring.springmvc.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class EscapeUtil {

    private static Log _log = LogFactory.getLog(EscapeUtil.class);

    /**
     * escape关键字,防止SQL注入和跨站攻击
     *
     * @param s
     * @return
     */
    public static final String escapse(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        String orgin = new String(s);
        s = s.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        s = s.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        s = s.replaceAll("'", "&#39;");
        s = s.replaceAll("eval\\((.*)\\)", "");
        s = s.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        s = s.replaceAll("\\n\\t\\r", "");
//        s = s.replaceAll("script", ""); // 没用,防止误改description单词
//        if (!orgin.equals(s)) {
//            _log.warn("XSS Escape:" + orgin + " ====> " + s);
//        }
        return s;

         // 将容易引起xss漏洞的半角字符直接替换成全角字符
//        StringBuilder sb = new StringBuilder(s.length() + 16);
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            switch (c) {
//                case '>':
//                    sb.append('＞');// 全角大于号
//                    break;
//                case '<':
//                    sb.append('＜');// 全角小于号
//                    break;
//                case '\'':
//                    sb.append('‘');// 全角单引号
//                    break;
//                case '\"':
//                    sb.append('“');// 全角双引号
//                    break;
//                case '&':
//                    sb.append('＆');// 全角
//                    break;
//                case '\\':
//                    sb.append('＼');// 全角斜线
//                    break;
//                case '#':
//                    sb.append('＃');// 全角井号
//                    break;
//                default:
//                    sb.append(c);
//                    break;
//            }
//        }
//        return sb.toString();
    }


    /**
     * escape关键字,防止SQL注入和跨站攻击
     *
     * @param s
     * @return
     */
    public static final boolean isEscapsed(String s) {
        return s.equals(EscapeUtil.escapse(s));
    }
}
