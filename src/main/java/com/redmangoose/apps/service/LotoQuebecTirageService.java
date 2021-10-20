package com.redmangoose.apps.service;

import com.redmangoose.apps.entity.last_draw.LotoQuebecTirage;
import com.redmangoose.apps.entity.stats.LotoQuebecTiragesStats;
import com.redmangoose.apps.repository.LotoQuebecTirageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotoQuebecTirageService {
    private final LotoQuebecTirageRepository repository;

    public LotoQuebecTirageService(LotoQuebecTirageRepository repository) {
        this.repository = repository;
    }

    public LotoQuebecTiragesStats getAllTirages() {
        return new LotoQuebecTiragesStats(repository.findFirstTirage(), repository.findLastTirage(), repository.findAll());
    }

    public LotoQuebecTirage getTirageByDate(String date) {
        List<LotoQuebecTirage> tirages = repository.findByDateTirage(date);
        if (tirages == null || tirages.size() == 0) {
            return null;
        }
        return tirages.get(0);
    }
}
