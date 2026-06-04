package com.kuafu.web.controller;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum LoginReverenceValue {
certStatusEnum("certStatusEnum","user_certification_all.get_cert_status_enum_list"),userInfo("userInfo","feedback_all.get_user_info_list"),memberTypeEnum("memberTypeEnum","wallet_all.get_member_type_enum_list"),heatLevelEnum("heatLevelEnum","order_suggestion_all.get_heat_level_enum_list"),carTypeEnum("carTypeEnum","user_info_all.get_car_type_enum_list"),messageTypeEnum("messageTypeEnum","message_center_all.get_message_type_enum_list"),member_type_enum("member_type_enum","member_type_enum.get_select_list"),dynamic_api_setting("dynamic_api_setting","dynamic_api_setting.get_select_list"),wallet("wallet","wallet.get_select_list"),user_certification("user_certification","user_certification.get_select_list"),invite_record("invite_record","invite_record.get_select_list"),toilet_parking("toilet_parking","toilet_parking.get_select_list"),message_center("message_center","message_center.get_select_list"),cert_status_enum("cert_status_enum","cert_status_enum.get_select_list"),feedback("feedback","feedback.get_select_list"),order_suggestion("order_suggestion","order_suggestion.get_select_list"),user_info("user_info","user_info.get_select_list"),message_type_enum("message_type_enum","message_type_enum.get_select_list"),kf_system_config("kf_system_config","kf_system_config.get_select_list"),car_type_enum("car_type_enum","car_type_enum.get_select_list"),heat_level_enum("heat_level_enum","heat_level_enum.get_select_list"),;
    private String label;
    private String value;


    LoginReverenceValue(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public static String getValue(String label) {
        for (LoginReverenceValue item : values()) {
            if (StringUtils.equalsIgnoreCase(item.getLabel(), label)) {
                return item.getValue();
            }
        }
        return null;
    }
}
