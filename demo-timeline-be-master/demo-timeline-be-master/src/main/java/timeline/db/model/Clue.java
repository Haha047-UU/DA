// Copyright (C) 2023 Meituan
// All rights reserved
package timeline.db.model;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wangxi
 * @version 1.0
 * @created 2023
 **/
@Entity
@Data
@Table(name = "te_clue")
public class Clue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String url;

    private String status;
}
