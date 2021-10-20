package com.redmangoose.apps.controller;

import com.redmangoose.apps.entity.LotoQuebecObject;
import com.redmangoose.apps.entity.errors.LotoQuebecWebRequestError;
import com.redmangoose.apps.entity.last_draw.LotoQuebecTirage;
import com.redmangoose.apps.entity.stats.LotoQuebecFrequencesStats;
import com.redmangoose.apps.service.LotoQuebecTirageService;
import com.redmangoose.apps.util.LotoClientWebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("loto")
public class LotoQuebecController {
    private final Logger log = LoggerFactory.getLogger(LotoQuebecController.class);

    private final LotoQuebecTirageService service;

    public LotoQuebecController(LotoQuebecTirageService service) {
        this.service = service;
    }

    @GetMapping(value = "last", produces = "application/json")
    public @ResponseBody
    ResponseEntity<LotoQuebecObject> getLastDraw() {
        log.info("Asking last results");
        LotoClientWebClient service = new LotoClientWebClient();
        LotoQuebecTirage tirage = service.getLastLotoResults();
        if (tirage == null) {
            log.error("Unable to retrieve results");
            return new ResponseEntity<>(new LotoQuebecWebRequestError(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(tirage, HttpStatus.OK);
    }

    @GetMapping(value = "stats", produces = "application/json")
    public @ResponseBody
    ResponseEntity<LotoQuebecObject> getStats() {
        log.info("Asking for stats");
        LotoClientWebClient service = new LotoClientWebClient();
        LotoQuebecFrequencesStats stats = service.getLastLotoStatistics();
        if (stats == null) {
            log.error("Unable to get stats results");
            return new ResponseEntity<>(new LotoQuebecWebRequestError(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

    @GetMapping(value = "source", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Map<String, String>> getResultSources() {
        log.info("Asking the URL source");
        LotoClientWebClient service = new LotoClientWebClient();
        Map<String, String> result = new HashMap<>();
        result.put("last", service.getLotoQuebecLastResultsURL());
        result.put("stats", service.getLotoQuebecStatsURL());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public @ResponseBody
    ResponseEntity<Object> getAllTirages(@RequestParam(required = false) String date) {
        log.info("All tirages");
        if (date == null) {
            return new ResponseEntity<>(service.getAllTirages(), HttpStatus.OK);
        }
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException ex) {
            Map<String, String> errors = new HashMap<>();
            errors.put("timestamp", LocalDateTime.now().toString());
            errors.put("status", "400");
            errors.put("message", "Bad date format");
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        LotoQuebecTirage tirage = service.getTirageByDate(date);
        if (tirage == null) {
            Map<String, String> errors = new HashMap<>();
            errors.put("timestamp", LocalDateTime.now().toString());
            errors.put("status", "404");
            errors.put("message", "No record for date '" + date + "' found");
            return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tirage, HttpStatus.OK);
    }
}
