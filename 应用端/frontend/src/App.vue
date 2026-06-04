<script setup>
import formatError from "./utils/format";
import storage from "@/config/storage";

const {proxy} = getCurrentInstance();

const globalFontSize = ref(0);

function setGlobalFontSize() {
  const fontSize = window.innerWidth / 100;
  const base = 3.5;
  const scale = 1.06;
  const size = fontSize * base * scale;
  globalFontSize.value = size;
  console.log('===========', window.innerWidth, fontSize, base, scale, size)
  document.documentElement.style.setProperty("font-size", `${size}px`, "important");
}

const mode = ref('');

onMounted(() => {
  setGlobalFontSize();
  window.addEventListener('resize', setGlobalFontSize);
  const systemInfo = uni.getSystemInfoSync();
  if (mode.value && mode.value === "preview") {
    document.body.classList.add('hide-nav')
  } else {
    if (systemInfo.deviceType !== 'pc') {
      document.body.classList.add('hide-nav')
    } else {
      if (typeof navigator !== "undefined") {
        const originalUA = navigator.userAgent;
        Object.defineProperty(navigator, 'userAgent', {
          value: originalUA + ' mobile',
          configurable: true
        })
      }
    }
  }
});

// #ifdef H5
const mo = new MutationObserver(() => {
  const currentSize = document.documentElement.style.fontSize;
  if (currentSize !== `${globalFontSize}px`) {
    document.documentElement.style.setProperty("font-size", `${globalFontSize.value}px`, "important");
    console.log('html style corrected:', currentSize, globalFontSize.value);
  }
});
mo.observe(document.documentElement, {attributes: true, attributeFilter: ['style']});
// #endif

const errorReportUrl = import.meta.env.VITE_APP_ERROR_REPORT_URL
const errorEnable = import.meta.env.VITE_APP_ERROR_ENABLE
const app_id = import.meta.env.VITE_APP_ID;
const lange = import.meta.env.VITE_APP_LANGE;

// 检查应用发布状态
const checkAppStatus = async () => {
  try {
    // 替换为实际接口调用
    const res = await proxy.$app.appStatus.getStatus(app_id);
    if (res.success) {
      const buildType = import.meta.env.VITE_APP_BUILD_TYPE;
      console.log('当前应用类型：', buildType);
      let isExpired = false;
      switch (buildType) {
        case 'h5':
          isExpired = res.data.h5_is_expired;
          break;
        case 'wx':
          isExpired = res.data.wx_is_expired;
          break;
        case 'apk':
          isExpired = res.data.apk_is_expired;
          break;
        case 'harmony':
          isExpired = res.data.harmonyOS_is_expired;
          break;
        default:
          isExpired = res.data.h5_is_expired; // 默认使用h5判断
      }
      if (isExpired) {
        // 跳转到暂停页面
        uni.reLaunch({
          url: '/pages/app_paused/index'
        });
      }
    }
  } catch (err) {
    console.error('检查应用状态失败:', err);
  }
};

onLaunch((options) => {
  //设置语言
  mode.value = options.query.mode;
  if (options.query.agent_token) storage.setItem('agent_token', options.query.agent_token)
  if (options.query.sign) storage.setItem('agent_sign', options.query.sign)

  // #ifdef H5
  const urlParams = new URLSearchParams(window.location.search)
  const agentToken = urlParams.get('agent_token')
  const agentSign = urlParams.get('sign')
  if (agentToken) storage.setItem('agent_token', agentToken)
  if (agentSign) storage.setItem('agent_sign', agentSign)
  // #endif

  console.log('===========', options.query)
  if (mode.value && mode.value === "preview") {
    document.body.classList.add('hide-nav')
  }
  if (lange === 'zh') {
    uni.setLocale("zh-Hans");
  } else {
    uni.setLocale(lange);
  }

  // 检查应用发布状态
  checkAppStatus();
});

function strToBool(str) {
  if (typeof str !== "string") return Boolean(str); // 非字符串也可处理
  return str.toLowerCase() === "true";
}


onError((err) => {
  console.error(err);
  console.log(typeof errorEnable, "true/false")
  if (!strToBool(errorEnable)) {
    console.log(errorEnable, "true/false")
    return;
  }
  // 进行异常信息的上报
  const pages = getCurrentPages();
  const currentPage = pages[pages.length - 1];
  const route = currentPage ? currentPage.route : "未知页面";
  proxy.$app.error.report(errorReportUrl, {
    'error_message': formatError(err),
    'path': route,
    "type": "frontend",
    "sub_source": "onError",
    'app_id': app_id
  })


});

onUnhandledRejection((res) => {
  console.error(res);
  if (!strToBool(errorEnable)) {
    return
  }
  // 进行异常信息的上报
  const pages = getCurrentPages();
  const currentPage = pages[pages.length - 1];
  const route = currentPage ? currentPage.route : "未知页面";
  proxy.$app.error.report(errorReportUrl, {
    'error_message': formatError(res),
    'path': route,
    "type": "frontend",
    "sub_source": "onUnhandledRejection",
    'app_id': app_id
  })
});
</script>
<style>
@import "@/static/iconfont/iconfont.css";

/*每个页面公共css */
::v-deep .uni-popup__wrapper {
  /* background-color: white !important; */
  border-radius: 10px;
  overflow-y: auto;
}

/* 禁止透传 */
::v-deep .uni-popup {
  z-index: 99999999 !important;
}


::v-deep .uni-picker-container {
  z-index: 99999999 !important;
}


html,
body {
  touch-action: manipulation;
}

page {
  height: 100% !important;
}

::v-deep .uni-card__cover-image {
  width: 100% !important;
}

*,
html,
body,
view,
div,
uni-view,
scroll-view {
  box-sizing: border-box;
  border-width: 0;
  border-style: solid;
}

uni-map {
  height: 100%;
}

/*日期选择器的z-index 放在 tabbar 上方*/
::v-deep .uni-calendar__content-mobile {
  padding-bottom: 100rpx;
  z-index: 99999999 !important;
}


/*日期选择器的z-index 放在 tabbar 上方*/
::v-deep uni-modal {
  z-index: 100000000 !important;
}

/* 去掉按钮默认样式 */
button::after, uni-button {
  border: none;
}

button, uni-button {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body.hide-nav uni-page-head {
  display: none !important;
}

body.hide-nav uni-page-wrapper {
  height: 100% !important;
}

::v-deep uni-toast {
  z-index: 100000000 !important;
}

/* ::v-deep .uni-stat__select {
  z-index: 99999999 !important;
  position: relative;
} */

</style>