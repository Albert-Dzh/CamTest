package com.tz.cam.service;

import com.tz.cam.pojo.Camera;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import java.util.List;


@SpringBootTest
class CamServiceTest {

    @Autowired
    CamService service;

    @Test
    void when_asking_cams_then_its_only_one_list() {
        StepVerifier.create(service.getCams()).expectNextCount(1).verifyComplete();
    }

    @Test
    void when_asking_cams_then_its_exactly_those_cams() {       // fingers crossed!
        Camera c1 = Camera.builder().id(1).urlType("LIVE").videoUrl("rtsp://127.0.0.1/1").value("fa4b588e-249b-11e9-ab14-d663bd873d93").ttl(120).build();
        Camera c2 = Camera.builder().id(20).urlType("ARCHIVE").videoUrl("rtsp://127.0.0.1/2").value("fa4b5b22-249b-11e9-ab14-d663bd873d93").ttl(60).build();
        Camera c3 = Camera.builder().id(3).urlType("ARCHIVE").videoUrl("rtsp://127.0.0.1/3").value("fa4b5d52-249b-11e9-ab14-d663bd873d93").ttl(120).build();
        Camera c4 = Camera.builder().id(2).urlType("LIVE").videoUrl("rtsp://127.0.0.1/20").value("fa4b5f64-249b-11e9-ab14-d663bd873d93").ttl(180).build();

        var cams = List.of(c1, c2, c3, c4);
        StepVerifier.create(service.getCams()).expectNext(cams).verifyComplete();
    }
}