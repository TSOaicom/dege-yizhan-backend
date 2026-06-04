// #ifdef MP || APP-ANDROID || APP
const BASE_UPLOAD_URL = import.meta.env.VITE_APP_SERVICE_API === '/' ? "/common/upload" : import.meta.env.VITE_APP_SERVICE_API + "/common/upload";
// #endif

// #ifdef H5
const BASE_UPLOAD_URL = import.meta.env.VITE_APP_BASE_API === '/' ? "/common/upload" : import.meta.env.VITE_APP_BASE_API + "/common/upload";
// #endif

export default {
    /**
     * 下载并保存文件
     * @param param
     * @returns {Promise<unknown>}
     */
    downloadFile(param = {}) {
        uni.showLoading()
        return new Promise((resolve, reject) => {
            if (!param.url) {
                reject(new Error('请提供下载地址'));
            }
            // #ifdef MP-WEIXIN
            uni.downloadFile({
                url: param.url,         // 下载链接，必传
                header: param.header || {},
                timeout: param.timeout || 30000,
                success: (res) => {
                    if (res.statusCode === 200) {
                        // 下载文件
                        uni.saveFile({
                            tempFilePath: res.tempFilePath, // 传入 uni.downloadFile 返回的临时路径
                            success: (res) => {
                                // res.savedFilePath 是本地永久保存路径
                                resolve({
                                    success: true,
                                    // savedFilePath: res.savedFilePath
                                });
                            },
                            fail: (err) => {
                                reject(err);
                            }
                        });

                    } else {
                        reject(new Error(`下载失败，状态码：${res.statusCode}`));
                    }
                },
                fail: (err) => {
                    reject(err);
                }, complete: () => {
                    uni.hideLoading()
                }
            });
            // #endif


            // #ifdef APP-ANDROID || APP
            // uni.showToast({title: `进入安卓` + param.url, duration: 3000, icon: "none"});
            const downloadTask = uni.downloadFile({
                url: param.url, //仅为示例，并非真实的资源
                success: (res) => {
                    if (res.statusCode === 200) {
                        console.log('下载成功');
                    }
                    let that = this;
                    uni.saveFile({
                        tempFilePath: res.tempFilePath,
                        success: function (red) {
                            uni.openDocument({
                                filePath: red.savedFilePath,
                                success: (sus) => {
                                    console.log('成功打开')
                                }
                            })
                            console.log(red)
                        }
                    });
                }
            })
            downloadTask.onProgressUpdate((res) => {
                console.log('下载进度' + res.progress);
                console.log('已经下载的数据长度' + res.totalBytesWritten);
                console.log('预期需要下载的数据总长度' + res.totalBytesExpectedToWrite);
            })
            // #endif


            // #ifdef H5
            // 如果是一个普通链接（直接下载）
            try {
                const a = document.createElement('a');
                a.href = param.url;
                a.target = '_blank';
                a.click();
                resolve({success: true});
            } catch (e) {
                reject(new Error(e));
            } finally {
                uni.hideLoading()
            }
            // #endif

        });
    },

    upload(file) {
        return new Promise((resolve, reject) => {
            uni.uploadFile({
                url: BASE_UPLOAD_URL,
                filePath: file,
                name: "file",
                header: {
                    "APP_ID": import.meta.env.VITE_APP_ID,
                    "APP_TYPE": import.meta.env.VITE_APP_TYPE,
                },
                success: (res) => {
                    try {
                        const result = JSON.parse(res.data)
                        resolve(result)
                    } catch (err) {
                        reject(err)
                    }
                },
                fail: (err) => {
                    reject(err)
                }
            });
        });
    }
}