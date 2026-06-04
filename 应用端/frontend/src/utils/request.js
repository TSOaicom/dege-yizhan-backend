import storage from "@/config/storage"
import home_page_name from "@/config/home_condfig";


const languageMap = {
    'en': 'en-US',
    'zh': 'zh-CN',
    'ja': 'ja-JP',
    'ko': 'ko-KR',
    // 可以根据需要添加更多映射
};

const getLanguageHeader = () => {
    const lang = import.meta.env.VITE_APP_LANGE || 'en';
    return languageMap[lang] || lang;
}

const getBaseUrl = () => {
    // #ifdef MP-WEIXIN || APP-PLUS || APP-HARMONY
    return import.meta.env.VITE_APP_SERVICE_API;
    // #endif

    // #ifdef H5
    return import.meta.env.VITE_APP_BASE_API;
    // #endif
}

const baseUrl = getBaseUrl();

// 未授权处理（导出供 excel 等使用）
export const handleUnauthorized = () => {
    // 清除token
    storage.removeItem("baas_token");

    // 延迟跳转，避免与当前页面操作冲突
    setTimeout(() => {
        uni.navigateTo({
            url: `/pages/${home_page_name}/index`
        });
    }, 800);
}

// 构建请求头（导出供 excel 等需自定义 headers 的场景合并 token 使用）
export const buildHeaders = () => {
    const token = storage.getItem("baas_token");
    const agentToken = storage.getItem("agent_token");
    const agentSign = storage.getItem("agent_sign");

    const headers = {
        'content-type': 'application/json',
        "Authorization": token ? `Bearer ${token}` : '',
        "APP_ID": import.meta.env.VITE_APP_ID,
        "APP_TYPE": import.meta.env.VITE_APP_TYPE,
        'Accept-Language': getLanguageHeader()
    };

    if (agentToken) headers['X-Agent-Token'] = agentToken;
    if (agentSign) headers['X-Agent-Sign'] = agentSign;

    return headers;
}

// 构建完整URL（导出供 excel 等使用）
export const buildUrl = (url) => {
    if (url.startsWith("https://") || url.startsWith("http://")) {
        return url;
    }
    return baseUrl + url;
}

let service = (res) => {
    let {url, data, method, headers, responseType} = res;
    return new Promise((resolve, reject) => {
        const requestConfig = {
            url: buildUrl(url),
            data: data,
            method: method.toUpperCase(),
            timeout: 180000,
            header: headers || buildHeaders(),
            success(res) {
                // 二进制响应（arraybuffer/blob）：直接返回原始数据，不按 JSON 解析
                if (responseType === 'arraybuffer' || responseType === 'blob') {
                    if (res.statusCode !== 200) {
                        // 错误时尝试解析 JSON 错误信息
                        try {
                            const decoder = new TextDecoder('utf-8');
                            const text = decoder.decode(new Uint8Array(res.data));
                            const errObj = JSON.parse(text);
                            uni.showToast({
                                title: errObj.message || '导出失败',
                                icon: "none"
                            });
                            reject(new Error(errObj.message));
                        } catch (e) {
                            reject(new Error('导出失败'));
                        }
                        return;
                    }
                    // 检查 Content-Type：若为 JSON 说明是业务错误（如无数据可导出）
                    const contentType = (res.header && res.header['Content-Type']) || (res.header && res.header['content-type']) || '';
                    if (contentType.includes('application/json')) {
                        try {
                            const decoder = new TextDecoder('utf-8');
                            const text = decoder.decode(new Uint8Array(res.data));
                            const errObj = JSON.parse(text);
                            uni.showToast({
                                title: errObj.message || '导出失败',
                                icon: "none"
                            });
                            reject(new Error(errObj.message));
                        } catch (e) {
                            reject(new Error('导出失败'));
                        }
                        return;
                    }
                    resolve(res.data);
                    return;
                }
                // 普通 JSON 响应
                if (res.data && res.data.code === 401) {
                    handleUnauthorized();
                } else if (res.data && res.data.code !== 0 && res.data.code !== undefined) {
                    uni.showToast({
                        title: res.data.message || '系统出错',
                        icon: "none"
                    })
                    resolve(res.data)
                } else {
                    resolve(res.data)
                }
            },
            fail(err) {
                reject(err)
                console.log(err)
            }
        };
        if (responseType) {
            requestConfig.responseType = responseType;
        }
        uni.request(requestConfig);
    })
}
export default service
