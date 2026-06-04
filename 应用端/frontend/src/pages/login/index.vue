<template>
  <base-layout>
    <view class="relative box-border min-h-screen w-full overflow-hidden bg-secondary-900 p-2 text-text-primary-50">
      <view class="absolute inset-0 bg-gradient-to-b from-secondary-800 via-secondary-900 to-secondary-900" />
      <view class="halo-one absolute left-0 top-0 size-72 rounded-full bg-primary-500 opacity-30" />
      <view class="halo-two absolute bottom-20 right-0 size-80 rounded-full bg-warning-500 opacity-20" />
      <view class="halo-three absolute right-10 top-64 size-56 rounded-full bg-success-500 opacity-20" />
      <view class="cockpit-grid absolute inset-0 opacity-30" />
      <view class="cockpit-vignette absolute inset-0" />

      <view class="relative z-10 box-border w-full px-3 pb-8 pt-12">
        <view class="mb-7 box-border flex w-full items-start justify-between">
          <view class="box-border flex-1 pr-4">
            <text class="block text-overline tracking-widest text-primary-200">
              DRIVER SMART COCKPIT
            </text>
            <text class="mt-2 block text-hero text-text-primary-50">
              司机工作台
            </text>
            <text class="mt-2 block text-body text-text-primary-300">
              快速登录，进入高效接单驾驶舱
            </text>
          </view>

          <view class="flex size-12 shrink-0 items-center justify-center rounded-3xl border border-primary-100 border-opacity-20 bg-primary-100 bg-opacity-10 shadow-2xl">
            <base-icon
              type="navigate-filled"
              size="24"
              color="#ffb400"
            />
          </view>
        </view>

        <view class="hero-card mb-5 box-border w-full">
          <image
            class="absolute inset-0 size-full opacity-30"
            mode="aspectFill"
            src="https://www.codeflying.net/preview/city-bridge.jpg"
          />
          <view class="absolute inset-0 bg-gradient-to-t from-secondary-900 via-secondary-900 to-transparent opacity-90" />

          <view class="relative z-10 box-border flex h-full flex-col justify-between p-5">
            <view class="flex items-center justify-between">
              <view class="rounded-full border border-success-400 border-opacity-30 bg-success-500 bg-opacity-20 px-3 py-1">
                <text class="text-caption text-success-100">
                  实时在线
                </text>
              </view>
              <view class="flex size-11 items-center justify-center rounded-3xl border border-primary-100 border-opacity-20 bg-secondary-900 bg-opacity-60">
                <base-icon
                  type="map-filled"
                  size="22"
                  color="#c3d0dd"
                />
              </view>
            </view>

            <view>
              <text class="block text-title text-text-primary-50">
                让每一次出车更有方向
              </text>
              <text class="mt-2 block text-body text-text-primary-300">
                登录后查看热力订单、厕所停车导航、钱包会员与司机认证状态。
              </text>
            </view>
          </view>
        </view>

        <view class="login-shell mb-5 box-border w-full">
          <view class="mb-5 box-border flex w-full items-center gap-3">
            <view
              class="tab-item active-press flex-1"
              :class="activeMode == 'login' ? 'tab-active' : 'tab-normal'"
              @click="switchMode('login')"
            >
              <base-icon
                type="phone-filled"
                size="18"
                :color="activeMode == 'login' ? '#0f172a' : '#c3d0dd'"
              />
              <text
                class="tab-text"
                :class="activeMode == 'login' ? 'text-text-primary-900' : 'text-text-primary-200'"
              >
                验证码登录
              </text>
            </view>

            <view
              class="tab-item active-press flex-1"
              :class="activeMode == 'passwdLogin' ? 'tab-active' : 'tab-normal'"
              @click="switchMode('passwdLogin')"
            >
              <base-icon
                type="locked-filled"
                size="18"
                :color="activeMode == 'passwdLogin' ? '#0f172a' : '#c3d0dd'"
              />
              <text
                class="tab-text"
                :class="activeMode == 'passwdLogin' ? 'text-text-primary-900' : 'text-text-primary-200'"
              >
                密码登录
              </text>
            </view>

            <view
              class="tab-item active-press flex-1"
              :class="activeMode == 'register' ? 'tab-active' : 'tab-normal'"
              @click="switchMode('register')"
            >
              <base-icon
                type="personadd-filled"
                size="18"
                :color="activeMode == 'register' ? '#0f172a' : '#c3d0dd'"
              />
              <text
                class="tab-text"
                :class="activeMode == 'register' ? 'text-text-primary-900' : 'text-text-primary-200'"
              >
                新司机注册
              </text>
            </view>
          </view>

          <view
            v-if="activeMode == 'login'"
            class="animate-panel-in box-border w-full"
          >
            <view class="mb-5">
              <text class="block text-title text-text-primary-50">
                手机号验证码登录
              </text>
              <text class="mt-2 block text-body text-text-primary-400">
                请输入手机号并完成验证码校验，系统将自动进入驾驶舱。
              </text>
            </view>

            <view class="base-login-wrap box-border w-full">
              <base-login
                login_type="phone"
                show_title="司机手机号登录"
                relevance-table="user_info"
                @loginSuccess="onLoginSuccess"
                @loginFail="onLoginFail"
              />
            </view>

            <view class="mt-5 flex items-center gap-3 rounded-3xl border border-primary-100 border-opacity-10 bg-secondary-900 bg-opacity-50 p-4">
              <view class="flex size-10 shrink-0 items-center justify-center rounded-2xl bg-warning-500 bg-opacity-20">
                <base-icon
                  type="locked-filled"
                  size="19"
                  color="#ffb400"
                />
              </view>
              <view class="flex-1">
                <text class="block text-subtitle text-text-primary-50">
                  安全提示
                </text>
                <text class="mt-1 block text-caption text-text-primary-400">
                  登录后请尽快完成资质认证，以获得完整接单权限。
                </text>
              </view>
            </view>
          </view>

          <view
            v-if="activeMode == 'passwdLogin'"
            class="animate-panel-in box-border w-full"
          >
            <view class="mb-5">
              <text class="block text-title text-text-primary-50">
                手机号密码登录
              </text>
              <text class="mt-2 block text-body text-text-primary-400">
                请输入手机号和密码，快速进入驾驶舱。
              </text>
            </view>

            <view class="base-login-wrap box-border w-full">
              <base-login
                login_type="passwd"
                show_title="司机密码登录"
                relevance-table="user_info"
                @loginSuccess="onLoginSuccess"
                @loginFail="onLoginFail"
              />
            </view>

            <view class="mt-5 flex items-center gap-3 rounded-3xl border border-primary-100 border-opacity-10 bg-secondary-900 bg-opacity-50 p-4">
              <view class="flex size-10 shrink-0 items-center justify-center rounded-2xl bg-warning-500 bg-opacity-20">
                <base-icon
                  type="locked-filled"
                  size="19"
                  color="#ffb400"
                />
              </view>
              <view class="flex-1">
                <text class="block text-subtitle text-text-primary-50">
                  安全提示
                </text>
                <text class="mt-1 block text-caption text-text-primary-400">
                  首次使用密码登录？请在管理后台设置初始密码。
                </text>
              </view>
            </view>
          </view>

          <view
            v-if="activeMode == 'register'"
            class="animate-panel-in box-border w-full"
          >
            <view class="mb-5">
              <text class="block text-title text-text-primary-50">
                补充司机资料
              </text>
              <text class="mt-2 block text-body text-text-primary-400">
                注册账号需要填写城市、车牌号与车辆类型，认证状态将默认为未认证。
              </text>
            </view>

            <view class="form-item">
              <text class="form-label">
                手机号
              </text>
              <view class="form-control">
                <base-icon
                  type="phone"
                  size="18"
                  color="#c3d0dd"
                />
                <uni-easyinput
                  v-model="registerForm.phone_number"
                  type="number"
                  placeholder="请输入手机号"
                  :maxlength="11"
                  clearable
                  class="box-border flex-1"
                  @input="onRegisterInput('phone_number', $event)"
                />
              </view>
            </view>

            <view class="form-item">
              <text class="form-label">
                密码
              </text>
              <view class="form-control">
                <base-icon
                  type="locked"
                  size="18"
                  color="#c3d0dd"
                />
                <uni-easyinput
                  v-model="registerForm.password"
                  type="password"
                  placeholder="请设置登录密码（6-20位）"
                  :maxlength="20"
                  clearable
                  class="box-border flex-1"
                  @input="onRegisterInput('password', $event)"
                />
              </view>
            </view>

            <view class="form-item">
              <text class="form-label">
                所在城市
              </text>
              <view class="form-control">
                <base-icon
                  type="location"
                  size="18"
                  color="#c3d0dd"
                />
                <uni-easyinput
                  v-model="registerForm.city"
                  type="text"
                  placeholder="例如：上海"
                  :maxlength="-1"
                  clearable
                  class="box-border flex-1"
                  @input="onRegisterInput('city', $event)"
                />
              </view>
            </view>

            <view class="form-item">
              <text class="form-label">
                车牌号
              </text>
              <view class="form-control">
                <base-icon
                  type="flag"
                  size="18"
                  color="#c3d0dd"
                />
                <uni-easyinput
                  v-model="registerForm.car_plate"
                  type="text"
                  placeholder="例如：沪A12345"
                  :maxlength="-1"
                  clearable
                  class="box-border flex-1"
                  @input="onRegisterInput('car_plate', $event)"
                />
              </view>
            </view>

            <view class="form-item">
              <text class="form-label">
                车辆类型
              </text>
              <view class="select-wrap">
                <uni-data-select
                  v-model="registerForm.car_type_enum_car_type_enum_id_1"
                  :localdata="carTypeOptions"
                  placeholder="请选择车辆类型"
                  :clear="false"
                  placement="bottom"
                  @change="onCarTypeChange"
                />
              </view>
            </view>

            <view class="mb-5 grid grid-cols-3 gap-3">
              <view class="mini-metric">
                <base-icon
                  type="auth-filled"
                  size="18"
                  color="#ffb400"
                />
                <text class="mt-2 block text-caption text-text-primary-300">
                  未认证
                </text>
              </view>
              <view class="mini-metric">
                <base-icon
                  type="map-pin"
                  size="18"
                  color="#42b861"
                />
                <text class="mt-2 block text-caption text-text-primary-300">
                  城市服务
                </text>
              </view>
              <view class="mini-metric">
                <base-icon
                  type="wallet-filled"
                  size="18"
                  color="#c3d0dd"
                />
                <text class="mt-2 block text-caption text-text-primary-300">
                  钱包权益
                </text>
              </view>
            </view>

            <view
              class="btn-core active-press box-border w-full"
              @click="handleRegister"
            >
              <view class="btn-shine" />
              <base-icon
                type="checkmarkempty"
                size="21"
                color="#f8fafc"
              />
              <text class="btn-text">
                注册并去认证
              </text>
            </view>

            <view
              class="btn-ghost active-press mt-4 box-border w-full"
              @click="switchMode('login')"
            >
              <base-icon
                type="right"
                size="18"
                color="#ffb400"
              />
              <text class="btn-ghost-text">
                已有账号，返回登录
              </text>
            </view>
          </view>
        </view>

        <view class="box-border grid w-full grid-cols-2 gap-3">
          <view class="info-card">
            <text class="block text-caption text-primary-200">
              接单效率
            </text>
            <text class="mt-2 block text-title text-text-primary-50">
              热力推荐
            </text>
            <text class="mt-1 block text-caption text-text-primary-400">
              登录后查看高价值区域
            </text>
          </view>

          <view class="info-card">
            <text class="block text-caption text-primary-200">
              司机权益
            </text>
            <text class="mt-2 block text-title text-text-primary-50">
              认证护航
            </text>
            <text class="mt-1 block text-caption text-text-primary-400">
              资质完善后体验更完整
            </text>
          </view>
        </view>
      </view>

      <view
        v-if="isLoading"
        class="loading-mask fixed inset-0 z-50 flex items-center justify-center"
      >
        <view class="loading-card">
          <view class="loading-orbit">
            <view class="loading-ring" />
            <view class="loading-core">
              <base-icon
                type="navigate-filled"
                size="26"
                color="#ffb400"
              />
            </view>
          </view>

          <text class="mt-5 block text-subtitle text-text-primary-50">
            {{ loadingTitle }}
          </text>
          <text class="mt-2 block text-caption text-text-primary-400">
            请稍候，正在同步司机身份
          </text>

          <view class="mt-5 flex items-center justify-center gap-2">
            <view class="loading-dot dot-one" />
            <view class="loading-dot dot-two" />
            <view class="loading-dot dot-three" />
          </view>
        </view>
      </view>
    </view>
  </base-layout>
</template>

<script setup>
const { proxy } = getCurrentInstance();

const activeMode = ref('login');
const isLoading = ref(false);
const loadingTitle = ref('正在处理');
const isSubmitting = ref(false);

const registerForm = ref({
  phone_number: '',
  password: '',
  city: '',
  car_plate: '',
  car_type_enum_car_type_enum_id_1: 1,
  cert_status_enum_cert_status_enum_id_1: 1,
  avatar: [],
  real_name: '',
  active_days: 0,
  continue_active_days: 0,
});

const carTypeOptions = ref([
  {
    value: 1,
    text: '油车',
  },
  {
    value: 2,
    text: '新能源车',
  },
]);

function switchMode(mode) {
  activeMode.value = mode;
}

function onRegisterInput(field, value) {
  nextTick(() => {
    registerForm.value[field] = value;
  });
}

function onCarTypeChange(value) {
  registerForm.value.car_type_enum_car_type_enum_id_1 = value;
}

function isValidPhone(phone) {
  return /^1[3-9]\d{9}$/.test(phone);
}

async function handleRegister() {
  if (isSubmitting.value) {
    return;
  }

  if (!registerForm.value.phone_number) {
    await proxy.$app.ui.toast().level('error').duration(2500).message('请输入手机号');
    return;
  }

  if (!isValidPhone(registerForm.value.phone_number)) {
    await proxy.$app.ui.toast().level('error').duration(2500).message('请输入正确的手机号');
    return;
  }

  if (!registerForm.value.password) {
    await proxy.$app.ui.toast().level('error').duration(2500).message('请设置登录密码');
    return;
  }

  if (registerForm.value.password.length < 6 || registerForm.value.password.length > 20) {
    await proxy.$app.ui.toast().level('error').duration(2500).message('密码长度需为6-20位');
    return;
  }

  if (!registerForm.value.city) {
    await proxy.$app.ui.toast().level('error').duration(2500).message('请输入所在城市');
    return;
  }

  if (!registerForm.value.car_plate) {
    await proxy.$app.ui.toast().level('error').duration(2500).message('请输入车牌号');
    return;
  }

  if (!registerForm.value.car_type_enum_car_type_enum_id_1) {
    await proxy.$app.ui.toast().level('error').duration(2500).message('请选择车辆类型');
    return;
  }

  isSubmitting.value = true;
  isLoading.value = true;
  loadingTitle.value = '正在创建司机账号';

  const res = await proxy.$app.auth.register({
    phone_number: registerForm.value.phone_number,
    password: registerForm.value.password,
    city: registerForm.value.city,
    car_plate: registerForm.value.car_plate,
    car_type_enum_car_type_enum_id_1: registerForm.value.car_type_enum_car_type_enum_id_1,
    cert_status_enum_cert_status_enum_id_1: 1,
    avatar: [],
    real_name: registerForm.value.real_name,
    active_days: 0,
    continue_active_days: 0,
  });

  isLoading.value = false;
  isSubmitting.value = false;

  if (res && res.success) {
    await proxy.$app.ui.toast().level('success').duration(2200).message('注册成功，请继续完成资质认证');
    await proxy.$app.router.navigate.to().url('/pages/certification/index').type('page');
  } else {
    await proxy.$app.ui.toast().level('error').duration(3000).message('注册失败，请稍后重试');
  }
}

async function onLoginSuccess() {
  if (isSubmitting.value) {
    return;
  }

  isSubmitting.value = true;
  isLoading.value = true;
  loadingTitle.value = '登录成功，正在进入驾驶舱';

  setTimeout(async () => {
    isLoading.value = false;
    isSubmitting.value = false;
    await proxy.$app.router.navigate.to().url('/pages/home/index').type('page');
  }, 900);
}

async function onLoginFail() {
  await proxy.$app.ui.toast().level('error').duration(3000).message('登录失败，请检查手机号或验证码');
}
</script>

<style scoped>
* {
  box-sizing: border-box;
}

.halo-one {
  transform: translate(-35%, -30%);
  filter: blur(46px);
}

.halo-two {
  transform: translate(28%, 15%);
  filter: blur(52px);
}

.halo-three {
  transform: translate(20%, -10%);
  filter: blur(44px);
}

.cockpit-grid {
  background-image:
    linear-gradient(rgba(195, 208, 221, 0.08) 1px, transparent 1px),
    linear-gradient(90deg, rgba(195, 208, 221, 0.08) 1px, transparent 1px);
  background-size: 28px 28px;
}

.cockpit-vignette {
  background:
    linear-gradient(180deg, rgba(13, 27, 42, 0.15), rgba(13, 27, 42, 0.92)),
    linear-gradient(90deg, rgba(13, 27, 42, 0.75), transparent 48%, rgba(13, 27, 42, 0.75));
}

.hero-card {
  position: relative;
  overflow: hidden;
  height: 214px;
  border-radius: 32px;
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.18);
  box-shadow:
    0 24px 70px rgba(13, 27, 42, 0.62),
    inset 0 1px 0 rgba(248, 250, 252, 0.14);
}

.login-shell {
  padding: 18px;
  border-radius: 34px;
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.18);
  box-shadow:
    0 24px 70px rgba(13, 27, 42, 0.68),
    inset 0 1px 0 rgba(248, 250, 252, 0.14);
}

.tab-item {
  min-height: 48px;
  border-radius: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 9px;
  border: 1px solid rgba(195, 208, 221, 0.14);
}

.tab-active {
  background: linear-gradient(135deg, #ffb400, #fff0cc);
  box-shadow:
    0 14px 32px rgba(255, 180, 0, 0.22),
    inset 0 1px 0 rgba(248, 250, 252, 0.36);
}

.tab-normal {
  background: rgba(13, 27, 42, 0.46);
}

.tab-text {
  font-size: 0.8rem;
  line-height: 1.2rem;
  font-weight: 600;
}

.active-press:active {
  transform: scale(0.985);
  opacity: 0.92;
}

.form-item {
  margin-bottom: 18px;
}

.form-label {
  display: block;
  margin-bottom: 8px;
  font-size: 0.65rem;
  line-height: 0.9425rem;
  font-weight: 500;
  color: #c3d0dd;
  letter-spacing: 0.08em;
}

.form-control {
  min-height: 54px;
  padding: 0 14px;
  border-radius: 22px;
  display: flex;
  align-items: center;
  gap: 10px;
  color: #f8fafc;
  background: rgba(13, 27, 42, 0.58);
  border: 1px solid rgba(195, 208, 221, 0.18);
  box-shadow:
    inset 0 1px 0 rgba(248, 250, 252, 0.08),
    0 10px 30px rgba(13, 27, 42, 0.35);
}

.form-control:focus-within {
  border-color: rgba(255, 180, 0, 0.58);
  box-shadow:
    0 0 0 3px rgba(255, 180, 0, 0.12),
    inset 0 1px 0 rgba(248, 250, 252, 0.12);
}

.select-wrap {
  min-height: 54px;
  padding: 0 14px;
  border-radius: 22px;
  color: #f8fafc;
  background: rgba(13, 27, 42, 0.58);
  border: 1px solid rgba(195, 208, 221, 0.18);
  box-shadow:
    inset 0 1px 0 rgba(248, 250, 252, 0.08),
    0 10px 30px rgba(13, 27, 42, 0.35);
}

.mini-metric {
  min-height: 78px;
  padding: 13px 8px;
  border-radius: 24px;
  text-align: center;
  background: rgba(13, 27, 42, 0.46);
  border: 1px solid rgba(195, 208, 221, 0.14);
  box-shadow:
    inset 0 1px 0 rgba(248, 250, 252, 0.08),
    0 10px 24px rgba(13, 27, 42, 0.22);
}

.btn-core {
  position: relative;
  overflow: hidden;
  height: 56px;
  border-radius: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  background: linear-gradient(135deg, #5a7c9e, #283845 48%, #0d1b2a);
  box-shadow:
    0 18px 42px rgba(13, 27, 42, 0.58),
    inset 0 1px 0 rgba(248, 250, 252, 0.22),
    inset 0 -1px 0 rgba(13, 27, 42, 0.45);
}

.btn-shine {
  position: absolute;
  top: 0;
  left: 14px;
  right: 14px;
  height: 1px;
  background: linear-gradient(90deg, transparent, rgba(248, 250, 252, 0.75), transparent);
}

.btn-text {
  position: relative;
  z-index: 1;
  font-size: 1rem;
  line-height: 1.35rem;
  font-weight: 600;
  color: #f8fafc;
}

.btn-ghost {
  height: 52px;
  border-radius: 22px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  background: rgba(13, 27, 42, 0.36);
  border: 1px solid rgba(255, 180, 0, 0.34);
  box-shadow:
    0 14px 36px rgba(13, 27, 42, 0.36),
    inset 0 1px 0 rgba(255, 240, 204, 0.12);
}

.btn-ghost-text {
  font-size: 1rem;
  line-height: 1.35rem;
  font-weight: 600;
  color: #fff0cc;
}

.info-card {
  min-height: 108px;
  padding: 16px;
  border-radius: 28px;
  background: rgba(225, 231, 238, 0.08);
  border: 1px solid rgba(195, 208, 221, 0.14);
  box-shadow:
    0 14px 36px rgba(13, 27, 42, 0.36),
    inset 0 1px 0 rgba(248, 250, 252, 0.1);
}

.loading-mask {
  background:
    linear-gradient(180deg, rgba(13, 27, 42, 0.78), rgba(13, 27, 42, 0.94)),
    linear-gradient(135deg, rgba(40, 56, 69, 0.45), rgba(13, 27, 42, 0.88));
}

.loading-card {
  width: 220px;
  padding: 28px 22px;
  border-radius: 34px;
  text-align: center;
  background: rgba(225, 231, 238, 0.12);
  border: 1px solid rgba(195, 208, 221, 0.2);
  box-shadow:
    0 28px 90px rgba(13, 27, 42, 0.78),
    inset 0 1px 0 rgba(248, 250, 252, 0.18),
    inset 0 -1px 0 rgba(13, 27, 42, 0.48);
}

.loading-orbit {
  position: relative;
  width: 82px;
  height: 82px;
  margin: 0 auto;
  border-radius: 999px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.loading-ring {
  position: absolute;
  inset: 0;
  border-radius: 999px;
  border: 2px solid rgba(195, 208, 221, 0.16);
  border-top-color: #ffb400;
  border-right-color: #42b861;
  animation: loadingRotate 1s linear infinite;
  box-shadow:
    0 0 28px rgba(255, 180, 0, 0.22),
    inset 0 0 18px rgba(195, 208, 221, 0.08);
}

.loading-core {
  width: 58px;
  height: 58px;
  border-radius: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(13, 27, 42, 0.72);
  border: 1px solid rgba(255, 180, 0, 0.26);
  box-shadow:
    inset 0 1px 0 rgba(248, 250, 252, 0.12),
    0 12px 30px rgba(13, 27, 42, 0.52);
}

.loading-dot {
  width: 6px;
  height: 6px;
  border-radius: 999px;
  background: #ffb400;
  opacity: 0.35;
  animation: loadingPulse 1.2s ease-in-out infinite;
}

.dot-two {
  animation-delay: 0.16s;
}

.dot-three {
  animation-delay: 0.32s;
}

.animate-panel-in {
  animation: panelIn 0.36s ease-out both;
}

@keyframes panelIn {
  from {
    opacity: 0;
    transform: translateY(12px);
  }

  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes loadingRotate {
  from {
    transform: rotate(0deg);
  }

  to {
    transform: rotate(360deg);
  }
}

@keyframes loadingPulse {
  0% {
    opacity: 0.28;
    transform: translateY(0);
  }

  50% {
    opacity: 1;
    transform: translateY(-4px);
  }

  100% {
    opacity: 0.28;
    transform: translateY(0);
  }
}

/* 全局统一穿透：uni-easyinput 深色驾驶舱样式 */
:deep(.uni-easyinput) {
  flex: 1;
}

:deep(.uni-easyinput__content) {
  background-color: transparent !important;
  border: 0 !important;
  color: #f8fafc !important;
  min-height: 52px;
}

:deep(.uni-easyinput__content-input) {
  background-color: transparent !important;
  color: #f8fafc !important;
  font-size: 0.8rem;
}

:deep(.uni-easyinput__placeholder-class) {
  color: #64748b !important;
}

:deep(.uni-input-input) {
  color: #f8fafc;
  font-size: 0.8rem;
}

:deep(.uni-input-placeholder) {
  color: #64748b;
}

:deep(.uni-select) {
  border: 0 !important;
  background-color: transparent !important;
}

:deep(.uni-select__input-text) {
  color: #f8fafc !important;
  font-size: 0.8rem;
}

:deep(.uni-select__input-placeholder) {
  color: #64748b !important;
}

:deep(.uni-select__selector) {
  background-color: #1a2635 !important;
  border: 1px solid rgba(195, 208, 221, 0.18) !important;
  border-radius: 18px !important;
}

:deep(.uni-select__selector-item) {
  color: #f8fafc !important;
}

:deep(.uni-select__selector-empty) {
  color: #94a3b8 !important;
}

/* base-login 内部表单统一深色质感 */
:deep(.base-login-wrap .uni-easyinput__content) {
  background-color: rgba(13, 27, 42, 0.58) !important;
  border: 1px solid rgba(195, 208, 221, 0.18) !important;
  border-radius: 22px !important;
  color: #f8fafc !important;
  box-shadow:
    inset 0 1px 0 rgba(248, 250, 252, 0.08),
    0 10px 30px rgba(13, 27, 42, 0.35);
}

:deep(.base-login-wrap .uni-easyinput__content-input) {
  background-color: transparent !important;
  color: #f8fafc !important;
}

:deep(.base-login-wrap .uni-forms-item__label) {
  color: #c3d0dd !important;
  font-size: 0.65rem !important;
  letter-spacing: 0.08em;
}

:deep(.base-login-wrap button) {
  border-radius: 24px !important;
  background: linear-gradient(135deg, #5a7c9e, #283845 48%, #0d1b2a) !important;
  color: #f8fafc !important;
  border: 0 !important;
  box-shadow:
    0 18px 42px rgba(13, 27, 42, 0.58),
    inset 0 1px 0 rgba(248, 250, 252, 0.22) !important;
}

:deep(.base-login-wrap button::after) {
  border: 0 !important;
}
</style>