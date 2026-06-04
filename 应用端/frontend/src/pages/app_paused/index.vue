<template>
  <view class="app-paused-container">
    <image class="page-logo" src="/static/loading.svg" mode="aspectFit" />
    <view class="app-paused-card">
      <!-- Icon 组合 -->
      <view class="icon-wrapper">
        <!-- 外壳 vector-1 -->
        <image class="icon-shell" src="/static/vector-1.svg" mode="aspectFit" />
        <!-- 居中暂停符号 vector -->
        <image class="paused-image" src="/static/vector.svg" mode="aspectFit" />
        <!-- 底部装饰 vector-2 -->
        <image class="icon-bottom" src="/static/vector-2.svg" mode="aspectFit" />
      </view>

      <!-- 文字提示 -->
      <text class="paused-text">{{ $tt('app_paused.title') }}</text>

      <!-- 按钮区域 -->
      <view class="button-wrapper">
        <button class="btn-primary" @click="handleCopyLink">
          {{ $tt('app_paused.copy_link_text') }}
        </button>
        <button class="btn-secondary" :class="{ 'btn-disabled': remindDisabled }" @click="handleRemindCreator">
          {{ $tt('app_paused.remind_creator_text') }}
        </button>
      </view>
    </view>
  </view>
</template>

<script setup>
import { ref, onMounted, getCurrentInstance } from 'vue';

const { proxy } = getCurrentInstance();

// 重启链接
const restartLink = ref('');

// 提醒按钮限流：60s内最多1次
const remindDisabled = ref(false);

// 页面加载时获取重启链接
onMounted(async () => {
  await getRestartLink();
});

// 获取重启链接
const getRestartLink = async () => {
  try {
    const res = await proxy.$app.appStatus.getStatus();
    if (res.success && res.data && res.data.url) {
      restartLink.value = res.data.url;
    }
  } catch (err) {
    console.error('获取重启链接失败:', err);
  }
};

// 复制链接到剪贴板
const handleCopyLink = async () => {
  const link = restartLink.value;
  if (!link) {
    proxy.$app.ui.toast().message(proxy.$tt('app_paused.no_link_available')).show();
    return;
  }

  // #ifdef H5
  try {
    await navigator.clipboard.writeText(link);
    proxy.$app.ui.toast().message(proxy.$tt('app_paused.link_copied')).show();
  } catch (err) {
    // 降级方案
    const textarea = document.createElement('textarea');
    textarea.value = link;
    textarea.style.position = 'fixed';
    textarea.style.opacity = '0';
    document.body.appendChild(textarea);
    textarea.select();
    document.execCommand('copy');
    document.body.removeChild(textarea);
    proxy.$app.ui.toast().message(proxy.$tt('app_paused.link_copied')).show();
  }
  // #endif

  // #ifdef APP-PLUS
  uni.setClipboardData({
    data: link,
    success: () => {
      proxy.$app.ui.toast().message(proxy.$tt('app_paused.link_copied')).show();
    },
    fail: () => {
      proxy.$app.ui.toast().message(proxy.$tt('app_paused.copy_failed')).show();
    }
  });
  // #endif

  // #ifdef MP-WEIXIN
  uni.setClipboardData({
    data: link,
    success: () => {
      proxy.$app.ui.toast().message(proxy.$tt('app_paused.link_copied')).show();
    }
  });
  // #endif
};

// 提醒创建者重启应用（60s内最多1次）
const handleRemindCreator = async () => {
  if (remindDisabled.value) {
    proxy.$app.ui.toast().message(proxy.$tt('app_paused.remind_rate_limit')).show();
    return;
  }

  remindDisabled.value = true;
  setTimeout(() => {
    remindDisabled.value = false;
  }, 60000);

  try {
    const res = await proxy.$app.appStatus.sendCreator();
    if (res.success) {
      proxy.$app.ui.toast().message(proxy.$tt('app_paused.reminder_sent')).show();
    } else {
      proxy.$app.ui.toast().message(res.msg || proxy.$tt('app_paused.reminder_failed')).show();
    }
  } catch (err) {
    console.error('发送提醒失败:', err);
    proxy.$app.ui.toast().message(proxy.$tt('app_paused.reminder_failed')).show();
  }
};
</script>

<style lang="scss" scoped>
.app-paused-container {
  width: 100%;
  height: 100vh;
  background-color: #FEFDF9;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
}

.page-logo {
  position: absolute;
  top: 40px;
  left: 40px;
  width: 70px;
  height: 70px;
  z-index: 10;
}

.app-paused-card {
  width: 340px;
  height: 406px;
  background: #FFFFFF;
  border-radius: 15px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 30px 18px;
  box-sizing: border-box;
}

.icon-wrapper {
  position: relative;
  width: 127px;
  height: 127px;
  // margin-bottom: 30px;
}

.icon-shell {
  width: 127px;
  height: 127px;
  position: absolute;
  top: 0;
  left: 0;
  z-index: 2;
}

.paused-image {
  height: 54px;
  width: 45px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 3;
}

.icon-bottom {
  width: 117px;
  height: 37px;
  position: absolute;
  bottom: 10px;
  left: 50%;
  transform: translateX(-50%);
  z-index: 1;
}

.paused-text {
  font-family: 'Inter', sans-serif;
  font-style: normal;
  font-weight: 500;
  font-size: 32px;
  line-height: 39px;
  color: #000000;
  text-align: center;
  margin-bottom: 32px;
}

.button-wrapper {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 22px;
}

.btn-primary {
  width: 304px;
  height: 41px;
  background: #FEA92B;
  border-radius: 20px;
  font-family: 'Inter', sans-serif;
  font-style: normal;
  font-weight: 500;
  font-size: 16px;
  line-height: 41px;
  text-align: center;
  color: #000000;
  border: none;
  padding: 0;
  margin: 0;
}

.btn-secondary {
  width: 304px;
  height: 41px;
  background: #585B64;
  border-radius: 20px;
  font-family: 'Inter', sans-serif;
  font-style: normal;
  font-weight: 500;
  font-size: 16px;
  line-height: 41px;
  text-align: center;
  color: #FFFFFF;
  border: none;
  padding: 0;
  margin: 0;
}

.btn-primary:active {
  opacity: 0.8;
}

.btn-secondary:active {
  opacity: 0.8;
}

.btn-disabled {
  opacity: 0.5;
}
</style>
