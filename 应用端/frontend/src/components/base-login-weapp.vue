<template>
  <view class=" flex flex-col items-center p-4 box-border">
    <button class="bg-success-500 hover:bg-success-600 text-white rounded-full px-8"
            @click="login_click">{{ t('login.wx_app_login') }}
    </button>
  </view>
  <view class="radios flex justify-center text-[28rpx]">
    <view class="radio flex justify-center items-center" @click="isCheck">
      <uni-icons
          class="mr-2"
          :type="radio1 ? 'checkbox-filled' : 'circle'"
          size="20"
          :color="radio1 ? '#22c55e' : '#cbd5e1'"
      />
      <span class="text-neutral-400">{{ t('login.read_and_agree') }}
          <span @click.stop="service_click" class="text-primary-500 hover:text-primary-600 cursor-pointer"
          >《{{ t('login.service_agreement') }}》</span>
          <span>{{ t('login.read_and_service') }}</span>
          <span @click.stop="privacy_click" class="text-primary-500 hover:text-primary-600 cursor-pointer"
          >《{{ t('login.privacy_agreement') }}》</span>
        </span>
    </view>
  </view>
  <view v-show="show_mock" class="flex justify-center mt-2">
    <text class="mock p-2">
      {{ t('login.button_msg') }}
      <uni-badge class="uni-badge-left-margin" text="?" type="info" @click="jump_help"/>
    </text>
  </view>

</template>

<script setup>

const props = defineProps({
  relevanceTable: {type: String, required: false}
});
const emit = defineEmits(['loginSuccess', 'loginFail', 'service', 'privacy']);


const {proxy} = getCurrentInstance();

const t = proxy.$tt;

const radio1 = ref(false)
const isCheck = (event) => {
  if (event) {
    event.stopPropagation();
  }
  // 直接更新状态，确保同步
  radio1.value = !radio1.value;
}

function login_click() {
  // 确保获取最新的选中状态
  if (radio1.value === true) {

    if (show_mock.value) {

      proxy.$app.auth.loginByWeApp({code: "codeflying", relevanceTable: props.relevanceTable}).then((res) => {
        login_success(res);
      }).catch(err => {
        login_error(err);
      })

    } else {
      uni.login({
        success: async (res) => {
          let code = res.code
          proxy.$app.auth.loginByWeApp({code: code, relevanceTable: props.relevanceTable}).then((res) => {
            login_success(res);
          }).catch(err => {
            login_error(err);
          })

        }, fail: (error) => {
          uni.showToast({
            title: proxy.$tt('login.weapp_env_title'),
            icon: 'none'
          })
        }
      })
    }
  } else {
    uni.showToast({
      title: proxy.$tt('login.read_and_service_msg'),
      icon: "none",
      duration: 2000
    });
  }
}


function login_error(err) {
  emit("loginFail");
}

const login_success = async (res) => {

  proxy.$app.auth.getUser().then((res) => {
    const item = res.data;
    proxy.$app.globals.write({
      variableName: "currentUser",
      value: item
    })

    emit("loginSuccess", item);

  }).catch(err => {
    console.log(err);
    emit("loginFail", err);
  });

}


const show_mock = ref(true)

onLoad(async (options) => {
  login_setting();
})

function login_setting() {
  proxy.$app.setting.login().then((res) => {
    let configList = res.data;
    if (!shouldShow('login.show.weapp', configList)) {
      show_mock.value = false;
    } else {
      show_mock.value = true;
    }
  });
}

function shouldShow(nameKey, configList) {
  const item = configList.find(item => item.name === nameKey);
  return item && item.content === "true";
}

const lange = import.meta.env.VITE_APP_LANGE;
function jump_help() {
  // #ifdef H5
  if (lange === 'en') {
    window.open("https://vvx03gck2p.feishu.cn/wiki/Cx3JwoqZbiQmirk74hdcnbzonIw", "_blank");
  } else {
    window.open("https://vvx03gck2p.feishu.cn/docx/DfiEdch6AosKbcxniEacQj63ndc", "_blank");
  }
  // #endif
}

function service_click() {
  emit("service");
}

function privacy_click() {
  emit("privacy");
}

</script>

<style>
.mock {
  display: block;
  border-radius: 10px 10px 10px 10px;
  background-color: rgba(255, 255, 255, 1);
  color: rgba(65, 67, 79, 1);
  font-size: 12px;
  text-align: left;
  box-shadow: 0 2px 6px 0 rgba(0, 0, 0, 0.4);
}
</style>
