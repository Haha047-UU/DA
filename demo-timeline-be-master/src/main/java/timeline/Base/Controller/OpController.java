// Copyright (C) 2022 Meituan
// All rights reserved
package timeline.Base.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import timeline.Base.Facade.ColumnFacade;
import timeline.db.model.Briefing;
import timeline.db.service.BriefingService;

/**
 * @author wangxi
 * @version 1.0
 * @created 2022/9/20 11:27
 **/
@RestController
public class OpController {

    @Autowired
    private BriefingService briefingService;
}
