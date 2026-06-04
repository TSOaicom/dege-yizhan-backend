package com.kuafu.web.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class SmsConfig {
    @Value("${SMS_ACCESS_KEY_ID:xxxxxxx}")
    private String accessKeyId;

    @Value("${SMS_ACCESS_KEY_SECRET:xxxxxxx}")
    private String accessKeySecret;

    @Value("${SMS_ENDPOINT:xxxxxxx}")
    private String endpoint;

    @Value("${SMS_SIGN_NAME:xxxxxxx}")
    private String signName;

    @Value("${SMS_TEMPLATE_CODE:xxxxxxx}")
    private String templateCode;
}
