<template>
    <base-wrapper
>
<base-header>
<search-operation
@batchDeleteEvent="handleBatchDelete"
@downloadEvent="downloadTemplate"
@exportEvent="handleExport"
firstSearchComment="标题"
:searchData="listQuery"
@refreshTableData="refreshTableData"
@addEvent="handleAdd"
uploadExcelAPI="message_center.import"
table_name="message_center"
firstSearchData="title"
>
<template #collapse>
    <el-form-item  label="标题" prop="title">
        <el-input clearable placeholder="标题" v-model="listQuery.title" />
    </el-form-item>

    <el-form-item  label="内容" prop="content">
        <el-input clearable placeholder="内容" v-model="listQuery.content" />
    </el-form-item>

    <el-form-item v-show="!props.params.type_enum_message_type_enum_id_1"  label="消息类型" prop="type_enum_message_type_enum_id_1">
        <base-select clearable v-model="listQuery.type_enum_message_type_enum_id_1" api="message_type_enum.list" show_name="type_name"></base-select>
    </el-form-item>

    <el-form-item  label="已读状态" prop="is_read">
        <el-select v-model="listQuery.is_read" :placeholder="$t('page.op.choose')">
            <el-option :label="$t('page.select_op.yes')" :value="true"></el-option>
            <el-option :label="$t('page.select_op.no')" :value="false"></el-option>
        </el-select>
    </el-form-item>

    <el-form-item   label="创建时间" prop="create_time">
        <el-date-picker placeholder="创建时间" v-model="listQuery.create_time" type="datetime"
         format="YYYY-MM-DD HH:mm:ss" value-format="YYYY-MM-DD HH:mm:ss" />
    </el-form-item>

</template>
</search-operation>
</base-header>
<base-table-p
@selection-change="handleSelectionChange"
ref="baseTableRef"
:params="listQuery"
api="message_center.page"
>
<el-table-column
width="55"
type="selection"
align="center"
>

</el-table-column>
<el-table-column
prop="message_center_id"
label="主键"
align="center"
>

</el-table-column>
<el-table-column
prop="title"
label="标题"
align="center"
>

</el-table-column>
<el-table-column
prop="content"
label="内容"
align="center"
>

</el-table-column>
<el-table-column
prop="is_read"
label="已读状态"
align="center"
>
<template #default="scope">
{{  scope.row.is_read? $t('page.select_op.yes'):$t('page.select_op.no') }}
</template>
</el-table-column>
<el-table-column
prop="create_time"
label="创建时间"
align="center"
>
<template #default="scope">
{{ parseTime(scope.row.create_time,'{y}-{m}-{d} {h}:{i}:{s}') }}
</template>
</el-table-column>
<el-table-column
prop="type_enum_message_type_enum_id_1_map.type_name"
label="消息类型"
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
path="/message_center/detail"
:query="{message_center_id: scope.row.message_center_id}"
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
:rules='{"title":[{"required": true,"message": $t("page.rule.required",["标题"]),"trigger": "blur"}],"content":[{"required": true,"message": $t("page.rule.required",["内容"]),"trigger": "blur"}],"type_enum_message_type_enum_id_1":[{"required": true,"message": $t("page.rule.required",["消息类型"]),"trigger": "blur"}],"is_read":[{"required": true,"message": $t("page.rule.required",["已读状态"]),"trigger": "blur"}]}'
label-width='100px'
>
    <el-form-item  label="标题" prop="title">
        <el-input clearable placeholder="标题" v-model="form.title" />
    </el-form-item>

    <el-form-item  label="内容" prop="content">
        <el-input clearable placeholder="内容" v-model="form.content" />
    </el-form-item>

    <el-form-item v-show="!props.params.type_enum_message_type_enum_id_1"  label="消息类型" prop="type_enum_message_type_enum_id_1">
        <base-select clearable v-model="form.type_enum_message_type_enum_id_1" api="message_type_enum.list" show_name="type_name"></base-select>
    </el-form-item>

    <el-form-item  label="已读状态" prop="is_read">
        <el-select v-model="form.is_read" :placeholder="$t('page.op.choose')">
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
{{ form.message_center_id }}
</base-cell-item>
<base-cell-item
label="标题"
>
{{ form.title }}
</base-cell-item>
<base-cell-item
label="内容"
>
{{ form.content }}
</base-cell-item>
<base-cell-item
label="消息类型"
>
{{ form.type_enum_message_type_enum_id_1 }}
</base-cell-item>
<base-cell-item
label="已读状态"
>
{{ form.is_read }}
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
    multipleSelection.value = selection.map(item => item.message_center_id);
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
        table_name: 'message_center',
        param: {
            message_center_id: row.message_center_id
        }
    });
    proxy.$modal.msgSuccess(res.message);
  }
  else{
    let res = await proxy.$api.message_center.delete(row.message_center_id);
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
        table_name: 'message_center',
        param: {
        ids: ids
        }
    });
    } else {
        res = await proxy.$api.message_center.deleteBatch(ids);
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
    await proxy.$api.message_center.downloadExcel({
      ...listQuery.value
    }, `${new Date().getTime()}.xlsx`, "export", "post")
  }catch (error) {
    console.error("导出失败", error)
  }
}
async function downloadTemplate() {
  try {
    await proxy.$api.message_center.downloadExcel({
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
                table_name: 'message_center',
                param: form.value
            });
            proxy.$modal.msgSuccess(res.message);
          }
          else{
            let res = await proxy.$api.message_center[dialogStatus.value](form.value);
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