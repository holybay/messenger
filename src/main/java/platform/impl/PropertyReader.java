package platform.impl;


import platform.IPropertyReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader implements IPropertyReader {

    private final Properties props;

    public PropertyReader(String fileName) {
        try (InputStream in = getClass().getResourceAsStream(fileName)) {
            props = new Properties();
            props.load(in);
            System.out.println("Props loaded");
        } catch (IOException e) {
            throw new RuntimeException("Failed to read the file name: " + fileName, e);
        }
    }

    @Override
    public Properties getAll() {
        return props;
    }

    @Override
    public String getProperty(String key) {
        return props.getProperty(key);
    }
}
