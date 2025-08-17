package org.xcom.shcema.infra.starter.config.introspect;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import org.springframework.stereotype.Component;
import org.xcom.shcema.core.annotation.DataDict;
import org.xcom.shcema.infra.starter.utils.DataDictSerializer;

/**
 * 注解数据字段 内省器
 *
 * @author xcom
 * @date 2024/8/8
 */

@Component
public class DataDictIntrospect extends NopAnnotationIntrospector {

    private static final long serialVersionUID = 1L;

    @Override
    public Object findSerializer(Annotated am) {
        DataDict dict = am.getAnnotation(DataDict.class);
        if (dict != null){
            return DataDictSerializer.class;
        }
        return null;
    }
}