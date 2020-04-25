package com.github.rajkumarvenkatasamy.dimension.datetime.repository;

import com.github.rajkumarvenkatasamy.dimension.datetime.model.DimDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateDimensionRepository<D> extends JpaRepository<DimDate, Integer> {
}
