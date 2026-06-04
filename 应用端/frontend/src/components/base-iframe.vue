<template>
  <iframe
      ref="iframeRef"
      sandbox="allow-scripts allow-modals"
      class="iframe-runtime"
      @load="onIframeLoad"
      :src="computedSrc"
      :srcdoc="computedSrcdoc"
  />
</template>

<script setup>
const {proxy} = getCurrentInstance()

const props = defineProps({
  htmlContent: {
    type: String,
    default: ''
  },
  url: {
    type: String,
    default: ''
  }
});

if (props.htmlContent && props.url) {
  console.warn(
      '[IframeRuntime] htmlContent and url cannot be used together. htmlContent will be used.'
  )
}

const computedSrc = computed(() => {
  return props.htmlContent ? undefined : props.url || 'about:blank'
})

const computedSrcdoc = computed(() => {
  return props.htmlContent || undefined
})

function onIframeLoad() {

}

watch(
    () => props.htmlContent,
    () => {
      console.log('=========1')
    },
    {immediate: true, deep: true}
)

watch(
    () => props.url,
    () => {
      console.log('=========2')
    },
    {immediate: true, deep: true}
)

</script>

<style scoped>
.iframe-runtime {
  width: 100%;
  height: 100%;
  border: none;
}
</style>