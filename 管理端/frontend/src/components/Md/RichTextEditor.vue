<template>
  <div class="rich-text-editor">
    <vue-quill
        v-model:content="content"
        theme="snow"
        content-type="html"
        :toolbar="toolbarOptions"
        style="height: 300px;"
    />
  </div>
</template>

<script setup>
import { QuillEditor as VueQuill } from "@vueup/vue-quill"
import "@vueup/vue-quill/dist/vue-quill.snow.css"

// defineModel 自动生成 props + emit
const content = defineModel({ default: "" })

// 自定义 toolbar 配置
const toolbarOptions = [
  ["bold", "italic", "underline", "strike"], // 加粗 斜体 下划线 删除线
  [{ header: [1, 2, 3, 4, 5, 6, false] }],   // 标题（h1-h6）
  [{ list: "ordered" }, { list: "bullet" }], // 列表
  [{ align: [] }],                           // 对齐方式（左/中/右/两端）
  // ["link", "image"],                         // 链接、图片
  // todo 放开图片
  ["link"],                         // 链接
  ["clean"]                                  // 清除格式
]
</script>

<style scoped>
.rich-text-editor {
  border: 1px solid #dcdfe6;
  border-radius: 6px;
  overflow: hidden;
}
</style>
