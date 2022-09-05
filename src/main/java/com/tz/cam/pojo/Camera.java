package com.tz.cam.pojo;

import lombok.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Camera camera = (Camera) o;
        return id == camera.id && ttl == camera.ttl &&
                Objects.equals(urlType, camera.urlType) &&
                Objects.equals(videoUrl, camera.videoUrl) &&
                Objects.equals(value, camera.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, urlType, videoUrl, value, ttl);
    }
}
