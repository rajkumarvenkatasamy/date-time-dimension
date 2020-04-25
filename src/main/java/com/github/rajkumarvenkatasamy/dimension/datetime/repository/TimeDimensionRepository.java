package com.github.rajkumarvenkatasamy.dimension.datetime.repository;

import com.github.rajkumarvenkatasamy.dimension.datetime.model.DimTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeDimensionRepository<D> extends JpaRepository<DimTime, Integer> {
}
