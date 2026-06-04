<template>
    <base-wrapper
>
<base-header>
<search-operation
@batchDeleteEvent="handleBatchDelete"
@downloadEvent="downloadTemplate"
@exportEvent="handleExport"
firstSearchComment="审核备注"
:searchData="listQuery"
@refreshTableData="refreshTableData"
@addEvent="handleAdd"
uploadExcelAPI="user_certification.import"
table_name="user_certification"
firstSearchData="remark"
>
<template #collapse>
    <el-form-item v-show="!props.params.user_info_user_info_id_1"  label="用户ID" prop="user_info_user_info_id_1">
        <base-select clearable v-model="listQuery.user_info_user_info_id_1" api="user_info.list" show_name="phone_number"></base-select>
    </el-form-item>

    <el-form-item v-show="!props.params.status_enum_cert_status_enum_id_1"  label="审核状态" prop="status_enum_cert_status_enum_id_1">
        <base-select clearable v-model="listQuery.status_enum_cert_status_enum_id_1" api="cert_status_enum.list" show_name="status"></base-select>
    </el-form-item>

    <el-form-item  label="审核备注" prop="remark">
        <el-input clearable placeholder="审核备注" v-model="listQuery.remark" />
    </el-form-item>

</template>
</search-operation>
</base-header>
<base-table-p
@selection-change="handleSelectionChange"
ref="baseTableRef"
:params="listQuery"
api="user_certification.page"
>
<el-table-column
width="55"
type="selection"
align="center"
>

</el-table-column>
<el-table-column
prop="user_certification_id"
label="主键"
align="center"
>

</el-table-column>
<el-table-column
prop="driver_license_resource_key"
label="驾驶证"
align="center"
>
<template #default="scope">
<image-preview
:height="50"
:src="scope.row.driver_license.map(item => item.url).join(',')"
:width="50"
/>
</template>
</el-table-column>
<el-table-column
prop="vehicle_license_resource_key"
label="行驶证"
align="center"
>
<template #default="scope">
<image-preview
:height="50"
:src="scope.row.vehicle_license.map(item => item.url).join(',')"
:width="50"
/>
</template>
</el-table-column>
<el-table-column
prop="taxi_license_resource_key"
label="营运证"
align="center"
>
<template #default="scope">
<image-preview
:height="50"
:src="scope.row.taxi_license.map(item => item.url).join(',')"
:width="50"
/>
</template>
</el-table-column>
<el-table-column
prop="remark"
label="审核备注"
align="center"
>

</el-table-column>
<el-table-column
prop="user_info_user_info_id_1_map.phone_number"
label="用户ID"
align="center"
>

</el-table-column>
<el-table-column
prop="status_enum_cert_status_enum_id_1_map.status"
label="审核状态"
align="center"
>

</el-table-column>
<el-table-column
:label="$t('page.op.title')"
width="220"
fixed="right"
align="center"
>
<template #default="scope">
<base-info-btn
path="/user_certification/detail"
:query="{user_certification_id: scope.row.user_certification_id}"
text="详情"
>
</base-info-btn>
<base-edit-btn
@ok="handleUpdate(scope.row)"
>
</base-edit-btn>
<base-delete-btn
@ok="handleDelete(scope.row)"
></base-delete-btn>
</template>
</el-table-column>
</base-table-p>
<base-dialog
v-if="dialogVisible"
:title="dialogTitleObj[dialogStatus]"
width="50%"
style="max-width: 600px;"
v-model="dialogVisible"
>
<el-form
ref='dataFormRef'
v-if='dialogStatus !== "detail"'
:model='form'
label-position='top'
:rules='{"user_info_user_info_id_1":[{"required": true,"message": $t("page.rule.required",["用户ID"]),"trigger": "blur"}],"status_enum_cert_status_enum_id_1":[{"required": true,"message": $t("page.rule.required",["审核状态"]),"trigger": "blur"}]}'
label-width='100px'
>
    <el-form-item v-show="!props.params.user_info_user_info_id_1"  label="用户ID" prop="user_info_user_info_id_1">
        <base-select clearable v-model="form.user_info_user_info_id_1" api="user_info.list" show_name="phone_number"></base-select>
    </el-form-item>

    <el-form-item  label="驾驶证" prop="driver_license_resource_key">
        <image-upload :limit="5" v-model="form.driver_license" :type="true" />
    </el-form-item>

    <el-form-item  label="行驶证" prop="vehicle_license_resource_key">
        <image-upload :limit="5" v-model="form.vehicle_license" :type="true" />
    </el-form-item>

    <el-form-item  label="营运证" prop="taxi_license_resource_key">
        <image-upload :limit="5" v-model="form.taxi_license" :type="true" />
    </el-form-item>

    <el-form-item v-show="!props.params.status_enum_cert_status_enum_id_1"  label="审核状态" prop="status_enum_cert_status_enum_id_1">
        <base-select clearable v-model="form.status_enum_cert_status_enum_id_1" api="cert_status_enum.list" show_name="status"></base-select>
    </el-form-item>

    <el-form-item  label="审核备注" prop="remark">
        <el-input clearable placeholder="审核备注" v-model="form.remark" />
    </el-form-item>

</el-form>
<base-cell
v-else
label-width="100px"
>
<base-cell-item
label="主键"
>
{{ form.user_certification_id }}
</base-cell-item>
<base-cell-item
label="用户ID"
>
{{ form.user_info_user_info_id_1 }}
</base-cell-item>
<base-cell-item
label="驾驶证"
>
{{ form.driver_license_resource_key }}
</base-cell-item>
<base-cell-item
label="行驶证"
>
{{ form.vehicle_license_resource_key }}
</base-cell-item>
<base-cell-item
label="营运证"
>
{{ form.taxi_license_resource_key }}
</base-cell-item>
<base-cell-item
label="审核状态"
>
{{ form.status_enum_cert_status_enum_id_1 }}
</base-cell-item>
<base-cell-item
label="审核备注"
>
{{ form.remark }}
</base-cell-item>
</base-cell>
<template #footer>
<el-button
@click="dialogVisible = false"
round
>{{ $t('page.op.cancel') }}</el-button>
<el-button
@click="submitForm"
round
color="#5D5FEF"
type="primary"
>{{ $t('page.op.submit') }}</el-button>
</template>
</base-dialog>
</base-wrapper>
</template>

<script setup>
import {ElMessageBox} from "element-plus";

const { proxy } = getCurrentInstance();

const props = defineProps({
    params: { type: Object, default: () => ({}) },
})
let multipleSelection = ref([])
function handleSelectionChange(selection) {
    multipleSelection.value = selection.map(item => item.user_certification_id);
}

// 计算属性，用于判断 params 的长度
const paramsLength = computed(() =>  Object.keys(props.params).length)

// 计算属性，判断 params 是否为空
const isParamsEmpty = computed(() => paramsLength.value === 0)

let listQuery = ref({});
let form = ref({});
let dialogVisible = ref(false);
let dialogStatus = ref('');
let dialogTitleObj = ref({update: proxy.$tt('page.op.edit'), add: proxy.$tt('page.op.add'), detail: proxy.$tt('page.op.detail')});
let rules = ref({});
if(isParamsEmpty.value){
  let routerQuery = proxy.$route.query;
  Object.assign(listQuery.value, routerQuery);
}
else{
  Object.assign(listQuery.value, props.params);
}


function refreshTableData() {
  proxy.$refs.baseTableRef.refresh();
}
function handleDetail(row) {
  form.value = Object.assign({}, row);
  dialogStatus.value = 'detail';
  dialogVisible.value = true;
}
function handleAdd() {
  form.value = { ...props.params };
  dialogStatus.value = 'add';
  dialogVisible.value = true;
}
function handleUpdate(row) {
  form.value = Object.assign({}, row);
  dialogStatus.value = 'update';
  dialogVisible.value = true;
}
async function handleDelete(row) {
  if(import.meta.env.VITE_APP_MODEL === 'PREVIEW'){
    let res = await proxy.$api.table.delete({
        table_name: 'user_certification',
        param: {
            user_certification_id: row.user_certification_id
        }
    });
    proxy.$modal.msgSuccess(res.message);
  }
  else{
    let res = await proxy.$api.user_certification.delete(row.user_certification_id);
    proxy.$modal.msgSuccess(res.message);
  }
  refreshTableData();
}

// 批量删除
async function handleBatchDelete() {
    if (multipleSelection.value.length === 0) return;

    try {
    await ElMessageBox.confirm(
        `确定要删除选中的 ${multipleSelection.value.length} 条数据吗？`,
        '提示',
        {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        }
    );
    const ids = multipleSelection.value
    let res;

    if (import.meta.env.VITE_APP_MODEL === 'PREVIEW') {
    res = await proxy.$api.table.deleteBatch({
        table_name: 'user_certification',
        param: {
        ids: ids
        }
    });
    } else {
        res = await proxy.$api.user_certification.deleteBatch(ids);
    }

    proxy.$modal.msgSuccess(res.message || "批量删除成功");
    refreshTableData();
    multipleSelection.value = [];
    } catch (error) {
        console.error("批量删除失败", error);
    }
}
async function handleExport() {
  try {
    await proxy.$api.user_certification.downloadExcel({
      ...listQuery.value
    }, `${new Date().getTime()}.xlsx`, "export", "post")
  }catch (error) {
    console.error("导出失败", error)
  }
}
async function downloadTemplate() {
  try {
    await proxy.$api.user_certification.downloadExcel({
      ...listQuery.value
    }, `${new Date().getTime()}.xlsx`, "downloadTemplate", "get")
  }catch (error) {
    console.error("导出失败", error)
  }
}
function submitForm() {
  if(dialogStatus.value == 'detail'){
      dialogVisible.value = false;
  }
  else {
      proxy.$refs.dataFormRef.validate(async (valid) => {
        if (valid) {
          if(import.meta.env.VITE_APP_MODEL === 'PREVIEW'){
            let res = await proxy.$api.table[dialogStatus.value]({
                table_name: 'user_certification',
                param: form.value
            });
            proxy.$modal.msgSuccess(res.message);
          }
          else{
            let res = await proxy.$api.user_certification[dialogStatus.value](form.value);
            proxy.$modal.msgSuccess(res.message);
          }
          refreshTableData();
          dialogVisible.value = false;
        }
      });
  }
}

function choose(item){
    form.value = Object.assign({}, item);
    dialogVisible.value = true;
    dialogStatus.value = 'update';
}
</script>

<style lang="scss" scoped>
</style>