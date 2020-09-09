import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.jobs.SkillsData;

import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        SkillsData sd = new SkillsData();
        for (Field field : sd.getClass().getFields()){
            System.out.println(field.getName());
            System.out.println(field.get(sd));
        }
    }
}
