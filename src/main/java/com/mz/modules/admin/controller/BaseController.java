package com.mz.modules.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mz.common.config.PageConfig;
import com.mz.common.dto.JsonResult;
import com.mz.common.utils.ShiroKit;
import com.mz.modules.admin.dao.entity.AdminEntity;
import com.mz.modules.admin.dao.entity.TSysUser;

import java.util.List;

@SuppressWarnings("unchecked")
public class BaseController {
    protected JsonResult toJson(int rows){
        if(rows > 0){
            return JsonResult.success();
        }else {
            return JsonResult.error();
        }
    }

    protected JsonResult toJson(Object obj){
        JsonResult retJson = JsonResult.success();
        retJson.put("data",obj);
        return retJson;
    }

    protected JsonResult toJsonWithPage(List list){
        JsonResult retJson = JsonResult.success();
        PageInfo page = new PageInfo(list);
        retJson.put("list",list);
        retJson.put("total",page.getTotal());
        return retJson;
    }

    /*
    需要与startPageWithMore成对出现
     */
    protected JsonResult toJsonWithPageMore(List list){
        JsonResult retJson = JsonResult.success();
        PageInfo page = new PageInfo(list);
        if(list.size() == page.getPageSize()){
            list.remove(list.size() - 1);
            retJson.put("more",1);
        }
        retJson.put("list",list);
        return retJson;
    }

    /*
    开启带更多查询的分页，不查询总数，只是比普通分页多查询一条数据，判断是否有更多
     */
    protected void startPageWithMore(JSONObject json){
        Integer pageNo = json.getInteger("pageNo");
        Integer pageSize = json.getInteger("pageSize");
        pageNo = pageNo == null? 1: pageNo;
        pageSize = pageSize == null ? PageConfig.PAGE_SIZE: pageSize;

        Page page = PageHelper.startPage(pageNo,pageSize,false);
        page.setPageSize(page.getPageSize() + 1);
    }

    /*
    开启带总数查询的分页
     */
    protected void startPageWithTotal(AdminEntity entity){
        Integer pageNo = entity.getPageNo();
        Integer pageSize = entity.getPageSize();
        pageNo = pageNo == null? 1: pageNo;
        pageSize = pageSize == null ? PageConfig.PAGE_SIZE: pageSize;
        pageSize = pageSize > PageConfig.MAX_PAGE_SIZE ? PageConfig.MAX_PAGE_SIZE: pageSize;

        PageHelper.startPage(pageNo,pageSize);
    }

    protected String getUserId(){
        TSysUser o = ShiroKit.getUser();
        if(o == null){
            return null;
        }
        return o.getId();
    }
}
