<template>
    <base-wrapper
>
<base-header>
<search-operation
@batchDeleteEvent="handleBatchDelete"
@downloadEvent="downloadTemplate"
@exportEvent="handleExport"
firstSearchComment="手机号"
:searchData="listQuery"
@refreshTableData="refreshTableData"
@addEvent="handleAdd"
uploadExcelAPI="user_info.import"
table_name="user_info"
firstSearchData="phoneNumber"
>
<template #collapse>
    <el-form-item  label="手机号" prop="phone_number">
        <el-input clearable placeholder="手机号" v-model="listQuery.phone_number" />
    </el-form-item>

    <el-form-item  label="城市" prop="city">
        <el-input clearable placeholder="城市" v-model="listQuery.city" />
    </el-form-item>

    <el-form-item  label="车牌号" prop="car_plate">
        <el-input clearable placeholder="车牌号" v-model="listQuery.car_plate" />
    </el-form-item>

    <el-form-item v-show="!props.params.car_type_enum_car_type_enum_id_1"  label="车辆类型" prop="car_type_enum_car_type_enum_id_1">
        <base-select clearable v-model="listQuery.car_type_enum_car_type_enum_id_1" api="car_type_enum.list" show_name="type_name"></base-select>
    </el-form-item>

    <el-form-item  label="姓名" prop="real_name">
        <el-input clearable placeholder="姓名" v-model="listQuery.real_name" />
    </el-form-item>

    <el-form-item v-show="!props.params.cert_status_enum_cert_status_enum_id_1"  label="认证状态" prop="cert_status_enum_cert_status_enum_id_1">
        <base-select clearable v-model="listQuery.cert_status_enum_cert_status_enum_id_1" api="cert_status_enum.list" show_name="status"></base-select>
    </el-form-item>

    <el-form-item  label="累计活跃天数" prop="active_days">
        <el-input clearable placeholder="累计活跃天数" v-model="listQuery.active_days" />
    </el-form-item>

    <el-form-item  label="连续活跃天数" prop="continue_active_days">
        <el-input clearable placeholder="连续活跃天数" v-model="listQuery.continue_active_days" />
    </el-form-item>

</template>
</search-operation>
</base-header>
<base-table-p
@selection-change="handleSelectionChange"
ref="baseTableRef"
:params="listQuery"
api="user_info.page"
>
<el-table-column
width="55"
type="selection"
align="center"
>

</el-table-column>
<el-table-column
prop="user_info_id"
label="主键"
align="center"
>

</el-table-column>
<el-table-column
prop="phone_number"
label="手机号"
align="center"
>

</el-table-column>
<el-table-column
prop="city"
label="城市"
align="center"
>

</el-table-column>
<el-table-column
prop="car_plate"
label="车牌号"
align="center"
>

</el-table-column>
<el-table-column
prop="avatar_resource_key"
label="头像"
align="center"
>
<template #default="scope">
<image-preview
:height="50"
:src="scope.row.avatar.map(item => item.url).join(',')"
:width="50"
/>
</template>
</el-table-column>
<el-table-column
prop="real_name"
label="姓名"
align="center"
>

</el-table-column>
<el-table-column
prop="active_days"
label="累计活跃天数"
align="center"
>

</el-table-column>
<el-table-column
prop="continue_active_days"
label="连续活跃天数"
align="center"
>

</el-table-column>
<el-table-column
prop="car_type_enum_car_type_enum_id_1_map.type_name"
label="车辆类型"
align="center"
>

</el-table-column>
<el-table-column
prop="cert_status_enum_cert_status_enum_id_1_map.status"
label="认证状态"
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
path="/user_info/detail"
:query="{user_info_id: scope.row.user_info_id}"
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
:rules='{"phone_number":[{"required": true,"message": $t("page.rule.required",["手机号"]),"trigger": "blur"}],"city":[{"required": true,"message": $t("page.rule.required",["城市"]),"trigger": "blur"}],"car_plate":[{"required": true,"message": $t("page.rule.required",["车牌号"]),"trigger": "blur"}],"car_type_enum_car_type_enum_id_1":[{"required": true,"message": $t("page.rule.required",["车辆类型"]),"trigger": "blur"}],"cert_status_enum_cert_status_enum_id_1":[{"required": true,"message": $t("page.rule.required",["认证状态"]),"trigger": "blur"}]}'
label-width='100px'
>
    <el-form-item  label="手机号" prop="phone_number">
        <el-input clearable placeholder="手机号" v-model="form.phone_number" />
    </el-form-item>

    <el-form-item  label="城市" prop="city">
        <el-input clearable placeholder="城市" v-model="form.city" />
    </el-form-item>

    <el-form-item  label="车牌号" prop="car_plate">
        <el-input clearable placeholder="车牌号" v-model="form.car_plate" />
    </el-form-item>

    <el-form-item v-show="!props.params.car_type_enum_car_type_enum_id_1"  label="车辆类型" prop="car_type_enum_car_type_enum_id_1">
        <base-select clearable v-model="form.car_type_enum_car_type_enum_id_1" api="car_type_enum.list" show_name="type_name"></base-select>
    </el-form-item>

    <el-form-item  label="头像" prop="avatar_resource_key">
        <image-upload :limit="5" v-model="form.avatar" :type="true" />
    </el-form-item>

    <el-form-item  label="姓名" prop="real_name">
        <el-input clearable placeholder="姓名" v-model="form.real_name" />
    </el-form-item>

    <el-form-item v-show="!props.params.cert_status_enum_cert_status_enum_id_1"  label="认证状态" prop="cert_status_enum_cert_status_enum_id_1">
        <base-select clearable v-model="form.cert_status_enum_cert_status_enum_id_1" api="cert_status_enum.list" show_name="status"></base-select>
    </el-form-item>

    <el-form-item  label="累计活跃天数" prop="active_days">
        <el-input clearable placeholder="累计活跃天数" v-model="form.active_days" />
    </el-form-item>

    <el-form-item  label="连续活跃天数" prop="continue_active_days">
        <el-input clearable placeholder="连续活跃天数" v-model="form.continue_active_days" />
    </el-form-item>

    <el-form-item  label="密码" prop="password">
        <el-input clearable placeholder="密码" v-model="form.password" />
    </el-form-item>

</el-form>
<base-cell
v-else
label-width="100px"
>
<base-cell-item
label="主键"
>
{{ form.user_info_id }}
</base-cell-item>
<base-cell-item
label="手机号"
>
{{ form.phone_number }}
</base-cell-item>
<base-cell-item
label="城市"
>
{{ form.city }}
</base-cell-item>
<base-cell-item
label="车牌号"
>
{{ form.car_plate }}
</base-cell-item>
<base-cell-item
label="车辆类型"
>
{{ form.car_type_enum_car_type_enum_id_1 }}
</base-cell-item>
<base-cell-item
label="头像"
>
{{ form.avatar_resource_key }}
</base-cell-item>
<base-cell-item
label="姓名"
>
{{ form.real_name }}
</base-cell-item>
<base-cell-item
label="认证状态"
>
{{ form.cert_status_enum_cert_status_enum_id_1 }}
</base-cell-item>
<base-cell-item
label="累计活跃天数"
>
{{ form.active_days }}
</base-cell-item>
<base-cell-item
label="连续活跃天数"
>
{{ form.continue_active_days }}
</base-cell-item>
<base-cell-item
label="密码"
>
{{ form.password }}
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
    multipleSelection.value = selection.map(item => item.user_info_id);
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
        table_name: 'user_info',
        param: {
            user_info_id: row.user_info_id
        }
    });
    proxy.$modal.msgSuccess(res.message);
  }
  else{
    let res = await proxy.$api.user_info.delete(row.user_info_id);
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
        table_name: 'user_info',
        param: {
        ids: ids
        }
    });
    } else {
        res = await proxy.$api.user_info.deleteBatch(ids);
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
    await proxy.$api.user_info.downloadExcel({
      ...listQuery.value
    }, `${new Date().getTime()}.xlsx`, "export", "post")
  }catch (error) {
    console.error("导出失败", error)
  }
}
async function downloadTemplate() {
  try {
    await proxy.$api.user_info.downloadExcel({
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
                table_name: 'user_info',
                param: form.value
            });
            proxy.$modal.msgSuccess(res.message);
          }
          else{
            let res = await proxy.$api.user_info[dialogStatus.value](form.value);
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