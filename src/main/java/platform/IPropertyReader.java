package platform;

import java.util.Properties;

public interface IPropertyReader {

    Properties getAll();

    String getProperty(String key);
}