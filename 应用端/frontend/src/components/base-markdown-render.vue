<template>
  <view class="markdown-content" v-html="compiledMarkdown"></view>
</template>

<script setup>
import { marked } from 'marked'

const props = defineProps({
  content: {
    type: String,
    default: ''
  }
})

// 配置 marked
onMounted(() => {
  marked.setOptions({
    breaks: true, // 支持换行
    gfm: true     // 支持 GitHub Flavored Markdown
  })
})

// 将 markdown 转换为 HTML
const compiledMarkdown = computed(() => {
  try {
    return marked(props.content || '')
  } catch (error) {
    console.error('Markdown渲染错误:', error)
    return props.content || ''
  }
})
</script>

<style scoped>
.markdown-content {
  width: 100%;
  word-break: break-word;
  line-height: 1.6;
}

/* 标题样式 */
.markdown-content >>> h1,
.markdown-content >>> h2,
.markdown-content >>> h3,
.markdown-content >>> h4,
.markdown-content >>> h5,
.markdown-content >>> h6 {
  font-weight: 600;
  margin: 16rpx 0 8rpx 0;
  line-height: 1.4;
}

.markdown-content >>> h1 {
  font-size: 40rpx;
  border-bottom: 2rpx solid #eee;
  padding-bottom: 8rpx;
}

.markdown-content >>> h2 {
  font-size: 36rpx;
  border-bottom: 1rpx solid #eee;
  padding-bottom: 6rpx;
}

.markdown-content >>> h3 {
  font-size: 32rpx;
}

.markdown-content >>> h4 {
  font-size: 28rpx;
}

.markdown-content >>> h5 {
  font-size: 26rpx;
}

.markdown-content >>> h6 {
  font-size: 24rpx;
  color: #666;
}

/* 段落样式 */
.markdown-content >>> p {
  margin: 12rpx 0;
  font-size: 28rpx;
  line-height: 1.6;
}

/* 列表样式 */
.markdown-content >>> ul,
.markdown-content >>> ol {
  margin: 12rpx 0;
  padding-left: 40rpx;
}

.markdown-content >>> li {
  margin: 8rpx 0;
  font-size: 28rpx;
  line-height: 1.6;
}

/* 代码样式 */
.markdown-content >>> code {
  background-color: #f5f5f5;
  padding: 4rpx 12rpx;
  border-radius: 6rpx;
  font-family: 'Monaco', 'Menlo', 'Consolas', monospace;
  font-size: 24rpx;
  color: #d73a49;
}

.markdown-content >>> pre {
  background-color: #f6f8fa;
  padding: 24rpx;
  border-radius: 12rpx;
  margin: 16rpx 0;
  overflow-x: auto;
}

.markdown-content >>> pre code {
  background-color: transparent;
  padding: 0;
  color: #333;
  font-size: 24rpx;
}

/* 引用样式 */
.markdown-content >>> blockquote {
  border-left: 8rpx solid #1677ff;
  margin: 16rpx 0;
  padding: 8rpx 16rpx;
  background-color: #f0f8ff;
  border-radius: 8rpx ！important;
}

.markdown-content >>> blockquote p {
  margin: 6rpx 0;
  color: #666;
}

/* 链接样式 */
.markdown-content >>> a {
  color: #1677ff;
 with: underline;
}

/* 分割线 */
.markdown-content >>> hr {
  border: none;
  border-top: 2rpx solid #eee;
  margin: 24rpx 0;
}

/* 表格样式 */
.markdown-content >>> table {
  border-collapse: collapse;
  width: 100%;
  margin: 16rpx 0;
  font-size: 26rpx;
}

.markdown-content >>> th,
.markdown-content >>> td {
  border: 2rpx solid #ddd;
  padding: 12rpx 16rpx;
  text-align: left;
}

.markdown-content >>> th {
  background-color: #f8f9fa;
  font-weight: 600;
}

.markdown-content >>> tr:nth-child(even) {
  background-color: #f9f9f9;
}

/* 强调文字 */
.markdown-content >>> strong {
  font-weight: 600;
}

.markdown-content >>> em {
  font-style: italic;
  color: #555;
}
</style>