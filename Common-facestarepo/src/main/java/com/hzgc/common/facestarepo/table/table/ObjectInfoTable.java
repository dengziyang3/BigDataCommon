package com.hzgc.common.facestarepo.table.table;

import java.io.Serializable;

public class ObjectInfoTable implements Serializable {

    public static final String TABLE_NAME = "objectinfo";   // 表名
    public static final String ROWKEY = "id";               // 对象信息的唯一标志

    // 以下是和平台组公用的属性
    public static final String PKEY = "pkey";               // 人员类型
    public static final String NAME = "name";               // 姓名
    public static final String IDCARD = "idcard";           // 身份证号
    public static final String SEX = "sex";                 //  性别
    public static final String PHOTO = "photo";             // 照片
    public static final String FEATURE = "feature";         // 特征值
    public static final String REASON = "reason";           // 理由
    public static final String CREATOR = "creator";         // 布控人
    public static final String CPHONE = "cphone";           // 布控人手机号
    public static final String CREATETIME = "createtime";   // 创建时间
    public static final String UPDATETIME = "updatetime";   // 更新时间
    public static final String STATUSTIME = "statustime";   // 状态更新时间
    public static final String RELATED = "sim";             // 相关度
    public static final String CARE = "care";               // 是否关爱人员 [0 = 非关爱人员（默认选项），1 = 关爱人员]
    public static final String IMPORTANT = "important";     // 是否重点关注人员 [1 = 非重点关注（默认选项），2 = 重点关注]
    public static final String STATUS = "status";           // 人员状态 [0 = 常住人口（默认选项），1 = 建议迁出]
    public static final String LOCATION = "location";       // 人员所在位置
}
