package com.mz.common.generator.mapper;

import com.mz.common.generator.entity.ColumnInfo;
import com.mz.common.generator.entity.TableInfo;

import java.util.List;

public interface GenMapper {
    public TableInfo selectTable(String tableName);
    public List<ColumnInfo> selectColumns(String tableName);
}
