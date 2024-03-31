package com.miniProject.miniProject.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
public class TimeRecordFilter {


    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    private String assignment;
}
