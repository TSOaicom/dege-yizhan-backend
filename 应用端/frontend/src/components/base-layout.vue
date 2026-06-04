<template>
  <view style="display: inline">
    <view :style="{ paddingBottom: isTab ? '100rpx' : '0' , display: 'block'}">
      <slot></slot>
    </view>
    <base-tabBar v-if="isTab" :tabList="tabList" class="safe-tabbar"/>
  </view>
</template>

<script setup>
import {ref, onMounted} from "vue";
import {onLoad} from "@dcloudio/uni-app";
import {dynamicTabBars} from "../tabbar/tabbar";

const tabList = ref(dynamicTabBars);

const isTab = ref(false);

const checkIsTab = () => {
  const pages = getCurrentPages();
  if (!pages.length) return;

  const curPage = pages[pages.length - 1];
  const route = `/${curPage.route}`;

  isTab.value = tabList.value.some(item => item.url === route);
};

onLoad(checkIsTab);
onMounted(checkIsTab);
</script>

<style scoped>
/* 基础布局容器 */
/*.layout-container {*/
/*  position: relative;*/
/*  width: 100vw;*/
/*  height: 100vh;*/
/*  display: flex;*/
/*  flex-direction: column;*/
/*}*/

/* 内容容器（可滚动区域） */
.content-container {
  /*width: 100%;*/
  /*flex: 1;*/
  /*box-sizing: border-box;*/
  /*overflow: hidden;*/
}

/* 安全区域的tabbar */
/*.safe-tabbar {*/
/*  position: fixed;*/
/*  bottom: 0;*/
/*  left: 0;*/
/*  right: 0;*/
/*  height: 100rpx;*/
/*  z-index: 9;*/
/*  background: white;*/
/*  box-shadow: 0 -2rpx 10rpx rgba(0, 0, 0, 0.05);*/
/*}*/
::v-deep .tabbar{
  z-index: 9999999;
}
</style>