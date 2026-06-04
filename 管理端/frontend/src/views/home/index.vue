<template>
  <div class="home-page min-h-screen flex flex-col items-center justify-center bg-gradient-to-br from-[#E3F2FD] via-white to-[#FFF8E1] p-6">
    <!-- 标题 -->
    <h1 class="text-3xl md:text-4xl font-bold text-gray-800 mb-3 drop-shadow-sm">
      {{ $t('page.home_help.title') }}
    </h1>

    <!-- 简介文字 -->
    <p class="text-gray-600 text-center max-w-xl mb-8 leading-relaxed">
      {{ $t('page.home_help.description') }}
    </p>

    <!-- 视频卡片 -->
    <div
        class="video-wrapper bg-white rounded-2xl shadow-lg overflow-hidden hover:shadow-2xl transition-shadow duration-300 w-full max-w-3xl aspect-video flex items-center justify-center"
    >
      <iframe v-if="F"
          class="w-full h-full"
          src="https://www.youtube.com/embed/7UYlAMEhPCA?si=bR3081DEUTZDC6C9&rel=0"
          title="系统使用说明视频"
          frameborder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
          referrerpolicy="strict-origin-when-cross-origin"
          allowfullscreen
      ></iframe>
      <iframe v-else
              class="w-full h-full"
              src="//player.bilibili.com/player.html?bvid=BV1btUqBzEMm&page=1"
              scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true">
      </iframe>
    </div>
  </div>
</template>

<script setup>
let app_vip = import.meta.env.VITE_APP_VIP === 'true'
let F = import.meta.env.VITE_APP_VERSION === 'F'
</script>

<style scoped>
.video-wrapper {
  max-width: 900px;
  width: 100%;
}
</style>
