<template>
  <base-layout>
    <view class="page-root relative box-border min-h-screen w-full bg-secondary-900 pb-24 text-text-primary-50">
      <view class="absolute inset-0 bg-gradient-to-b from-secondary-800 via-secondary-900 to-secondary-900" />
      <view class="halo-one absolute left-0 top-0 size-72 rounded-full bg-primary-500 opacity-25" />
      <view class="halo-two absolute bottom-20 right-0 size-80 rounded-full bg-warning-500 opacity-20" />
      <view class="halo-three absolute right-8 top-72 size-56 rounded-full bg-success-500 opacity-20" />
      <view class="cockpit-grid absolute inset-0 opacity-30" />
      <view class="cockpit-vignette absolute inset-0" />

      <view class="relative z-10 box-border p-2 pt-12">
        <view class="box-border px-3">
          <view class="mb-5 flex items-start justify-between">
            <view class="box-border flex-1 pr-3">
              <text class="block text-overline tracking-widest text-primary-200">
                ORDER INTELLIGENCE
              </text>
              <text class="mt-2 block text-hero text-text-primary-50">
                今日订单建议
              </text>
              <text class="mt-2 block text-body text-text-primary-300">
                高峰、热区与接单方向，一屏掌控
              </text>
            </view>

            <view class="header-icon">
              <base-icon
                type="navigate-filled"
                size="24"
                color="#ffb400"
              />
            </view>
          </view>

          <view class="hero-card mb-5">
            <image
              class="absolute inset-0 size-full opacity-35"
              mode="aspectFill"
              src="https://www.codeflying.net/preview/city-bridge.jpg"
            />
            <view class="absolute inset-0 bg-gradient-to-t from-secondary-900 via-secondary-900 to-transparent opacity-90" />

            <view class="relative z-10 box-border flex h-full flex-col justify-between p-5">
              <view class="flex items-center justify-between">
                <view class="pill-warning">
                  <text class="text-caption text-warning-100">
                    实时推荐指数
                  </text>
                </view>
                <view class="flex items-center gap-2">
                  <view class="status-dot" />
                  <text class="text-caption text-success-100">
                    数据已同步
                  </text>
                </view>
              </view>

              <view>
                <view class="mb-2 flex items-end gap-2">
                  <text class="text-hero text-text-primary-50">
                    {{ topRecommendIndex }}
                  </text>
                  <text class="mb-1 text-body text-primary-200">
                    最高推荐
                  </text>
                </view>
                <text class="block truncate text-subtitle text-text-primary-50">
                  {{ topAreaName }}
                </text>
                <text class="mt-2 block text-body text-text-primary-300">
                  当前{{ activePeriodText }}建议优先前往 {{ topAreaName }}，预计订单热度更高
                </text>

                <view class="mt-5 grid grid-cols-3 gap-3">
                  <view class="metric-mini">
                    <text class="block text-title text-warning-100">
                      {{ totalOrders }}
                    </text>
                    <text class="block text-caption text-primary-200">
                      预估订单
                    </text>
                  </view>
                  <view class="metric-mini">
                    <text class="block text-title text-success-100">
                      {{ hotAreaCount }}
                    </text>
                    <text class="block text-caption text-primary-200">
                      热门区域
                    </text>
                  </view>
                  <view class="metric-mini">
                    <text class="block text-title text-primary-100">
                      {{ avgDistance }}
                    </text>
                    <text class="block text-caption text-primary-200">
                      均距公里
                    </text>
                  </view>
                </view>
              </view>
            </view>
          </view>

          <scroll-view
            scroll-x
            class="mb-5 w-full"
          >
            <view class="inline-flex gap-3 pb-2">
              <view
                v-for="item in periodOptions"
                :key="item.value"
                class="period-tab active-press"
                :class="activePeriod == item.value ? 'period-tab-active' : ''"
                @click="onPeriodChange(item.value)"
              >
                <base-icon
                  :type="item.icon"
                  size="17"
                  :color="activePeriod == item.value ? '#0d1b2a' : '#c3d0dd'"
                />
                <text
                  class="period-text"
                  :class="activePeriod == item.value ? 'period-text-active' : ''"
                >
                  {{ item.text }}
                </text>
              </view>
            </view>
          </scroll-view>

          <view class="chart-card mb-5">
            <view class="mb-4 flex items-center justify-between">
              <view class="box-border flex-1 pr-2">
                <text class="block text-subtitle text-text-primary-50">
                  全天订单分布曲线
                </text>
                <text class="mt-1 block text-caption text-text-primary-400">
                  根据区域热度与时段权重生成接单节奏参考
                </text>
              </view>
              <view class="chart-badge">
                <base-icon
                  type="calendar-filled"
                  size="15"
                  color="#ffb400"
                />
                <text class="text-caption text-warning-100">
                  {{ activePeriodText }}
                </text>
              </view>
            </view>

            <view class="chart-wrap">
              <qiun-data-charts
                type="line"
                :chart-data="orderChartData"
                :opts="lineChartOptions"
                :animation="true"
              />
            </view>
          </view>

          <view class="peak-card mb-5">
            <view class="flex items-center gap-4">
              <view class="peak-icon">
                <base-icon
                  type="fire-filled"
                  size="28"
                  color="#ffb400"
                />
              </view>
              <view class="flex-1">
                <text class="block text-caption text-primary-200">
                  今日高峰时段
                </text>
                <text class="mt-1 block text-title text-text-primary-50">
                  {{ peakTimeRange }}
                </text>
                <text class="mt-1 block text-body text-text-primary-300">
                  建议提前靠近核心商圈，减少空驶等待
                </text>
              </view>
            </view>

            <view class="mt-5 flex items-center gap-3">
              <view class="heat-track flex-1">
                <view
                  class="heat-progress"
                  :style="{ width: peakPercent + '%' }"
                />
              </view>
              <text class="text-caption text-warning-100">
                {{ peakPercent }}%
              </text>
            </view>
          </view>

          <view class="section-title mb-4">
            <view>
              <text class="block text-subtitle text-text-primary-50">
                热门区域榜单
              </text>
              <text class="mt-1 block text-caption text-text-primary-400">
                按推荐指数排序，优先展示高价值区域
              </text>
            </view>
            <view
              class="refresh-btn active-press"
              @click="onRefreshClick"
            >
              <base-icon
                type="refresh"
                size="16"
                color="#f8fafc"
              />
              <text class="text-caption text-text-primary-50">
                刷新
              </text>
            </view>
          </view>

          <view
            v-if="suggestionList.length > 0"
            class="mb-6"
          >
            <view
              v-for="(item, index) in suggestionList"
              :key="item.order_suggestion_id"
              class="rank-card active-card mb-4"
            >
              <view class="rank-top">
                <view
                  class="rank-number"
                  :class="index == 0 ? 'rank-number-hot' : ''"
                >
                  <text class="text-subtitle">
                    {{ index + 1 }}
                  </text>
                </view>

                <view class="min-w-0 flex-1">
                  <view class="flex items-center gap-2">
                    <text class="truncate text-subtitle text-text-primary-50">
                      {{ item.area_name }}
                    </text>
                    <view
                      class="heat-pill"
                      :style="{ borderColor: getHeatColor(item), backgroundColor: getHeatBgColor(item) }"
                    >
                      <view
                        class="heat-dot"
                        :style="{ backgroundColor: getHeatColor(item) }"
                      />
                      <text class="text-caption text-text-primary-50">
                        {{ item.level_name || getHeatName(item) }}
                      </text>
                    </view>
                  </view>
                  <text class="mt-1 block truncate text-caption text-text-primary-400">
                    建议方向：{{ item.suggest_direction }}
                  </text>
                </view>

                <view class="score-box">
                  <text class="block text-title text-warning-100">
                    {{ formatDecimal(item.recommend_index) }}
                  </text>
                  <text class="block text-overline text-primary-300">
                    指数
                  </text>
                </view>
              </view>

              <view class="rank-body mt-4">
                <view class="info-chip">
                  <base-icon
                    type="cart-filled"
                    size="16"
                    color="#ffb400"
                  />
                  <text class="text-caption text-primary-100">
                    {{ item.order_count }} 单
                  </text>
                </view>
                <view class="info-chip">
                  <base-icon
                    type="location-filled"
                    size="16"
                    color="#42b861"
                  />
                  <text class="text-caption text-primary-100">
                    {{ formatDecimal(item.distance) }} km
                  </text>
                </view>
                <view class="info-chip">
                  <base-icon
                    type="paperplane-filled"
                    size="16"
                    color="#c3d0dd"
                  />
                  <text class="text-caption text-primary-100">
                    {{ item.suggest_direction }}
                  </text>
                </view>
              </view>

              <view class="mt-4 flex items-center gap-3">
                <view class="recommend-track flex-1">
                  <view
                    class="recommend-progress"
                    :style="{ width: getRecommendPercent(item.recommend_index) + '%' }"
                  />
                </view>
                <text class="text-caption text-warning-100">
                  {{ getRecommendPercent(item.recommend_index) }}%
                </text>
              </view>
            </view>
          </view>

          <view
            v-else
            class="empty-card mb-6"
          >
            <view class="empty-icon">
              <base-icon
                type="info-filled"
                size="30"
                color="#c3d0dd"
              />
            </view>
            <text class="mt-4 block text-subtitle text-text-primary-50">
              暂无推荐区域
            </text>
            <text class="mt-2 block text-body text-text-primary-400">
              请稍后刷新，系统会持续同步今日订单热区
            </text>
          </view>
        </view>
      </view>

      <view
        v-if="isLoading"
        class="loading-mask"
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
            正在同步建议
          </text>
          <text class="mt-2 block text-caption text-text-primary-400">
            正在计算今日订单热区与高峰时段
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
const activePeriod = ref('all')
const suggestionList = ref([])
const heatLevelList = ref([])
const orderChartData = ref({
  categories: [],
  series: [
    {
      name: '预估订单',
      data: [],
    },
  ],
})

const periodOptions = ref([
  { value: 'all', text: '全天', icon: 'calendar' },
  { value: 'morning', text: '上午', icon: 'up' },
  { value: 'afternoon', text: '下午', icon: 'fire' },
  { value: 'night', text: '夜间', icon: 'moon' },
])

const lineChartOptions = ref({
  color: ['#ffb400', '#42b861'],
  padding: [12, 10, 8, 10],
  enableScroll: false,
  legend: {
    show: false,
  },
  xAxis: {
    disableGrid: true,
    fontColor: '#c3d0dd',
    fontSize: 10,
  },
  yAxis: {
    gridType: 'dash',
    dashLength: 3,
    gridColor: 'rgba(195, 208, 221, 0.14)',
    fontColor: '#94a3b8',
    fontSize: 10,
  },
  extra: {
    line: {
      type: 'curve',
      width: 3,
      activeType: 'hollow',
    },
  },
})

const topRecommendIndex = ref('0.0')
const topAreaName = ref('暂无区域')
const totalOrders = ref(0)
const hotAreaCount = ref(0)
const avgDistance = ref('0.0')
const peakTimeRange = ref('等待数据')
const peakPercent = ref(0)

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

function getActivePeriodText() {
  const found = periodOptions.value.find(item => item.value == activePeriod.value)
  return found ? found.text : '全天'
}

const activePeriodText = ref('全天')

function onPeriodChange(value) {
  if (activePeriod.value == value) {
    return
  }
  activePeriod.value = value
  activePeriodText.value = getActivePeriodText()
  debouncedLoadData()
}

function onRefreshClick() {
  proxy.$app.ui.toast().level('none').duration(1600).message('正在刷新今日订单建议')
  debouncedLoadData()
}

const debouncedLoadData = debounce(() => {
  loadData()
}, 350)

async function loadData() {
  isLoading.value = true

  const heatRes = await proxy.$app.db.from('heat_level_enum').list().order('heat_level_enum_id', 'asc')
  if (heatRes.success && heatRes.data) {
    heatLevelList.value = heatRes.data
  }

  const suggestionRes = await proxy.$app.db.from('order_suggestion')
    .page()
    .page(1, 50)
    .order('recommend_index', 'desc')

  if (suggestionRes.code == 0 && suggestionRes.data && suggestionRes.data.records) {
    const records = suggestionRes.data.records.map(item => {
      const heat = getHeatById(item.heat_level_enum_heat_level_enum_id_1)
      return {
        order_suggestion_id: item.order_suggestion_id,
        area_name: item.area_name,
        order_count: Number(item.order_count || 0),
        distance: Number(item.distance || 0),
        recommend_index: Number(item.recommend_index || 0),
        suggest_direction: item.suggest_direction || '待判断',
        heat_level_enum_heat_level_enum_id_1: item.heat_level_enum_heat_level_enum_id_1,
        level_name: item.level_name || heat.level_name || '热区',
        color: item.color || heat.color || '#ffb400',
      }
    })

    suggestionList.value = records.sort((a, b) => Number(b.recommend_index || 0) - Number(a.recommend_index || 0))
    buildSummary()
    buildChartData()
  }

  isLoading.value = false
}

function getHeatById(id) {
  const found = heatLevelList.value.find(item => Number(item.heat_level_enum_id) == Number(id))
  return found || {}
}

function getHeatColor(item) {
  if (item && item.color) {
    return item.color
  }
  const heat = getHeatById(item.heat_level_enum_heat_level_enum_id_1)
  return heat.color || '#ffb400'
}

function getHeatBgColor(item) {
  const color = getHeatColor(item)
  if (color == '#FF3B30') {
    return 'rgba(243, 115, 115, 0.18)'
  }
  if (color == '#FFD600') {
    return 'rgba(255, 180, 0, 0.18)'
  }
  if (color == '#34C759') {
    return 'rgba(66, 184, 97, 0.18)'
  }
  return 'rgba(195, 208, 221, 0.12)'
}

function getHeatName(item) {
  const heat = getHeatById(item.heat_level_enum_heat_level_enum_id_1)
  return heat.level_name || '热区'
}

function buildSummary() {
  const list = suggestionList.value
  if (!list.length) {
    topRecommendIndex.value = '0.0'
    topAreaName.value = '暂无区域'
    totalOrders.value = 0
    hotAreaCount.value = 0
    avgDistance.value = '0.0'
    peakTimeRange.value = '等待数据'
    peakPercent.value = 0
    return
  }

  const first = list[0]
  topRecommendIndex.value = formatDecimal(first.recommend_index)
  topAreaName.value = first.area_name || '暂无区域'
  totalOrders.value = list.reduce((sum, item) => sum + Number(item.order_count || 0), 0)
  hotAreaCount.value = list.filter(item => Number(item.recommend_index || 0) >= 4).length
  const distanceTotal = list.reduce((sum, item) => sum + Number(item.distance || 0), 0)
  avgDistance.value = formatDecimal(distanceTotal / list.length)

  const chartInfo = createDistribution()
  peakTimeRange.value = chartInfo.peakLabel
  peakPercent.value = chartInfo.peakPercent
}

function buildChartData() {
  const chartInfo = createDistribution()
  const nextData = {
    categories: chartInfo.labels,
    series: [
      {
        name: '预估订单',
        data: chartInfo.values,
      },
    ],
  }
  orderChartData.value = JSON.parse(JSON.stringify(nextData))
}

function createDistribution() {
  const baseTotal = suggestionList.value.reduce((sum, item) => sum + Number(item.order_count || 0), 0)
  const safeTotal = baseTotal > 0 ? baseTotal : 1

  let labels = []
  let weights = []

  if (activePeriod.value == 'morning') {
    labels = ['06', '07', '08', '09', '10', '11']
    weights = [0.42, 0.72, 1.18, 1.35, 0.96, 0.78]
  } else if (activePeriod.value == 'afternoon') {
    labels = ['12', '13', '14', '15', '16', '17']
    weights = [0.76, 0.68, 0.82, 0.92, 1.16, 1.34]
  } else if (activePeriod.value == 'night') {
    labels = ['18', '19', '20', '21', '22', '23']
    weights = [1.42, 1.28, 1.08, 0.9, 0.72, 0.5]
  } else {
    labels = ['06', '08', '10', '12', '14', '16', '18', '20', '22']
    weights = [0.45, 1.28, 0.92, 0.72, 0.82, 1.05, 1.42, 1.12, 0.68]
  }

  const values = weights.map(weight => Math.max(1, Math.round(safeTotal * weight / 3)))
  let peakIndex = 0
  values.forEach((value, index) => {
    if (value > values[peakIndex]) {
      peakIndex = index
    }
  })

  const peakValue = values[peakIndex] || 0
  const maxValue = Math.max.apply(null, values)
  const percent = maxValue > 0 ? Math.round((peakValue / maxValue) * 100) : 0
  const peakLabel = labels[peakIndex] ? labels[peakIndex] + ':00 - ' + labels[peakIndex] + ':59' : '等待数据'

  return {
    labels,
    values,
    peakLabel,
    peakPercent: percent,
  }
}

function formatDecimal(value) {
  const num = Number(value || 0)
  return num.toFixed(1)
}

function getRecommendPercent(value) {
  const num = Number(value || 0)
  const percent = Math.round((num / 5) * 100)
  if (percent > 100) {
    return 100
  }
  if (percent < 0) {
    return 0
  }
  return percent
}

onLoad(() => {
  activePeriodText.value = getActivePeriodText()
  loadData()
})
</script>

<style scoped>
* {
  box-sizing: border-box;
}

.page-root {
  max-width: 100vw;
  overflow-x: hidden;
}

.halo-one {
  transform: translate(-35%, -30%);
  filter: blur(48px);
}

.halo-two {
  transform: translate(28%, 15%);
  filter: blur(54px);
}

.halo-three {
  transform: translate(20%, -10%);
  filter: blur(46px);
}

.cockpit-grid {
  background-image:
    linear-gradient(rgba(195, 208, 221, 0.08) 1px, transparent 1px),
    linear-gradient(90deg, rgba(195, 208, 221, 0.08) 1px, transparent 1px);
  background-size: 28px 28px;
}

.cockpit-vignette {
  background:
    linear-gradient(180deg, rgba(13, 27, 42, 0.08), rgba(13, 27, 42, 0.95)),
    linear-gradient(90deg, rgba(13, 27, 42, 0.72), transparent 48%, rgba(13, 27, 42, 0.72));
}

.header-icon {
  width: 48px;
  height: 48px;
  border-radius: 22px;
  background: rgba(225, 231, 238, 0.12);
  border: 1px solid rgba(195, 208, 221, 0.2);
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow:
    0 16px 40px rgba(13, 27, 42, 0.48),
    inset 0 1px 0 rgba(248, 250, 252, 0.16);
}

.hero-card {
  position: relative;
  overflow: hidden;
  height: 310px;
  border-radius: 34px;
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.18);
  box-shadow:
    0 26px 78px rgba(13, 27, 42, 0.66),
    inset 0 1px 0 rgba(248, 250, 252, 0.14),
    inset 0 -1px 0 rgba(13, 27, 42, 0.42);
}

.pill-warning {
  padding: 6px 12px;
  border-radius: 999px;
  background: rgba(255, 180, 0, 0.18);
  border: 1px solid rgba(255, 195, 51, 0.28);
}

.status-dot {
  width: 8px;
  height: 8px;
  border-radius: 999px;
  background: #42b861;
  box-shadow: 0 0 18px rgba(66, 184, 97, 0.8);
}

.metric-mini {
  padding: 12px 10px;
  border-radius: 22px;
  background: rgba(13, 27, 42, 0.54);
  border: 1px solid rgba(195, 208, 221, 0.14);
  box-shadow:
    inset 0 1px 0 rgba(248, 250, 252, 0.08),
    0 10px 28px rgba(13, 27, 42, 0.34);
}

.period-tab {
  flex-shrink: 0;
  min-height: 42px;
  padding: 0 16px;
  border-radius: 999px;
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.16);
  display: flex;
  align-items: center;
  gap: 7px;
  box-shadow:
    0 12px 28px rgba(13, 27, 42, 0.35),
    inset 0 1px 0 rgba(248, 250, 252, 0.1);
}

.period-tab-active {
  background: linear-gradient(135deg, #ffb400, #ffe199);
  border-color: rgba(255, 225, 153, 0.8);
  box-shadow:
    0 16px 36px rgba(255, 180, 0, 0.24),
    inset 0 1px 0 rgba(255, 249, 235, 0.7);
}

.period-text {
  font-size: 0.8rem;
  line-height: 1.2rem;
  color: #c3d0dd;
  font-weight: 600;
}

.period-text-active {
  color: #0d1b2a;
}

.chart-card,
.peak-card,
.rank-card,
.empty-card {
  border-radius: 30px;
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.16);
  box-shadow:
    0 22px 64px rgba(13, 27, 42, 0.54),
    inset 0 1px 0 rgba(248, 250, 252, 0.12);
}

.chart-card {
  padding: 18px;
}

.chart-wrap {
  width: 100%;
  height: 260px;
}

.chart-badge {
  flex-shrink: 0;
  padding: 7px 10px;
  border-radius: 999px;
  background: rgba(255, 180, 0, 0.16);
  border: 1px solid rgba(255, 195, 51, 0.22);
  display: flex;
  align-items: center;
  gap: 5px;
}

.peak-card {
  padding: 18px;
}

.peak-icon {
  width: 64px;
  height: 64px;
  border-radius: 28px;
  background: radial-gradient(circle at 30% 20%, rgba(255, 225, 153, 0.28), rgba(255, 180, 0, 0.1));
  border: 1px solid rgba(255, 195, 51, 0.24);
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 16px 36px rgba(255, 180, 0, 0.14);
}

.heat-track,
.recommend-track {
  height: 8px;
  border-radius: 999px;
  background: rgba(195, 208, 221, 0.12);
  overflow: hidden;
}

.heat-progress {
  height: 8px;
  border-radius: 999px;
  background: linear-gradient(90deg, #42b861, #ffb400, #f37373);
}

.section-title {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
}

.refresh-btn {
  flex-shrink: 0;
  padding: 9px 12px;
  border-radius: 999px;
  background: rgba(90, 124, 158, 0.28);
  border: 1px solid rgba(195, 208, 221, 0.18);
  display: flex;
  align-items: center;
  gap: 6px;
  box-shadow:
    0 12px 30px rgba(13, 27, 42, 0.36),
    inset 0 1px 0 rgba(248, 250, 252, 0.12);
}

.rank-card {
  padding: 16px;
}

.rank-top {
  display: flex;
  align-items: center;
  gap: 12px;
}

.rank-number {
  width: 42px;
  height: 42px;
  border-radius: 18px;
  flex-shrink: 0;
  color: #c3d0dd;
  background: rgba(13, 27, 42, 0.58);
  border: 1px solid rgba(195, 208, 221, 0.14);
  display: flex;
  align-items: center;
  justify-content: center;
}

.rank-number-hot {
  color: #0d1b2a;
  background: linear-gradient(135deg, #ffb400, #ffe199);
  box-shadow: 0 14px 34px rgba(255, 180, 0, 0.2);
}

.heat-pill {
  flex-shrink: 0;
  max-width: 84px;
  padding: 4px 8px;
  border-radius: 999px;
  border: 1px solid rgba(195, 208, 221, 0.2);
  display: flex;
  align-items: center;
  gap: 5px;
}

.heat-dot {
  width: 7px;
  height: 7px;
  border-radius: 999px;
}

.score-box {
  flex-shrink: 0;
  min-width: 58px;
  text-align: right;
}

.rank-body {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.info-chip {
  padding: 8px 10px;
  border-radius: 999px;
  background: rgba(13, 27, 42, 0.44);
  border: 1px solid rgba(195, 208, 221, 0.12);
  display: flex;
  align-items: center;
  gap: 5px;
}

.recommend-progress {
  height: 8px;
  border-radius: 999px;
  background: linear-gradient(90deg, #5a7c9e, #42b861, #ffb400);
}

.empty-card {
  padding: 36px 20px;
  text-align: center;
}

.empty-icon {
  width: 68px;
  height: 68px;
  margin: 0 auto;
  border-radius: 30px;
  background: rgba(90, 124, 158, 0.18);
  border: 1px solid rgba(195, 208, 221, 0.18);
  display: flex;
  align-items: center;
  justify-content: center;
}

.active-press:active,
.active-card:active {
  transform: scale(0.985);
  opacity: 0.94;
}

.loading-mask {
  position: fixed;
  inset: 0;
  z-index: 50;
  display: flex;
  align-items: center;
  justify-content: center;
  background:
    linear-gradient(180deg, rgba(13, 27, 42, 0.78), rgba(13, 27, 42, 0.95)),
    linear-gradient(135deg, rgba(40, 56, 69, 0.5), rgba(13, 27, 42, 0.9));
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