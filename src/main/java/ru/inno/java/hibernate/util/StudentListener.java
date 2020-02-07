package ru.inno.java.hibernate.util;

import org.hibernate.event.spi.PreLoadEvent;
import org.hibernate.event.spi.PreLoadEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.java.hibernate.entity.Student;

public class StudentListener implements PreLoadEventListener {
    private static final Logger logger = LoggerFactory.getLogger("StudentListener");

    @Override
    public void onPreLoad(PreLoadEvent event) {
        if (event.getEntity() instanceof Student) {
            logger.info("Hello, i'm student pre load event listener!");
            logger.info("{}", event);
        }
    }
}
