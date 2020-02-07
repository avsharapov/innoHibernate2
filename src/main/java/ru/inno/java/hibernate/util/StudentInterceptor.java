package ru.inno.java.hibernate.util;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.java.hibernate.entity.Student;

import java.io.Serializable;

public class StudentInterceptor extends EmptyInterceptor {
    private static final Logger logger = LoggerFactory.getLogger("StudentInterceptor");

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof Student) {
            logger.info("Hello, i'm student interceptor!");
        }
        return super.onSave(entity, id, state, propertyNames, types);
    }
}
