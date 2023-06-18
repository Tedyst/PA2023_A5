package ro.tedyst;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class LocaleExplore {
    Map<LocaleTypes, Properties> properties = new HashMap<>(Map.of(
            LocaleTypes.ENGLISH, new Properties(),
            LocaleTypes.ROMANIAN, new Properties()
    ));

    public LocaleExplore() throws IOException {
        properties.get(LocaleTypes.ENGLISH).load(LocaleExplore.class.getResourceAsStream("/Messages_en.properties"));
        properties.get(LocaleTypes.ROMANIAN).load(LocaleExplore.class.getResourceAsStream("/Messages_ro.properties"));
    }

    public String getMessage(LocaleTypes localeTypes, String key){
        if (!properties.containsKey(localeTypes)) {
            return "Locale not supported";
        }
        return properties.get(localeTypes).getProperty(key);
    }
}
