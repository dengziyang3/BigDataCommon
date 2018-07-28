package com.hzgc.common.service.error;

import java.util.MissingResourceException;

/**
 * rest错误消息转换
 *
 * @author liuzk
 */
public final class RestErrorTranslator {

    /**
     * 根据错误码获取错误消息
     *
     * @param err    错误码
     * @param params 参数列表（可变参数）
     * @return 错误信息
     */
    public static String get(int err, String... params) {
        return MessageReader.getMessage(err, params);
    }

    /**
     * 错误消息读取器
     *
     * @author liuzk
     */
    private final static class MessageReader {

//        private static ResourceBundle bundle = null;
//
//        static {
//            bundle = ResourceBundle.getBundle("META-INF/message/Message_zh");
//        }

        /**
         * 根据错误码获取错误信息
         *
         * @param err  错误码
         * @param args 参数
         * @return 错误信息
         */
        public static String getMessage(Integer err, String... args) {
            String s;
            try {
                String t = String.valueOf(err);
                s = format(t, args);
            } catch (MissingResourceException e) {
                s = e.getMessage();
            }
            return s;
        }

        /**
         * 格式化提示信息
         *
         * @param message 消息
         * @param args    参数
         * @return 格式化后的消息
         */
        private static String format(String message, String... args) {
            if ((args == null) || (args.length == 0)) {
                return message;
            }

            String msg = message;
            for (int i = 0; i < args.length; ++i) {
                if (null != args[i]) {
                    msg = msg.replace("{" + i + "}", args[i]);
                }
            }

            return msg;
        }
    }
}
