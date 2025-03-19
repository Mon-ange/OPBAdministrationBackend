package ca.openbox.shift.repository;

import ca.openbox.shift.dataobject.EmployeePreferWorkdayDO;
import ca.openbox.shift.dataobject.StatutoryHolidayDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.List;

public interface StatutoryHolidayRepository extends Repository<StatutoryHolidayDO,Integer> {

    List<StatutoryHolidayDO> findAll();
    List<StatutoryHolidayDO> findByStatutoryDateAfter(LocalDate date);
    List<StatutoryHolidayDO> findByStatutoryDateBetween(LocalDate startDate, LocalDate endDate);

    // Retrieve only the date field, excluding other columns
    @Query("SELECT h.statutoryDate FROM StatutoryHolidayDO h")
    List<LocalDate> findAllHolidayDates();
}
