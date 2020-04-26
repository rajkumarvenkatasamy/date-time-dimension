package com.github.rajkumarvenkatasamy.dimension.datetime.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "dim_date")
public class DimDate {

    @Id
    @Column(name = "date_key")
    private Long dateKey;

    @Column(name = "date_value")
    private LocalDate date;

    @Column(name = "date_in_number")
    private Integer dateInNumber;

    @Column(name = "day_name")
    private String dayName;

    @Column(name = "short_day_name")
    private String shortDayName;

    @Column(name = "is_weekday")
    private Character isWeekday;

    @Column(name = "is_weekend")
    private Character isWeekend;

    @Column(name = "day_of_week")
    private int dayOfWeek;

    @Column(name = "day_of_week_in_month")
    private Integer dayOfWeekInMonth;

    @Column(name = "day_of_month")
    private Integer dayOfMonth;

    @Column(name = "day_of_year")
    private Integer dayOfYear;

    @Column(name = "week_of_month")
    private Integer weekOfMonth;

    @Column(name = "week_of_year")
    private Integer weekOfYear;

    @Column(name = "week_start_date")
    private LocalDate weekStartDate;

    @Column(name = "week_end_date")
    private LocalDate weekEndDate;

    @Column(name = "week_key")
    private Long weekKey;

    @Column(name = "month_name")
    private String monthName;

    @Column(name = "short_month_name")
    private String shortMonthName;

    @Column(name = "year_month")
    private String yearMonth;

    @Column(name = "month_of_year")
    private Integer monthOfYear;

    @Column(name = "trailing_3_months")
    private String trailing3Months;

    @Column(name = "last_3_months")
    private String last3Months;

    @Column(name = "last_6_months")
    private String last6Months;

    @Column(name = "num_weeks_in_month")
    private Integer numWeeksInMonth;

    @Column(name = "month_key")
    private Long monthKey;

    @Column(name = "quarter")
    private String quarter;

    @Column(name = "quarter_name")
    private String quarterName;

    @Column(name = "year_quarter_name")
    private String yearQuarterName;

    @Column(name = "quarter_key")
    private Long quarterKey;

    @Column(name = "half_year_number")
    private Integer halfYearNumber;

    @Column(name = "half_year_name")
    private String halfYearName;

    @Column(name = "half_year_key")
    private Long halfYearKey;

    @Column(name = "year_half_year_name")
    private String yearHalfYearName;

    @Column(name = "year")
    private Integer year;

    @Column(name = "year_key")
    private Long yearKey;

    @Column(name = "fiscal_week_of_year")
    private Integer fiscalWeekOfYear;

    @Column(name = "fiscal_month_of_year")
    private Integer fiscalMonthOfYear;

    @Column(name = "fiscal_quarter")
    private Integer fiscalQuarter;

    @Column(name = "fiscal_half")
    private Integer fiscalHalf;

    @Column(name = "fiscal_year")
    private Integer fiscalYear;

    @Column(name = "fiscal_year_quarter_name")
    private String fiscalYearQuarterName;

    @Column(name = "fiscal_year_half_year_name")
    private String fiscalYearHalfYearName;

    @Column(name = "fiscal_day_of_year")
    private Integer fiscalDayOfYear;
}
