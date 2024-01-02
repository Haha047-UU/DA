// Copyright (C) 2023 Meituan
// All rights reserved
package timeline.Base.Model;

import lombok.Data;

import java.util.List;

/**
 * @author wangxi
 * @version 1.0
 * @created 2023/4/18 17:39
 **/

@Data
public class Post {
    private String title;
    private String content;
    private String author;
    private String timestamp;
    private List<Product> productList;

    @Data
    public static class Product {
        private String name;
        private Integer minPrice;
        private Integer maxPrice;
    }
}
