package com.miniProject.miniProject.repository;

import com.miniProject.miniProject.entity.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TimeRecordRepository extends JpaRepository<TimeRecord, Integer>, PagingAndSortingRepository<TimeRecord, Integer>, JpaSpecificationExecutor<TimeRecord> {

    List<TimeRecord> findByDate(LocalDate date);

    List<TimeRecord> findByDateBetween(LocalDate startDate, LocalDate endDate);

    List<TimeRecord >findByAssignment_User_Id(Integer  id);
}
