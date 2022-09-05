package com.tz.cam.controller;


import com.tz.cam.pojo.Camera;
import com.tz.cam.service.CamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CamController {

    private final CamService service;

    @GetMapping("/v1/api/getCamInfo")
    public Mono<List<Camera>> getCamInfo() { return service.getCams(); }
}
