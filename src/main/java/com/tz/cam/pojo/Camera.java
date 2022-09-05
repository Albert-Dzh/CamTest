package com.tz.cam.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class Camera {

    long id;
    String urlType;
    String videoUrl;
    String value;
    int ttl;

}
