<template>
  <base-layout>
    <view class="relative box-border min-h-screen w-full overflow-hidden bg-secondary-900 text-text-primary-50">
      <view class="absolute inset-0 bg-gradient-to-b from-secondary-800 via-secondary-900 to-secondary-900" />
      <view class="halo-one absolute left-0 top-0 size-72 rounded-full bg-primary-500 opacity-30 blur-3xl" />
      <view class="halo-two absolute bottom-20 right-0 size-80 rounded-full bg-warning-500 opacity-20 blur-3xl" />
      <view class="halo-three absolute right-8 top-64 size-56 rounded-full bg-success-500 opacity-20 blur-3xl" />
      <view class="cockpit-grid absolute inset-0 opacity-30" />
      <view class="cockpit-vignette absolute inset-0" />

      <scroll-view
        scroll-y
        class="relative z-10 box-border h-screen w-full"
      >
        <view class="box-border p-2">
          <view class="box-border px-3 pb-8 pt-12">
            <view class="mb-6 box-border flex items-start justify-between">
              <view class="box-border flex-1 pr-4">
                <text class="block text-overline uppercase tracking-widest text-primary-200">
                  DRIVER VERIFY COCKPIT
                </text>
                <text class="mt-2 block text-hero text-text-primary-50">
                  资质认证
                </text>
                <text class="mt-2 block text-body text-text-primary-300">
                  上传证件资料，完成平台实名认证，开启更可靠的接单体验。
                </text>
              </view>

              <view class="flex size-12 shrink-0 items-center justify-center rounded-3xl border border-primary-100 border-opacity-20 bg-primary-100 bg-opacity-10 shadow-xl">
                <base-icon
                  type="locked-filled"
                  size="23"
                  color="#ffb400"
                />
              </view>
            </view>

            <view class="hero-card mb-5 box-border">
              <image
                class="absolute inset-0 size-full opacity-35"
                mode="aspectFill"
                src="https://www.codeflying.net/preview/technological-lines.jpg"
              />
              <view class="absolute inset-0 bg-gradient-to-t from-secondary-900 via-secondary-900 to-transparent opacity-90" />

              <view class="relative z-10 box-border flex h-full flex-col justify-between p-5">
                <view class="box-border flex items-center justify-between">
                  <view class="rounded-full border border-warning-400 border-opacity-30 bg-warning-500 bg-opacity-20 px-3 py-1">
                    <text class="text-caption text-warning-100">
                      {{ statusText }}
                    </text>
                  </view>

                  <view class="flex size-11 items-center justify-center rounded-3xl border border-primary-100 border-opacity-20 bg-secondary-900 bg-opacity-60">
                    <base-icon
                      :type="statusIcon"
                      size="21"
                      :color="statusColor"
                    />
                  </view>
                </view>

                <view>
                  <text class="block text-title text-text-primary-50">
                    {{ statusTitle }}
                  </text>
                  <text class="mt-2 block text-body text-text-primary-300">
                    {{ statusDescription }}
                  </text>

                  <view class="mt-5">
                    <view class="mb-2 box-border flex items-center justify-between">
                      <text class="text-caption text-primary-100">
                        资料完整度
                      </text>
                      <text class="text-caption text-warning-100">
                        {{ completionPercent }}%
                      </text>
                    </view>
                    <view class="h-2 overflow-hidden rounded-full bg-primary-100 bg-opacity-10">
                      <view
                        class="progress-bar h-2 rounded-full bg-warning-500"
                        :style="{ width: completionPercent + '%' }"
                      />
                    </view>
                  </view>
                </view>
              </view>
            </view>

            <view class="mb-5 box-border grid grid-cols-3 gap-3">
              <view class="metric-card box-border">
                <base-icon
                  type="camera-filled"
                  size="21"
                  color="#c3d0dd"
                />
                <text class="mt-2 block text-title text-text-primary-50">
                  {{ driverFiles.length }}
                </text>
                <text class="mt-1 block text-caption text-text-primary-400">
                  驾驶证
                </text>
              </view>
              <view class="metric-card box-border">
                <base-icon
                  type="images-filled"
                  size="21"
                  color="#c3d0dd"
                />
                <text class="mt-2 block text-title text-text-primary-50">
                  {{ vehicleFiles.length }}
                </text>
                <text class="mt-1 block text-caption text-text-primary-400">
                  行驶证
                </text>
              </view>
              <view class="metric-card box-border">
                <base-icon
                  type="folder-add-filled"
                  size="21"
                  color="#c3d0dd"
                />
                <text class="mt-2 block text-title text-text-primary-50">
                  {{ taxiFiles.length }}
                </text>
                <text class="mt-1 block text-caption text-text-primary-400">
                  营运证
                </text>
              </view>
            </view>

            <view class="upload-section mb-5 box-border">
              <view class="mb-4 box-border flex items-center justify-between">
                <view>
                  <text class="block text-subtitle text-text-primary-50">
                    证件影像资料
                  </text>
                  <text class="mt-1 block text-caption text-text-primary-400">
                    每类至少上传 1 张，最多 3 张
                  </text>
                </view>
                <view class="rounded-full border border-success-400 border-opacity-30 bg-success-500 bg-opacity-20 px-3 py-1">
                  <text class="text-caption text-success-100">
                    加密传输
                  </text>
                </view>
              </view>

              <view class="upload-card mb-4 box-border">
                <view class="mb-3 box-border flex items-center gap-3">
                  <view class="flex size-10 shrink-0 items-center justify-center rounded-2xl border border-primary-200 border-opacity-20 bg-primary-500 bg-opacity-20">
                    <base-icon
                      type="contact-filled"
                      size="21"
                      color="#c3d0dd"
                    />
                  </view>
                  <view class="box-border flex-1">
                    <text class="block text-subtitle text-text-primary-50">
                      驾驶证
                    </text>
                    <text class="mt-1 block text-caption text-text-primary-400">
                      请上传清晰、完整、无遮挡的驾驶证照片
                    </text>
                  </view>
                  <text class="text-caption text-warning-100">
                    {{ driverFiles.length }}/3
                  </text>
                </view>
                <view class="upload-wrap box-border">
                  <base-upload
                    v-model="driverFiles"
                    :limit="3"
                    mode="grid"
                    media-type="image"
                    :initial-files="driverInitialFiles"
                    @success="onDriverUploadSuccess"
                    @delete="onDriverUploadDelete"
                  />
                </view>
              </view>

              <view class="upload-card mb-4 box-border">
                <view class="mb-3 box-border flex items-center gap-3">
                  <view class="flex size-10 shrink-0 items-center justify-center rounded-2xl border border-primary-200 border-opacity-20 bg-primary-500 bg-opacity-20">
                    <base-icon
                      type="flag-filled"
                      size="21"
                      color="#c3d0dd"
                    />
                  </view>
                  <view class="box-border flex-1">
                    <text class="block text-subtitle text-text-primary-50">
                      行驶证
                    </text>
                    <text class="mt-1 block text-caption text-text-primary-400">
                      用于核验车辆身份与车牌信息
                    </text>
                  </view>
                  <text class="text-caption text-warning-100">
                    {{ vehicleFiles.length }}/3
                  </text>
                </view>
                <view class="upload-wrap box-border">
                  <base-upload
                    v-model="vehicleFiles"
                    :limit="3"
                    mode="grid"
                    media-type="image"
                    :initial-files="vehicleInitialFiles"
                    @success="onVehicleUploadSuccess"
                    @delete="onVehicleUploadDelete"
                  />
                </view>
              </view>

              <view class="upload-card box-border">
                <view class="mb-3 box-border flex items-center gap-3">
                  <view class="flex size-10 shrink-0 items-center justify-center rounded-2xl border border-primary-200 border-opacity-20 bg-primary-500 bg-opacity-20">
                    <base-icon
                      type="medal-filled"
                      size="21"
                      color="#c3d0dd"
                    />
                  </view>
                  <view class="box-border flex-1">
                    <text class="block text-subtitle text-text-primary-50">
                      营运证
                    </text>
                    <text class="mt-1 block text-caption text-text-primary-400">
                      确认车辆具备合规营运资质
                    </text>
                  </view>
                  <text class="text-caption text-warning-100">
                    {{ taxiFiles.length }}/3
                  </text>
                </view>
                <view class="upload-wrap box-border">
                  <base-upload
                    v-model="taxiFiles"
                    :limit="3"
                    mode="grid"
                    media-type="image"
                    :initial-files="taxiInitialFiles"
                    @success="onTaxiUploadSuccess"
                    @delete="onTaxiUploadDelete"
                  />
                </view>
              </view>
            </view>

            <view class="notice-card mb-5 box-border">
              <view class="box-border flex items-start gap-3">
                <view class="flex size-10 shrink-0 items-center justify-center rounded-2xl border border-warning-400 border-opacity-30 bg-warning-500 bg-opacity-20">
                  <base-icon
                    type="info-filled"
                    size="20"
                    color="#ffb400"
                  />
                </view>
                <view class="box-border flex-1">
                  <text class="block text-subtitle text-text-primary-50">
                    审核说明
                  </text>
                  <text class="mt-2 block text-body text-text-primary-300">
                    提交后资料将进入人工审核流程。请确保图片内容真实有效、边角完整、文字清晰，审核结果会同步至认证状态。
                  </text>
                  <view
                    v-if="currentRemark"
                    class="mt-3 box-border rounded-2xl border border-danger-400 border-opacity-20 bg-danger-500 bg-opacity-10 p-3"
                  >
                    <text class="block text-caption text-danger-100">
                      审核备注：{{ currentRemark }}
                    </text>
                  </view>
                </view>
              </view>
            </view>

            <view class="action-panel box-border">
              <view
                class="btn-core active-press"
                :class="{ 'btn-disabled': isSubmitting || isLoading }"
                @click="submitCertification"
              >
                <view class="btn-shine" />
                <base-icon
                  type="cloud-upload-filled"
                  size="20"
                  color="#f8fafc"
                />
                <text class="btn-text">
                  {{ submitButtonText }}
                </text>
              </view>

              <view class="mt-3 box-border grid grid-cols-2 gap-3">
                <view
                  class="btn-secondary active-press"
                  @click="refreshCertification"
                >
                  <base-icon
                    type="refresh-filled"
                    size="18"
                    color="#e2e8f0"
                  />
                  <text class="btn-secondary-text">
                    刷新状态
                  </text>
                </view>

                <view
                  class="btn-ghost active-press"
                  @click="goHome"
                >
                  <base-icon
                    type="navigate-filled"
                    size="18"
                    color="#ffb400"
                  />
                  <text class="btn-ghost-text">
                    进入首页
                  </text>
                </view>
              </view>
            </view>
          </view>
        </view>
      </scroll-view>

      <view
        v-if="isLoading"
        class="loading-mask absolute inset-0 z-50 flex items-center justify-center"
      >
        <view class="loading-card">
          <view class="loading-orbit">
            <view class="loading-ring" />
            <view class="loading-core">
              <base-icon
                type="locked-filled"
                size="25"
                color="#ffb400"
              />
            </view>
          </view>
          <text class="mt-5 block text-subtitle text-text-primary-50">
            正在同步认证状态
          </text>
          <text class="mt-2 block text-caption text-text-primary-400">
            请稍候，驾驶舱正在校验证件资料
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

const isLoading = ref(false);
const isSubmitting = ref(false);
const currentUser = ref(null);
const currentCert = ref(null);
const statusOptions = ref([]);

const driverFiles = ref([]);
const vehicleFiles = ref([]);
const taxiFiles = ref([]);

const driverInitialFiles = computed(() => makeInitialFiles(driverFiles.value));
const vehicleInitialFiles = computed(() => makeInitialFiles(vehicleFiles.value));
const taxiInitialFiles = computed(() => makeInitialFiles(taxiFiles.value));

const currentStatusId = computed(() => {
  if (!currentCert.value) {
    return 0;
  }
  return Number(currentCert.value.status_enum_cert_status_enum_id_1 || 0);
});

const currentRemark = computed(() => {
  if (!currentCert.value || !currentCert.value.remark) {
    return '';
  }
  return currentCert.value.remark;
});

const statusText = computed(() => {
  if (!currentCert.value) {
    return '未提交';
  }
  if (currentCert.value.status) {
    return currentCert.value.status;
  }
  const found = statusOptions.value.find(item => Number(item.cert_status_enum_id) == currentStatusId.value);
  if (found) {
    return found.status;
  }
  return '状态同步中';
});

const statusTitle = computed(() => {
  if (currentStatusId.value == 3) {
    return '认证已通过';
  }
  if (currentStatusId.value == 2) {
    return '资料审核中';
  }
  if (currentStatusId.value == 4) {
    return '认证未通过';
  }
  return '完成资料上传';
});

const statusDescription = computed(() => {
  if (currentStatusId.value == 3) {
    return '你的司机资质已完成核验，可以进入首页查看实时订单建议。';
  }
  if (currentStatusId.value == 2) {
    return '平台正在核验你的资料，审核期间仍可刷新查看最新状态。';
  }
  if (currentStatusId.value == 4) {
    return '请根据审核备注修正资料后重新提交，确保照片清晰完整。';
  }
  return '请依次上传驾驶证、行驶证与营运证，提交后进入审核流程。';
});

const statusIcon = computed(() => {
  if (currentStatusId.value == 3) {
    return 'checkbox-filled';
  }
  if (currentStatusId.value == 2) {
    return 'spinner-cycle';
  }
  if (currentStatusId.value == 4) {
    return 'close';
  }
  return 'cloud-upload-filled';
});

const statusColor = computed(() => {
  if (currentStatusId.value == 3) {
    return '#42b861';
  }
  if (currentStatusId.value == 2) {
    return '#ffb400';
  }
  if (currentStatusId.value == 4) {
    return '#f37373';
  }
  return '#c3d0dd';
});

const completionPercent = computed(() => {
  let total = 0;
  if (driverFiles.value.length > 0) {
    total += 1;
  }
  if (vehicleFiles.value.length > 0) {
    total += 1;
  }
  if (taxiFiles.value.length > 0) {
    total += 1;
  }
  return Math.round(total / 3 * 100);
});

const submitButtonText = computed(() => {
  if (isSubmitting.value) {
    return '正在提交';
  }
  if (currentStatusId.value == 4) {
    return '重新提交认证';
  }
  if (currentStatusId.value == 2) {
    return '更新认证资料';
  }
  if (currentStatusId.value == 3) {
    return '资料已认证，重新提交';
  }
  return '提交认证申请';
});

onLoad(() => {
  initPage();
});

onShow(() => {
  if (currentUser.value && currentUser.value.user_info_id) {
    loadCertification(false);
  }
});

async function initPage() {
  if (isLoading.value) {
    return;
  }
  isLoading.value = true;

  const userRes = await proxy.$app.auth.getUser();
  if (userRes.success && userRes.data) {
    currentUser.value = userRes.data;
  }

  await loadStatusOptions();

  if (currentUser.value && currentUser.value.user_info_id) {
    await loadCertification(false);
  }

  isLoading.value = false;
}

async function loadStatusOptions() {
  const res = await proxy.$app.db.from('cert_status_enum').list().order('cert_status_enum_id', 'asc');
  if (res.success && Array.isArray(res.data)) {
    statusOptions.value = res.data;
  }
}

async function loadCertification(showLoading) {
  if (!currentUser.value || !currentUser.value.user_info_id) {
    return;
  }

  if (showLoading) {
    isLoading.value = true;
  }

  const res = await proxy.$app.db
    .from('user_certification')
    .page()
    .page(1, 1)
    .eq('user_info_user_info_id_1', Number(currentUser.value.user_info_id))
    .order('user_certification_id', 'desc');

  if (res.success && res.data && Array.isArray(res.data.records) && res.data.records.length > 0) {
    currentCert.value = res.data.records[0];
    driverFiles.value = normalizeFiles(currentCert.value.driver_license);
    vehicleFiles.value = normalizeFiles(currentCert.value.vehicle_license);
    taxiFiles.value = normalizeFiles(currentCert.value.taxi_license);
  }

  if (showLoading) {
    isLoading.value = false;
  }
}

async function refreshCertification() {
  if (isLoading.value || isSubmitting.value) {
    return;
  }
  await loadCertification(true);
  await proxy.$app.ui.toast().level('success').duration(1800).message('认证状态已刷新');
}

async function submitCertification() {
  if (isSubmitting.value || isLoading.value) {
    return;
  }

  if (!currentUser.value || !currentUser.value.user_info_id) {
    await proxy.$app.ui.toast().level('error').duration(2200).message('未获取到登录用户信息');
    return;
  }

  if (driverFiles.value.length < 1) {
    await proxy.$app.ui.toast().level('error').duration(2200).message('请至少上传 1 张驾驶证照片');
    return;
  }

  if (vehicleFiles.value.length < 1) {
    await proxy.$app.ui.toast().level('error').duration(2200).message('请至少上传 1 张行驶证照片');
    return;
  }

  if (taxiFiles.value.length < 1) {
    await proxy.$app.ui.toast().level('error').duration(2200).message('请至少上传 1 张营运证照片');
    return;
  }

  isSubmitting.value = true;
  proxy.$app.ui.loading.showLoading('正在提交认证');

  const payload = {
    user_info_user_info_id_1: Number(currentUser.value.user_info_id),
    driver_license: toSaveFiles(driverFiles.value),
    vehicle_license: toSaveFiles(vehicleFiles.value),
    taxi_license: toSaveFiles(taxiFiles.value),
    status_enum_cert_status_enum_id_1: 2,
    remark: '',
  };

  let result = null;

  if (currentCert.value && currentCert.value.user_certification_id) {
    result = await proxy.$app.db
      .from('user_certification')
      .update()
      .set(payload)
      .eq('user_certification_id', Number(currentCert.value.user_certification_id));
  } else {
    result = await proxy.$app.db
      .from('user_certification')
      .insert()
      .values(payload);
  }

  proxy.$app.ui.loading.hideLoading();
  isSubmitting.value = false;

  if (result && result.success) {
    await proxy.$app.ui.toast().level('success').duration(1800).message('认证资料已提交，进入审核中');
    await loadCertification(false);
    setTimeout(() => {
      goHome();
    }, 700);
  } else {
    await proxy.$app.ui.toast().level('error').duration(2400).message(result && result.message ? result.message : '提交失败，请稍后再试');
  }
}

function goHome() {
  proxy.$app.router.navigate.to().url('/pages/home/index').type('page');
}

function onDriverUploadSuccess(e) {
  handleUploadSuccess(e, driverFiles);
}

function onVehicleUploadSuccess(e) {
  handleUploadSuccess(e, vehicleFiles);
}

function onTaxiUploadSuccess(e) {
  handleUploadSuccess(e, taxiFiles);
}

function onDriverUploadDelete(e) {
  handleUploadDelete(e, driverFiles);
}

function onVehicleUploadDelete(e) {
  handleUploadDelete(e, vehicleFiles);
}

function onTaxiUploadDelete(e) {
  handleUploadDelete(e, taxiFiles);
}

function handleUploadSuccess(e, targetRef) {
  if (!e || !e.success || !e.data || !e.data.url) {
    proxy.$app.ui.toast().level('error').duration(1800).message('图片上传失败');
    return;
  }

  const exists = targetRef.value.some(item => item && item.url == e.data.url);
  if (!exists) {
    targetRef.value.push({
      url: e.data.url,
    });
  }
}

function handleUploadDelete(e, targetRef) {
  if (!e || e.index == undefined || e.index == null) {
    return;
  }
  targetRef.value.splice(Number(e.index), 1);
}

function normalizeFiles(files) {
  if (!Array.isArray(files)) {
    return [];
  }
  return files
    .filter(item => item && item.url)
    .map(item => {
      return {
        url: item.url,
      };
    });
}

function toSaveFiles(files) {
  if (!Array.isArray(files)) {
    return [];
  }
  return files
    .filter(item => item && item.url)
    .map(item => {
      return {
        url: item.url,
      };
    });
}

function makeInitialFiles(files) {
  if (!Array.isArray(files)) {
    return [];
  }
  return files
    .filter(item => item && item.url)
    .map(item => {
      return {
        url: getFullFileUrl(item.url),
      };
    });
}

function getFullFileUrl(url) {
  if (!url) {
    return '';
  }
  if (String(url).indexOf('http') == 0) {
    return url;
  }
  return proxy.get_resource_url(url);
}
</script>

<style scoped>
.halo-one {
  transform: translate(-35%, -30%);
}

.halo-two {
  transform: translate(28%, 15%);
}

.halo-three {
  transform: translate(20%, -10%);
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
  height: 250px;
  border-radius: 32px;
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.18);
  box-shadow:
    0 24px 70px rgba(13, 27, 42, 0.62),
    inset 0 1px 0 rgba(248, 250, 252, 0.14),
    inset 0 -1px 0 rgba(13, 27, 42, 0.35);
}

.metric-card {
  min-height: 108px;
  padding: 14px 10px;
  border-radius: 26px;
  text-align: center;
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.16);
  box-shadow:
    0 16px 42px rgba(13, 27, 42, 0.42),
    inset 0 1px 0 rgba(248, 250, 252, 0.12);
}

.upload-section {
  padding: 18px;
  border-radius: 32px;
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.18);
  box-shadow:
    0 24px 70px rgba(13, 27, 42, 0.56),
    inset 0 1px 0 rgba(248, 250, 252, 0.14);
}

.upload-card {
  padding: 15px;
  border-radius: 28px;
  background: rgba(13, 27, 42, 0.52);
  border: 1px solid rgba(195, 208, 221, 0.14);
  box-shadow:
    inset 0 1px 0 rgba(248, 250, 252, 0.08),
    0 12px 34px rgba(13, 27, 42, 0.28);
}

.upload-wrap {
  padding: 12px;
  border-radius: 24px;
  background: rgba(13, 27, 42, 0.42);
  border: 1px dashed rgba(195, 208, 221, 0.22);
}

.notice-card {
  padding: 16px;
  border-radius: 30px;
  background: rgba(225, 231, 238, 0.09);
  border: 1px solid rgba(255, 180, 0, 0.18);
  box-shadow:
    0 18px 48px rgba(13, 27, 42, 0.46),
    inset 0 1px 0 rgba(255, 240, 204, 0.1);
}

.action-panel {
  padding: 16px;
  border-radius: 30px;
  background: rgba(13, 27, 42, 0.5);
  border: 1px solid rgba(195, 208, 221, 0.16);
  box-shadow:
    0 18px 48px rgba(13, 27, 42, 0.46),
    inset 0 1px 0 rgba(248, 250, 252, 0.1);
}

.active-press:active {
  transform: scale(0.985);
  opacity: 0.92;
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

.btn-disabled {
  opacity: 0.55;
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

.btn-secondary {
  min-height: 50px;
  border-radius: 22px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 9px;
  background: rgba(225, 231, 238, 0.12);
  border: 1px solid rgba(195, 208, 221, 0.18);
  box-shadow:
    0 14px 36px rgba(13, 27, 42, 0.42),
    inset 0 1px 0 rgba(248, 250, 252, 0.14);
}

.btn-secondary-text {
  font-size: 0.8rem;
  line-height: 1.2rem;
  font-weight: 600;
  color: #e2e8f0;
}

.btn-ghost {
  min-height: 50px;
  border-radius: 22px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 9px;
  background: rgba(13, 27, 42, 0.36);
  border: 1px solid rgba(255, 180, 0, 0.34);
  box-shadow:
    0 14px 36px rgba(13, 27, 42, 0.36),
    inset 0 1px 0 rgba(255, 240, 204, 0.12);
}

.btn-ghost-text {
  font-size: 0.8rem;
  line-height: 1.2rem;
  font-weight: 600;
  color: #fff0cc;
}

.progress-bar {
  transition: width 0.35s ease;
}

.loading-mask {
  background:
    linear-gradient(180deg, rgba(13, 27, 42, 0.72), rgba(13, 27, 42, 0.92)),
    linear-gradient(135deg, rgba(40, 56, 69, 0.45), rgba(13, 27, 42, 0.88));
}

.loading-card {
  width: 240px;
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

:deep(.uni-input-input) {
  color: #f8fafc;
  font-size: 0.8rem;
}

:deep(.uni-input-placeholder) {
  color: #64748b;
}

:deep(.uni-textarea-textarea) {
  color: #f8fafc;
  font-size: 0.8rem;
  line-height: 1.2rem;
}

:deep(.uni-textarea-placeholder) {
  color: #64748b;
}
</style>