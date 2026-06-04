<template>
  <base-wrapper>

    <div class="flex-between-start" style="background-color: #5D5FEF; height: 140px;">
      <div class="flex-c-center-start h-full">
        <div class="m-x-20">
          <span style="font-weight: bold; font-size: 20px; color: #FFFFFF;">{{ $t('page.api.title') }}</span>
        </div>
        <div class="m-x-20 m-t-4">
          <span style="font-size: 14px; color: #FFFFFF;">{{ $t('page.api.description') }}</span>
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
          :to="{ path: '/api_setting_manger' }"
      >
        {{ $t('page.api.title') }}
      </el-breadcrumb-item>
    </el-breadcrumb>
    <dynamic_api_setting/>

  </base-wrapper>
</template>
<script setup>
</script>