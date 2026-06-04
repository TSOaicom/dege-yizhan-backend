import {createClient, pluginLoader} from 'aipexbase-js';
import localPlugin from "@/api/plugin"
import service from "@/utils/request"
import storage from "@/config/storage"

// 循环注册本地插件
for (const pluginName in localPlugin) {
    const pluginModule = localPlugin[pluginName];
    // 检查是否是函数或对象（插件可以是函数或对象）
    if (typeof pluginModule === 'function' || typeof pluginModule === 'object') {
        if (typeof pluginModule === 'object' && typeof pluginModule.default === 'function') {
            pluginLoader.register(pluginName, pluginModule.default);
        } else if (typeof pluginModule === 'object' && typeof pluginModule.default === 'object') {
            pluginLoader.register(pluginName, pluginModule.default);
        } else {
            pluginLoader.register(pluginName, pluginModule);
        }
    }
}

// 语言代码映射
const languageMap = {
    'en': 'en-US',
    'zh': 'zh-CN',
    'ja': 'ja-JP',
    'ko': 'ko-KR',
    // 可以根据需要添加更多映射
};

const getLanguageHeader = () => {
    const lang = import.meta.env.VITE_APP_LANGE || 'en';
    // 如果语言代码在映射表中，使用映射值，否则使用原值
    return languageMap[lang] || lang;
}

const requestClient = (url, options) => {
    const agentToken = storage.getItem("agent_token");
    const agentSign = storage.getItem("agent_sign");
    const agentHeaders = {};
    if (agentToken) agentHeaders['X-Agent-Token'] = agentToken;
    if (agentSign) agentHeaders['X-Agent-Sign'] = agentSign;

    return service({
        url,
        method: options.method || "GET",
        headers: {...options.headers, 'Accept-Language': getLanguageHeader(), ...agentHeaders},
        data: options.body ? JSON.parse(options.body) : {},
    })
}


let baseUrl = ""

const apiKey = import.meta.env.VITE_APP_ID
const client = createClient({
    baseUrl: baseUrl,
    apiKey: apiKey,
    storage: storage,
    request: requestClient
});

export default client;