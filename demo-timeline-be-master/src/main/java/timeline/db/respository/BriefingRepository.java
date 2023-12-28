// Copyright (C) 2023 Meituan
// All rights reserved
package timeline.db.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import timeline.db.model.Briefing;

/**
 * @author wangxi
 * @version 1.0
 * @created 2023/4/19 15:32
 **/

@Repository
public interface BriefingRepository extends JpaRepository<Briefing, Long> {
}