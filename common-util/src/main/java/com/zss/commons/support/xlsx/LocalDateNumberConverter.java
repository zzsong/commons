package com.zss.commons.support.xlsx;



import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateNumberConverter implements Converter<LocalDate> {

    @Override
    public Class supportJavaTypeKey() {
        return LocalDate.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.NUMBER;
    }

    @Override
    public LocalDate convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if (cellData.getNumberValue()==null || BigDecimal.ZERO.compareTo(cellData.getNumberValue())>=0){
            return null;
        }
        //处理科学记数法
        return LocalDate.parse(cellData.getNumberValue().toPlainString(),DateTimeFormatter.BASIC_ISO_DATE);
    }

    @Override
    public CellData convertToExcelData(LocalDate value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new CellData(value.format(DateTimeFormatter.BASIC_ISO_DATE));
    }
}
