<template>
  <base-layout>
    <view class="relative box-border min-h-screen w-full overflow-hidden bg-secondary-900 pb-20 text-text-primary-50">
      <view class="absolute inset-0 bg-gradient-to-b from-secondary-800 via-secondary-900 to-secondary-900" />
      <view class="halo-one absolute left-0 top-0 size-72 rounded-full bg-primary-500 opacity-25" />
      <view class="halo-two absolute bottom-24 right-0 size-80 rounded-full bg-success-500 opacity-20" />
      <view class="halo-three absolute right-8 top-80 size-56 rounded-full bg-warning-500 opacity-20" />
      <view class="cockpit-grid absolute inset-0 opacity-30" />
      <view class="cockpit-vignette absolute inset-0" />

      <scroll-view
        scroll-y
        class="relative z-10 box-border h-screen w-full"
        refresher-enabled
        :refresher-triggered="refreshing"
        @refresherrefresh="onPullRefresh"
      >
        <view class="box-border w-full p-2 pb-24 pt-12">
          <view class="mb-5 px-3">
            <view class="flex items-center justify-between">
              <view class="box-border flex-1 pr-3">
                <text class="block truncate text-overline tracking-widest text-primary-200">
                  DRIVER RELIEF NAVIGATION
                </text>
                <text class="mt-2 block truncate text-hero text-text-primary-50">
                  厕所停车导航
                </text>
                <text class="mt-2 block text-body text-text-primary-300">
                  一键找到附近开放公厕与可停车点位
                </text>
              </view>
              <view class="flex size-14 items-center justify-center rounded-3xl border border-primary-100 border-opacity-20 bg-primary-100 bg-opacity-10 shadow-xl">
                <base-icon
                  type="map-filled"
                  size="28"
                  color="#ffb400"
                />
              </view>
            </view>
          </view>

          <view
            class="hero-card active-card relative mx-3 mb-4 overflow-hidden rounded-3xl"
            @click="focusCurrentLocation"
          >
            <image
              class="absolute inset-0 size-full opacity-35"
              mode="aspectFill"
              src="https://www.codeflying.net/preview/city-bridge.jpg"
            />
            <view class="absolute inset-0 bg-gradient-to-r from-secondary-900 via-secondary-900 to-transparent opacity-90" />
            <view class="relative z-10 p-5">
              <view class="flex items-start justify-between gap-3">
                <view class="box-border flex-1">
                  <view class="mb-4 inline-flex items-center rounded-full border border-success-400 border-opacity-30 bg-success-500 bg-opacity-20 px-3 py-1">
                    <view class="pulse-dot mr-2 size-2 rounded-full bg-success-400" />
                    <text class="text-caption text-success-100">
                      实时定位已接入
                    </text>
                  </view>
                  <text class="block text-title text-text-primary-50">
                    附近服务点
                  </text>
                  <view class="mt-2 flex items-end gap-2">
                    <text class="text-hero text-warning-500">
                      {{ visiblePlaces.length }}
                    </text>
                    <text class="mb-1 text-body text-text-primary-300">
                      个可用点位
                    </text>
                  </view>
                </view>
                <view class="flex size-16 items-center justify-center rounded-3xl border border-primary-200 border-opacity-20 bg-secondary-900 bg-opacity-70">
                  <base-icon
                    type="location-filled"
                    size="31"
                    color="#c3d0dd"
                  />
                </view>
              </view>

              <view class="mt-5 grid grid-cols-3 gap-3">
                <view class="metric-card">
                  <text class="block text-caption text-text-primary-400">
                    开放中
                  </text>
                  <text class="mt-1 block text-subtitle text-success-300">
                    {{ openCount }}
                  </text>
                </view>
                <view class="metric-card">
                  <text class="block text-caption text-text-primary-400">
                    有车位
                  </text>
                  <text class="mt-1 block text-subtitle text-warning-300">
                    {{ parkingCount }}
                  </text>
                </view>
                <view class="metric-card">
                  <text class="block text-caption text-text-primary-400">
                    最近距离
                  </text>
                  <text class="mt-1 block text-subtitle text-primary-100">
                    {{ nearestDistanceText }}
                  </text>
                </view>
              </view>
            </view>
          </view>

          <view class="mx-3 mb-4 rounded-3xl border border-primary-100 border-opacity-20 bg-primary-100 bg-opacity-10 p-3 shadow-xl">
            <view class="flex items-center justify-between gap-3">
              <view class="box-border flex-1">
                <text class="block text-subtitle text-text-primary-50">
                  智能筛选
                </text>
                <text class="mt-1 block text-caption text-text-primary-400">
                  仅查看有免费车位且开放的点位
                </text>
              </view>
              <view
                class="filter-switch"
                :class="onlyParking ? 'filter-switch-on' : 'filter-switch-off'"
                @click="toggleOnlyParking"
              >
                <view
                  class="filter-knob"
                  :class="onlyParking ? 'filter-knob-on' : 'filter-knob-off'"
                />
              </view>
            </view>
          </view>

          <view class="map-panel mx-3 mb-4">
            <view class="flex items-center justify-between p-4 pb-3">
              <view class="box-border flex flex-1 items-center gap-3">
                <view class="flex size-10 items-center justify-center rounded-2xl border border-primary-200 border-opacity-20 bg-primary-500 bg-opacity-20">
                  <base-icon
                    type="navigate-filled"
                    size="20"
                    color="#ffb400"
                  />
                </view>
                <view class="box-border flex-1">
                  <text class="block truncate text-subtitle text-text-primary-50">
                    点位地图
                  </text>
                  <text class="mt-1 block truncate text-caption text-text-primary-400">
                    点击标记可选择目的地
                  </text>
                </view>
              </view>
              <view class="rounded-full border border-primary-200 border-opacity-20 bg-secondary-900 bg-opacity-70 px-3 py-1">
                <text class="text-caption text-primary-100">
                  {{ mapScale }}级
                </text>
              </view>
            </view>

            <view class="map-wrap">
              <base-map-view
                class="map-view w-full"
                :longitude="mapLongitude"
                :latitude="mapLatitude"
                :scale="mapScale"
                :markers="markers"
                :polyline="[]"
                @markerTapClick="onMarkerTap"
                @mapTap="onMapTap"
              />
            </view>
          </view>

          <view class="mx-3 mb-4 flex items-center gap-3">
            <view
              class="refresh-card active-card flex-1"
              @click="refreshData"
            >
              <base-icon
                type="refresh-filled"
                size="20"
                color="#c3d0dd"
              />
              <text class="ml-2 text-subtitle text-text-primary-50">
                刷新点位
              </text>
            </view>
            <view
              class="mini-action active-card"
              @click="focusCurrentLocation"
            >
              <base-icon
                type="map-pin-ellipse"
                size="22"
                color="#ffb400"
              />
            </view>
          </view>

          <view
            v-if="isLoading"
            class="loading-card mx-3 mb-4"
          >
            <view class="loading-orbit">
              <view class="loading-ring" />
              <view class="loading-core">
                <base-icon
                  type="map-filled"
                  size="22"
                  color="#ffb400"
                />
              </view>
            </view>
            <view class="box-border flex-1">
              <text class="block text-subtitle text-text-primary-50">
                正在同步附近点位
              </text>
              <text class="mt-1 block text-caption text-text-primary-400">
                请稍候，正在校准位置与停车信息
              </text>
            </view>
          </view>

          <view class="mx-3 mb-3 flex items-end justify-between">
            <view>
              <text class="block text-overline tracking-widest text-primary-200">
                NEARBY STOPS
              </text>
              <text class="mt-1 block text-title text-text-primary-50">
                推荐点位
              </text>
            </view>
            <view class="rounded-full border border-primary-100 border-opacity-20 bg-primary-100 bg-opacity-10 px-3 py-1">
              <text class="text-caption text-primary-100">
                {{ onlyParking ? '车位优先' : '全部展示' }}
              </text>
            </view>
          </view>

          <view
            v-if="visiblePlaces.length == 0 && !isLoading"
            class="empty-card mx-3"
          >
            <view class="mx-auto flex size-16 items-center justify-center rounded-3xl border border-primary-200 border-opacity-20 bg-primary-500 bg-opacity-20">
              <base-icon
                type="info-filled"
                size="30"
                color="#c3d0dd"
              />
            </view>
            <text class="mt-4 block text-center text-title text-text-primary-50">
              暂无匹配点位
            </text>
            <text class="mt-2 block text-center text-body text-text-primary-400">
              可关闭筛选或点击刷新重新获取附近信息
            </text>
            <view
              class="empty-action active-card mt-5"
              @click="toggleOnlyParking"
            >
              <text class="text-subtitle text-warning-100">
                调整筛选条件
              </text>
            </view>
          </view>

          <view
            v-for="item in visiblePlaces"
            :key="item.toilet_parking_id"
            class="place-card active-card mx-3 mb-4"
            @click="selectPlace(item)"
          >
            <view class="flex items-start gap-4">
              <view
                class="place-icon-wrap"
                :class="item.is_open ? 'place-icon-open' : 'place-icon-close'"
              >
                <base-icon
                  :type="item.has_parking ? 'map-pin' : 'location-filled'"
                  size="25"
                  :color="item.is_open ? '#ffb400' : '#94a3b8'"
                />
              </view>

              <view class="box-border min-w-0 flex-1">
                <view class="flex items-start justify-between gap-3">
                  <view class="box-border min-w-0 flex-1">
                    <text class="block truncate text-subtitle text-text-primary-50">
                      {{ item.name }}
                    </text>
                    <text class="mt-1 block truncate text-caption text-text-primary-400">
                      距离约 {{ formatDistance(item.distanceValue) }} · {{ item.has_parking ? '支持免费停车' : '暂无停车信息' }}
                    </text>
                  </view>
                  <view
                    class="status-pill"
                    :class="item.is_open ? 'status-open' : 'status-close'"
                  >
                    <text class="text-caption">
                      {{ item.is_open ? '开放' : '关闭' }}
                    </text>
                  </view>
                </view>

                <view class="mt-4 grid grid-cols-3 gap-2">
                  <view class="info-chip">
                    <text class="block text-caption text-text-primary-500">
                      车位
                    </text>
                    <text class="mt-1 block text-body text-text-primary-50">
                      {{ item.parking_count || 0 }} 个
                    </text>
                  </view>
                  <view class="info-chip">
                    <text class="block text-caption text-text-primary-500">
                      经度
                    </text>
                    <text class="mt-1 block truncate text-body text-text-primary-50">
                      {{ formatCoordinate(item.longitude) }}
                    </text>
                  </view>
                  <view class="info-chip">
                    <text class="block text-caption text-text-primary-500">
                      纬度
                    </text>
                    <text class="mt-1 block truncate text-body text-text-primary-50">
                      {{ formatCoordinate(item.latitude) }}
                    </text>
                  </view>
                </view>

                <view class="mt-4 flex items-center gap-3">
                  <view
                    class="nav-primary active-card"
                    @click.stop="openNavSheet(item)"
                  >
                    <base-icon
                      type="navigate-filled"
                      size="18"
                      color="#f8fafc"
                    />
                    <text class="ml-2 text-body text-text-primary-50">
                      立即导航
                    </text>
                  </view>
                  <view
                    class="nav-secondary active-card"
                    @click.stop="centerPlace(item)"
                  >
                    <base-icon
                      type="map-filled"
                      size="18"
                      color="#c3d0dd"
                    />
                    <text class="ml-2 text-body text-primary-100">
                      查看地图
                    </text>
                  </view>
                </view>
              </view>
            </view>
          </view>

          <view class="mx-3 mb-4 mt-2 rounded-3xl border border-primary-100 border-opacity-10 bg-secondary-800 bg-opacity-70 p-5">
            <view class="flex items-start gap-3">
              <view class="flex size-10 items-center justify-center rounded-2xl bg-warning-500 bg-opacity-20">
                <base-icon
                  type="help-filled"
                  size="20"
                  color="#ffb400"
                />
              </view>
              <view class="box-border flex-1">
                <text class="block text-subtitle text-text-primary-50">
                  驾驶员提示
                </text>
                <text class="mt-2 block text-body text-text-primary-400">
                  点位数据会根据当前位置计算距离。出发前建议优先选择“开放中”且“有免费车位”的站点，减少绕行时间。
                </text>
              </view>
            </view>
          </view>
        </view>
      </scroll-view>

      <uni-popup
        ref="navPopup"
        type="bottom"
      >
        <view class="nav-popup rounded-t-xl border border-primary-100 border-opacity-20 bg-secondary-900 p-4">
          <view class="mb-4 flex items-center justify-between">
            <view class="box-border flex-1 pr-3">
              <text class="block text-title text-text-primary-50">
                选择导航软件
              </text>
              <text class="mt-1 block truncate text-caption text-text-primary-400">
                {{ selectedPlace ? selectedPlace.name : '请选择目的地' }}
              </text>
            </view>
            <view
              class="flex size-11 items-center justify-center rounded-3xl bg-primary-500 bg-opacity-20"
              @click="closeNavSheet"
            >
              <base-icon
                type="close"
                size="20"
                color="#c3d0dd"
              />
            </view>
          </view>

          <view class="space-y-3">
            <view
              class="popup-action popup-action-primary active-card"
              @click="startNav('amap')"
            >
              <base-icon
                type="navigate-filled"
                size="22"
                color="#f8fafc"
              />
              <text class="ml-3 text-subtitle text-text-primary-50">
                高德地图导航
              </text>
              <base-icon
                type="right"
                size="17"
                color="#e2e8f0"
              />
            </view>

            <view
              class="popup-action active-card"
              @click="startNav('baidu')"
            >
              <base-icon
                type="map-filled"
                size="22"
                color="#c3d0dd"
              />
              <text class="ml-3 text-subtitle text-primary-100">
                百度地图导航
              </text>
              <base-icon
                type="right"
                size="17"
                color="#94a3b8"
              />
            </view>

            <view
              class="popup-action active-card"
              @click="startNav('qqmap')"
            >
              <base-icon
                type="location-filled"
                size="22"
                color="#c3d0dd"
              />
              <text class="ml-3 text-subtitle text-primary-100">
                腾讯地图导航
              </text>
              <base-icon
                type="right"
                size="17"
                color="#94a3b8"
              />
            </view>

            <view
              class="popup-cancel active-card"
              @click="closeNavSheet"
            >
              <text class="text-subtitle text-text-primary-400">
                取消
              </text>
            </view>
          </view>
        </view>
      </uni-popup>
    </view>
  </base-layout>
</template>

<script setup>
const { proxy } = getCurrentInstance();

const isLoading = ref(false);
const refreshing = ref(false);
const onlyParking = ref(false);
const places = ref([]);
const selectedPlace = ref(null);
const mapLatitude = ref(31.2304);
const mapLongitude = ref(121.4737);
const mapScale = ref(15);
const currentLatitude = ref(31.2304);
const currentLongitude = ref(121.4737);
const markers = ref([]);
const lastRefreshTime = ref(0);

const visiblePlaces = computed(() => {
  let arr = places.value || [];
  if (onlyParking.value) {
    arr = arr.filter(item => item.has_parking && item.is_open && Number(item.parking_count || 0) > 0);
  }
  return arr.slice().sort((a, b) => Number(a.distanceValue || 999999) - Number(b.distanceValue || 999999));
});

const openCount = computed(() => {
  return places.value.filter(item => item.is_open).length;
});

const parkingCount = computed(() => {
  return places.value.filter(item => item.has_parking && Number(item.parking_count || 0) > 0).length;
});

const nearestDistanceText = computed(() => {
  if (!visiblePlaces.value.length) {
    return '--';
  }
  return formatDistance(visiblePlaces.value[0].distanceValue);
});

onLoad(() => {
  initPage();
});

async function initPage() {
  if (isLoading.value) {
    return;
  }
  isLoading.value = true;
  const loc = await proxy.$app.location.getCurrentLocation({ isHighAccuracy: true });
  if (loc && loc.success) {
    currentLatitude.value = Number(loc.latitude);
    currentLongitude.value = Number(loc.longitude);
    mapLatitude.value = Number(loc.latitude);
    mapLongitude.value = Number(loc.longitude);
  }
  await loadPlaces();
  isLoading.value = false;
}

async function loadPlaces() {
  const result = await proxy.$app.db.from('toilet_parking').page().page(1, 50).order('toilet_parking_id', 'asc');
  if (result && result.success && result.data && result.data.records) {
    places.value = result.data.records.map(item => {
      const distanceValue = calcDistance(
        currentLatitude.value,
        currentLongitude.value,
        Number(item.latitude),
        Number(item.longitude),
      );
      return {
        toilet_parking_id: item.toilet_parking_id,
        name: item.name,
        latitude: Number(item.latitude),
        longitude: Number(item.longitude),
        has_parking: Boolean(item.has_parking),
        parking_count: Number(item.parking_count || 0),
        is_open: Boolean(item.is_open),
        distanceValue: distanceValue,
      };
    });
    buildMarkers();
  }
}

function buildMarkers() {
  const markerList = [
    {
      id: 0,
      markerId: 0,
      latitude: currentLatitude.value,
      longitude: currentLongitude.value,
      iconPath: '/static/mark_icon.png',
      width: 28,
      height: 28,
      title: '当前位置',
    },
  ];

  visiblePlaces.value.forEach(item => {
    markerList.push({
      id: Number(item.toilet_parking_id),
      markerId: Number(item.toilet_parking_id),
      latitude: Number(item.latitude),
      longitude: Number(item.longitude),
      iconPath: '/static/mark_icon.png',
      width: item.has_parking ? 34 : 30,
      height: item.has_parking ? 34 : 30,
      title: item.name,
    });
  });

  markers.value = markerList;
}

function toggleOnlyParking() {
  onlyParking.value = !onlyParking.value;
  buildMarkers();
  proxy.$app.ui.toast()
    .level('success')
    .duration(1600)
    .message(onlyParking.value ? '已开启车位优先筛选' : '已展示全部附近点位');
}

async function refreshData() {
  const now = Date.now();
  if (now - lastRefreshTime.value < 1200) {
    proxy.$app.ui.toast()
      .level('none')
      .duration(1200)
      .message('刷新太频繁，请稍候');
    return;
  }
  lastRefreshTime.value = now;
  if (isLoading.value) {
    return;
  }
  isLoading.value = true;
  const loc = await proxy.$app.location.getCurrentLocation({ isHighAccuracy: true });
  if (loc && loc.success) {
    currentLatitude.value = Number(loc.latitude);
    currentLongitude.value = Number(loc.longitude);
  }
  await loadPlaces();
  focusCurrentLocation();
  isLoading.value = false;
  proxy.$app.ui.toast()
    .level('success')
    .duration(1800)
    .message('附近点位已刷新');
}

async function onPullRefresh() {
  refreshing.value = true;
  await refreshData();
  refreshing.value = false;
}

function focusCurrentLocation() {
  mapLatitude.value = currentLatitude.value;
  mapLongitude.value = currentLongitude.value;
  mapScale.value = 15;
}

function selectPlace(item) {
  selectedPlace.value = item;
  centerPlace(item);
}

function centerPlace(item) {
  selectedPlace.value = item;
  mapLatitude.value = Number(item.latitude);
  mapLongitude.value = Number(item.longitude);
  mapScale.value = 17;
}

function onMarkerTap(e) {
  const markerId = Number(e.markerId || e.id || 0);
  if (markerId == 0) {
    focusCurrentLocation();
    return;
  }
  const target = places.value.find(item => Number(item.toilet_parking_id) == markerId);
  if (target) {
    selectPlace(target);
    proxy.$app.ui.toast()
      .level('none')
      .duration(1500)
      .message('已选中：' + target.name);
  }
}

function onMapTap() {
  proxy.$app.ui.toast()
    .level('none')
    .duration(1200)
    .message('请点击地图标记选择点位');
}

function openNavSheet(item) {
  selectedPlace.value = item;
  centerPlace(item);
  nextTick(() => {
    proxy.$refs.navPopup.open();
  });
}

function closeNavSheet() {
  proxy.$refs.navPopup.close();
}

async function startNav(type) {
  if (!selectedPlace.value) {
    proxy.$app.ui.toast()
      .level('error')
      .duration(1800)
      .message('请先选择目的地');
    return;
  }

  const target = selectedPlace.value;
  if (type == 'amap') {
    await proxy.$app.nav.amap().lat(Number(target.latitude)).lng(Number(target.longitude)).address(target.name);
  }
  if (type == 'baidu') {
    await proxy.$app.nav.baidu().lat(Number(target.latitude)).lng(Number(target.longitude)).address(target.name);
  }
  if (type == 'qqmap') {
    await proxy.$app.nav.qqmap().lat(Number(target.latitude)).lng(Number(target.longitude)).address(target.name);
  }

  closeNavSheet();
}

function calcDistance(lat1, lng1, lat2, lng2) {
  const radLat1 = lat1 * Math.PI / 180;
  const radLat2 = lat2 * Math.PI / 180;
  const a = radLat1 - radLat2;
  const b = lng1 * Math.PI / 180 - lng2 * Math.PI / 180;
  const s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
  return s * 6378.137;
}

function formatDistance(distance) {
  const value = Number(distance || 0);
  if (value < 1) {
    return Math.round(value * 1000) + '米';
  }
  return value.toFixed(1) + '公里';
}

function formatCoordinate(value) {
  return Number(value || 0).toFixed(4);
}
</script>

<style scoped>
.halo-one {
  filter: blur(58px);
  transform: translate(-35%, -30%);
}

.halo-two {
  filter: blur(62px);
  transform: translate(28%, 15%);
}

.halo-three {
  filter: blur(54px);
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
    linear-gradient(180deg, rgba(13, 27, 42, 0.1), rgba(13, 27, 42, 0.92)),
    linear-gradient(90deg, rgba(13, 27, 42, 0.72), transparent 48%, rgba(13, 27, 42, 0.74));
}

.hero-card {
  min-height: 238px;
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.18);
  box-shadow:
    0 24px 70px rgba(13, 27, 42, 0.62),
    inset 0 1px 0 rgba(248, 250, 252, 0.14);
}

.metric-card {
  padding: 10px;
  border-radius: 20px;
  background: rgba(13, 27, 42, 0.58);
  border: 1px solid rgba(195, 208, 221, 0.12);
  box-shadow:
    inset 0 1px 0 rgba(248, 250, 252, 0.08),
    0 10px 26px rgba(13, 27, 42, 0.32);
}

.map-panel {
  overflow: hidden;
  border-radius: 30px;
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.18);
  box-shadow:
    0 22px 66px rgba(13, 27, 42, 0.55),
    inset 0 1px 0 rgba(248, 250, 252, 0.12);
}

.map-wrap {
  height: 320px;
  width: 100%;
  overflow: hidden;
  border-radius: 26px;
  border-top: 1px solid rgba(195, 208, 221, 0.1);
  background: #1d2838;
}

.map-view {
  height: 320px;
}

.filter-switch {
  width: 58px;
  height: 34px;
  border-radius: 999px;
  padding: 3px;
  box-sizing: border-box;
  transition: all 0.22s ease;
}

.filter-switch-on {
  background: linear-gradient(135deg, #42b861, #286e3b);
  box-shadow: 0 12px 26px rgba(66, 184, 97, 0.25);
}

.filter-switch-off {
  background: rgba(13, 27, 42, 0.72);
  border: 1px solid rgba(195, 208, 221, 0.18);
}

.filter-knob {
  width: 28px;
  height: 28px;
  border-radius: 999px;
  background: #f8fafc;
  transition: all 0.22s ease;
  box-shadow: 0 8px 18px rgba(13, 27, 42, 0.35);
}

.filter-knob-on {
  transform: translateX(24px);
}

.filter-knob-off {
  transform: translateX(0);
}

.refresh-card {
  height: 54px;
  border-radius: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.16);
  box-shadow:
    0 14px 36px rgba(13, 27, 42, 0.42),
    inset 0 1px 0 rgba(248, 250, 252, 0.12);
}

.mini-action {
  width: 54px;
  height: 54px;
  border-radius: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(255, 180, 0, 0.16);
  border: 1px solid rgba(255, 180, 0, 0.28);
  box-shadow:
    0 14px 36px rgba(13, 27, 42, 0.36),
    inset 0 1px 0 rgba(255, 240, 204, 0.12);
}

.loading-card {
  display: flex;
  align-items: center;
  gap: 14px;
  padding: 16px;
  border-radius: 28px;
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.18);
  box-shadow:
    0 18px 48px rgba(13, 27, 42, 0.48),
    inset 0 1px 0 rgba(248, 250, 252, 0.12);
}

.loading-orbit {
  position: relative;
  width: 54px;
  height: 54px;
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
}

.loading-core {
  width: 40px;
  height: 40px;
  border-radius: 18px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(13, 27, 42, 0.72);
  border: 1px solid rgba(255, 180, 0, 0.26);
}

.place-card {
  overflow: hidden;
  padding: 16px;
  border-radius: 30px;
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.16);
  box-shadow:
    0 18px 48px rgba(13, 27, 42, 0.48),
    inset 0 1px 0 rgba(248, 250, 252, 0.12);
}

.place-icon-wrap {
  width: 58px;
  height: 58px;
  border-radius: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}

.place-icon-open {
  background: rgba(255, 180, 0, 0.16);
  border: 1px solid rgba(255, 180, 0, 0.28);
}

.place-icon-close {
  background: rgba(100, 116, 139, 0.14);
  border: 1px solid rgba(148, 163, 184, 0.18);
}

.status-pill {
  padding: 4px 10px;
  border-radius: 999px;
  flex-shrink: 0;
}

.status-open {
  color: #dbf0e0;
  background: rgba(66, 184, 97, 0.18);
  border: 1px solid rgba(103, 198, 127, 0.26);
}

.status-close {
  color: #cbd5e1;
  background: rgba(100, 116, 139, 0.16);
  border: 1px solid rgba(148, 163, 184, 0.2);
}

.info-chip {
  padding: 9px 8px;
  border-radius: 18px;
  background: rgba(13, 27, 42, 0.48);
  border: 1px solid rgba(195, 208, 221, 0.1);
  min-width: 0;
}

.nav-primary {
  flex: 1;
  height: 42px;
  border-radius: 19px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #5a7c9e, #283845 54%, #0d1b2a);
  box-shadow:
    0 12px 28px rgba(13, 27, 42, 0.42),
    inset 0 1px 0 rgba(248, 250, 252, 0.18);
}

.nav-secondary {
  flex: 1;
  height: 42px;
  border-radius: 19px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(225, 231, 238, 0.08);
  border: 1px solid rgba(195, 208, 221, 0.14);
}

.empty-card {
  padding: 28px 18px;
  border-radius: 32px;
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.16);
  box-shadow:
    0 18px 48px rgba(13, 27, 42, 0.48),
    inset 0 1px 0 rgba(248, 250, 252, 0.12);
}

.empty-action {
  height: 48px;
  border-radius: 22px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(255, 180, 0, 0.16);
  border: 1px solid rgba(255, 180, 0, 0.28);
}

.nav-popup {
  padding-bottom: 34px;
  box-shadow:
    0 -22px 66px rgba(13, 27, 42, 0.72),
    inset 0 1px 0 rgba(248, 250, 252, 0.14);
}

.popup-action {
  height: 56px;
  border-radius: 24px;
  padding: 0 16px;
  display: flex;
  align-items: center;
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.16);
  box-shadow:
    0 12px 30px rgba(13, 27, 42, 0.38),
    inset 0 1px 0 rgba(248, 250, 252, 0.1);
}

.popup-action text {
  flex: 1;
}

.popup-action-primary {
  background: linear-gradient(135deg, #5a7c9e, #283845 52%, #0d1b2a);
  border: 1px solid rgba(195, 208, 221, 0.22);
}

.popup-cancel {
  height: 50px;
  border-radius: 22px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.active-card {
  transition: all 0.18s ease;
}

.active-card:active {
  transform: scale(0.985);
  opacity: 0.92;
}

.pulse-dot {
  animation: pulseDot 1.6s ease-in-out infinite;
}

@keyframes pulseDot {
  0% {
    opacity: 0.35;
    transform: scale(0.86);
  }

  50% {
    opacity: 1;
    transform: scale(1.18);
  }

  100% {
    opacity: 0.35;
    transform: scale(0.86);
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
</style>