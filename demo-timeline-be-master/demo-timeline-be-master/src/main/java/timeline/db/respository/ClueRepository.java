// Copyright (C) 2023 Meituan
// All rights reserved
package timeline.db.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import timeline.db.model.Clue;

import java.util.Optional;

/**
 * @author wangxi
 * @version 1.0
 * @created 2023
 **/

@Repository
public interface ClueRepository extends JpaRepository<Clue, Long> {
    Optional<Clue> findByUrl(String url);
}