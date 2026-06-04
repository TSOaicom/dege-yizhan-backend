<template>
    <span ref="boxRef">
      <el-popover :visible="visible" placement="top" :width="200" trigger="click">
        <p>{{ $t('page.op.del_msg') }}</p>
        <div style="text-align: right; margin: 0">
          <el-button link @click="visible = false">{{ $t('page.op.cancel') }}</el-button>
          <el-button link @click="handleOk">{{ $t('page.op.submit') }}</el-button>
        </div>
        <template #reference>
          <el-button link style="margin-left: 5px; color: #f36161" @click="visible = true"> 
            <el-icon class="m-r-6"><Delete /></el-icon>  {{ $t('page.op.delete') }}
          </el-button>
        </template>
      </el-popover>
    </span>
</template>

<script setup>
const { proxy } = getCurrentInstance();
let visible = ref(false);

//组件定义的事件
const emits = defineEmits(['ok']);

function handleOk() {
  emits('ok');

  visible.value = false;
}

</script>