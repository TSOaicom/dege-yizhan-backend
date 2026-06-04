<template>
  <base-wrapper>

    <base-table-p
        ref="baseTableRef"
        :params="listQuery"
        api="dynamic_api_setting.page"
        preview_api_manager="dynamic_api_setting_p.page"
    >


      <el-table-column prop="id" :label="$t('page.api.columns.id')" align="center"/>
      <el-table-column prop="keyName" :label="$t('page.api.columns.keyName')" align="center"/>
      <el-table-column prop="description" :label="$t('page.api.columns.description')" align="center"/>

      <el-table-column prop="protocol" :label="$t('page.api.columns.protocol')" align="center"/>
      <el-table-column prop="method" :label="$t('page.api.columns.method')" align="center"/>
      <el-table-column prop="url" :label="$t('page.api.columns.url')" align="center"/>
      <el-table-column prop="token" label="token" align="center">
        <template #default="scope">
          <span>******</span>
        </template>
      </el-table-column>
      <el-table-column prop="header" :label="$t('page.api.columns.header')" align="center"/>
      <el-table-column prop="bodyType" :label="$t('page.api.columns.bodyType')" align="center"/>
      <el-table-column prop="bodyTemplate" :label="$t('page.api.columns.bodyTemplate')" align="center"/>

      <el-table-column width="100" fixed="right" :label="$t('page.api.columns.op')" align="center">
        <template #default="scope">
          <base-edit-btn
              @ok="handleUpdate(scope.row)"
          >
          </base-edit-btn>
        </template>
      </el-table-column>

    </base-table-p>

    <base-dialog
        :title="dialogTitleObj[dialogStatus]"
        width="50%"
        style="max-width: 600px;"
        v-model="dialogVisible"
        @close="closeDialog"
    >
      <el-form
          ref='dataFormRef'
          v-if='dialogStatus !== "detail"'
          :model='form'
          label-position='top'
          label-width='100px'
      >
        <el-form-item :label="$t('page.api.columns.method')" prop="method">
          <el-input :placeholder="$t('page.api.columns.method')" v-model="form.method"/>
        </el-form-item>


        <el-form-item :label="$t('page.api.columns.url')" prop="url">
          <el-input :placeholder="$t('page.api.columns.url')" v-model="form.url"/>
        </el-form-item>

        <el-form-item label="token" prop="url">
          <el-input placeholder="token" v-model="form.token"/>
        </el-form-item>

        <el-form-item :label="$t('page.api.columns.header')" prop="header">
          <el-input :placeholder="$t('page.api.columns.header')" v-model="form.header"/>
        </el-form-item>

        <el-form-item :label="$t('page.api.columns.bodyType')" prop="bodyType">
          <el-input :placeholder="$t('page.api.columns.bodyType')" v-model="form.bodyType"/>
        </el-form-item>

        <el-form-item :label="$t('page.api.columns.bodyTemplate')" prop="bodyType">
          <el-input :placeholder="$t('page.api.columns.bodyTemplate')" v-model="form.bodyTemplate"/>
        </el-form-item>

      </el-form>

      <template #footer>
        <el-button
            @click="closeDialog"
            round
        >{{ $t('page.op.cancel') }}
        </el-button>
        <el-button
            @click="submitForm"
            round
            color="#5D5FEF"
            type="primary"
        >{{ $t('page.op.submit') }}
        </el-button>
      </template>
    </base-dialog>

  </base-wrapper>
</template>
<script setup>
const {proxy} = getCurrentInstance();

let listQuery = ref({});
let form = ref({});
let dialogVisible = ref(false);
let dialogStatus = ref('');
let dialogTitleObj = ref({update: proxy.$tt('page.op.edit'), add: proxy.$tt('page.op.add'), detail: '详情'});

const closeDialog = () => {
  dialogVisible.value = false
}

function handleUpdate(row) {
  form.value = Object.assign({}, row);
  dialogStatus.value = 'update';
  dialogVisible.value = true;
}

async function submitForm() {
  if (dialogStatus.value == 'detail') {
    dialogVisible.value = false;
  } else {
    let res = await proxy.$api.dynamic_api_setting_p[dialogStatus.value](form.value);
    proxy.$modal.msgSuccess(res.message);
    refreshTableData();
    dialogVisible.value = false;
  }
}

function refreshTableData() {
  proxy.$refs.baseTableRef.refresh();
}

</script>