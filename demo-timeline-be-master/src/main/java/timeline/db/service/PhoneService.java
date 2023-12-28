// Copyright (C) 2023 Meituan
// All rights reserved
package timeline.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import timeline.db.model.Phone;
import timeline.db.respository.PhoneRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangxi
 * @version 1.0
 * @created 2023/4/19 15:34
 **/
@Service
public class PhoneService {

    @Autowired
    private PhoneRepository repository;

    public Map<Long, List<Phone>> getPhoneByBriefing(List<Long> briefingIds) {
        //按briefingIds查询Phone
        List<Phone> byBriefingIdIn = repository.findByBriefingIdIn(briefingIds);
        Map<Long, List<Phone>> result = new HashMap<>();
        for (Phone phone : byBriefingIdIn) {
            List<Phone> orDefault = result.getOrDefault(phone.getBriefingId(), new ArrayList<>());
            orDefault.add(phone);
            result.put(phone.getBriefingId(), orDefault);
        }
        return result;
    }
}
