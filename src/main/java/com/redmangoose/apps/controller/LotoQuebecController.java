package com.redmangoose.apps.controller;

import com.redmangoose.apps.service.LotoClientService;
import com.redmangoose.apps.entity.LotoQuebecObject;
import com.redmangoose.apps.entity.stats.LotoQuebecStatistiques;
import com.redmangoose.apps.entity.last_draw.LotoQuebecTirage;
import com.redmangoose.apps.entity.errors.LotoQuebecWebRequestError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("loto")
public class LotoQuebecController {
    private final Logger log = LoggerFactory.getLogger(LotoQuebecController.class);

    @GetMapping(value = "last", produces = "application/json")
    public @ResponseBody ResponseEntity<LotoQuebecObject> getLastDraw() {
        log.info("Asking last results");
        LotoClientService service = new LotoClientService();
        LotoQuebecTirage tirage = service.getLastLotoResults();
        if (tirage == null) {
            log.error("Unable to retrieve results");
            return new ResponseEntity<>(new LotoQuebecWebRequestError(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(tirage, HttpStatus.OK);
    }

    @GetMapping(value = "stats", produces = "application/json")
    public @ResponseBody ResponseEntity<LotoQuebecObject> getStats() {
        log.info("Asking for stats");
        LotoClientService service = new LotoClientService();
        LotoQuebecStatistiques stats = service.getLastLotoStatistics();
        if (stats == null) {
            log.error("Unable to get stats results");
            return new ResponseEntity<>(new LotoQuebecWebRequestError(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

    @GetMapping(value = "source", produces = "application/json")
    public @ResponseBody ResponseEntity<Map<String, String>> getResultSources() {
        log.info("Asking the URL source");
        LotoClientService service = new LotoClientService();
        Map<String, String> result = new HashMap<>();
        result.put("last", service.getLotoQuebecLastResultsURL());
        result.put("stats", service.getLotoQuebecStatsURL());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
