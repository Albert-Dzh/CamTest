package com.tz.cam.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenInfo {
    private String value;
    private int ttl;
}
