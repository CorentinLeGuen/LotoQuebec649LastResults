package com.redmangoose.apps.lottery.controller;

import com.redmangoose.apps.lottery.entity.Draw;
import com.redmangoose.apps.lottery.entity.Statistics;
import com.redmangoose.apps.lottery.service.DrawService;
import com.redmangoose.apps.lottery.utils.LotteryURLs;
import com.redmangoose.apps.lottery.utils.LotteryWebClient;
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
public class MainApplicationController {
    public static final String TIMESTAMP = "timestamp";
    public static final String STATUS = "status";
    public static final String MESSAGE = "message";
    private final Logger log = LoggerFactory.getLogger(MainApplicationController.class);

    private final DrawService service;

    public MainApplicationController(DrawService service) {
        this.service = service;
    }

    @GetMapping(value = "last", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Object> getLastDraw() {
        log.info("Asking last results");
        LotteryWebClient serviceClient = new LotteryWebClient();
        Draw tirage = serviceClient.getLastLotoResults();
        if (tirage == null) {
            log.error("Unable to retrieve results");
            Map<String, String> errors = new HashMap<>();
            errors.put(TIMESTAMP, LocalDateTime.now().toString());
            errors.put(STATUS, "500");
            errors.put(MESSAGE, "Unable to contact remote server");
            return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(tirage, HttpStatus.OK);
    }

    @GetMapping(value = "stats", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Object> getStats() {
        log.info("Asking for stats");
        LotteryWebClient serviceClient = new LotteryWebClient();
        Statistics stats = serviceClient.getLastLotoStatistics();
        if (stats == null) {
            log.error("Unable to get stats results");
            Map<String, String> errors = new HashMap<>();
            errors.put(TIMESTAMP, LocalDateTime.now().toString());
            errors.put(STATUS, "500");
            errors.put(MESSAGE, "Unable to contact remote server");
            return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

    @GetMapping(value = "source", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Map<String, String>> getResultSources() {
        log.info("Asking the URL source");
        Map<String, String> result = new HashMap<>();
        result.put(TIMESTAMP, LocalDateTime.now().toString());
        result.put("/loto/last", LotteryURLs.LOTO_QUEBEC_LAST_RESULTS_URL.toString());
        result.put("/loto/stats", LotteryURLs.LOTO_QUEBEC_STATS_URL.toString());
        result.put("/loto?date=yyyy-MM-dd", LotteryURLs.LOTO_QUEBEC_RESULTS_URL.toString());
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
            errors.put(TIMESTAMP, LocalDateTime.now().toString());
            errors.put(STATUS, "400");
            errors.put(MESSAGE, "Bad date format");
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Draw tirage = service.getTirageByDate(date);
        if (tirage == null) {
            Map<String, String> errors = new HashMap<>();
            errors.put(TIMESTAMP, LocalDateTime.now().toString());
            errors.put(STATUS, "404");
            errors.put(MESSAGE, "No record for date '" + date + "' found");
            return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tirage, HttpStatus.OK);
    }
}
