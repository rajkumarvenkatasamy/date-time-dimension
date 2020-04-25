package com.github.rajkumarvenkatasamy.dimension.datetime.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "dim_time")
public class DimTime {

    @Id
    @Column(name = "time_key")
    private Integer timeKey;

    @Column(name = "hour_24")
    private Integer hour24;

    @Column(name = "hour_24_short_string")
    private String hour24ShortString;

    @Column(name = "hour_24_minute_string")
    private String hour24MinuteString;

    @Column(name = "hour_24_full_string")
    private String hour24FullString;

    @Column(name = "hour_12")
    private Integer hour12;

    @Column(name = "hour_12_short_string")
    private String hour12ShortString;

    @Column(name = "hour_12_minute_string")
    private String hour12MinuteString;

    @Column(name = "hour_12_full_string")
    private String hour12FullString;

    @Column(name = "am_pm")
    private String amPM;

    @Column(name = "minute")
    private Integer minute;

    @Column(name = "second")
    private Integer second;

    @Column(name = "start_minute_15min_slab")
    private Integer startMinuteIn15MinSlab;

    @Column(name = "end_minute_15min_slab")
    private Integer endMinuteIn15MinSlab;

    @Column(name = "slab_for_15min")
    private String slabFor15Minutes;

    @Column(name = "start_minute_30min_slab")
    private Integer startMinuteIn30MinSlab;

    @Column(name = "end_minute_30min_slab")
    private Integer endMinuteIn30MinSlab;

    @Column(name = "slab_for_30min")
    private String slabFor30Minutes;

}
