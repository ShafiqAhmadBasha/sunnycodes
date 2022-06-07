package utilities;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class Common {

    public static Properties envData = null;

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
