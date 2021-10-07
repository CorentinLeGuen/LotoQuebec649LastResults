package com.redmangoose.apps.controller;

import com.redmangoose.apps.LotoClientService;
import com.redmangoose.apps.entity.LotoQuebecObject;
import com.redmangoose.apps.entity.LotoQuebecTirage;
import com.redmangoose.apps.entity.LotoQuebecWebRequestError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("loto")
public class LotoQuebecController {
    @GetMapping(value = "/last", produces = "application/json")
    public ResponseEntity<LotoQuebecObject> getLastDraw() {
        LotoClientService service = new LotoClientService();
        LotoQuebecTirage tirage = service.getLastLotoResults();
        if (tirage == null) {
            return new ResponseEntity<>(new LotoQuebecWebRequestError(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(tirage, HttpStatus.OK);
    }
}
