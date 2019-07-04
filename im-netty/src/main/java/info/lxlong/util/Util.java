package info.lxlong.util;

import info.lxlong.model.Message;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class Util {

    private static SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String encodeMsg(Message message) {
        return message.getUsername() + "~" + (ymd.format(message.getSentTime())) + "~" + message.getMsg();
    }

    public static String formatDateTime(Date time) {
        return ymd.format(time);
    }

    public static Date parseDateTime(String time) {
        try {
            return ymd.parse(time);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 打印日志
     * @param msg
     */
    public static void printMsg(Message msg) {
        log.info("\n" +
                "\t┍⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓┑\n" +
                "\t├ [time]: {}\n" +
                "\t├ [msg ]: {}\n" +
                "\t┕⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓┙", Util.formatDateTime(msg.getSentTime()), msg.getMsg());
    }
}
