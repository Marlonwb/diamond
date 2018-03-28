package person.marlon.diamond.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class I18nUtil {

    private static MessageSource messageSource = ApplicationContextUtil.getBean("messageSource");

    /**
     * 根据系统自动检测出的locale输出内容
     *
     * @param key
     * @param args 内容里面参数的替换
     * @return
     */
    public static String getMessage(String key, Object[] args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }

    /**
     * 根据系统自动检测出的locale输出内容（不需要传入参数的情况）
     *
     * @param key
     * @return
     */
    public static String getMessage(String key) {
//        System.out.println(Locale.getDefault());
//        System.out.println(LocaleContextHolder.getLocale());
       return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

//    public static String getMessageCN(String key) {
//        return messageSource.getMessage(key, null, Locale.SIMPLIFIED_CHINESE);
//    }

}
