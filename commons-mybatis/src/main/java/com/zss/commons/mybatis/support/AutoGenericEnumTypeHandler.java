package com.zss.commons.mybatis.support;

import com.zss.commons.mybatis.enumeration.BaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoGenericEnumTypeHandler<E extends BaseEnum> extends BaseTypeHandler<E> {

    private Class<E> enumType;
    private E[] enums;

    public AutoGenericEnumTypeHandler(){}

    public AutoGenericEnumTypeHandler(Class<E> type){
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.enumType = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getName() + " does not represent an enum type.");
        }
    }

    private E loadEnum(int index) {
        for (E e : enums) {
            if (e.getIndex() == index) {
                return e;
            }
        }
        throw new IllegalArgumentException(enumType.getName() + "  unknown enumerated type  index:" + index);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getIndex());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if(rs.getObject(columnName) == null){
            return null;
        }
        int index = rs.getInt(columnName);
        return loadEnum(index);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if(rs.getObject(columnIndex) == null){
            return null;
        }
        int index = rs.getInt(columnIndex);
        return loadEnum(index);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if(cs.getObject(columnIndex) == null){
            return null;
        }
        int index = cs.getInt(columnIndex);
        return loadEnum(index);
    }
}
