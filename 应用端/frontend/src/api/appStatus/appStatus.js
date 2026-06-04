import service from "@/utils/request";

export default {
    getStatus(appId) {
        return service({
            url: "/admin/app/publish/result",
            method: "get",
            data: {
                app_id: appId || import.meta.env.VITE_APP_ID
            }
        }).then(res => res.data || {});
    },
    sendCreator(appId) {
        return service({
            url: "/admin/app/send/creator",
            method: "post",
            data: {
                app_id: appId || import.meta.env.VITE_APP_ID
            }
        }).then(res => res.data || {});
    }
}
