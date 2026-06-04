<template>
  <view class="chat-container">

    <view class="chat-header">
      <view class="header-title">AI应用助手</view>
      <view class="header-actions">
        <text class="icon-btn" @click="onClearChat">清空</text>
      </view>
    </view>

    <scroll-view
        class="message-list"
        scroll-y
        scroll-with-animation
        :scroll-into-view=scrollIntoView
        :style="{height: listHeight + 'px'}"
    >

      <view v-if="messages.length === 0" class="welcome-tip">
        <text class="welcome-text">你好！我是AI助手，有什么可以帮你的吗？</text>
      </view>


      <view
          v-for="(msg, index) in messages"
          :key="msg.id"
          :id="'msg-' + index"
          class="message-item"
          :class="msg.role"
      >

        <template v-if="msg.role === 'user'">
          <view class="avatar user-avatar">
            <text class="avatar-text">我</text>
          </view>
          <!-- 消息内容 -->
          <view class="message-content">
            <view class="message-bubble">
              <text class="message-text">{{ msg.content }}</text>
              <view v-if="msg.loading" class="loading-dots">
                <text class="dot"></text>
                <text class="dot"></text>
                <text class="dot"></text>
              </view>
            </view>
            <view class="message-time">{{ msg.time }}</view>
          </view>

        </template>


        <template v-else-if="msg.role === 'assistant'">
          <view class="avatar assistant-avatar">
            <text class="avatar-text">AI</text>
          </view>

          <view class="message-content">
            <view class="message-bubble">
              <!-- HTML类型消息 -->
              <view v-if="msg.type === 'html' && msg.htmlData" class="html-message"
                    @click="openHtmlModal(msg.htmlData)">
                <view class="html-title">{{ msg.htmlData.title }}</view>
                <view class="html-hint">点击查看详情</view>
              </view>
              <!-- 普通Markdown消息 -->
              <base-markdown-render v-else :content="msg.content"/>
              <view v-if="msg.loading" class="loading-dots">
                <text class="dot"></text>
                <text class="dot"></text>
                <text class="dot"></text>
              </view>
            </view>
            <view class="message-time">{{ msg.time }}</view>
          </view>
        </template>
      </view>
    </scroll-view>

    <view class="input-area">
      <view class="input-wrapper">
        <textarea
            class="message-input"
            placeholder="请输入消息..."
            v-model="inputText"
            :disabled="isSending"
            maxlength="500"
            auto-height
            confirm-type="send"
            @confirm="onSendMessage"
        />
        <view v-show="inputText.length > 0" class="char-count">{{ inputText.length }}/500</view>
      </view>
      <button
          class="send-btn"
          :disabled="!inputText.trim() || isSending"
          @click="onSendMessage"
      >
        {{ isSending ? '发送中...' : '发送' }}
      </button>
    </view>

    <!-- HTML弹窗 -->
    <view v-if="showHtmlModal" class="html-modal" @click="closeHtmlModal">
      <view class="modal-content" @click.stop>
        <view class="modal-header">
          <view class="modal-title">{{ currentHtmlData.title }}</view>
          <view class="modal-close" @click="closeHtmlModal">✕</view>
        </view>
        <view class="modal-body">
          <base-iframe :htmlContent="currentHtmlData.html"/>
        </view>
      </view>
    </view>
  </view>
</template>

<script setup>

const {proxy} = getCurrentInstance()

const scrollIntoView = ref('')

const messages = ref([])
const inputText = ref('')
const isSending = ref(false)
const listHeight = ref(0)

const conversation_id = ref('')

// HTML弹窗相关
const showHtmlModal = ref(false)
const currentHtmlData = ref({title: '', html: ''})


const calculateListHeight = () => {
  const systemInfo = uni.getSystemInfoSync()
  listHeight.value = systemInfo.windowHeight - 100 - 200 // 头部100rpx，输入框200rpx
}

const formatMessageTime = () => {
  const now = new Date()
  const hours = String(now.getHours()).padStart(2, '0')
  const minutes = String(now.getMinutes()).padStart(2, '0')
  return `${hours}:${minutes}`
}


const onSendMessage = async () => {
  if (!inputText.value.trim() || isSending.value) return

  const userMessage = {
    id: Date.now(),
    role: 'user',
    content: inputText.value.trim(),
    time: formatMessageTime()
  }


  messages.value.push(userMessage)
  inputText.value = ''

  // 添加正在加载的助手消息
  const loadingMessage = {
    id: Date.now() + 1,
    role: 'assistant',
    content: '',
    time: formatMessageTime(),
    loading: true
  }
  messages.value.push(loadingMessage)
  isSending.value = true

  // 滚动到底部
  scrollToBottom()

  try {
    // 模拟AI回复（这里可以替换为实际的API调用）
    let res = await proxy.$app.chat.chat().conversation_id(conversation_id.value).text(userMessage.content);
    const lastMessage = messages.value[messages.value.length - 1]

    if (res.success) {
      conversation_id.value = res.data.conversationId;

      // 检查返回类型是否为html
      if (res.data.type === 'html') {
        try {
          // content是JSON字符串，需要解析
          const htmlData = typeof res.data.answer === 'string'
              ? JSON.parse(res.data.answer)
              : res.data.answer;

          lastMessage.type = 'html';
          lastMessage.htmlData = {
            title: htmlData.title || '未命名',
            html: htmlData.html || ''
          };
          lastMessage.content = htmlData.title || '点击查看HTML内容';
        } catch (e) {
          console.error('解析HTML数据失败:', e);
          lastMessage.content = '数据格式错误';
        }
      } else {
        // 普通markdown消息
        lastMessage.content = res.data.answer;
      }
    } else {
      lastMessage.content = "网络异常";
    }
    lastMessage.loading = false

    // 滚动到底部
    scrollToBottom()
  } catch (error) {
    console.error('发送消息失败:', error)
    // 显示错误消息
    const lastMessage = messages.value[messages.value.length - 1]
    lastMessage.content = '抱歉，消息发送失败，请稍后重试。'
    lastMessage.loading = false
  } finally {
    isSending.value = false
  }
}


// 清空聊天
const onClearChat = () => {
  uni.showModal({
    title: '确认清空',
    content: '确定要清空所有聊天记录吗？',
    success: (res) => {
      if (res.confirm) {
        messages.value = []
        conversation_id.value = ''
        uni.showToast({
          title: '已清空聊天记录',
          icon: 'success'
        })
      }
    }
  })
}

// 滚动到底部
const scrollToBottom = () => {
  nextTick(() => {
    const lastIndex = messages.value.length - 1
    if (lastIndex >= 0) {
      scrollIntoView.value = 'msg-' + lastIndex;
    }
  })
}

// 打开HTML弹窗
const openHtmlModal = (htmlData) => {
  currentHtmlData.value = htmlData
  showHtmlModal.value = true
}

// 关闭HTML弹窗
const closeHtmlModal = () => {
  showHtmlModal.value = false
}

// 初始化
onMounted(() => {
  calculateListHeight()

  // 监听窗口大小变化
  uni.onWindowResize(() => {
    calculateListHeight()
  })
})
</script>

<style lang="scss" scoped>

.chat-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
  background-color: #f5f5f5;
}

// 头部
.chat-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 30rpx;
  background-color: #ffffff;
  border-bottom: 1rpx solid #e0e0e0;
}

.header-title {
  font-size: 36rpx;
  font-weight: 600;
  color: #333;
}

.header-actions {
  .icon-btn {
    font-size: 28rpx;
    color: #1677ff;
    padding: 10rpx 20rpx;
  }
}

// 消息列表
.message-list {
  flex: 1;
  padding: 30rpx;
}

.welcome-tip {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 120rpx 40rpx;
  text-align: center;
}

.welcome-icon {
  font-size: 120rpx;
  margin-bottom: 30rpx;
}

.welcome-text {
  font-size: 30rpx;
  color: #666;
  line-height: 1.5;
}

.message-item {
  display: flex;
  margin-bottom: 40rpx;
  animation: fadeIn 0.3s ease-in-out;

  &.user {
    flex-direction: row-reverse;
  }
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(20rpx);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

// 头像
.avatar {
  width: 80rpx;
  height: 80rpx;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}

.user-avatar {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  margin-left: 20rpx;
}

.assistant-avatar {
  background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
  margin-right: 20rpx;
}

.avatar-text {
  color: #ffffff;
  font-size: 28rpx;
  font-weight: 600;
}

// 消息内容区域
.message-content {
  max-width: 480rpx;
  display: flex;
  flex-direction: column;

  .message-item.user & {
    align-items: flex-end;
  }
}

// 消息气泡
.message-bubble {
  padding: 24rpx 30rpx;
  border-radius: 18rpx;
  position: relative;
  background-color: #ffffff;
  border-bottom-left-radius: 6rpx;
  box-shadow: 0 2rpx 8rpx rgba(0, 0, 0, 0.06);

  .message-item.user & {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    border-bottom-left-radius: 18rpx;
    border-bottom-right-radius: 6rpx;
  }

  .message-item.assistant & {
    :deep(.markdown-content) {
      p {
        margin: 8rpx 0;
        line-height: 1.6;
        font-size: 28rpx;
      }

      h1, h2, h3, h4, h5, h6 {
        font-weight: 600;
        margin: 16rpx 0 8rpx 0;
        line-height: 1.4;
      }

      h1 {
        font-size: 36rpx;
      }

      h2 {
        font-size: 32rpx;
      }

      h3 {
        font-size: 30rpx;
      }

      code {
        background: #f5f5f5;
        padding: 4rpx 12rpx;
        border-radius: 6rpx;
        font-family: 'Monaco', 'Menlo', 'Consolas', monospace;
        font-size: 24rpx;
        color: #d73a49;
      }

      pre {
        background: #f6f8fa;
        padding: 20rpx;
        border-radius: 12rpx;
        margin: 12rpx 0;
        overflow-x: auto;
      }

      pre code {
        background: transparent;
        padding: 0;
        color: #333;
      }

      blockquote {
        border-left: 8rpx solid #1677ff;
        margin: 12rpx 0;
        padding: 8rpx 16rpx;
        background: #f0f8ff;
      }

      ul, ol {
        padding-left: 32rpx;
      }

      ul li, ol li {
        margin: 4rpx 0;
        line-height: 1.6;
      }

      a {
        color: #1677ff;
        with: underline;
      }
    }
  }
}

.message-text {
  font-size: 30rpx;
  line-height: 1.6;
  word-break: break-word;

  .message-item.user & {
    color: #ffffff;
  }

  .message-item.assistant & {
    color: #333;
  }
}

// 加载动画
.loading-dots {
  display: inline-flex;
  align-items: center;
  margin-left: 12rpx;

  .dot {
    width: 12rpx;
    height: 12rpx;
    border-radius: 50%;
    background-color: #999;
    margin: 0 4rpx;
    animation: bounce 1.4s infinite ease-in-out both;

    &:nth-child(1) {
      animation-delay: -0.32s;
    }

    &:nth-child(2) {
      animation-delay: -0.16s;
    }
  }
}

@keyframes bounce {
  0%, 80%, 100% {
    transform: scale(0);
  }
  40% {
    transform: scale(1);
  }
}

// 消息时间
.message-time {
  font-size: 22rpx;
  color: #999;
  margin-top: 10rpx;
  padding: 0 10rpx;
}

// 输入区域
.input-area {
  display: flex;
  align-items: flex-end;
  padding: 20rpx 30rpx;
  background-color: #ffffff;
  border-top: 1rpx solid #e0e0e0;
  padding-bottom: calc(20rpx + env(safe-area-inset-bottom));
}

.input-wrapper {
  flex: 1;
  position: relative;
  margin-right: 20rpx;
}

.message-input {
  width: 100%;
  min-height: 80rpx;
  max-height: 200rpx;
  padding: 20rpx 24rpx;
  background-color: #f5f5f5;
  border-radius: 24rpx;
  font-size: 30rpx;
  line-height: 1.5;
  box-sizing: border-box;
}

.char-count {
  position: absolute;
  right: 20rpx;
  bottom: 10rpx;
  font-size: 22rpx;
  color: #999;
}

.send-btn {
  width: 140rpx;
  height: 80rpx;
  line-height: 80rpx;
  text-align: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: #ffffff;
  border-radius: 24rpx;
  font-size: 28rpx;
  border: none;
  padding: 0;

  &:disabled {
    opacity: 0.5;
  }
}

// HTML消息样式
.html-message {
  cursor: pointer;
  padding: 20rpx;
  border: 2rpx solid #1677ff;
  border-radius: 12rpx;
  background: linear-gradient(135deg, #e8f4ff 0%, #f0f8ff 100%);
  transition: all 0.3s ease;

  &:active {
    opacity: 0.8;
    transform: scale(0.98);
  }
}

.html-title {
  font-size: 30rpx;
  font-weight: 600;
  color: #333;
  margin-bottom: 8rpx;
}

.html-hint {
  font-size: 24rpx;
  color: #1677ff;
  display: flex;
  align-items: center;

  &::after {
    content: ' >';
    margin-left: 4rpx;
  }
}

// HTML弹窗样式
.html-modal {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.6);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 9999;
  padding: 40rpx;
  animation: fadeIn 0.3s ease;
}

.modal-content {
  width: 100%;
  max-width: 1200rpx;
  height: 80vh;
  background-color: #ffffff;
  border-radius: 24rpx;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  animation: slideUp 0.3s ease;
}

@keyframes slideUp {
  from {
    opacity: 0;
    transform: translateY(60rpx);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 30rpx 40rpx;
  border-bottom: 1rpx solid #e0e0e0;
  background-color: #fafafa;
}

.modal-title {
  font-size: 32rpx;
  font-weight: 600;
  color: #333;
  flex: 1;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.modal-close {
  width: 60rpx;
  height: 60rpx;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 40rpx;
  color: #666;
  cursor: pointer;
  border-radius: 50%;
  transition: all 0.2s ease;

  &:active {
    background-color: #f0f0f0;
    color: #333;
  }
}

.modal-body {
  flex: 1;
  overflow: hidden;
  position: relative;
}
</style>