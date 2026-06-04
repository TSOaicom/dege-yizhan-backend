import service from "@/utils/request";

export default {
    sendEmailCode(params) {
        const {email} = params;
        return service({
            url: "/login/mail/sendCode",
            method: "post",
            data: {
                phone: email
            }
        });
    },
    sendPhoneCode(params) {
        const {phone} = params;
        return service({
            url: "/login/phone/sendCode",
            method: "post",
            data: {
                phone: phone
            }
        });
    },
    loginWechatAuthUri() {
        return service({
            url: "/login/wechat",
            method: "get",
        });
    }
}