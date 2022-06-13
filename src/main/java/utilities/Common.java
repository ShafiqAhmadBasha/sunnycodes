package utilities;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Properties;

public class Common {

    public static Properties envData = null;

    public static final HashMap<String,  String> menu = new HashMap<String, String>(){{
        put("client", "Client");
        put("management","Menu Management");
    }};

    public static final HashMap<String,  String> menuOption = new HashMap<String, String>(){{
        put("store", "Store");
        put("menuitem","Menu Item");
        put("category","Category");
    }};

    public static void laodEnvData(){
        try{
            String path = System.getProperty("user.dir")+"\\src\\test\\java\\resources\\"+System.getProperty("env")+".properties";
            FileReader fr = new FileReader(new File(path));
            envData = new Properties();
            envData.load(fr);

        }catch(Exception ex){

        }
    }

    public static String getEnvData(String key){
        return envData.getProperty(key);
    }
}
