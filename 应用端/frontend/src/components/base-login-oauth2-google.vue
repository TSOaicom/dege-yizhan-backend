<template>
  <view class=" flex flex-col items-center p-4 box-border">
    <button class="bg-success-500 hover:bg-success-600 text-white rounded-full px-8"
            @click="login_click">{{ t('login.google_login') }}
    </button>
    <view v-show="show_mock" class="mt-2">
      <text class="mock p-2">
        {{ t('login.button_msg') }}
        <uni-badge class="uni-badge-left-margin" text="?" type="info" @click="jump_help"/>
      </text>
    </view>
  </view>
</template>

<script setup>
const props = defineProps({
  relevanceTable: {type: String, required: false}
});
const emit = defineEmits(['loginSuccess', 'loginFail']);

const {proxy} = getCurrentInstance();

const t = proxy.$tt;

async function login_click() {
    try {
      if (show_mock.value) {
        let wechatForm = {code: 'codeflying'};
        wechatForm.relevanceTable = props.relevanceTable;
        proxy.$app.auth.loginByWeChat(wechatForm).then((res) => {
          login_success(res.data);
        }).catch(err => {
          login_error(err);
        });
    } else {
      let authUrl = await proxy.$app.auth.loginOauth2Uri({provider: 'google'});
      if (authUrl.success) {
        window.location.href = authUrl.data;
      } else {
        login_error(authUrl.message);
      }
    }
    } catch (error) {
      console.error('OAuth2 Google Login Failed:', error);
      login_error(error);
    }
}

onLoad(async (options) => {
  console.log(options, "options============")
  // 调用配置检查
  login_setting();
  // 处理 OAuth2 回调
  if (options.token != null && options.token !== '') {
    login_success(options.token);
  }

})

function login_error(err) {
  emit("loginFail");
}

const login_success = async (token) => {

  // 等待 token set 完毕
  await proxy.$app.setToken(token);


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

function login_setting() {
  proxy.$app.setting.login().then((res) => {
    let configList = res.data;
    if (shouldShow('login.show.google', configList)) {
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
