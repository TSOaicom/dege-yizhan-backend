/**
 * toast 提示
 */
class ToastBuilder {
    constructor() {
        this.__param = {}

        // 使用微任务延迟执行，让链式调用先完成
        Promise.resolve().then(() => {
            if (!this.__promise && !this.__autoExecuteScheduled && Object.keys(this.__param).length > 0) {
                this.__autoExecuteScheduled = true;
                this.__promise = this.__toast();
            }
        });
    }

    message(message) {
        this.__param.message = message
        return this;
    }

    duration(duration) {
        this.__param.duration = duration
        return this;
    }

    level(level) {
        this.__param.level = level
        return this;
    }



    __toast() {
        const {message = '完成', duration = 3000, level = 'none'} = this.__param;
        const result = {
            success: true,
            message: "成功"
        };
        return new Promise((resolve, reject) => {
            uni.showToast({
                title: message,
                icon: level,
                duration: duration,
                success: () => resolve(result),
                fail: (err) => reject(err)
            })
        })
    }

    then(resolve, reject) {
        // 如果还没有创建 promise，就创建并标记为手动执行
        if (!this.__promise) {
            this.__autoExecuteScheduled = true; // 阻止自动执行
            this.__promise = this.__toast();
        }
        return this.__promise.then(resolve, reject);
    }


    // 普通调用 - 立即执行
    show() {
        this.__toast();
        return this;
    }
}


class ModelBuilder {

    constructor() {
        this.__param = {};
        this.__promise = null;
        this.__autoExecuteScheduled = false;

        // 使用微任务延迟执行，让链式调用先完成
        Promise.resolve().then(() => {
            if (!this.__promise && !this.__autoExecuteScheduled && Object.keys(this.__param).length > 0) {
                this.__autoExecuteScheduled = true;
                this.__promise = this.__execute();
            }
        });
    }

    title(title) {
        this.__param.title = title
        return this
    }

    message(message) {
        this.__param.message = message
        return this
    }

    confirmText(confirmText) {
        this.__param.confirmText = confirmText
        return this
    }

    cancelText(cancelText) {
        this.__param.cancelText = cancelText
        return this
    }

    __execute() {
        const {
            title,
            message,
            confirmText = '确认',
            cancelText = '取消'
        } = this.__param;

        return new Promise((resolve, reject) => {
            uni.showModal({
                title,
                content: message,
                confirmText: confirmText,
                cancelText: cancelText,
                success: (res) => {
                    console.log(res)
                    if (res.confirm) {
                        res['success'] = true
                        resolve(res);
                    } else {
                        res['success'] = false
                        resolve(res);
                    }
                }, fail: (err) => reject(err)
            });
        });
    }

    then(onFulfilled, onRejected) {
        // 如果还没有创建 promise，就创建并标记为手动执行
        if (!this.__promise) {
            this.__autoExecuteScheduled = true; // 阻止自动执行
            this.__promise = this.__execute();
        }
        return this.__promise.then(onFulfilled, onRejected);
    }
}


export default {
    toast() {
        return new ToastBuilder()
    },
    model() {
        return new ModelBuilder()
    },
    loading: {
        showLoading(title='加载中') {
            uni.showLoading({
                title: title || '加载中',
            })
        },
        hideLoading() {
            uni.hideLoading()
        }
    }
}