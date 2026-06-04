<template>
  <base-layout>
    <view class="relative box-border min-h-screen w-full overflow-hidden bg-secondary-900 pb-24 text-text-primary-50">
      <view class="absolute inset-0 bg-gradient-to-b from-secondary-800 via-secondary-900 to-secondary-900" />
      <view class="halo-one absolute left-0 top-0 size-72 rounded-full bg-primary-500 opacity-30" />
      <view class="halo-two absolute bottom-24 right-0 size-80 rounded-full bg-warning-500 opacity-20" />
      <view class="halo-three absolute right-10 top-64 size-56 rounded-full bg-success-500 opacity-20" />
      <view class="cockpit-grid absolute inset-0 opacity-30" />
      <view class="cockpit-vignette absolute inset-0" />

      <view class="relative z-10 box-border p-2">
        <view class="box-border px-3 pb-4 pt-12">
          <view class="mb-5 flex items-start justify-between gap-3">
            <view class="box-border flex-1">
              <text class="block text-overline tracking-widest text-primary-200">
                DRIVER HEAT COCKPIT
              </text>
              <text class="title-glow mt-2 block text-hero text-text-primary-50">
                实时订单热力
              </text>
              <text class="mt-2 block text-body text-text-primary-300">
                智能识别高价值区域，减少空驶，提升接单效率
              </text>
            </view>

            <view class="flex flex-col items-end gap-2">
              <view class="status-pill">
                <view class="live-dot" />
                <text class="text-caption text-success-100">
                  实时同步
                </text>
              </view>
              <view class="status-pill dark-pill">
                <base-icon
                  type="location-filled"
                  size="14"
                  color="#ffb400"
                />
                <text class="truncate text-caption text-primary-100">
                  {{ currentCityText }}
                </text>
              </view>
            </view>
          </view>

          <view class="mb-5 grid grid-cols-3 gap-3">
            <view class="metric-card">
              <text class="block text-caption text-primary-200">
                推荐区域
              </text>
              <text class="mt-2 block text-title text-text-primary-50">
                {{ suggestions.length }}
              </text>
              <text class="mt-1 block text-overline text-text-primary-400">
                个热点
              </text>
            </view>
            <view class="metric-card highlight-card">
              <text class="block text-caption text-warning-100">
                预估订单
              </text>
              <text class="mt-2 block text-title text-warning-100">
                {{ totalOrders }}
              </text>
              <text class="mt-1 block text-overline text-text-primary-300">
                单
              </text>
            </view>
            <view class="metric-card">
              <text class="block text-caption text-primary-200">
                最高指数
              </text>
              <text class="mt-2 block text-title text-success-100">
                {{ bestIndex }}
              </text>
              <text class="mt-1 block text-overline text-text-primary-400">
                分
              </text>
            </view>
          </view>

          <view class="glass-panel mb-5 box-border rounded-3xl p-3">
            <view class="mb-3 flex items-center justify-between">
              <view>
                <text class="block text-subtitle text-text-primary-50">
                  城市热力雷达
                </text>
                <text class="mt-1 block text-caption text-text-primary-400">
                  点击地图标记查看区域建议
                </text>
              </view>
              <view class="heat-legend">
                <view class="legend-item">
                  <view class="legend-dot red-dot" />
                  <text class="text-overline text-text-primary-300">
                    高热
                  </text>
                </view>
                <view class="legend-item">
                  <view class="legend-dot yellow-dot" />
                  <text class="text-overline text-text-primary-300">
                    中热
                  </text>
                </view>
                <view class="legend-item">
                  <view class="legend-dot green-dot" />
                  <text class="text-overline text-text-primary-300">
                    低热
                  </text>
                </view>
              </view>
            </view>

            <view class="map-shell">
              <base-map-view
                :longitude="centerLongitude"
                :latitude="centerLatitude"
                :scale="14"
                :markers="markers"
                :polyline="polyline"
                @markerTapClick="onMarkerTap"
                @mapTap="onMapTap"
              />

              <view class="map-overlay-card">
                <view class="flex items-center gap-2">
                  <base-icon
                    type="navigate-filled"
                    size="16"
                    color="#ffb400"
                  />
                  <text class="truncate text-caption text-text-primary-50">
                    {{ selectedSuggestion.area_name || '等待选择热点区域' }}
                  </text>
                </view>
                <text class="mt-1 block truncate text-overline text-text-primary-400">
                  {{ selectedSuggestion.suggest_direction ? '建议向' + selectedSuggestion.suggest_direction + '行驶' : '标记点由推荐指数与距离智能映射' }}
                </text>
              </view>
            </view>
          </view>

          <view
            v-if="selectedSuggestion.order_suggestion_id"
            class="focus-card active-card mb-5"
            @click="focusSuggestion(selectedSuggestion)"
          >
            <view class="mb-4 flex items-center justify-between">
              <view class="box-border flex flex-1 items-center gap-3">
                <view
                  class="focus-icon"
                  :style="{ background: heatSoftColor(selectedSuggestion) }"
                >
                  <base-icon
                    type="fire-filled"
                    size="26"
                    :color="selectedSuggestion.color || '#ffb400'"
                  />
                </view>
                <view class="box-border flex-1">
                  <text class="block truncate text-title text-text-primary-50">
                    {{ selectedSuggestion.area_name }}
                  </text>
                  <text class="mt-1 block truncate text-caption text-text-primary-400">
                    当前最值得关注的订单热区
                  </text>
                </view>
              </view>
              <view class="score-badge">
                <text class="text-subtitle text-warning-100">
                  {{ selectedSuggestion.recommend_index }}
                </text>
              </view>
            </view>

            <view class="grid grid-cols-4 gap-2">
              <view class="mini-stat">
                <text class="block text-overline text-text-primary-400">
                  订单量
                </text>
                <text class="mt-1 block text-subtitle text-text-primary-50">
                  {{ selectedSuggestion.order_count }}
                </text>
              </view>
              <view class="mini-stat">
                <text class="block text-overline text-text-primary-400">
                  距离
                </text>
                <text class="mt-1 block text-subtitle text-text-primary-50">
                  {{ selectedSuggestion.distance }}km
                </text>
              </view>
              <view class="mini-stat">
                <text class="block text-overline text-text-primary-400">
                  方向
                </text>
                <text class="mt-1 block text-subtitle text-text-primary-50">
                  {{ selectedSuggestion.suggest_direction }}
                </text>
              </view>
              <view class="mini-stat">
                <text class="block text-overline text-text-primary-400">
                  热力
                </text>
                <text class="mt-1 block text-subtitle text-text-primary-50">
                  {{ selectedSuggestion.level_name || '热区' }}
                </text>
              </view>
            </view>
          </view>

          <view class="control-card mb-5">
            <view class="flex items-center justify-between gap-3">
              <view class="box-border flex flex-1 items-center gap-3">
                <view
                  class="voice-orb"
                  :class="voiceEnabled ? 'voice-on' : 'voice-off'"
                >
                  <base-icon
                    :type="voiceEnabled ? 'sound-filled' : 'micoff-filled'"
                    size="22"
                    :color="voiceEnabled ? '#ffb400' : '#94a3b8'"
                  />
                </view>
                <view class="box-border flex-1">
                  <text class="block text-subtitle text-text-primary-50">
                    语音播报
                  </text>
                  <text class="mt-1 block text-caption text-text-primary-400">
                    {{ voiceEnabled ? '刷新后自动播报最佳区域' : '已关闭播报，仅展示数据' }}
                  </text>
                </view>
              </view>

              <view
                class="toggle-track active-card"
                :class="voiceEnabled ? 'toggle-on' : 'toggle-off'"
                @click="toggleVoice"
              >
                <view
                  class="toggle-thumb"
                  :class="voiceEnabled ? 'thumb-on' : 'thumb-off'"
                />
              </view>
            </view>

            <view class="mt-4 grid grid-cols-2 gap-3">
              <view
                class="btn-secondary active-press"
                @click="refreshNow"
              >
                <base-icon
                  type="refresh-filled"
                  size="18"
                  color="#e2e8f0"
                />
                <text class="btn-secondary-text">
                  {{ isLoading ? '刷新中' : '手动刷新' }}
                </text>
              </view>
              <view
                class="btn-ghost active-press"
                @click="pauseVoice"
              >
                <base-icon
                  type="headphones"
                  size="18"
                  color="#ffb400"
                />
                <text class="btn-ghost-text">
                  暂停播报
                </text>
              </view>
            </view>

            <view class="mt-4 flex items-center justify-between">
              <text class="text-caption text-text-primary-400">
                自动刷新间隔
              </text>
              <view class="rounded-full border border-primary-100 border-opacity-20 bg-primary-100 bg-opacity-10 px-3 py-1">
                <text class="text-caption text-primary-100">
                  30 秒
                </text>
              </view>
            </view>
          </view>

          <view class="section-head mb-3">
            <view>
              <text class="block text-subtitle text-text-primary-50">
                推荐区域榜单
              </text>
              <text class="mt-1 block text-caption text-text-primary-400">
                按推荐指数排序，优先前往高价值热点
              </text>
            </view>
            <base-icon
              type="list"
              size="20"
              color="#c3d0dd"
            />
          </view>

          <view
            v-if="suggestions.length > 0"
            class="space-list"
          >
            <view
              v-for="(item, index) in suggestions"
              :key="item.order_suggestion_id"
              class="suggest-card active-card"
              @click="focusSuggestion(item)"
            >
              <view
                class="rank-block"
                :class="index == 0 ? 'rank-first' : ''"
              >
                <text class="rank-text">
                  {{ index + 1 }}
                </text>
              </view>

              <view class="box-border flex-1">
                <view class="mb-2 flex items-center justify-between gap-2">
                  <view class="box-border flex flex-1 items-center gap-2">
                    <text class="truncate text-subtitle text-text-primary-50">
                      {{ item.area_name }}
                    </text>
                    <view
                      class="level-pill"
                      :style="{ borderColor: item.color || '#ffb400' }"
                    >
                      <view
                        class="tiny-dot"
                        :style="{ backgroundColor: item.color || '#ffb400' }"
                      />
                      <text class="text-overline text-text-primary-300">
                        {{ item.level_name || '热区' }}
                      </text>
                    </view>
                  </view>
                  <view class="index-chip">
                    <base-icon
                      type="star-filled"
                      size="13"
                      color="#ffb400"
                    />
                    <text class="text-caption text-warning-100">
                      {{ item.recommend_index }}
                    </text>
                  </view>
                </view>

                <view class="heat-bar mb-3">
                  <view
                    class="heat-bar-fill"
                    :style="{ width: getHeatWidth(item), background: heatGradient(item) }"
                  />
                </view>

                <view class="grid grid-cols-3 gap-2">
                  <view class="info-chip">
                    <base-icon
                      type="cart-filled"
                      size="14"
                      color="#c3d0dd"
                    />
                    <text class="text-caption text-text-primary-300">
                      {{ item.order_count }}单
                    </text>
                  </view>
                  <view class="info-chip">
                    <base-icon
                      type="map-pin"
                      size="14"
                      color="#c3d0dd"
                    />
                    <text class="text-caption text-text-primary-300">
                      {{ item.distance }}km
                    </text>
                  </view>
                  <view class="info-chip">
                    <base-icon
                      type="navigate"
                      size="14"
                      color="#c3d0dd"
                    />
                    <text class="text-caption text-text-primary-300">
                      向{{ item.suggest_direction }}
                    </text>
                  </view>
                </view>
              </view>
            </view>
          </view>

          <view
            v-else
            class="empty-card"
          >
            <view class="empty-icon">
              <base-icon
                type="map"
                size="32"
                color="#c3d0dd"
              />
            </view>
            <text class="mt-4 block text-subtitle text-text-primary-50">
              暂无推荐区域
            </text>
            <text class="mt-2 block text-body text-text-primary-400">
              请稍后刷新，系统将持续同步城市订单热力。
            </text>
          </view>
        </view>
      </view>

      <view
        v-if="isLoading"
        class="loading-mask absolute inset-0 z-50 flex items-center justify-center"
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
            正在刷新热力
          </text>
          <text class="mt-2 block text-caption text-text-primary-400">
            同步订单建议与地图标记
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
const { proxy } = getCurrentInstance()

const isLoading = ref(false)
const suggestions = ref([])
const selectedSuggestion = ref({})
const markers = ref([])
const polyline = ref([])
const centerLatitude = ref(31.2304)
const centerLongitude = ref(121.4737)
const currentCityText = ref('定位中')
const voiceEnabled = ref(true)
const isPlaying = ref(false)
const audioPlayer = ref(null)
const refreshTimer = ref(null)
const refreshingLock = ref(false)

const totalOrders = computed(() => {
  return suggestions.value.reduce((sum, item) => sum + Number(item.order_count || 0), 0)
})

const bestIndex = computed(() => {
  if (!suggestions.value.length) {
    return '0.0'
  }
  const maxValue = Math.max(...suggestions.value.map(item => Number(item.recommend_index || 0)))
  return maxValue.toFixed(1)
})

function debounce(fn, delay) {
  let timer = null
  return function debouncedFunction() {
    const args = arguments
    if (timer) {
      clearTimeout(timer)
    }
    timer = setTimeout(() => {
      fn.apply(null, args)
    }, delay)
  }
}

function getStorageBoolean(key, defaultValue) {
  const value = uni.getStorageSync(key)
  if (value == '') {
    return defaultValue
  }
  return value == true || value == 'true'
}

function setStorageValue(key, value) {
  uni.setStorageSync(key, value)
}

async function initPage() {
  voiceEnabled.value = getStorageBoolean('home_voice_enabled', true)
  await loadCurrentLocation()
  await loadSuggestions(true)
  startAutoRefresh()
}

async function loadCurrentLocation() {
  const res = await proxy.$app.location.getCurrentLocation({ isHighAccuracy: true })
  if (res && res.success && res.latitude && res.longitude) {
    centerLatitude.value = Number(res.latitude)
    centerLongitude.value = Number(res.longitude)
    currentCityText.value = '当前位置'
  } else {
    currentCityText.value = '上海默认点'
  }
}

async function loadSuggestions(needVoice) {
  if (refreshingLock.value) {
    return
  }

  refreshingLock.value = true
  isLoading.value = true

  const result = await proxy.$app.db.from('order_suggestion')
    .page()
    .page(1, 20)
    .order('recommend_index', 'desc')

  if (result && result.code == 0 && result.data && result.data.records) {
    suggestions.value = result.data.records.map(item => normalizeSuggestion(item))
    if (suggestions.value.length > 0) {
      selectedSuggestion.value = suggestions.value[0]
    } else {
      selectedSuggestion.value = {}
    }
    buildMapData()
    if (needVoice && voiceEnabled.value && suggestions.value.length > 0) {
      await playSuggestionVoice()
    }
  } else {
    await proxy.$app.ui.toast().level('error').message('订单热力刷新失败')
  }

  isLoading.value = false
  refreshingLock.value = false
}

function normalizeSuggestion(item) {
  const nextItem = Object.assign({}, item)
  nextItem.order_count = Number(nextItem.order_count || 0)
  nextItem.distance = Number(nextItem.distance || 0)
  nextItem.recommend_index = Number(nextItem.recommend_index || 0)
  if (!nextItem.level_name) {
    nextItem.level_name = inferLevelName(nextItem.recommend_index)
  }
  if (!nextItem.color) {
    nextItem.color = inferLevelColor(nextItem.level_name)
  }
  return nextItem
}

function inferLevelName(score) {
  if (score >= 4.5) {
    return '红'
  }
  if (score >= 3.5) {
    return '黄'
  }
  return '绿'
}

function inferLevelColor(levelName) {
  if (levelName == '红') {
    return '#FF3B30'
  }
  if (levelName == '黄') {
    return '#FFD600'
  }
  return '#34C759'
}

function buildMapData() {
  const builtMarkers = suggestions.value.map((item, index) => {
    const point = getPointByDirection(item.suggest_direction, Number(item.distance || 1), index)
    return {
      id: item.order_suggestion_id,
      markerId: item.order_suggestion_id,
      latitude: point.latitude,
      longitude: point.longitude,
      title: item.area_name,
      iconPath: '/static/mark_icon.png',
      width: 32,
      height: 32,
    }
  })

  markers.value = builtMarkers

  if (builtMarkers.length > 0) {
    centerLatitude.value = builtMarkers[0].latitude
    centerLongitude.value = builtMarkers[0].longitude
    polyline.value = [{
      points: [
        {
          latitude: centerLatitude.value,
          longitude: centerLongitude.value,
        },
        {
          latitude: builtMarkers[0].latitude,
          longitude: builtMarkers[0].longitude,
        },
      ],
      color: '#FFB400CC',
      width: 5,
      dottedLine: false,
    }]
  } else {
    polyline.value = []
  }
}

function getPointByDirection(direction, distance, index) {
  const safeDistance = distance > 0 ? distance : 1
  let dx = 0
  let dy = 0
  const text = direction || ''

  if (text.indexOf('东') !== -1) {
    dx = 1
  }
  if (text.indexOf('西') !== -1) {
    dx = -1
  }
  if (text.indexOf('北') !== -1) {
    dy = 1
  }
  if (text.indexOf('南') !== -1) {
    dy = -1
  }

  if (dx == 0 && dy == 0) {
    const angle = (index * 72) * Math.PI / 180
    dx = Math.cos(angle)
    dy = Math.sin(angle)
  }

  const length = Math.sqrt(dx * dx + dy * dy) || 1
  dx = dx / length
  dy = dy / length

  const latOffset = dy * safeDistance / 111
  const cosValue = Math.cos(centerLatitude.value * Math.PI / 180)
  const lngOffset = dx * safeDistance / (111 * (cosValue || 0.8))

  return {
    latitude: Number((centerLatitude.value + latOffset).toFixed(6)),
    longitude: Number((centerLongitude.value + lngOffset).toFixed(6)),
  }
}

function onMarkerTap(e) {
  const markerId = e.markerId || e.id
  const found = suggestions.value.find(item => Number(item.order_suggestion_id) == Number(markerId))
  if (found) {
    focusSuggestion(found)
  }
}

async function onMapTap(e) {
  if (e && e.latitude && e.longitude) {
    await proxy.$app.ui.toast().level('none').duration(1200).message('已记录地图触点，可点击标记查看热区')
  }
}

function focusSuggestion(item) {
  selectedSuggestion.value = item
  const point = getPointByDirection(item.suggest_direction, Number(item.distance || 1), 0)
  centerLatitude.value = point.latitude
  centerLongitude.value = point.longitude
  polyline.value = [{
    points: [
      {
        latitude: centerLatitude.value,
        longitude: centerLongitude.value,
      },
      {
        latitude: point.latitude,
        longitude: point.longitude,
      },
    ],
    color: '#FFB400CC',
    width: 5,
    dottedLine: false,
  }]
}

function toggleVoice() {
  voiceEnabled.value = !voiceEnabled.value
  setStorageValue('home_voice_enabled', voiceEnabled.value)
  if (!voiceEnabled.value) {
    pauseVoice()
  }
  proxy.$app.ui.toast().level('success').message(voiceEnabled.value ? '语音播报已开启' : '语音播报已关闭')
}

function pauseVoice() {
  if (audioPlayer.value) {
    audioPlayer.value.pause()
    isPlaying.value = false
  }
}

async function playSuggestionVoice() {
  const content = buildVoiceContent()
  if (!content) {
    return
  }

  const cacheKey = 'home_voice_audio_' + encodeURIComponent(content)
  let audioUrl = uni.getStorageSync(cacheKey)

  if (!audioUrl) {
    const audioRes = await proxy.$app.ai.textToAudio().text(content)
    if (audioRes && audioRes.success && audioRes.data && audioRes.data.SongDetail && audioRes.data.SongDetail.AudioUrl) {
      audioUrl = audioRes.data.SongDetail.AudioUrl
      uni.setStorageSync(cacheKey, audioUrl)
    }
  }

  if (audioUrl) {
    if (audioPlayer.value) {
      audioPlayer.value.destroy()
      audioPlayer.value = null
    }

    audioPlayer.value = new proxy.$audioPlayer({
      src: audioUrl,
      autoplay: false,
      loop: false,
    })

    audioPlayer.value.on('ended', () => {
      isPlaying.value = false
    })

    audioPlayer.value.play()
    isPlaying.value = true
  }
}

function buildVoiceContent() {
  if (!suggestions.value.length) {
    return ''
  }

  const topItems = suggestions.value.slice(0, 3)
  const lines = topItems.map((item, index) => {
    return '第' + (index + 1) + '推荐，' + item.area_name + '，预计订单' + item.order_count + '单，距离' + item.distance + '公里，建议向' + item.suggest_direction + '行驶，推荐指数' + item.recommend_index + '。'
  })

  return '司机您好，当前城市订单热力已刷新。' + lines.join('') + '请结合路况安全驾驶。'
}

function refreshNow() {
  debouncedRefresh()
}

const debouncedRefresh = debounce(() => {
  loadSuggestions(true)
}, 500)

function startAutoRefresh() {
  if (refreshTimer.value) {
    clearInterval(refreshTimer.value)
  }
  refreshTimer.value = setInterval(() => {
    loadSuggestions(true)
  }, 30000)
}

function getHeatWidth(item) {
  const percent = Math.min(100, Math.max(8, Number(item.recommend_index || 0) * 20))
  return percent + '%'
}

function heatGradient(item) {
  const color = item.color || inferLevelColor(item.level_name)
  return 'linear-gradient(90deg, rgba(195,208,221,0.18), ' + color + ')'
}

function heatSoftColor(item) {
  const color = item.color || inferLevelColor(item.level_name)
  return 'linear-gradient(135deg, rgba(225,231,238,0.14), ' + color + '33)'
}

onLoad(() => {
  initPage()
})

onUnload(() => {
  if (refreshTimer.value) {
    clearInterval(refreshTimer.value)
    refreshTimer.value = null
  }
  if (audioPlayer.value) {
    audioPlayer.value.destroy()
    audioPlayer.value = null
  }
})
</script>

<style scoped>
.halo-one {
  transform: translate(-35%, -30%);
  filter: blur(52px);
}

.halo-two {
  transform: translate(28%, 15%);
  filter: blur(60px);
}

.halo-three {
  transform: translate(20%, -10%);
  filter: blur(48px);
}

.cockpit-grid {
  background-image:
    linear-gradient(rgba(195, 208, 221, 0.08) 1px, transparent 1px),
    linear-gradient(90deg, rgba(195, 208, 221, 0.08) 1px, transparent 1px);
  background-size: 28px 28px;
}

.cockpit-vignette {
  background:
    linear-gradient(180deg, rgba(13, 27, 42, 0.12), rgba(13, 27, 42, 0.94)),
    linear-gradient(90deg, rgba(13, 27, 42, 0.75), transparent 48%, rgba(13, 27, 42, 0.75));
}

.title-glow {
  text-shadow: 0 8px 32px rgba(90, 124, 158, 0.58);
}

.status-pill {
  max-width: 112px;
  height: 28px;
  padding: 0 10px;
  border-radius: 999px;
  display: flex;
  align-items: center;
  gap: 6px;
  background: rgba(66, 184, 97, 0.12);
  border: 1px solid rgba(103, 198, 127, 0.24);
  box-shadow:
    0 10px 28px rgba(13, 27, 42, 0.36),
    inset 0 1px 0 rgba(248, 250, 252, 0.1);
}

.dark-pill {
  background: rgba(225, 231, 238, 0.1);
  border-color: rgba(195, 208, 221, 0.18);
}

.live-dot {
  width: 7px;
  height: 7px;
  border-radius: 999px;
  background: #42b861;
  box-shadow: 0 0 14px rgba(66, 184, 97, 0.9);
  animation: livePulse 1.6s ease-in-out infinite;
}

.metric-card {
  min-height: 92px;
  padding: 14px 12px;
  border-radius: 26px;
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.16);
  box-shadow:
    0 18px 48px rgba(13, 27, 42, 0.45),
    inset 0 1px 0 rgba(248, 250, 252, 0.12);
}

.highlight-card {
  background:
    linear-gradient(145deg, rgba(255, 180, 0, 0.18), rgba(225, 231, 238, 0.08));
  border-color: rgba(255, 180, 0, 0.28);
}

.glass-panel {
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.18);
  box-shadow:
    0 24px 70px rgba(13, 27, 42, 0.62),
    inset 0 1px 0 rgba(248, 250, 252, 0.14);
}

.heat-legend {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.legend-item {
  display: flex;
  align-items: center;
  gap: 5px;
}

.legend-dot {
  width: 8px;
  height: 8px;
  border-radius: 999px;
}

.red-dot {
  background: #ff3b30;
  box-shadow: 0 0 10px rgba(255, 59, 48, 0.6);
}

.yellow-dot {
  background: #ffd600;
  box-shadow: 0 0 10px rgba(255, 214, 0, 0.55);
}

.green-dot {
  background: #34c759;
  box-shadow: 0 0 10px rgba(52, 199, 89, 0.55);
}

.map-shell {
  position: relative;
  height: 320px;
  overflow: hidden;
  border-radius: 28px;
  background: #1d2838;
  border: 1px solid rgba(195, 208, 221, 0.12);
}

.map-shell base-map-view {
  display: block;
  width: 100%;
  height: 320px;
}

.map-overlay-card {
  position: absolute;
  left: 12px;
  right: 12px;
  bottom: 12px;
  padding: 12px 14px;
  border-radius: 22px;
  background: rgba(13, 27, 42, 0.82);
  border: 1px solid rgba(195, 208, 221, 0.18);
  box-shadow:
    0 18px 42px rgba(13, 27, 42, 0.55),
    inset 0 1px 0 rgba(248, 250, 252, 0.1);
}

.focus-card {
  padding: 16px;
  border-radius: 30px;
  background:
    linear-gradient(145deg, rgba(90, 124, 158, 0.2), rgba(13, 27, 42, 0.78));
  border: 1px solid rgba(255, 180, 0, 0.22);
  box-shadow:
    0 24px 70px rgba(13, 27, 42, 0.58),
    inset 0 1px 0 rgba(255, 240, 204, 0.14);
}

.focus-icon {
  width: 58px;
  height: 58px;
  border-radius: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid rgba(195, 208, 221, 0.16);
}

.score-badge {
  min-width: 54px;
  height: 44px;
  padding: 0 12px;
  border-radius: 18px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(255, 180, 0, 0.14);
  border: 1px solid rgba(255, 180, 0, 0.26);
}

.mini-stat {
  padding: 10px 6px;
  text-align: center;
  border-radius: 18px;
  background: rgba(13, 27, 42, 0.46);
  border: 1px solid rgba(195, 208, 221, 0.12);
}

.control-card {
  padding: 16px;
  border-radius: 30px;
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.16);
  box-shadow:
    0 20px 58px rgba(13, 27, 42, 0.5),
    inset 0 1px 0 rgba(248, 250, 252, 0.12);
}

.voice-orb {
  width: 52px;
  height: 52px;
  border-radius: 22px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid rgba(195, 208, 221, 0.16);
}

.voice-on {
  background: rgba(255, 180, 0, 0.14);
  box-shadow: 0 0 28px rgba(255, 180, 0, 0.18);
}

.voice-off {
  background: rgba(225, 231, 238, 0.08);
}

.toggle-track {
  width: 56px;
  height: 32px;
  padding: 3px;
  border-radius: 999px;
  border: 1px solid rgba(195, 208, 221, 0.18);
}

.toggle-on {
  background: rgba(66, 184, 97, 0.28);
}

.toggle-off {
  background: rgba(13, 27, 42, 0.58);
}

.toggle-thumb {
  width: 24px;
  height: 24px;
  border-radius: 999px;
  background: #f8fafc;
  transition: transform 0.2s ease;
  box-shadow: 0 8px 18px rgba(13, 27, 42, 0.45);
}

.thumb-on {
  transform: translateX(24px);
}

.thumb-off {
  transform: translateX(0);
}

.btn-secondary,
.btn-ghost {
  height: 48px;
  border-radius: 21px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 9px;
}

.btn-secondary {
  background: rgba(225, 231, 238, 0.12);
  border: 1px solid rgba(195, 208, 221, 0.18);
  box-shadow:
    0 14px 36px rgba(13, 27, 42, 0.36),
    inset 0 1px 0 rgba(248, 250, 252, 0.12);
}

.btn-ghost {
  background: rgba(13, 27, 42, 0.36);
  border: 1px solid rgba(255, 180, 0, 0.34);
  box-shadow:
    0 14px 36px rgba(13, 27, 42, 0.32),
    inset 0 1px 0 rgba(255, 240, 204, 0.1);
}

.btn-secondary-text,
.btn-ghost-text {
  font-size: 0.8rem;
  line-height: 1.2rem;
  font-weight: 600;
}

.btn-secondary-text {
  color: #e2e8f0;
}

.btn-ghost-text {
  color: #fff0cc;
}

.section-head {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.space-list {
  display: flex;
  flex-direction: column;
  gap: 14px;
}

.suggest-card {
  display: flex;
  gap: 13px;
  padding: 14px;
  border-radius: 28px;
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.15);
  box-shadow:
    0 18px 46px rgba(13, 27, 42, 0.45),
    inset 0 1px 0 rgba(248, 250, 252, 0.1);
}

.rank-block {
  width: 38px;
  height: 38px;
  border-radius: 17px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(90, 124, 158, 0.22);
  border: 1px solid rgba(195, 208, 221, 0.14);
}

.rank-first {
  background: linear-gradient(135deg, #ffb400, #996c00);
  box-shadow: 0 12px 26px rgba(255, 180, 0, 0.22);
}

.rank-text {
  font-size: 1rem;
  line-height: 1.35rem;
  font-weight: 700;
  color: #f8fafc;
}

.level-pill,
.index-chip,
.info-chip {
  display: flex;
  align-items: center;
  justify-content: center;
}

.level-pill {
  gap: 4px;
  padding: 3px 7px;
  border-radius: 999px;
  background: rgba(13, 27, 42, 0.42);
  border: 1px solid rgba(195, 208, 221, 0.18);
}

.tiny-dot {
  width: 6px;
  height: 6px;
  border-radius: 999px;
}

.index-chip {
  gap: 3px;
  min-width: 48px;
  padding: 4px 8px;
  border-radius: 999px;
  background: rgba(255, 180, 0, 0.12);
  border: 1px solid rgba(255, 180, 0, 0.22);
}

.info-chip {
  gap: 4px;
  min-height: 30px;
  border-radius: 14px;
  background: rgba(13, 27, 42, 0.36);
  border: 1px solid rgba(195, 208, 221, 0.1);
}

.heat-bar {
  height: 8px;
  border-radius: 999px;
  overflow: hidden;
  background: rgba(225, 231, 238, 0.08);
}

.heat-bar-fill {
  height: 8px;
  border-radius: 999px;
  transition: width 0.35s ease;
}

.empty-card {
  padding: 28px 18px;
  text-align: center;
  border-radius: 30px;
  background: rgba(225, 231, 238, 0.08);
  border: 1px solid rgba(195, 208, 221, 0.14);
}

.empty-icon {
  width: 64px;
  height: 64px;
  margin: 0 auto;
  border-radius: 26px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(90, 124, 158, 0.16);
  border: 1px solid rgba(195, 208, 221, 0.14);
}

.active-card:active,
.active-press:active {
  transform: scale(0.985);
  opacity: 0.92;
}

.loading-mask {
  background:
    linear-gradient(180deg, rgba(13, 27, 42, 0.72), rgba(13, 27, 42, 0.92)),
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

@keyframes livePulse {
  0% {
    opacity: 0.4;
    transform: scale(0.85);
  }

  50% {
    opacity: 1;
    transform: scale(1.15);
  }

  100% {
    opacity: 0.4;
    transform: scale(0.85);
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
</style>