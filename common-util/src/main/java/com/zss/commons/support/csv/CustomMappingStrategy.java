package com.zss.commons.support.csv;

import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.stream.Stream;

public class CustomMappingStrategy<T> extends ColumnPositionMappingStrategy<T> {


    @Override
    public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {

        Field[] fields = FieldUtils.getAllFields(bean.getClass());
        int maxPosition = Stream.of(fields).filter(f->isCsvPosition(f))
                .map(f->getPositionIndex(f))
                .max(Integer::compare).get();

        String[] columns = new String[maxPosition + 1];
        for (Field f : fields) {
            String columnName = f.getName();
            if (f.getAnnotation(CsvBindByName.class) != null){
                final CsvBindByName bindByNameAnnotation = f.getAnnotation(CsvBindByName.class);
                columnName = bindByNameAnnotation.column();
            }
            if (isCsvPosition(f)){
                int position = getPositionIndex(f);
                columns[position] = columnName;
            }
        }
        super.generateHeader(bean);
        return columns;
    }

    private boolean isCsvPosition(Field f){
        if (f.getAnnotation(CsvCustomBindByPosition.class) != null
                || f.getAnnotation(CsvBindAndSplitByPosition.class) != null
                || f.getAnnotation(CsvBindByPosition.class) != null) {
            return true;
        }
        return false;
    }

    private int getPositionIndex(Field f){
        if (f.getAnnotation(CsvCustomBindByPosition.class) != null){
            return f.getAnnotation(CsvCustomBindByPosition.class).position();
        }
        if (f.getAnnotation(CsvBindAndSplitByPosition.class) != null){
            return f.getAnnotation(CsvBindAndSplitByPosition.class).position();
        }
        if (f.getAnnotation(CsvBindByPosition.class) != null){
            return f.getAnnotation(CsvBindByPosition.class).position();
        }
        return 0;
    }

}
