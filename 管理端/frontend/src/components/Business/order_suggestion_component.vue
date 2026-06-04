<template>
    <base-wrapper
>
<base-header>
<search-operation
@batchDeleteEvent="handleBatchDelete"
@downloadEvent="downloadTemplate"
@exportEvent="handleExport"
firstSearchComment="区域名称"
:searchData="listQuery"
@refreshTableData="refreshTableData"
@addEvent="handleAdd"
uploadExcelAPI="order_suggestion.import"
table_name="order_suggestion"
firstSearchData="areaName"
>
<template #collapse>
    <el-form-item  label="区域名称" prop="area_name">
        <el-input clearable placeholder="区域名称" v-model="listQuery.area_name" />
    </el-form-item>

    <el-form-item  label="预估订单量" prop="order_count">
        <el-input clearable placeholder="预估订单量" v-model="listQuery.order_count" />
    </el-form-item>

    <el-form-item  label="距离(公里)" prop="distance">
        <el-input clearable placeholder="距离(公里)" v-model="listQuery.distance" />
    </el-form-item>

    <el-form-item  label="推荐指数" prop="recommend_index">
        <el-input clearable placeholder="推荐指数" v-model="listQuery.recommend_index" />
    </el-form-item>

    <el-form-item  label="建议方向" prop="suggest_direction">
        <el-input clearable placeholder="建议方向" v-model="listQuery.suggest_direction" />
    </el-form-item>

    <el-form-item v-show="!props.params.heat_level_enum_heat_level_enum_id_1"  label="热力等级" prop="heat_level_enum_heat_level_enum_id_1">
        <base-select clearable v-model="listQuery.heat_level_enum_heat_level_enum_id_1" api="heat_level_enum.list" show_name="level_name"></base-select>
    </el-form-item>

</template>
</search-operation>
</base-header>
<base-table-p
@selection-change="handleSelectionChange"
ref="baseTableRef"
:params="listQuery"
api="order_suggestion.page"
>
<el-table-column
width="55"
type="selection"
align="center"
>

</el-table-column>
<el-table-column
prop="order_suggestion_id"
label="主键"
align="center"
>

</el-table-column>
<el-table-column
prop="area_name"
label="区域名称"
align="center"
>

</el-table-column>
<el-table-column
prop="order_count"
label="预估订单量"
align="center"
>

</el-table-column>
<el-table-column
prop="distance"
label="距离(公里)"
align="center"
>

</el-table-column>
<el-table-column
prop="recommend_index"
label="推荐指数"
align="center"
>

</el-table-column>
<el-table-column
prop="suggest_direction"
label="建议方向"
align="center"
>

</el-table-column>
<el-table-column
prop="heat_level_enum_heat_level_enum_id_1_map.level_name"
label="热力等级"
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
path="/order_suggestion/detail"
:query="{order_suggestion_id: scope.row.order_suggestion_id}"
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
:rules='{"area_name":[{"required": true,"message": $t("page.rule.required",["区域名称"]),"trigger": "blur"}],"order_count":[{"required": true,"message": $t("page.rule.required",["预估订单量"]),"trigger": "blur"}],"distance":[{"required": true,"message": $t("page.rule.required",["距离(公里)"]),"trigger": "blur"}],"recommend_index":[{"required": true,"message": $t("page.rule.required",["推荐指数"]),"trigger": "blur"}],"suggest_direction":[{"required": true,"message": $t("page.rule.required",["建议方向"]),"trigger": "blur"}],"heat_level_enum_heat_level_enum_id_1":[{"required": true,"message": $t("page.rule.required",["热力等级"]),"trigger": "blur"}]}'
label-width='100px'
>
    <el-form-item  label="区域名称" prop="area_name">
        <el-input clearable placeholder="区域名称" v-model="form.area_name" />
    </el-form-item>

    <el-form-item  label="预估订单量" prop="order_count">
        <el-input clearable placeholder="预估订单量" v-model="form.order_count" />
    </el-form-item>

    <el-form-item  label="距离(公里)" prop="distance">
        <el-input clearable placeholder="距离(公里)" v-model="form.distance" />
    </el-form-item>

    <el-form-item  label="推荐指数" prop="recommend_index">
        <el-input clearable placeholder="推荐指数" v-model="form.recommend_index" />
    </el-form-item>

    <el-form-item  label="建议方向" prop="suggest_direction">
        <el-input clearable placeholder="建议方向" v-model="form.suggest_direction" />
    </el-form-item>

    <el-form-item v-show="!props.params.heat_level_enum_heat_level_enum_id_1"  label="热力等级" prop="heat_level_enum_heat_level_enum_id_1">
        <base-select clearable v-model="form.heat_level_enum_heat_level_enum_id_1" api="heat_level_enum.list" show_name="level_name"></base-select>
    </el-form-item>

</el-form>
<base-cell
v-else
label-width="100px"
>
<base-cell-item
label="主键"
>
{{ form.order_suggestion_id }}
</base-cell-item>
<base-cell-item
label="区域名称"
>
{{ form.area_name }}
</base-cell-item>
<base-cell-item
label="预估订单量"
>
{{ form.order_count }}
</base-cell-item>
<base-cell-item
label="距离(公里)"
>
{{ form.distance }}
</base-cell-item>
<base-cell-item
label="推荐指数"
>
{{ form.recommend_index }}
</base-cell-item>
<base-cell-item
label="建议方向"
>
{{ form.suggest_direction }}
</base-cell-item>
<base-cell-item
label="热力等级"
>
{{ form.heat_level_enum_heat_level_enum_id_1 }}
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
    multipleSelection.value = selection.map(item => item.order_suggestion_id);
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
        table_name: 'order_suggestion',
        param: {
            order_suggestion_id: row.order_suggestion_id
        }
    });
    proxy.$modal.msgSuccess(res.message);
  }
  else{
    let res = await proxy.$api.order_suggestion.delete(row.order_suggestion_id);
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
        table_name: 'order_suggestion',
        param: {
        ids: ids
        }
    });
    } else {
        res = await proxy.$api.order_suggestion.deleteBatch(ids);
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
    await proxy.$api.order_suggestion.downloadExcel({
      ...listQuery.value
    }, `${new Date().getTime()}.xlsx`, "export", "post")
  }catch (error) {
    console.error("导出失败", error)
  }
}
async function downloadTemplate() {
  try {
    await proxy.$api.order_suggestion.downloadExcel({
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
                table_name: 'order_suggestion',
                param: form.value
            });
            proxy.$modal.msgSuccess(res.message);
          }
          else{
            let res = await proxy.$api.order_suggestion[dialogStatus.value](form.value);
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