package ca.openbox.shift.repository;

import ca.openbox.shift.dataobject.EmployeePreferWorkdayDO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

public interface EmployeePreferWorkdayRepository extends Repository<EmployeePreferWorkdayDO,Integer> {
    @Query("SELECT w FROM EmployeePreferWorkdayDO w where MONTH(w.preferDate) = :month")
    public List<EmployeePreferWorkdayDO> findAllByMonth(@Param("month") int month);
    @Modifying
    @Transactional
    @Query("DELETE FROM EmployeePreferWorkdayDO w where w.name=:name and MONTH(w.preferDate)=:month")
    public void deleteByNameAndMonth(@Param("name") String name,@Param("month") int month);
    public void save(EmployeePreferWorkdayDO employeePreferWorkdayDO);
}
