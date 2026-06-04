import {Html5Qrcode} from "html5-qrcode";





class QrCodeScanner {
    constructor() {
        this.param = {}
    }

    scanType(type) {
        this.param.scanType = type
        return this
    }

    onlyFromCamera(enable) {
        this.param.onlyFromCamera = enable
        return this
    }

    then(resolve, reject) {
        return this.__execute().then(resolve, reject)
    }


    __execute = () => {
        const {onlyFromCamera = false, scanType = ['qrCode']} = this.param
        return new Promise((resolve, reject) => {
            // #ifdef MP-WEIXIN || APP-PLUS
            uni.scanCode({
                onlyFromCamera,
                scanType,
                success: (res) => resolve({
                    success: true,
                    result: res.result
                }),
                fail: (err) => reject(err)
            });
            // #endif

            // #ifdef H5
            // uni.showLoading()
            // 创建隐藏的容器（即使不用摄像头扫描也需要）
            const containerId = 'html5qr-container-hidden';
            let container = document.getElementById(containerId);
            if (!container) {
                container = document.createElement('view');
                container.id = containerId;
                container.style.display = 'none'; // 隐藏容器
                document.body.appendChild(container);
            }

            const input = document.createElement('input');
            input.type = 'file';
            input.accept = 'image/*';


            // 处理文件选择
            input.onchange = async (e) => {
                uni.showLoading()
                const file = e.target.files[0];
                if (!file) {
                    reject(new Error('未选择文件'));
                    return;
                }

                try {
                    // 使用 html5-qrcode 解析二维码
                    const html5QrCode = new Html5Qrcode(containerId);
                    const result = await html5QrCode.scanFile(file, true);
                    resolve({
                        success: true,
                        result: result
                    });
                } catch (err) {
                    reject(new Error(`二维码解析失败: ${err}`));
                } finally {
                    uni.hideLoading()
                }
            };

            // 主动触发文件选择
            input.click();
            // #endif
        });
    }

}





class Share {

    constructor() {
        this.param = {}
        this._promise = null; // 缓存 Promise 实例
    }

    type(type) {
        this.param.type = type
        return this
    }

    text(text) {
        this.param.text = text
        return this
    }

    then(resolve, reject) {

        return this.__execute().then(resolve, reject);
    }



    // 确保 Promise 已创建
    _ensurePromise() {
        if (!this._promise) {
            this._promise = this.__execute();
        }
    }

    __execute = () => {
        const {
            type = 'page',
            text = '',   // 可带占位符的文本
        } = this.param;

        return new Promise((resolve, reject) => {
            // #ifdef H5
            if (type === 'page') {
                const currentUrl = window.location.href;
                // 占位符替换
                // 示例：text = "点击这里访问：{url}"
                const finalText = text
                    ? text.replace(/\{url\}/g, currentUrl)
                    : currentUrl;
                uni.setClipboardData({
                    data: finalText,
                    success: () => {
                        resolve({
                            "shareLink": currentUrl,
                            "shareText": finalText
                        }); // 成功时返回复制的链接
                    },
                    fail: (err) => {
                        console.error('复制失败:', err);
                        reject(err); // 失败时返回错误
                    }
                });
            } else if (type === 'linkOrText') {
                uni.setClipboardData({
                    data: text,
                    success: () => {
                        resolve(); // 成功时返回复制的链接
                    },
                    fail: (err) => {
                        console.error('复制失败:', err);
                        reject(err); // 失败时返回错误
                    }
                });
            } else {
                reject(new Error('暂不支持的分享类型'));
            }
            // #endif

            // #ifdef MP-WEIXIN
            let finalText = text;
            if (type === 'page') {
                finalText = text
                    ? text.replace(/\{url\}/g, "【微信小程序环境中暂不支持获取当前页面路径,请点击待分享页面的右上角进行分享】")
                    : text;
            }

            uni.setClipboardData({
                data: finalText,
                success: () => {
                    uni.showModal({
                        title: '分享提示',
                        content: '请点击右上角进行分享',
                        showCancel: false, // 如果只希望用户点击确定，可以隐藏取消按钮
                        confirmText: '知道了',
                        success: (res) => {
                            // 点击确认时 resolve
                            if (res.confirm) {
                                resolve();
                            } else {
                                // 如果有取消按钮，这里可以处理取消逻辑
                                resolve();
                            }
                        },
                        fail: (err) => {
                            // 弹窗调用失败也 resolve，避免阻塞
                            resolve();
                        }
                    });
                },
                fail: (err) => {
                    console.error('复制失败:', err);
                    uni.showModal({
                        title: '分享提示',
                        content: '请点击右上角进行分享',
                        showCancel: false, // 如果只希望用户点击确定，可以隐藏取消按钮
                        confirmText: '知道了',
                        success: (res) => {
                            // 点击确认时 resolve
                            if (res.confirm) {
                                resolve();
                            } else {
                                // 如果有取消按钮，这里可以处理取消逻辑
                                resolve();
                            }
                        },
                        fail: (err) => {
                            // 弹窗调用失败也 resolve，避免阻塞
                            resolve();
                        }
                    });

                }
            });
            // #endif

            //   #ifdef APP-PLUS || APP-HARMONY
            uni.setClipboardData({
                data: text,
                success: () => {
                    uni.showModal({
                        title: '提示',
                        content: 'App端暂时不支持分享功能～',
                        showCancel: false, // 只显示“确定”按钮
                        confirmText: '知道了',
                        success: (res) => {
                            // 用户点击确认，reject Promise
                            reject(new Error('App端暂时不支持分享功能'));
                        },
                        fail: (err) => {
                            // 弹窗调用失败，也 reject
                            reject(err);
                        }
                    });
                },
                fail: (err) => {
                    console.log(err)
                    uni.showModal({
                        title: '提示',
                        content: 'App端暂时不支持分享功能～',
                        showCancel: false, // 只显示“确定”按钮
                        confirmText: '知道了',
                        success: (res) => {
                            // 用户点击确认，reject Promise
                            reject(new Error('App端暂时不支持分享功能'));
                        },
                        fail: (err) => {
                            // 弹窗调用失败，也 reject
                            reject(err);
                        }
                    });
                }
            });
            // #endif
        });
    };

}

const shareApi = () => {
    return new Share();
}

const qr = {
    scanCode() {
        return new QrCodeScanner()
    }
}


export default {
    qr,
    shareApi
}