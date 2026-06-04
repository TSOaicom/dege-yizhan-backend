<template>
  <!--  <canvas ref="qrCanvas" :width="size" :height="size"></canvas>-->

  <view class="qr-container">
    <canvas
        id="qrcode"
        canvas-id="qrcode"
        class="qr-canvas"
        :style="{ width: `${props.size}px`, height: `${props.size}px` }"
    ></canvas>
  </view>
</template>

<script setup>
import {ref, onMounted, watch, getCurrentInstance, nextTick} from 'vue';
import UQRCode from 'uqrcodejs'


const props = defineProps({

  text: {
    type: String,
    required: true,
  },
  size: {
    type: Number,
    default: 200,
    validator: (value) => value > 0,
  },
})

const instance = getCurrentInstance();
const qrCanvas = ref(null);
// 生成二维码
const generateQRCode = async () => {
  if (!props.text) return;
  try {
    await nextTick();
    // 获取uQRCode实例
    let qr = new UQRCode();
    // 设置二维码内容
    qr.data = props.text;
    // 设置二维码大小，必须与canvas设置的宽高一致
    qr.size = props.size;
    // 调用制作二维码方法
    qr.make();
    // 获取canvas上下文：在自定义组件中必须传入当前组件实例（this），
    // script setup 下没有 this，需要通过 getCurrentInstance().proxy 获取
    const canvasContext = uni.createCanvasContext('qrcode', instance.proxy);
    // 设置uQRCode实例的canvas上下文
    qr.canvasContext = canvasContext;
    // 调用绘制方法将二维码图案绘制到canvas上
    qr.drawCanvas();

  } catch (error) {
    console.error('QR Code generation failed:', error);
  }
};

// 组件挂载时生成二维码（延迟一帧，确保 canvas 节点已渲染到小程序原生层）
onMounted(() => {
  setTimeout(generateQRCode, 50);
});

// 文本或尺寸变化时重绘
watch(() => [props.text, props.size], () => {
  setTimeout(generateQRCode, 50);
});


const reload = () => {
  setTimeout(generateQRCode, 50);
}
/**
 * 外部可调用的方法
 */
defineExpose({
  reload,
})


</script>


<style lang="scss">
.qr-container {
  @apply flex justify-center items-center;

  .qr-canvas {
    @apply block;
    // 确保canvas显示为块级元素
    aspect-ratio: 1/1; // 保持正方形比例
  }
}
</style>