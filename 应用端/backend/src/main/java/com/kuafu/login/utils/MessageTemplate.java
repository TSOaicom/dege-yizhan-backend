package com.kuafu.login.utils;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.teaopenapi.models.Config;
import com.kuafu.web.config.SmsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MessageTemplate {

    @Autowired
    private SmsConfig smsConfig;

    /**
     * 发送短信
     *
     * @param phone
     * @param code
     * @throws Exception
     */
    public void sendMessage(String phone, String code) throws Exception {
        String accessKeyId = smsConfig.getAccessKeyId();
        String accessKeySecret = smsConfig.getAccessKeySecret();
        Config config = new Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret);
        String endpoint = smsConfig.getEndpoint();
        config.endpoint = endpoint;
        Client client = new Client(config);
        String signName = smsConfig.getSignName();
        String templateCode = smsConfig.getTemplateCode();
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setTemplateCode(templateCode)
                .setSignName(signName)
                .setPhoneNumbers(phone)
                .setTemplateParam("{\"code\":\"" + code + "\"}");
        SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
        SendSmsResponseBody body = sendSmsResponse.getBody();
        System.out.println("短信发送结果：" + body.code);//打印结果
        System.out.println("短信发送结果：" + body.message);//打印结果
    }
}
