package timeline.Base.Controller;


import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.RequestParam;  
import org.springframework.web.bind.annotation.RestController;

import timeline.db.service.BriefingService;  

@RestController
public class MyController {
	@GetMapping("/get_data")  
    public Object getData(@RequestParam(value = "$switchFlag", defaultValue = "0") int switchFlag) {  
        
		@Autowired
	    private BriefingService briefingService;
		
		@GetMapping("/get_data")  
	    public Object getData(@RequestParam(value = "$switchFlag", defaultValue = "0") int switchFlag) {  
	        Object data = {  
	                "test": ["100", 100, "haha"],  
	                "test1": {  
	                        "test": ["100", 100, "haha"],  
	                        "test2": 100  
	                },  
	                "test3": "100",  
	                "test4": 100  
	        };  
	          
	        if (switchFlag == 1) {  
	            modifyData(data);  
	        }  
	          
	        return data;  
	    }  
	      
	    private void modifyData(Object obj) {  
	        if (obj instanceof String) {  
	            if (((String) obj).equals("100")) {  
	                obj = "200";  
	            }  
	        } else if (obj instanceof Object[]) {  
	            Object[] array = (Object[]) obj;  
	            for (int i = 0; i < array.length; i++) {  
	                modifyData(array[i]);  
	            }  
	        } else if (obj instanceof java.util.Map) {  
	            java.util.Map<String, Object> map = (java.util.Map<String, Object>) obj;  
	            for (String key : map.keySet()) {  
	                modifyData(map.get(key));  
	            }  
	        }  
	    }  
}
