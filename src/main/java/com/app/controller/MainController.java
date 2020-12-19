package com.app.controller;

import com.app.model.dto.InfoDto;
import com.app.model.dto.get.GetPackDto;
import com.app.service.json.JSONService;
import com.app.service.model.PackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    private final JSONService jsonService;
    private final PackService packService;

    @GetMapping("/listThreeOffers")
    public InfoDto<List<GetPackDto>> ThreeBestOffers(){
        return InfoDto.<List<GetPackDto>>builder().data(packService.getBestThreePack()).build();
    }

    @GetMapping("/data")
    public InfoDto<String> database() {
        jsonService.saveDataToDatabase();
        return InfoDto.<String>builder().data("CREATE DATA TO DATABASE").build();
    }
}
