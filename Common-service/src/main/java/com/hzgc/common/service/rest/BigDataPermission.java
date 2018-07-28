package com.hzgc.common.service.rest;

import com.hzgc.common.service.auth.annotation.AuthorizeCode;

public class BigDataPermission {

    /**
     * DynRepo模块权限颗粒
     */
    @AuthorizeCode(id = 2301, name = "查询", menu = "23", description = "人脸查询")
    public static final String FACE_SEARCH = "face/search";
    @AuthorizeCode(id = 6401, name = "查询", menu = "67", description = "抓拍历史查询")
    public static final String HISTORY_FACE_SEARCH = "history_face/search";
    @AuthorizeCode(id = 7001, name = "查询", menu = "70", description = "人脸布控查询")
    public static final String FACE_CTRL = "face_ctrl/search";
    @AuthorizeCode(id = 2401, name = "查询", menu = "24", description = "特征检索查询")
    public static final String FEATURE_SEARCH = "feature/search";
    /**
     * StaRepo模块权限颗粒
     */
    @AuthorizeCode(id = 5201, name = "查看", menu = "52", description = "人脸查看")
    public static final String OBJECT_VIEW = "object/view";
    @AuthorizeCode(id = 5202, name = "操作", menu = "52", description = "人脸操作")
    public static final String OBJECT_OPERATION = "object/operation";

    /**
     * Dispatch模块权限颗粒
     */
    @AuthorizeCode(id = 2201, name = "查看", menu = "22", description = "识别规则查看")
    public static final String RULE_VIEW = "rule/view";
    @AuthorizeCode(id = 2202, name = "操作", menu = "22", description = "识别规则操作")
    public static final String RULE_OPERATION = "rule/operation";


}
