package com.redmangoose.apps.repository;

import com.redmangoose.apps.entity.last_draw.LotoQuebecTirage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotoQuebecTirageRepository extends CrudRepository<LotoQuebecTirage, Integer> {
    @Query("SELECT t FROM LotoQuebecTirage t WHERE t.date_tirage = ?1")
    List<LotoQuebecTirage> findByDateTirage(String date);

    @Query(nativeQuery = true, value = "SELECT TOP 1 DATE_TIRAGE FROM TIRAGE ORDER BY DATE_TIRAGE")
    String findFirstTirage();

    @Query(nativeQuery = true, value = "SELECT TOP 1 DATE_TIRAGE FROM TIRAGE ORDER BY DATE_TIRAGE DESC")
    String findLastTirage();
}
