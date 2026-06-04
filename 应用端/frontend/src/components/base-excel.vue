<template>
  <view class="excel-wrapper">
    <!-- 触发按钮 -->
    <button
        class="px-6 py-2 bg-primary-500 text-white font-semibold rounded-full shadow
         hover:bg-primary-600 active:scale-95 transition-all duration-200
         focus:outline-none focus:ring-2 focus:ring-primary-300"
        @click="openPopup"
    >
      {{ props.title }}
    </button>
    <!-- 弹窗 -->
    <uni-popup ref="popup" type="bottom">
      <view class="popup-container">
        <view class="popup-title">请选择操作</view>
        <button class="popup-btn import" @click="onImportExcel" @importSuccess="import_success"
                @importError="import_error">📥 {{ t('excel.import_excel') }}
        </button>
        <button class="popup-btn export" @click="onExportExcel" @exportSuccess="export_success"
                @exportError="export_error">📤 {{ t('excel.export_excel') }}
        </button>
        <button class="popup-btn template" @click="downloadTemplate">📑 {{ t('excel.download_template') }} </button>
        <button class="popup-btn cancel" @click="closePopup">{{ t('excel.cancel') }}</button>
      </view>
    </uni-popup>
  </view>
</template>

<script setup>
const {proxy} = getCurrentInstance()

const t = proxy.$tt;

const props = defineProps({
  title: {
    type: String,
    default: "Excel"
  },
  tableName: {
    type: String,
    default: () => ""
  },
  condition: {
    type: Object,
    default: () => {
    }
  },
})
const emit = defineEmits(["import_success", "import_error", "export_success", "export_error"]);
const popup = ref(null)
const openPopup = () => popup.value.open()
const closePopup = () => popup.value.close()

function import_success() {
  emit('import_success')
}

function import_error() {
  emit('import_error')
}

function export_success() {
  emit('export_success')
}

function export_error() {
  emit('export_error')
}

const onImportExcel = () => {
  closePopup()
  uni.chooseFile({
    count: 1,
    type: "file",
    extension: ["xlsx", "xls"],
    success: async (res) => {
      try {
        const file = res.tempFiles[0]
        if (!file) {
          await uni.showToast({title: proxy.$tt('excel.no_selected'), icon: "none"})
          return
        }

        // 构造 FormData
        const formData = new FormData()
        formData.append("file", file)
        // 调用导入接口
        const rsp = await proxy.$app.excel.import(props.tableName, formData)
        if (rsp.code === 0) {
          await uni.showToast({title: proxy.$tt('excel.import_success'), icon: "success"})
          import_success()
        } else {
          await uni.showToast({title: rsp.msg || proxy.$tt('excel.import_failed'), icon: "none"})
          import_error()
        }
      } catch (err) {
        await uni.showToast({title: proxy.$tt('excel.import_failed'), icon: "none"})
        import_error()
      }
    },
    fail: (err) => {
      console.error("文件选择失败", err)
      uni.showToast({title: proxy.$tt('excel.select_failed'), icon: "none"})
    }
  })
}

const onExportExcel = () => {
  closePopup()
  try {
    proxy.$app.excel.export({
      table_name: props.tableName,
      param: props.condition,
    });
    uni.showToast({title: proxy.$tt('excel.export_success'), icon: "success"})
    export_success()
  } catch (e) {
    uni.showToast({title: proxy.$tt('excel.export_failed'), icon: "none"})
    export_error()
  }
}

const downloadTemplate = () => {
  proxy.$app.excel.downloadExcelTemplate(props.tableName).then(() => {
    uni.showToast({title: proxy.$tt('excel.template_download_success'), icon: "success"})
    closePopup()
  })
}
</script>

<style scoped>
.btn-main {
  background-color: #2196F3;
  color: #fff;
  border-radius: 30rpx;
  padding: 20rpx 40rpx;
  font-size: 28rpx;
}

.popup-container {
  background: #fff;
  padding: 30rpx;
  border-radius: 20rpx 20rpx 0 0;
}

.popup-title {
  text-align: center;
  font-size: 32rpx;
  font-weight: bold;
  margin-bottom: 20rpx;
}

.popup-btn {
  width: 100%;
  padding: 20rpx;
  font-size: 28rpx;
  border-radius: 12rpx;
  margin-bottom: 20rpx;
}

.popup-btn.import {
  background-color: #e8f5e9;
  color: #4CAF50;
}

.popup-btn.export {
  background-color: #e3f2fd;
  color: #2196F3;
}

.popup-btn.template {
  background-color: #fff3e0;
  color: #fb8c00;
}

.popup-btn.cancel {
  background-color: #f5f5f5;
  color: #666;
}
</style>
