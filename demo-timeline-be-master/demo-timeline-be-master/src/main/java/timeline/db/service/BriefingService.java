// Copyright (C) 2023 Meituan
// All rights reserved
package timeline.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import timeline.db.model.Briefing;
import timeline.db.respository.BriefingRepository;

import java.util.List;

/**
 * @author wangxi
 * @version 1.0
 * @created 2023/4/19 15:34
 **/
@Service
public class BriefingService {

    @Autowired
    private BriefingRepository repository;

    public List<Briefing> getAll(Pageable pageable) {
        Page<Briefing> page = repository.findAll(pageable);
        return page.getContent();
    }

    public List<Briefing> getAll() {
        return repository.findAll();
    }

    public Briefing create(Briefing briefing) {
        return repository.save(briefing);
    }
}
