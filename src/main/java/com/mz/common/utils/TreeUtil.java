package com.mz.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class TreeUtil {
    public static List<JSONObject> toTreeList(List oldList) {
        String prePid = "-1";
        List<JSONObject> tmpList = new ArrayList<>();
        Map<String, List<JSONObject>> childMap = new HashMap<>();
        Map<String, JSONObject> allMap = new HashMap<>();
        for (Object obj : oldList) {
            JSONObject menuJSON = (JSONObject) JSON.toJSON(obj);

            if (!menuJSON.getString("pid").equals(prePid) && !prePid.equals("-1")) {
                if (allMap.containsKey(prePid) && !allMap.get(prePid).containsKey("children")) {
                    allMap.get(prePid).put("children", tmpList);
                } else {
                    childMap.put(prePid, tmpList);
                }
                tmpList = new ArrayList<>();
            }
            if (childMap.containsKey(menuJSON.getString("id"))) {
                //菜单有子菜单，安装上
                menuJSON.put("children", childMap.get(menuJSON.getString("id")));
                childMap.remove(menuJSON.getString("id"));
            }
            prePid = menuJSON.getString("pid");
            tmpList.add(menuJSON);
            allMap.put(menuJSON.getString("id"), menuJSON);
        }

        if(childMap.size() > 0){
            for(List<JSONObject> child : childMap.values()){
                tmpList.addAll(child);
            }

        }

        return tmpList;
    }

    public static void setChildrenRoleWithParent(List<JSONObject> dataList, String parentRole){
        for(JSONObject item : dataList){
            if(parentRole != null) {
                item.put("roleId", parentRole);
            }
            if(item.containsKey("children")){
                setChildrenRoleWithParent((List<JSONObject>)item.get("children"),
                        parentRole!=null?parentRole:item.getString("roleId"));
            }
        }
    }

    /**
     * @方法名: parseMenuTree<br>
     * @描述: 组装菜单<br>
     * @param list 数据库里面获取到的全量菜单列表
     * @return
     */
    public static List<JSONObject> parseMenuTree(List<JSONObject> list){
        List<JSONObject> result = new ArrayList<>();

        String rootId = list.get(0).getString("pid");

        // 1、获取第一级节点
        for (JSONObject obj : list) {
            obj.put("children", new JSONArray());
            if(rootId.equals(obj.getString("pid"))) {
                result.add(obj);
            }
        }

        // 2、递归获取子节点
        for (JSONObject parent : result) {
            recursiveTree(parent, list);
        }

        return result;
    }

    public static void recursiveTree(JSONObject parent, List<JSONObject> list) {
        for (JSONObject obj : list) {
            if(Objects.equals(parent.getString("id"),obj.getString("pid"))) {
                recursiveTree(obj, list);
                parent.getJSONArray("children").add(obj);
            }
        }
    }


}
