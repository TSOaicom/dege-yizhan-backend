/**
 * 音频录音服务 API
 * 封装浏览器的 MediaRecorder API，提供简单易用的录音功能
 */

// #ifdef MP || APP-ANDROID || APP
const BASE_UPLOAD_URL = import.meta.env.VITE_APP_SERVICE_API === '/' ? "/common/upload" : import.meta.env.VITE_APP_SERVICE_API + "/common/upload";
// #endif

// #ifdef H5
const BASE_UPLOAD_URL = import.meta.env.VITE_APP_BASE_API === '/' ? "/common/upload" : import.meta.env.VITE_APP_BASE_API + "/common/upload";

// #endif

class AudioRecorderService {
    constructor() {
        this.recorder = null
        this.stream = null
        this.timer = null
        this.audioContext = null
        this.analyser = null
        this.dataArray = null
        this.animationId = null
        this.audioElement = null

        // 事件监听器
        this.listeners = {
            onStart: [],
            onStop: [],
            onError: [],
            onTimeUpdate: [],
            onVisualize: []
        }

        // 配置
        this.config = {
            echoCancellation: true,
            noiseSuppression: true,
            autoGainControl: true,
            audioFormat: 'audio/webm',
            fftSize: 256,
            visualizationBars: 20
        }
    }

    /**
     * 添加事件监听器
     * @param {string} event - 事件名称 (onStart, onStop, onError, onTimeUpdate, onVisualize)
     * @param {Function} callback - 回调函数
     */
    on(event, callback) {
        if (this.listeners[event]) {
            this.listeners[event].push(callback)
        }
    }

    /**
     * 移除事件监听器
     * @param {string} event - 事件名称
     * @param {Function} callback - 要移除的回调函数
     */
    off(event, callback) {
        if (this.listeners[event]) {
            this.listeners[event] = this.listeners[event].filter(cb => cb !== callback)
        }
    }

    /**
     * 触发事件
     * @param {string} event - 事件名称
     * @param {*} data - 事件数据
     */
    emit(event, data) {
        if (this.listeners[event]) {
            this.listeners[event].forEach(callback => callback(data))
        }
    }

    /**
     * 初始化音频上下文，用于音频可视化
     */
    initAudioContext() {
        if (!this.audioContext) {
            this.audioContext = new (window.AudioContext || window.webkitAudioContext)()
            this.analyser = this.audioContext.createAnalyser()
            this.analyser.fftSize = this.config.fftSize
            const bufferLength = this.analyser.frequencyBinCount
            this.dataArray = new Uint8Array(bufferLength)
        }
    }

    /**
     * 更新音频可视化数据
     */
    updateVisualization() {
        if (!this.analyser) return

        this.analyser.getByteFrequencyData(this.dataArray)

        // 提取前 N 个频率频段用于可视化
        const bars = []
        const {visualizationBars} = this.config
        for (let i = 0; i < visualizationBars; i++) {
            const value = this.dataArray[i]
            const percentage = Math.min(100, (value / 255) * 100)
            bars.push(percentage)
        }

        this.emit('onVisualize', bars)

        if (this.isRecording()) {
            this.animationId = requestAnimationFrame(() => this.updateVisualization())
        }
    }

    /**
     * 开始录音
     * @returns {Promise<void>}
     */
    async start() {
        try {
            // 请求麦克风权限
            this.stream = await navigator.mediaDevices.getUserMedia({
                audio: {
                    echoCancellation: this.config.echoCancellation,
                    noiseSuppression: this.config.noiseSuppression,
                    autoGainControl: this.config.autoGainControl
                }
            })

            // 初始化音频上下文
            this.initAudioContext()

            // 创建录音器
            const mediaRecorder = new MediaRecorder(this.stream, {
                mimeType: 'audio/webm;codecs=opus'
            })

            const chunks = []
            mediaRecorder.ondataavailable = (e) => {
                if (e.data.size > 0) {
                    chunks.push(e.data)
                }
            }

            mediaRecorder.onstop = () => {
                // 创建音频 Blob
                const blob = new Blob(chunks, {type: this.config.audioFormat})
                const url = URL.createObjectURL(blob)

                // 清理流
                this.stream.getTracks().forEach(track => track.stop())
                this.stream = null

                // 停止定时器和可视化
                if (this.timer) {
                    clearInterval(this.timer)
                    this.timer = null
                }
                if (this.animationId) {
                    cancelAnimationFrame(this.animationId)
                    this.animationId = null
                }

                // 触发停止事件
                this.emit('onStop', {blob, url})
            }

            // 开始录音
            mediaRecorder.start()
            this.recorder = mediaRecorder

            // 连接流到分析器用于可视化
            const source = this.audioContext.createMediaStreamSource(this.stream)
            source.connect(this.analyser)

            // 开始定时器
            let recordingTime = 0
            this.timer = setInterval(() => {
                recordingTime++
                this.emit('onTimeUpdate', recordingTime)
            }, 1000)

            // 开始可视化
            this.updateVisualization()

            // 触发开始事件
            this.emit('onStart', {startTime: Date.now()})

        } catch (error) {
            console.error('Error starting recording:', error)
            this.emit('onError', {
                error: error,
                message: '无法访问麦克风'
            })
            throw error
        }
    }

    /**
     * 停止录音
     */
    stop() {
        if (this.recorder && this.isRecording()) {
            this.recorder.stop()
            this.recorder = null
        }
    }

    /**
     * 检查是否正在录音
     * @returns {boolean}
     */
    isRecording() {
        return this.recorder && this.recorder.state === 'recording'
    }

    /**
     * 播放音频
     * @param {string} audioUrl - 音频 URL
     * @returns {Promise<void>}
     */
    async play(audioUrl) {
        if (!audioUrl) {
            throw new Error('无效的音频 URL')
        }

        if (!this.audioElement || this.audioElement.src !== audioUrl) {
            this.audioElement = new Audio(audioUrl)
        }

        try {
            await this.audioElement.play()
        } catch (error) {
            console.error('Error playing audio:', error)
            throw error
        }
    }

    /**
     * 暂停播放
     */
    pause() {
        if (this.audioElement) {
            this.audioElement.pause()
        }
    }

    /**
     * 停止播放
     */
    stopPlay() {
        if (this.audioElement) {
            this.audioElement.pause()
            this.audioElement.currentTime = 0
        }
    }

    /**
     * 检查是否正在播放
     * @returns {boolean}
     */
    isPlaying() {
        return this.audioElement && !this.audioElement.paused
    }

    upload(params = {}) {
        return new Promise((resolve, reject) => {
            const {blob} = params;
            console.log('Blob type:', blob.type);
            console.log('Blob size:', blob.size);

            // 检查是否为有效的音频 Blob
            if (!blob || blob.size === 0) {
                reject(new Error('无效的音频数据'));
                return;
            }

            const isWebm = blob.type === 'audio/webm' || blob.type.includes('webm');
            const isOgg = blob.type === 'audio/ogg';

            // 根据实际的 blob 类型选择正确的扩展名
            let ext = 'wav';
            if (isWebm) ext = 'webm';
            if (isOgg) ext = 'ogg';

            const fileName = `record-${Date.now()}.${ext}`;
            console.log('File name:', fileName);

            fetch(BASE_UPLOAD_URL, {
                method: 'POST',
                headers: {
                    "APP_ID": import.meta.env.VITE_APP_ID,
                    "APP_TYPE": import.meta.env.VITE_APP_TYPE,
                },
                body: (() => {
                    const formData = new FormData();
                    formData.append('file', blob, fileName);
                    return formData;
                })()
            })
                .then(response => response.json())
                .then(result => {
                    console.log('Upload result:', result);
                    resolve(result);
                })
                .catch(error => {
                    console.error('Upload error:', error);
                    reject(error);
                });

        });
    }

    /**
     * 下载音频文件
     * @param {Blob} audioBlob - 音频 Blob 对象
     * @param {string} filename - 文件名 (默认: 'recording.wav')
     */
    download(audioBlob, filename = 'recording.wav') {
        const url = URL.createObjectURL(audioBlob)
        const a = document.createElement('a')
        a.href = url
        a.download = filename
        document.body.appendChild(a)
        a.click()
        document.body.removeChild(a)
        URL.revokeObjectURL(url)
    }

    /**
     * 清理资源
     */
    destroy() {
        // 停止录音
        if (this.isRecording()) {
            this.stop()
        }

        // 停止播放
        if (this.audioElement) {
            this.audioElement.pause()
            this.audioElement = null
        }

        // 清理定时器和动画
        if (this.timer) {
            clearInterval(this.timer)
            this.timer = null
        }
        if (this.animationId) {
            cancelAnimationFrame(this.animationId)
            this.animationId = null
        }

        // 清理流
        if (this.stream) {
            this.stream.getTracks().forEach(track => track.stop())
            this.stream = null
        }

        // 清理音频上下文
        if (this.audioContext) {
            this.audioContext.close()
            this.audioContext = null
            this.analyser = null
            this.dataArray = null
        }

        // 清空事件监听器
        Object.keys(this.listeners).forEach(key => {
            this.listeners[key] = []
        })
    }

}

class AudioRecorderServiceByWeApp {
    constructor() {
        this.recorderManager = uni.getRecorderManager();
        this.innerAudioContext = null

        this.recordingTime = 0;

        // 事件监听器
        this.listeners = {
            onStart: [],
            onStop: [],
            onError: [],
            onTimeUpdate: [],
            onVisualize: []
        }

        this.config = {
            duration: 60000,
            sampleRate: 16000,
            numberOfChannels: 1,
            encodeBitRate: 96000,
            format: 'mp3'
        }

        this._bindEvents();
    }

    on(event, callback) {
        if (this.listeners[event]) {
            this.listeners[event].push(callback)
        }
    }

    off(event, callback) {
        if (this.listeners[event]) {
            this.listeners[event] = this.listeners[event].filter(cb => cb !== callback)
        }
    }

    emit(event, data) {
        if (this.listeners[event]) {
            this.listeners[event].forEach(callback => callback(data))
        }
    }

    _bindEvents() {
        this.recorderManager.onStart(() => {
            this.emit('onStart', {startTime: Date.now()})
        })

        this.recorderManager.onStop((res) => {
            this._clearTimer()

            this.emit('onStop', {
                tempFilePath: res.tempFilePath,
                url: res.tempFilePath,
                duration: this.recordingTime
            })
        })

        this.recorderManager.onError((err) => {
            this.emit('onError', err)
        })
    }

    _startTimer() {
        this.timer = setInterval(() => {
            this.recordingTime++
            this.emit('onTimeUpdate', this.recordingTime)
        }, 1000)
    }

    _clearTimer() {
        if (this.timer) {
            clearInterval(this.timer)
            this.timer = null
        }
    }

    start(options = {}) {

        this._clearTimer();

        this.recordingTime = 0

        this.recorderManager.start({
            duration: this.config.duration,
            sampleRate: this.config.sampleRate,
            numberOfChannels: this.config.numberOfChannels,
            encodeBitRate: this.config.encodeBitRate,
            format: this.config.format
        })

        this._startTimer();
    }

    stop() {
        this.recorderManager.stop()
    }

    isRecording() {
        return !!this.timer
    }

    upload(params = {}) {
        return new Promise((resolve, reject) => {
            const {url} = params;
            uni.uploadFile({
                url: BASE_UPLOAD_URL,
                filePath: url,
                name: 'file',
                header: {
                    "APP_ID": import.meta.env.VITE_APP_ID,
                    "APP_TYPE": import.meta.env.VITE_APP_TYPE,
                },
                success: (res) => {
                    try {
                        const result = JSON.parse(res.data);
                        console.log('Upload result:', result);
                        resolve(result);
                    } catch (err) {
                        console.error('Parse error:', err);
                        reject(err);
                    }
                },
                fail: (err) => {
                    console.error('Upload error:', err);
                    reject(err);
                }
            });
        });
    }

    play(audioUrl) {
        if (!audioUrl) throw new Error('invalid audio url')

        if (!this.innerAudioContext) {
            this.innerAudioContext = uni.createInnerAudioContext()
        }

        this.innerAudioContext.src = audioUrl
        this.innerAudioContext.play()
    }

    pause() {
        this.innerAudioContext?.pause()
    }

    stopPlay() {
        if (this.innerAudioContext) {
            this.innerAudioContext.stop()
        }
    }

    isPlaying() {
        return this.innerAudioContext && !this.innerAudioContext.paused
    }

    destroy() {
        this._clearTimer();

        this.stopPlay()
        this.innerAudioContext?.destroy()
        this.innerAudioContext = null
    }

}

// 导出类，允许创建多个实例
export default {
    recorder: () => {
        // #ifdef MP || APP-ANDROID || APP
        console.log(1111)
        return new AudioRecorderServiceByWeApp();
        // #endif

        // #ifdef H5
        console.log(2222)
        return new AudioRecorderService();
        // #endif
    }
}