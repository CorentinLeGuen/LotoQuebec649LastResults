package com.redmangoose.apps.lottery.repository;

import com.redmangoose.apps.lottery.entity.Draw;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrawRepository extends CrudRepository<Draw, Integer> {
    @Query("SELECT t FROM Draw t WHERE t.dateTirage = ?1")
    List<Draw> findByDateTirage(String date);

    @Query(nativeQuery = true, value = "SELECT TOP 1 DATE_TIRAGE FROM TIRAGE ORDER BY DATE_TIRAGE")
    String findFirstTirage();

    @Query(nativeQuery = true, value = "SELECT TOP 1 DATE_TIRAGE FROM TIRAGE ORDER BY DATE_TIRAGE DESC")
    String findLastTirage();
}
