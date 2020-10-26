package com.mz.common.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class MQDTO {

    /**
     * 执行类型： -1、删除 0、新增 1、修改 2、查询 3、更新（包括新增和修改）
     */
    private String type;

    /**
     * 接收关键词
     */
    private String key;

    /**
     * 接收消息体
     */
    private String body;

    /**
     * 传输数据状态：默认0，正常
     */
    private int status;

    /**
     * 消息标记♂
     */
    private String flag;

    /**
     * 接收备注
     */
    private String remark;

    @Override
    public String toString() {
        return "MQDTO{" +
                "type='" + type + '\'' +
                ", key='" + key + '\'' +
                ", body=" + body +
                ", remark='" + remark + '\'' +
                '}';
    }



    public static MQDTO getInstance(String type, String key, Object body) {
        MQDTO mqdto = new MQDTO();
        mqdto.setType(type);
        mqdto.setKey(key);
        mqdto.setBody(JSONObject.toJSONString(body));
        return mqdto;
    }

    /**
     * 获取新增类型的实例
     *
     * @param key key
     * @param body body
     * @return
     */
    public static MQDTO getAddInstance(String key, Object body) {
        return getInstance("0", key, body);
    }

    /**
     * 获取修改类型的实例
     *
     * @param key key
     * @param body body
     * @return
     */
    public static MQDTO getUpdateInstance(String key, Object body) {
        return getInstance("1", key, body);
    }

    /**
     * 获取删除类型的实例
     *
     * @param key key
     * @param body body
     * @return
     */
    public static MQDTO getRemoveInstance(String key, Object body) {
        return getInstance("-1", key, body);
    }

    public String toJSON() {
        return JSONObject.toJSONString(this);
    }
}
