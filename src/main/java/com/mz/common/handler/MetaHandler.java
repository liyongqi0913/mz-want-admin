package com.mz.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class MetaHandler implements MetaObjectHandler {

    private static final Logger logger = LoggerFactory.getLogger(MetaHandler.class);
//    private static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 新增数据执行
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        logger.info(" -------------------- start insert fill ...  --------------------");

        //3.3.1
//        this.strictInsertFill(metaObject, "createdTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
//        this.fillStrategy(metaObject, "createdTime", LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug请升级到之后的版本如`3.3.1.8-SNAPSHOT`)
//
//        this.strictInsertFill(metaObject, "updatedTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
//        this.fillStrategy(metaObject, "updatedTime", LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug请升级到之后的版本如`3.3.1.8-SNAPSHOT`)

//        this.strictInsertFill(metaObject, "createBy", String.class, user.getId()); // 起始版本 3.3.0(推荐使用)
//        this.fillStrategy(metaObject, "createBy",  user.getId()); // 也可以使用(3.3.0 该方法有bug请升级到之后的版本如`3.3.1.8-SNAPSHOT`)

        //3.1.0
        this.setFieldValByName("createdTime",new Date(),metaObject);
        this.setFieldValByName("updatedTime", new Date(), metaObject);
        //有些地方不需要自动填充
        Object id = this.getFieldValByName("id", metaObject);
        if(id==null||"".equals(id)){
            this.setFieldValByName("id", IdWorker.get32UUID(), metaObject);
        }
//        this.setFieldValByName("createdBy", ShiroKit.getUser(), metaObject);
//        this.setFieldValByName("updatedBy", ShiroKit.getUser(), metaObject);
    }

    /**
     * 更新数据执行
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        logger.info(" -------------------- start update fill ...  --------------------");
        this.setFieldValByName("updatedTime", new Date(),metaObject);
//        this.setFieldValByName("updatedBy", ShiroKit.getUser(), metaObject);
//        this.strictUpdateFill(metaObject,"updatedTime",String.class, df.format(LocalDateTime.now()));
    }

}
