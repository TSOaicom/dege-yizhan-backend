import service, { buildHeaders, buildUrl, handleUnauthorized } from "@/utils/request";

const BASE_API = "/api/data";

export default {
    downloadExcelTemplate(table_name, filename = "Excel模版.xlsx") {
        const url = `/${table_name}/downloadTemplate`
        // #ifdef H5
        return service({
            url: url,
            method: 'get',
            responseType: 'arraybuffer',
        }).then(data => {
            // data 为 ArrayBuffer，转为 Blob 下载
            const blob = new Blob([data], {
                type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
            })
            this.downloadByBlob(blob, filename)
        }).catch(err => {
            console.error('下载模版失败', err)
            uni.showToast({ title: err?.message || '下载失败', icon: 'none' })
        })
        // #endif

        // #ifdef MP-WEIXIN || APP-PLUS
        uni.showLoading({title: '下载中...'})
        uni.downloadFile({
            url,
            success: (res) => {
                if (res.statusCode === 200) {
                    // 自动打开文档
                    uni.openDocument({
                        filePath: res.tempFilePath,
                        fileType: 'xlsx',
                        success: () => console.log('打开成功'),
                        fail: (err) => {
                            console.error('打开失败', err)
                            uni.showToast({title: '文件下载成功，但无法打开', icon: 'none'})
                        }
                    })
                } else {
                    uni.showToast({title: '下载失败', icon: 'none'})
                }
            },
            fail: (err) => {
                console.error('下载失败', err)
                uni.showToast({title: '下载失败', icon: 'none'})
            },
            complete: () => uni.hideLoading()
        })
        // #endif
    },
    import(tableName, formData) {
        const url = `/${tableName}/import`
        const importHeaders = () => {
            const headers = buildHeaders()
            delete headers['content-type']
            return headers
        }
        // #ifdef H5
        // uni.request 无法正确发送 FormData multipart，改用 fetch 确保 multipart 编码
        return fetch(buildUrl(url), { method: 'POST', headers: importHeaders(), body: formData })
            .then(r => r.json())
            .then(data => {
                if (data && data.code === 401) handleUnauthorized()
                return data
            })
        // #endif
        // #ifndef H5
        return service({ url, method: 'post', data: formData, headers: importHeaders() })
        // #endif
    },
    export(params) {
        const {table_name, param = {}} = params;
        // #ifdef H5
        return service({
            url: BASE_API + `/export?table=${table_name}`,
            method: 'post',
            data: param,
            responseType: 'arraybuffer'  // 必须用 arraybuffer 获取二进制，避免被当作 JSON 解析导致文件损坏
        }).then(response => {
            // response 为 ArrayBuffer，需转为 Blob 再下载
            const blob = new Blob([response], {
                type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
            })
            const link = document.createElement('a')
            link.href = URL.createObjectURL(blob)
            const uuid = Date.now() + '_' + Math.random().toString(36).slice(2)
            link.download = `${table_name}_${uuid}.xlsx`
            link.click()
            URL.revokeObjectURL(link.href)
        }).catch(err => {
            console.error('导出失败', err)
            uni.showToast({ title: err?.message || '导出失败', icon: 'none' })
        })
        // #endif


        // #ifdef MP-WEIXIN
        // 微信小程序环境
        return new Promise((resolve, reject) => {
            uni.downloadFile({
                url: BASE_API + `/export?table=${table_name}`,
                formData: param,
                success: (res) => {
                    if (res.statusCode === 200) {
                        // 自动打开文件
                        uni.openDocument({
                            filePath: res.tempFilePath,
                            success: () => {
                                console.log('打开文档成功')
                                resolve()
                            },
                            fail: reject
                        })
                    } else {
                        reject('下载失败')
                    }
                },
                fail: reject
            })
        })
        // #endif

        // #ifdef APP-PLUS
        // App 原生环境（安卓/IOS）
        return new Promise((resolve, reject) => {
            const downloadTask = uni.downloadFile({
                url: BASE_API + `/export?table=${table_name}`,
                formData: param,
                success: (res) => {
                    if (res.statusCode === 200) {
                        uni.openDocument({
                            filePath: res.tempFilePath,
                            success: () => {
                                console.log('打开文档成功')
                                resolve()
                            },
                            fail: reject
                        })
                    } else {
                        reject('下载失败')
                    }
                },
                fail: reject
            })
        })
        // #endif
    },
    blobValidate(data) {
        // 验证是否为blob格式
        return data.type !== 'application/json'
    },
    downloadByBlob(blob, filename) {
        const url = window.URL.createObjectURL(blob)
        const link = document.createElement('a')
        link.href = url
        link.download = filename || '下载文件.xlsx'
        document.body.appendChild(link)
        link.click()
        document.body.removeChild(link)
        window.URL.revokeObjectURL(url)
    }

}