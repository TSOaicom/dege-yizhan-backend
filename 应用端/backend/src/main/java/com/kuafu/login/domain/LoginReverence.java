package com.kuafu.login.domain;

import com.kuafu.login.domain.SelectVo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum LoginReverence {

            kfSystemConfig("系统配置","kfSystemConfig"),
        userInfo("用户信息","userInfo"),
        carTypeEnum("车辆类型","carTypeEnum"),
        certStatusEnum("认证状态","certStatusEnum"),
        userCertification("资质认证","userCertification"),
        orderSuggestion("订单建议","orderSuggestion"),
        heatLevelEnum("热力等级","heatLevelEnum"),
        toiletParking("厕所停车","toiletParking"),
        messageCenter("消息中心","messageCenter"),
        messageTypeEnum("消息类型","messageTypeEnum"),
        wallet("钱包","wallet"),
        memberTypeEnum("会员类型","memberTypeEnum"),
        inviteRecord("邀请记录","inviteRecord"),
        feedback("反馈","feedback"),
    ;


    private String label;
    private String value;

    LoginReverence(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public static List<SelectVo> all() {
        final LoginReverence[] values = LoginReverence.values();
        return Arrays.stream(values).map(r -> {
            return new SelectVo(r.getValue(), r.getLabel(),r.getLabel());
        }).collect(Collectors.toList());
    }
}
