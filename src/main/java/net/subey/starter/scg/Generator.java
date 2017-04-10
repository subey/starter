package net.subey.starter.scg;


import net.subey.starter.example.Example;
import net.subey.starter.scg.annotation.Dictionary;
import net.subey.starter.scg.annotation.DictionaryItem;
import net.subey.starter.scg.annotation.Label;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

public class Generator {
    private final Logger log = LoggerFactory.getLogger(Generator.class);

    public void run(Object entity) throws IOException {

        Class<?> entityClass = entity.getClass();
        log.debug("entityClass="+entityClass);
        /*if (entityClass.isAnnotationPresent(XXX.class)) {

        }*/
        for (int i = 0; i < entityClass.getDeclaredFields().length; i++) {
            Field field = entityClass.getDeclaredFields()[i];

            if (field.isAnnotationPresent(Label.class)) {
                Label label = field.getAnnotation(Label.class);
                // do something
                log.debug("Label="+label.value());


            }
            if (field.isAnnotationPresent(Dictionary.class)) {
                Dictionary dic = field.getAnnotation(Dictionary.class);

                for (int j = 0; j < dic.value().length; j++) {
                    DictionaryItem di = dic.value()[j];
                    log.debug("Dictionary=" + dic.key() + ", "+di.key() + "="+di.value());
                }

            }
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/README.md.twig");
        JtwigModel model = JtwigModel.newModel()
                .with("entityClass", entityClass);

        File root = new File("./out");
        if(!root.exists()){
            root.mkdirs();
        }
        template.render(model, new FileOutputStream( root.getPath() +"/README.md"));

    }
}
