package com.miniProject.miniProject.repository;

import com.miniProject.miniProject.dto.TimeRecordDto;
import com.miniProject.miniProject.entity.TimeRecord;
import com.miniProject.miniProject.filter.TimeRecordFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TimeRecordRepository extends JpaRepository<TimeRecord, Integer>, PagingAndSortingRepository<TimeRecord, Integer>, JpaSpecificationExecutor<TimeRecord> {

    List<TimeRecord> findByDate(Date date);

    List<TimeRecord> findByDateBetween(Date startDate, Date endDate);
}
