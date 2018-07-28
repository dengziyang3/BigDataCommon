package com.hzgc.common.facealarm.table;

/**
 * 告警有关的es表
 */
public class AlarmTable {

    public static final String INDEX = "alarm";
    public static final String OFF_TYPE = "off_alarm";               //离线告警的type
    public static final String REC_TYPE = "rec_alarm";               //识别告警新增的告警的type
    public final static String IDENTIFY = "100";                      //识别告警
    public final static String ADDED = "101";                          //新增告警
    public final static String OFFLINE = "102";                        //离线告警
    public final static String UNKNOWN = "104";                        //告警不详
    public static final String IPC_ID = "ipc_id";
    public static final String ALARM_TYPE = "alarm_type";
    public static final String HOST_NAME = "host_name";
    public static final String BIG_PICTURE_URL = "big_picture_url";
    public static final String SMALL_PICTURE = "small_picture_url";
    public static final String STATIC_ID = "static_id";
    public static final String SIMILARITY = "similarity";
    public static final String OBJECT_TYPE = "object_type";
    public static final String ALARM_TIME = "alarm_time";
    public static final String LAST_APPEARANCE_TIME = "last_appearance_time";
    public static final String FLAG = "flag";
    public static final String CONFIRM = "confirm";

    public AlarmTable() {
    }
}
