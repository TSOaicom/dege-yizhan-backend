import service from "@/utils/request";

export default {
    login() {
        return service({
            url: "/system/setting/login",
            method: "get",
        });
    },
    settings() {
        return service({
            url: "/system/settings",
            method: "get",
        });
    },
    saveSetting(data) {
        return service({
            url: "/system/settings",
            method: "post",
            data: data
        });
    }
};