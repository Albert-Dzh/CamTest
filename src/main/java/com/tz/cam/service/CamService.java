package com.tz.cam.service;

import com.tz.cam.pojo.BaseInfo;
import com.tz.cam.pojo.Camera;
import com.tz.cam.pojo.SrcInfo;
import com.tz.cam.pojo.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CamService {

    private final WebClient wc;
    private final RestTemplate restTemplate;
    public final String URL = "https://www.mocky.io/v2/5c51b9dd3400003252129fb5";

    public CamService() {
        this.wc = WebClient.create(URL);
        this.restTemplate = new RestTemplate();
    }

    public Mono<List<Camera>> getCams() {
        return wc.get()
                .retrieve()
                .bodyToFlux(BaseInfo.class)
                .publishOn(Schedulers.boundedElastic())
                .map(c -> {
                    var s = restTemplate.getForObject(c.getSourceDataUrl(), SrcInfo.class);
                    var t = restTemplate.getForObject(c.getTokenDataUrl(), TokenInfo.class);

                    return Camera.builder()
                            .id(c.getId())
                            .urlType(s.getUrlType())
                            .videoUrl(s.getVideoUrl())
                            .value(t.getValue())
                            .ttl(t.getTtl())
                            .build();
                })
                .collectList();
    }
}
