<template>
    <base-wrapper
>
<base-header>
<search-operation
@batchDeleteEvent="handleBatchDelete"
@downloadEvent="downloadTemplate"
@exportEvent="handleExport"
firstSearchComment="厕所名称"
:searchData="listQuery"
@refreshTableData="refreshTableData"
@addEvent="handleAdd"
uploadExcelAPI="toilet_parking.import"
table_name="toilet_parking"
firstSearchData="name"
>
<template #collapse>
    <el-form-item  label="厕所名称" prop="name">
        <el-input clearable placeholder="厕所名称" v-model="listQuery.name" />
    </el-form-item>

    <el-form-item  label="纬度" prop="latitude">
        <el-input clearable placeholder="纬度" v-model="listQuery.latitude" />
    </el-form-item>

    <el-form-item  label="经度" prop="longitude">
        <el-input clearable placeholder="经度" v-model="listQuery.longitude" />
    </el-form-item>

    <el-form-item  label="有免费车位" prop="has_parking">
        <el-select v-model="listQuery.has_parking" :placeholder="$t('page.op.choose')">
            <el-option :label="$t('page.select_op.yes')" :value="true"></el-option>
            <el-option :label="$t('page.select_op.no')" :value="false"></el-option>
        </el-select>
    </el-form-item>

    <el-form-item  label="车位数量" prop="parking_count">
        <el-input clearable placeholder="车位数量" v-model="listQuery.parking_count" />
    </el-form-item>

    <el-form-item  label="开放状态" prop="is_open">
        <el-select v-model="listQuery.is_open" :placeholder="$t('page.op.choose')">
            <el-option :label="$t('page.select_op.yes')" :value="true"></el-option>
            <el-option :label="$t('page.select_op.no')" :value="false"></el-option>
        </el-select>
    </el-form-item>

</template>
</search-operation>
</base-header>
<base-table-p
@selection-change="handleSelectionChange"
ref="baseTableRef"
:params="listQuery"
api="toilet_parking.page"
>
<el-table-column
width="55"
type="selection"
align="center"
>

</el-table-column>
<el-table-column
prop="toilet_parking_id"
label="主键"
align="center"
>

</el-table-column>
<el-table-column
prop="name"
label="厕所名称"
align="center"
>

</el-table-column>
<el-table-column
prop="latitude"
label="纬度"
align="center"
>

</el-table-column>
<el-table-column
prop="longitude"
label="经度"
align="center"
>

</el-table-column>
<el-table-column
prop="has_parking"
label="有免费车位"
align="center"
>
<template #default="scope">
{{  scope.row.has_parking? $t('page.select_op.yes'):$t('page.select_op.no') }}
</template>
</el-table-column>
<el-table-column
prop="parking_count"
label="车位数量"
align="center"
>

</el-table-column>
<el-table-column
prop="is_open"
label="开放状态"
align="center"
>
<template #default="scope">
{{  scope.row.is_open? $t('page.select_op.yes'):$t('page.select_op.no') }}
</template>
</el-table-column>
<el-table-column
:label="$t('page.op.title')"
width="220"
fixed="right"
align="center"
>
<template #default="scope">
<base-info-btn
path="/toilet_parking/detail"
:query="{toilet_parking_id: scope.row.toilet_parking_id}"
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
:rules='{"name":[{"required": true,"message": $t("page.rule.required",["厕所名称"]),"trigger": "blur"}],"latitude":[{"required": true,"message": $t("page.rule.required",["纬度"]),"trigger": "blur"}],"longitude":[{"required": true,"message": $t("page.rule.required",["经度"]),"trigger": "blur"}],"has_parking":[{"required": true,"message": $t("page.rule.required",["有免费车位"]),"trigger": "blur"}],"is_open":[{"required": true,"message": $t("page.rule.required",["开放状态"]),"trigger": "blur"}]}'
label-width='100px'
>
    <el-form-item  label="厕所名称" prop="name">
        <el-input clearable placeholder="厕所名称" v-model="form.name" />
    </el-form-item>

    <el-form-item  label="纬度" prop="latitude">
        <el-input clearable placeholder="纬度" v-model="form.latitude" />
    </el-form-item>

    <el-form-item  label="经度" prop="longitude">
        <el-input clearable placeholder="经度" v-model="form.longitude" />
    </el-form-item>

    <el-form-item  label="有免费车位" prop="has_parking">
        <el-select v-model="form.has_parking" :placeholder="$t('page.op.choose')">
            <el-option :label="$t('page.select_op.yes')" :value="true"></el-option>
            <el-option :label="$t('page.select_op.no')" :value="false"></el-option>
        </el-select>
    </el-form-item>

    <el-form-item  label="车位数量" prop="parking_count">
        <el-input clearable placeholder="车位数量" v-model="form.parking_count" />
    </el-form-item>

    <el-form-item  label="开放状态" prop="is_open">
        <el-select v-model="form.is_open" :placeholder="$t('page.op.choose')">
            <el-option :label="$t('page.select_op.yes')" :value="true"></el-option>
            <el-option :label="$t('page.select_op.no')" :value="false"></el-option>
        </el-select>
    </el-form-item>

</el-form>
<base-cell
v-else
label-width="100px"
>
<base-cell-item
label="主键"
>
{{ form.toilet_parking_id }}
</base-cell-item>
<base-cell-item
label="厕所名称"
>
{{ form.name }}
</base-cell-item>
<base-cell-item
label="纬度"
>
{{ form.latitude }}
</base-cell-item>
<base-cell-item
label="经度"
>
{{ form.longitude }}
</base-cell-item>
<base-cell-item
label="有免费车位"
>
{{ form.has_parking }}
</base-cell-item>
<base-cell-item
label="车位数量"
>
{{ form.parking_count }}
</base-cell-item>
<base-cell-item
label="开放状态"
>
{{ form.is_open }}
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
    multipleSelection.value = selection.map(item => item.toilet_parking_id);
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
        table_name: 'toilet_parking',
        param: {
            toilet_parking_id: row.toilet_parking_id
        }
    });
    proxy.$modal.msgSuccess(res.message);
  }
  else{
    let res = await proxy.$api.toilet_parking.delete(row.toilet_parking_id);
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
        table_name: 'toilet_parking',
        param: {
        ids: ids
        }
    });
    } else {
        res = await proxy.$api.toilet_parking.deleteBatch(ids);
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
    await proxy.$api.toilet_parking.downloadExcel({
      ...listQuery.value
    }, `${new Date().getTime()}.xlsx`, "export", "post")
  }catch (error) {
    console.error("导出失败", error)
  }
}
async function downloadTemplate() {
  try {
    await proxy.$api.toilet_parking.downloadExcel({
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
                table_name: 'toilet_parking',
                param: form.value
            });
            proxy.$modal.msgSuccess(res.message);
          }
          else{
            let res = await proxy.$api.toilet_parking[dialogStatus.value](form.value);
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