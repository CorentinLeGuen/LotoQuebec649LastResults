package com.redmangoose.apps.lottery.service;

import com.redmangoose.apps.lottery.entity.Draw;
import com.redmangoose.apps.lottery.entity.LotteryObject;
import com.redmangoose.apps.lottery.entity.Statistics;
import com.redmangoose.apps.lottery.repository.DrawRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DrawService {
    private final DrawRepository repository;

    public DrawService(DrawRepository repository) {
        this.repository = repository;
    }

    public Statistics getAllTirages() {
        List<LotteryObject> allResults = new LinkedList<>();
        repository.findAll().forEach(allResults::add);
        return new Statistics(repository.findFirstTirage(), repository.findLastTirage(), allResults);
    }

    public Draw getTirageByDate(String date) {
        List<Draw> tirages = repository.findByDateTirage(date);
        if (tirages == null || tirages.isEmpty()) {
            return null;
        }
        return tirages.get(0);
    }
}
