// Copyright (C) 2023 Meituan
// All rights reserved
package timeline.db.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.annotation.Documented;

/**
 * @author wangxi
 * @version 1.0
 * @created 2023/4/19 15:22
 **/
@Entity
@Data
@Table(name = "te_briefing")
public class Briefing {

    public static String BEGIN_TIME = "beginTime";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    //开始时间 - 格式为：20230412
    private String beginTime;
    //结束时间 - 格式为：20230412
    private String endTime;
    //地点
    private String location;
}
