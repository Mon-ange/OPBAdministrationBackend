package ca.openbox.shift.repository;

import ca.openbox.shift.dataobject.KPIRecordDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KPIRecordRepository extends JpaRepository<KPIRecordDO, Long> {

    @Query("SELECT k FROM KPIRecordDO k WHERE k.title LIKE :yearPattern")
    List<KPIRecordDO> findByYear(@Param("yearPattern") String yearPattern);
}
