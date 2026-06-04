import home_page_name from "@/config/home_condfig";

const defaultType = "page";


/**
 * 打开外部链接
 * @param url
 */
function openExternalUrl(url) {
    if (!url) {
        uni.showToast({
            title: '无效的链接',
            icon: 'none'
        });
        return;
    }

    // #ifdef H5
    window.location.href = url;
    // #endif

    // #ifdef APP-PLUS
    plus.runtime.openURL(url);
    // #endif

    // #ifdef MP
    uni.showModal({
        title: '提示',
        content: '当前平台不支持打开外部链接',
        showCancel: false
    });
    // #endif
}

class NavigatorToBuilder {
    constructor() {
        this.__param = {}
        this.__promise = null;

        // 使用微任务延迟执行，让链式调用先完成
        Promise.resolve().then(() => {
            if (!this.__promise && !this.__autoExecuteScheduled && Object.keys(this.__param).length > 0) {
                this.__autoExecuteScheduled = true;
                this.__promise = this.__execute();
            }
        });
    }

    url(url) {
        this.__param.url = url
        return this;
    }

    type(type) {
        this.__param.type = type
        return this;
    }

    model(model) {
        this.__param.model = model
        return this;
    }

    then(resolve, reject) {
        // 如果还没有创建 promise，就创建并标记为手动执行
        if (!this.__promise) {
            this.__autoExecuteScheduled = true; // 阻止自动执行
            this.__promise = this.__execute();
        }
        return this.__promise.then(resolve, reject);
    }


    __execute() {

        let {
            url = '',
                type = defaultType, // 默认跳转类型
                mode = this.__param.model || 'navigate' // 支持 model 参数
        } = this.__param;


        return new Promise((resolve, reject) => {
            if (!url) {
                uni.showToast({
                    title: '请提供链接地址',
                    icon: 'none'
                });
                reject(new Error('链接地址为空'));
                return;
            }
            switch (type) {
                case 'page':
                    if (!url.includes("/" + home_page_name + "/")) {
                        let urlParts = url.split("/");
                        for (let i = 0; i < urlParts.length; i++) {
                            if (urlParts[i] === 'pages') {
                                urlParts[i] = 'pagesA'
                            }
                        }
                        // 2. 处理多余的 index
                        const newParts = [];
                        for (let i = 0; i < urlParts.length; i++) {
                            const part = urlParts[i];

                            if (part === "index") {
                                // 计算从 i 开始连续 index 的长度
                                let j = i;
                                while (j < urlParts.length && urlParts[j] === "index") j++;
                                const runLen = j - i;

                                // 保留数量：最多保留 2 个（根据需要也可以改成 1 或者保持全部）
                                const keep = Math.min(runLen, 2);
                                for (let k = 0; k < keep; k++) newParts.push("index");

                                // 跳过已处理的 index
                                i = j - 1;
                            } else {
                                newParts.push(part);
                            }
                        }
                        // 只获取一个index
                        url = urlParts.join("/");
                    }
                    if (mode === 'redirect') {
                        uni.redirectTo({
                            url,
                            success: () => resolve({
                                success: true
                            }),
                            fail: (err) => reject(err)
                        })
                    } else if (mode === 'reLaunch') {
                        uni.reLaunch({
                            url,
                            success: () => resolve({
                                success: true
                            }),
                            fail: (err) => reject(err)
                        })
                    } else {
                        uni.navigateTo({
                            url,
                            success: () => resolve({
                                success: true
                            }),
                            fail: (err) => reject(err)
                        });
                    }

                    break;
                case 'external':
                    try {
                        openExternalUrl(url);
                        resolve({
                            success: true
                        });
                    } catch (e) {
                        console.error(e)
                        reject(e);
                    }
                    break;
                default:
                    uni.showModal({
                        title: '提示',
                        content: '无效的跳转类型',
                        showCancel: false
                    });
                    reject(new Error('无效的跳转类型'));
            }
        });
    }
}

const navigate = {
    to() {
        return new NavigatorToBuilder()
    },
    back() {
        return new Promise((resolve, reject) => {
            uni.navigateBack({
                success: () => resolve(),
                fail: (err) => reject(err)
            });
        })
    }
}

const reload = () => {
    // 获取当前页面栈
    const pages = getCurrentPages()
    const curPage = pages[pages.length - 1]

    // 获取当前页面的路径和参数
    const route = curPage.route
    const options = curPage.options || {} // 页面参数

    // 构建 query 参数字符串
    const query = Object.keys(options)
        .map(key => `${key}=${encodeURIComponent(options[key])}`)
        .join('&')

    console.log('refresh', route, query)
    return new Promise((resolve, reject) => {
        uni.redirectTo({
            url: `/${route}${query ? '?' + query : ''}`,
            success: () => {
                resolve({
                    success: true
                })
            },
            fail: () => {
                reject(new Error('刷新失败'))
            }
        })
    })
}

export default {
    navigate,
    reload
}