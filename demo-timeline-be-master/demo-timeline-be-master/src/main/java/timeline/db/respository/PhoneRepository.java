// Copyright (C) 2023 Meituan
// All rights reserved
package timeline.db.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import timeline.db.model.Briefing;
import timeline.db.model.Phone;

import java.util.List;

/**
 * @author wangxi
 * @version 1.0
 * @created 2023
 **/

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    List<Phone> findByBriefingId(Long briefingId);

    List<Phone> findByBriefingIdIn(List<Long> briefingIdList);
}