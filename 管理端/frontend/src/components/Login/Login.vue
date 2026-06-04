<template>
  <div class="login-page">
    <div class="login-card">
      <h2>{{ $t('login.login_title') }}</h2>

      <el-form
          ref="formRef"
          :model="form"
          :rules="rules"
          label-width="auto"
          class="login-form"
          status-icon
      >
        <el-form-item prop="phone">
          <el-input
              :placeholder="$t('login.enter_user_name')"
              v-model="form.phone"
          />
        </el-form-item>
        <el-form-item prop="password">
          <el-input
              :placeholder="$t('login.enter_pass_wd')"
              type="password"
              v-model="form.password"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" class="login-btn" @click="submitForm(formRef)">
            {{ $t('login.login_btn') }}
          </el-button>
        </el-form-item>
      </el-form>

      <h5 class="login-tip">{{ $t('login.login_default_msg') }}</h5>
    </div>
  </div>
</template>

<script setup>

const {proxy} = getCurrentInstance();

// 定义事件
const emit = defineEmits(["loginSucceess"]);

/**
 * 数据
 */
const form = ref({
  name: "",
  phone: "",
  password: "",
});

const formRef = ref();

const rules = reactive({
  phone: [
    {required: true, message: "请输入用户名", trigger: "blur"},
    // { min: 1, max: 15, message: "用户名长度1-15位", trigger: "blur" },
  ],
  password: [
    {
      required: true,
      message: "请输入密码",
      trigger: "blur",
    },
    // { min: 6, max: 18, message: "密码长度", trigger: "blur" },
  ],
});

const query = proxy.$route.query;
const login_type = import.meta.env.VITE_USE_LOGIN_TYPE;

const appId = import.meta.env.VITE_WX_APP_ID;
const callback = import.meta.env.VITE_WX_CALL_BACK;

if (login_type == 'wx_h5' && query.code != null && query.code != '') {
  proxy.$api.login.loginWxWeb(query).then((res) => {
    login_success(res);
  });
}

//  登录
const submitForm = async (formEl) => {
  if (!formEl) return;
  await formEl.validate((valid, fields) => {
    if (valid) {
      proxy.$api.login.loginPasswd(form.value).then((res) => {
        login_success(res);
      });
    } else {
      console.log("error submit!", fields);
    }
  });
};

function login_success(res) {
  proxy.$modal.msgSuccess("登录成功");
  localStorage.setItem("token", res.data);
  emit("loginSucceess");
}

function login_click() {
  let url = `https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appId}&redirect_uri=${encodeURIComponent(callback)}&response_type=code&scope=snsapi_base&state=codeflying#wechat_redirect`;
  window.location.href = url;
}

onMounted(() => {
  login_setting();
});

function login_setting() {
  proxy.$api.setting.login().then((res) => {
    let configList = res.data;
    if (!shouldShow('login.show.account', configList)) {
      form.value.phone = '';
      form.value.password = '';
    } else {
      form.value.phone = 'admin';
      form.value.password = '123456';
    }
  });
}

function shouldShow(nameKey, configList) {
  const item = configList.find(item => item.name === nameKey);
  return item && item.content === "true";
}

</script>

<style lang="scss" scoped>
/* 整体背景：柔和 AI 渐变光晕 */
.login-page {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: radial-gradient(circle at center, #fff0f6 0%, #fffbe6 100%);
  overflow: hidden;
  position: relative;
}

/* 卡片样式：玻璃态 + 柔光边 */
.login-card {
  width: 400px;
  padding: 50px 40px;
  border-radius: 16px;
  background: rgba(255, 255, 255, 0.5);
  backdrop-filter: blur(12px);
  box-shadow: 0 4px 30px rgba(255, 107, 203, 0.2),
  0 0 0 1px rgba(255, 179, 71, 0.1);
  text-align: center;
  transition: transform 0.3s ease, box-shadow 0.3s ease;

  &:hover {
    transform: translateY(-4px);
    box-shadow: 0 8px 40px rgba(255, 107, 203, 0.25),
    0 0 0 1px rgba(255, 179, 71, 0.1);
  }

  h2 {
    font-size: 22px;
    font-weight: 600;
    background: linear-gradient(90deg, #ff6bcb, #ffb347, #ffe259);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    margin-bottom: 30px;
  }
}

/* 表单样式 */
.login-form {
  .el-input__wrapper {
    background-color: rgba(255, 255, 255, 0.8);
    box-shadow: inset 0 0 6px rgba(255, 107, 203, 0.1);
    border-radius: 8px;
    transition: all 0.3s;

    &:hover,
    &.is-focus {
      box-shadow: 0 0 0 2px rgba(255, 107, 203, 0.3);
    }
  }

  .el-input__inner {
    color: #333;
  }

  .el-form-item {
    margin-bottom: 22px;
  }

  .login-btn {
    width: 100%;
    height: 42px;
    border: none;
    font-weight: 500;
    border-radius: 8px;
    background: linear-gradient(90deg, #ff6bcb, #ffb347, #ffe259);
    color: #fff;
    box-shadow: 0 0 12px rgba(255, 107, 203, 0.4);
    transition: all 0.3s ease;

    &:hover {
      box-shadow: 0 0 20px rgba(255, 179, 71, 0.6);
      transform: translateY(-1px);
    }
  }
}

.login-tip {
  margin-top: 20px;
  color: rgba(0, 0, 0, 0.45);
  font-size: 13px;
}
</style>