<template>
  <view class="tabbar" v-if="tabLists.length">
    <view
        v-for="(item, index) in tabLists"
        :key="index"
        :class="[
        'tab_box',
        isActive(item) ? 'on' : ''
      ]"
        :style="{ width: 100 / tabLists.length + '%' ,color: item.style?.fontColor?.[isActive(item) ? 'active' : 'default']}"
        @click="tabFn(item.url)"
    >
      <view class="tab_top">
        <base-icon
            :type="item.icon[isActive(item) ? 'active' : 'default']"
            size="22"
            :color="item.style?.iconColor?.[isActive(item) ? 'active' : 'default']"
        ></base-icon>
      </view>
      <view class="tab_bot">
        {{ item.title }}
      </view>
    </view>
  </view>
</template>

<script setup>
import { ref, getCurrentInstance } from 'vue';

const { proxy } = getCurrentInstance();

const props = defineProps({
  tabList: {
    type: Array,
    default: () => [],
  },
});

const tabLists = ref([]);
const currentRoute = ref('');


tabLists.value = props.tabList

const getCurrentRoute = () => {
  const pages = getCurrentPages();
  if (pages.length) {
    const currentPage = pages[pages.length - 1];
    currentRoute.value = `/${currentPage.route}`;
  }
};
getCurrentRoute();

const isActive = (item) => currentRoute.value === item.url;

async function tabFn  (url) {
  await proxy.$app.router.navigate.to().url(url).type('page').model('reLaunch');
}
</script>

<style scoped>
.tabbar {
  width: 100%;
  height: 100rpx;
  background: white;
  display: flex;
  position: fixed;
  bottom: 0;
  left: 0;
  z-index: 999;
  box-shadow: 0 -2rpx 10rpx rgba(0, 0, 0, 0.05);
}

.tab_box {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.tab_top {
  width: 100%;
  text-align: center;
}

.tab_bot {
  width: 100%;
  text-align: center;
  font-size: 24rpx;
  margin-top: 6rpx;
}
</style>
