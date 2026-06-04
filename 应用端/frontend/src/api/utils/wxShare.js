import get_page_title from "../../config/get_page_title";

/**
 * 将页面 options 对象转为 query 字符串，如 { id: 1, type: 2 } => "id=1&type=2"
 */
function buildQuery(options) {
    if (!options || typeof options !== 'object') return '';
    const query = Object.keys(options)
        .filter(key => options[key] !== undefined && options[key] !== null)
        .map(key => `${key}=${encodeURIComponent(options[key])}`)
        .join('&');
    return query;
}

/**
 * 获取当前页面完整路径（含 query 参数）
 */
function getCurrentPageFullPath() {
    const pages = getCurrentPages();
    const view = pages[pages.length - 1];
    const query = buildQuery(view.options || {});
    return {
        route: view.route,
        fullPath: query ? `/${view.route}?${query}` : `/${view.route}`
    };
}

export default {
    data() {
        return {
            share: {
                // 转发的标题 （默认标题）
                title: '默认标题--分享标题',
                // 默认是当前页面，必须是以'/'开头的完整路径
                path: ''
            }
        }
    },
    // 发送给朋友
    onShareAppMessage(res) {
        const { route, fullPath } = getCurrentPageFullPath();
        this.share.path = fullPath;
        this.share.title = get_page_title(route);
        return this.share;
    },
    //分享到朋友圈
    onShareTimeline(res) {
        const { route, fullPath } = getCurrentPageFullPath();
        this.share.path = fullPath;
        this.share.title = get_page_title(route);
        return this.share;
    },

}
