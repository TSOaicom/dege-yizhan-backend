<template>
  <div class="settings-page min-h-screen  p-8">

    <!-- 顶部标题栏 -->
    <div class="flex-between-start" style="background-color: #5D5FEF; height: 140px;">
      <div class="flex-c-center-start h-full">
        <div class="m-x-20">
          <span style="font-weight: bold; font-size: 20px; color: #FFFFFF;">{{ $t('page.system.title') }}</span>
        </div>
        <div class="m-x-20 m-t-4">
          <span style="font-size: 14px; color: #FFFFFF;">{{ $t('page.system.description') }}</span>
        </div>
      </div>
      <div class="flex-start-end h-full m-r-10">
        <img src="@/assets/logo/document.png" height="130px"/>
      </div>
    </div>
    <el-breadcrumb
        separator="/"
        class="m-20"
    >
      <el-breadcrumb-item
          :to="{ path: '/' }"
      >
        {{ $t('page.home') }}
      </el-breadcrumb-item>
      <el-breadcrumb-item
          :to="{ path: '/system_setting_manger' }"
      >
        {{ $t('page.system.title') }}
      </el-breadcrumb-item>
    </el-breadcrumb>

    <div class="flex flex-col gap-6 ">
      <!-- 启用开关 -->
      <el-card class="rounded-2xl shadow-md">
        <template #header>
          <div class="font-semibold text-gray-700">{{ $t('page.system.mock.title') }}</div>
        </template>
        <div class="flex items-center justify-between">
          <span>{{ $t('page.system.mock.show_default_account') }}</span>
          <el-switch v-model="mockConfig['login.show.account']" active-color="#f472b6" inactive-color="#e5e7eb"
                     active-value="true" inactive-value="false"/>
        </div>
        <div v-if="!F" class="flex items-center justify-between">
          <span>{{ $t('page.system.mock.wechat') }}</span>
          <el-switch v-model="mockConfig['login.show.wechat']" active-color="#f472b6" inactive-color="#e5e7eb"
                     active-value="true" inactive-value="false"/>
        </div>
        <div v-if="!F" class="flex items-center justify-between">
          <span>{{ $t('page.system.mock.weapp') }}</span>
          <el-switch v-model="mockConfig['login.show.weapp']" active-color="#f472b6" inactive-color="#e5e7eb"
                     active-value="true" inactive-value="false"/>
        </div>
        <div class="flex items-center justify-between">
          <span>{{ $t('page.system.mock.email') }}</span>
          <el-switch v-model="mockConfig['login.show.mail']" active-color="#f472b6" inactive-color="#e5e7eb"
                     active-value="true" inactive-value="false"/>
        </div>
        <div v-if="!F" class="flex items-center justify-between">
          <span>{{ $t('page.system.mock.phone') }}</span>
          <el-switch v-model="mockConfig['login.show.phone']" active-color="#f472b6" inactive-color="#e5e7eb"
                     active-value="true" inactive-value="false"/>
        </div>
        <div class="flex items-center justify-between">
          <span>{{ $t('page.system.mock.google') }}</span>
          <el-switch v-model="mockConfig['login.show.google']" active-color="#f472b6" inactive-color="#e5e7eb"
                     active-value="true" inactive-value="false"/>
        </div>
        <div v-if="!F" class="flex items-center justify-between">
          <span>{{ $t('page.system.mock.wx_pay_mock_enable') }}</span>
          <el-switch v-model="mockConfig['wx.pay.mock_enable']" active-color="#f472b6" inactive-color="#e5e7eb"
                     active-value="true" inactive-value="false"/>
        </div>
        <div v-if="!F" class="flex items-center justify-between">
          <span>{{ $t('page.system.mock.wx_pay_is_test') }}</span>
          <el-switch v-model="mockConfig['wx.pay.is_test']" active-color="#f472b6" inactive-color="#e5e7eb"
                     active-value="true" inactive-value="false"/>
        </div>
      </el-card>

      <el-card v-if="!F" class="rounded-2xl shadow-md">
        <template #header>
          <div class="font-semibold text-gray-700"> {{ $t('page.system.wechat.title') }}</div>
        </template>
        <div class="flex flex-col gap-3">
          <el-form-item :label="$t('page.system.wechat.app_id')">
            <el-input
                v-model="weChatConfig['wx.pay.mp-app-id']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>

          <el-form-item :label="$t('page.system.wechat.app_secret')">
            <el-input
                v-model="weChatConfig['wx.pay.mp-app-secret']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>
        </div>
      </el-card>

      <el-card class="rounded-2xl shadow-md">
        <template #header>
          <div class="font-semibold text-gray-700">{{ $t('page.system.email.title') }}</div>
        </template>
        <div class="flex flex-col gap-3">
          <el-form-item :label="$t('page.system.email.host')">
            <el-input
                v-model="emailConfig['mail.host']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>

          <el-form-item :label="$t('page.system.email.port')">
            <el-input
                v-model="emailConfig['mail.port']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>

          <el-form-item :label="$t('page.system.email.user')">
            <el-input
                v-model="emailConfig['mail.user']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>

          <el-form-item :label="$t('page.system.email.passwd')">
            <el-input
                v-model="emailConfig['mail.passwd']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>

          <el-form-item :label="$t('page.system.email.code_template')" class="flex flex-col items-start">
            <el-input
                v-model="emailConfig['mail.code_template']"
                class="custom-input"
                type="textarea"
                :rows="3"
                style="max-width: 400px;"
                size="large"
            />
            <span class="text-yellow-600 text-sm mt-1 block">
              *{{ $t('page.system.email.code_template_msg') }} &#123;&#123;code&#125;&#125;
            </span>
          </el-form-item>


        </div>
      </el-card>

      <!-- Google OAuth 配置 -->
      <el-card class="rounded-2xl shadow-md">
        <template #header>
          <div class="font-semibold text-gray-700">{{ $t('page.system.google.title') }}</div>
        </template>
        <div class="flex flex-col gap-3">
          <el-form-item :label="$t('page.system.google.client_id')">
            <el-input
                v-model="googleConfig['google.oauth.client_id']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>

          <el-form-item :label="$t('page.system.google.client_secret')">
            <el-input
                v-model="googleConfig['google.oauth.client_secret']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>

          <el-form-item :label="$t('page.system.google.callback_uri')">
            <el-input
                v-model="googleConfig['google.oauth.callback_uri']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>

          <el-form-item :label="$t('page.system.google.redirect_uri')">
            <el-input
                v-model="googleConfig['google.oauth.redirect_uri']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>
        </div>
      </el-card>

      <!-- Stripe 支付配置 -->
      <el-card v-if="F" class="rounded-2xl shadow-md">
        <template #header>
          <div class="flex justify-between items-center">
            <div class="font-semibold text-gray-700">
              {{ $t('page.system.stripe.title') }}
            </div>
            <el-button
                round
                text
                class="m-l-10 m-b-2"
                @click="openFHelp"
            >
              <el-icon class="m-r-2">
                <QuestionFilled/>
              </el-icon>
            </el-button>
          </div>
        </template>
        <div class="flex flex-col gap-3">
          <el-form-item :label="$t('page.system.stripe.key')">
            <el-input
                v-model="stripeConfig['stripe.pay.secretKey']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>

          <el-form-item :label="$t('page.system.stripe.webhook_secret')">
            <el-input
                v-model="stripeConfig['stripe.webhook.secret']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>
        </div>
      </el-card>

      <!-- 微信支付 -->
      <el-card v-if="!F" class="rounded-2xl shadow-md">
        <template #header>
          <div class="flex justify-between items-center">
            <div class="font-semibold text-gray-700">
              {{ $t('page.system.wechat.pay.title') }}
            </div>
            <el-button
                round
                text
                class="m-l-10 m-b-2"
                @click="openHelp"
            >
              <el-icon class="m-r-2">
                <QuestionFilled/>
              </el-icon>
            </el-button>
          </div>
        </template>
        <div class="flex flex-col gap-3">

          <el-form-item :label="$t('page.system.wechat.pay.wx_pay_wechat_enable')">
            <el-switch v-model="weChatPayConfig['wx.pay.wechat_enable']" active-color="#f472b6" inactive-color="#e5e7eb"
                       active-value="true" inactive-value="false"/>
          </el-form-item>

          <el-form-item :label="$t('page.system.wechat.pay.wechat_app_id')">
            <el-input
                v-model="weChatPayConfig['wechat.app-id']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>
          <el-form-item :label="$t('page.system.wechat.pay.wechat_app_secret')">
            <el-input
                v-model="weChatPayConfig['wechat.app-secret']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>

          <el-form-item :label="$t('page.system.wechat.pay.wx_pay_pay_back_url')">
            <el-input
                v-model="weChatPayConfig['wx.pay.pay-back-url']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>

          <el-form-item :label="$t('page.system.wechat.pay.wx_pay_mch_serial_no')">
            <el-input
                v-model="weChatPayConfig['wx.pay.mch-serial-no']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>

          <el-form-item :label="$t('page.system.wechat.pay.wx_pay_app_id')">
            <el-input
                v-model="weChatPayConfig['wx.pay.app-id']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>

          <el-form-item :label="$t('page.system.wechat.pay.wx_pay_mch_id')">
            <el-input
                v-model="weChatPayConfig['wx.pay.mch-id']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>

          <el-form-item :label="$t('page.system.wechat.pay.wx_pay_api_v3_key')">
            <el-input
                v-model="weChatPayConfig['wx.pay.api-v3-key']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>

          <el-form-item :label="$t('page.system.wechat.pay.wx_pay_mp_app_id')">
            <el-input
                v-model="weChatPayConfig['wx.pay.mp-app-id']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>

          <el-form-item :label="$t('page.system.wechat.pay.wx_pay_mp_app_secret')">
            <el-input
                v-model="weChatPayConfig['wx.pay.mp-app-secret']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>

          <el-form-item :label="$t('page.system.wechat.pay.wechat_mp_redirect_uri')">
            <el-input
                v-model="weChatPayConfig['wechat.mp-redirect_uri']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>

          <el-form-item :label="$t('page.system.wechat.pay.wechat_mp_frontend_redirect_uri')">
            <el-input
                v-model="weChatPayConfig['wechat.mp-frontend_redirect_uri']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>

          <el-form-item :label="$t('page.system.wechat.pay.wx_pay_private_key')">
            <el-input
                v-model="weChatPayConfig['wx.pay.private-key']"
                class="custom-input"
                style="max-width: 400px;"
                size="large"
            />
          </el-form-item>

        </div>
      </el-card>

      <!-- 底部操作 -->
      <div class="flex justify-end gap-4 mx-4 mb-4">
        <el-button
            type="primary"
            class=" border-none text-white hover:opacity-90"
            @click="saveLoginConfig"
        >
          {{ $t('page.system.save') }}
        </el-button>
      </div>
    </div>
  </div>
</template>

<script setup>
const {proxy} = getCurrentInstance();
let F = import.meta.env.VITE_APP_VERSION === 'F'

const mockConfig = ref({})
const weChatConfig = ref({});
const emailConfig = ref({});
const googleConfig = ref({});
const stripeConfig = ref({});
const weChatPayConfig = ref({});


onMounted(() => {
  fetchSettings();
});

function fetchSettings() {

  proxy.$api.setting.settings().then((res) => {
    if (res.success) {
      const map = Object.fromEntries(
          res.data.map(item => [item.name, item.content])
      );

      getMockConfig(map);
      getMailSetting(map);
      getWeChatSetting(map);
      getGoogleSetting(map);
      getStripeSetting(map);

      getWeChatConfig(map);
    }
  });

}

function getMockConfig(map) {
  const keys = ["login.show.account", "login.show.google", "login.show.wechat", "login.show.weapp", "login.show.mail", "login.show.phone", "wx.pay.mock_enable", "wx.pay.is_test"];
  mockConfig.value = keys.reduce((obj, key) => {
    obj[key] = map[key];
    return obj;
  }, {});
  console.log(mockConfig.value);
}

function getMailSetting(map) {
  const keys = ["mail.host", "mail.user", "mail.passwd", "mail.port", "login.mail.code_template",];
  emailConfig.value = keys.reduce((obj, key) => {
    obj[key] = map[key];
    return obj;
  }, {});
}

function getWeChatSetting(map) {
  const keys = ["wx.pay.mp-app-id", "wx.pay.mp-app-secret"];
  weChatConfig.value = keys.reduce((obj, key) => {
    obj[key] = map[key];
    return obj;
  }, {});
}

function getGoogleSetting(map) {
  const keys = ["google.oauth.client_id", "google.oauth.client_secret", "google.oauth.callback_uri", "google.oauth.redirect_uri"];
  googleConfig.value = keys.reduce((obj, key) => {
    obj[key] = map[key];
    return obj;
  }, {});
}

function getStripeSetting(map) {
  const keys = ["stripe.pay.secretKey", "stripe.webhook.secret"];
  stripeConfig.value = keys.reduce((obj, key) => {
    obj[key] = map[key];
    return obj;
  }, {});
}

function getWeChatConfig(map) {
  const keys = [
    "wechat.app-id", "wechat.app-secret", "wx.pay.pay-back-url",
    "wx.pay.mch-serial-no", "wx.pay.app-id", "wx.pay.mch-id", "wx.pay.api-v3-key",
    "wx.pay.wechat_enable", "wx.pay.mp-app-id", "wechat.mp-redirect_uri", "wx.pay.mp-app-secret",
    "wechat.mp-frontend_redirect_uri", "wx.pay.private-key"
  ]
  weChatPayConfig.value = keys.reduce((obj, key) => {
    obj[key] = map[key];
    return obj;
  }, {});
}


async function saveLoginConfig() {
  await proxy.$api.setting.saveSetting(mockConfig.value);
  await proxy.$api.setting.saveSetting(emailConfig.value);
  await proxy.$api.setting.saveSetting(weChatConfig.value);
  await proxy.$api.setting.saveSetting(googleConfig.value);
  await proxy.$api.setting.saveSetting(stripeConfig.value);
  await proxy.$api.setting.saveSetting(weChatPayConfig.value);
  proxy.$modal.msgSuccess(proxy.$tt('page.system.save_msg'))
}

function openHelp() {
  window.open('https://vvx03gck2p.feishu.cn/docx/JZscddNawo0aXPx2HZzcy2ybn0b', '_blank');
}

function openFHelp(){
  window.open('https://vvx03gck2p.feishu.cn/wiki/EN24wBIQ1iViENkQkkhcTbBPnFf', '_blank');
}

</script>

<style scoped>
.settings-page {
  font-family: "Inter", "PingFang SC", sans-serif;
}

.el-card {
  transition: all 0.2s ease;
}

.el-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 14px rgba(0, 0, 0, 0.1);
}
</style>
