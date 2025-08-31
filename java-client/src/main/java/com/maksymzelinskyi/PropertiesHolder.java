package com.maksymzelinskyi;

import java.io.IOException;
import java.util.Properties;

public class PropertiesHolder {

    private static PropertiesHolder instance;

    private Properties props = new Properties();

    private PropertiesHolder() {
        try {
            props.load(Main.class.getClassLoader().getResourceAsStream("application.properties"));
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static PropertiesHolder getInstance() {
        if(instance==null) instance = new PropertiesHolder();
        return instance;

    }

    public Properties getProps() {
        return props;
    }
}
