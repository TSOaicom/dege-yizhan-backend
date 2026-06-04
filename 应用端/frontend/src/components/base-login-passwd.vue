<template>
  <view class="w-full flex flex-col items-center p-4 box-border bg-gray-100 rounded-md shadow-lg">

    <!-- 卡片容器 -->
    <view class="w-full max-w-[360px] p-6 bg-opacity-0">
      <!-- 表单 -->
      <uni-forms ref="formRef"
                 class="w-full"
                 :modelValue="form"
                 :rules="rules"
                 label-width="auto"
                 status-icon>
        <!-- 手机号/邮箱 -->
        <uni-forms-item name="phone">
          <uni-easyinput
              id="login_ref_input_phone"
              v-model="form.phone"
              :placeholder="t('login.enter_user_name')"
              class="placeholder-neutral-400 bg-neutral-50 rounded-[30px] border-none"
          />
        </uni-forms-item>

        <!-- 密码 -->
        <uni-forms-item name="password">
          <uni-easyinput
              id="login_ref_input_password"
              v-model="form.password"
              type="password"
              :placeholder="t('login.enter_pass_wd')"
              class="placeholder-neutral-400 bg-neutral-50 rounded-[30px] border-none"
          />
        </uni-forms-item>

        <!-- 登录按钮 -->
        <uni-forms-item>
          <button
              id="login_ref_button_kf"
              class="bg-primary-500 hover:bg-primary-600 text-white w-full h-[50px] mt-2 rounded-[30px]
                   shadow-md flex items-center justify-center transition-colors duration-300"
              @click="submitForm(formRef)"
          >
            {{ t('login.login_btn') }}
          </button>
        </uni-forms-item>
      </uni-forms>
    </view>

  </view>
</template>

<script setup>
const {proxy} = getCurrentInstance();

const t = proxy.$tt;
const app_id = import.meta.env.VITE_APP_ID;

const props = defineProps({
  openId: {
    type: String,
    default: () => '',
  },
  relevanceTable: {
    type: String,
    required: false
  }
});

const emit = defineEmits(["loginSuccess", "loginFail"]);

const formRef = ref();

const form = ref({
  phone: "",
  password: "",
});

const rules = ref({
  phone: {
    rules: [
      {
        required: true,
        errorMessage: proxy.$tt('login.enter_user_name')
      },
      {
        minLength: 1,
        maxLength: 100,
        errorMessage: proxy.$tt('login.username_length_rule_text')
      }
    ]
  },
  password: {
    rules: [
      {required: true, errorMessage: proxy.$tt('login.enter_pass_wd')},
      {minLength: 3, maxLength: 18, errorMessage: proxy.$tt('login.password_length_rule_text')}
    ]
  }
});

const submitForm = async (formEl) => {
  if (!formEl) return;
  await proxy.$refs.formRef.validate().then(res => {

    form.value.relevanceTable = props.relevanceTable
    form.value.openId = props.openId
    proxy.$app.auth.login(form.value).then((res) => {
      login_success(res);
    }).catch(err => {
      console.error(err)
      login_error(err);
    });
  }).catch(err => {
    console.log("error submit!", err);
  });
};


onLoad(async (options) => {
  login_setting();
})


function login_setting() {
  proxy.$app.setting.login().then((res) => {
    let configList = res.data;
    if (!shouldShow('login.show.account', configList)) {
      form.value.phone = '';
    } else {
      form.value.phone = 'codeflying_' + app_id;
      form.value.password = '123456';
    }
  });

}

function shouldShow(nameKey, configList) {
  const item = configList.find(item => item.name === nameKey);
  return item && item.content === "true";
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


</script>