package timeline.Base.Controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import timeline.db.service.BriefingService;


public class MyController {


    public Map<String, Object> getData(@RequestParam(value = "$switchFlag", defaultValue = "0") int switchFlag) {
        Map<String, Object> data = new HashMap<>();

        List<Object> testList = Arrays.asList("100", 100, "haha");
        Map<String, Object> test1Map = new HashMap<>();
        test1Map.put("test", testList);
        test1Map.put("test2", 100);

        data.put("test", testList);
        data.put("test1", test1Map);
        data.put("test3", "100");
        data.put("test4", 100);

        if (switchFlag == 1) {
            modifyData(data);  // Ensure this method exists and can modify Map<String, Object>
        }

        return data;
    }

    private void modifyData(Map<String, Object> data) {
        for (String key : data.keySet()) {
            Object value = data.get(key);
            if (value instanceof String) {
                if (((String) value).equals("100")) {
                    data.put(key, "200");
                }
            } else if (value instanceof Object[]) {
                Object[] array = (Object[]) value;
                for (int i = 0; i < array.length; i++) {
                    modifyData(Map.of(key, array[i]));
                }
            } else if (value instanceof Map) {
                Map<String, Object> nestedMap = (Map<String, Object>) value;
                modifyData(nestedMap);
            }
        }
    }

