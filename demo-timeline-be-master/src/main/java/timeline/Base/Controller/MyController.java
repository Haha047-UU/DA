package timeline.Base.Controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;


public class MyController {

    // ...

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

    // ...

    // Example modifyData method (you need to implement it based on your requirements)
    private void modifyData(Map<String, Object> data) {
        // Modify the data map as per your requirements
    }
}

