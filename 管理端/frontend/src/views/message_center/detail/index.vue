<template>
     
  <base-wrapper
>
<div class="flex-between-start" style="background-color: #5D5FEF; height: 140px;">
    <div class="flex-c-center-start h-full">
      <div class="m-x-20">
          <span style="font-weight: bold; font-size: 20px; color: #FFFFFF;">消息中心</span>
      </div>
      <div class="m-x-20 m-t-4">
        <span style="font-size: 14px; color: #FFFFFF;">主键、标题、内容、消息类型、已读状态、创建时间。</span>
      </div>
    </div>
    <div class="flex-start-end h-full m-r-10">
        <img src="@/assets/logo/document.png" height="130px" />
    </div>
</div>
<el-breadcrumb
separator="/"
class="m-20"
>
<el-breadcrumb-item
:to="{ path: '/' }"
>
{{ $t('page.home') }}
</el-breadcrumb-item>
<el-breadcrumb-item
:to="{ path: '/message_center' }"
>
消息中心
</el-breadcrumb-item>
<el-breadcrumb-item
>
消息中心 {{ $t('page.detail') }} 
</el-breadcrumb-item>
</el-breadcrumb>
<base-wrapper
class="p-10"
>
<el-tabs
class="demo-tabs"
>
<el-tab-pane
:label="'消息中心 ' + $t('page.detail') "
>
<el-form
:model='form'
ref='dataFormRef'
label-position='top'
:rules='{"title":[{"required": true,"message": $t("page.rule.required",["标题"]),"trigger": "blur"}],"content":[{"required": true,"message": $t("page.rule.required",["内容"]),"trigger": "blur"}],"type_enum_message_type_enum_id_1":[{"required": true,"message": $t("page.rule.required",["消息类型"]),"trigger": "blur"}],"is_read":[{"required": true,"message": $t("page.rule.required",["已读状态"]),"trigger": "blur"}]}'
label-width='150px'
>
    <el-form-item  label="标题" prop="title">
        <el-input clearable placeholder="标题" v-model="form.title" />
    </el-form-item>

    <el-form-item  label="内容" prop="content">
        <el-input clearable placeholder="内容" v-model="form.content" />
    </el-form-item>

    <el-form-item   label="消息类型" prop="type_enum_message_type_enum_id_1">
        <base-select clearable v-model="form.type_enum_message_type_enum_id_1" api="message_type_enum.list" show_name="type_name"></base-select>
    </el-form-item>

    <el-form-item  label="已读状态" prop="is_read">
        <el-select v-model="form.is_read" :placeholder="$t('page.op.choose')">
            <el-option :label="$t('page.select_op.yes')" :value="true"></el-option>
            <el-option :label="$t('page.select_op.no')" :value="false"></el-option>
        </el-select>
    </el-form-item>

    <el-form-item   label="创建时间" prop="create_time">
        <el-date-picker placeholder="创建时间" v-model="form.create_time" type="datetime"
         format="YYYY-MM-DD HH:mm:ss" value-format="YYYY-MM-DD HH:mm:ss" />
    </el-form-item>

</el-form>
<base-cell
header="所属消息类型"
label-width="180px"
class="m-b-10"
>
<base-cell-item
label="类型名称"
>
{{ formAll.type_name }}
</base-cell-item>
</base-cell>
<base-layout
style="text-align: center;"
w_full="true"
>
<el-button
@click="submitForm"
round
color="#5D5FEF"
style="width: 200px;"
type="primary"
>保 存</el-button>
</base-layout>
</el-tab-pane>
</el-tabs>
</base-wrapper>
</base-wrapper>
   
</template>

<script setup>

const { proxy } = getCurrentInstance();
let form = ref({});
let formAll = ref({});
let rules = ref({});
let routerQuery = proxy.$route.query;
let detail = ref('all')

function submitForm() {
  proxy.$refs.dataFormRef.validate(async (valid) => {
    if (valid) {

      if(import.meta.env.VITE_APP_MODEL === 'PREVIEW'){
          let res = await proxy.$api.table['update']({
              table_name: 'message_center',
              param: form.value
          });

          proxy.$modal.msgSuccess(res.message);

          if(res.code == 0){
              refresh();
          }
      }
      else{
          let res = await proxy.$api.message_center['update'](form.value);
          proxy.$modal.msgSuccess(res.message);
          if(res.code == 0){
            refresh();
          }
      }
    }
  });
}

async function getDetail(id) {
    if(import.meta.env.VITE_APP_MODEL === 'PREVIEW'){
        let res = await proxy.$api.table.get({
            table_name: 'message_center',
            param: {
                message_center_id: id
            }
        });

        if(res.code == 0){
            form.value = Object.assign({}, res.data);
        }
        else{
            proxy.$modal.msgError(res.message);
        }
    }
    else{
        let res = await proxy.$api.message_center['get'](id);
        if(res.code == 0){
            form.value = Object.assign({}, res.data);
        }
        else{
            proxy.$modal.msgError(res.message);
        }
    }
}

async function getDetailAll(id){
    if(import.meta.env.VITE_APP_MODEL === 'PREVIEW'){
        let res = await proxy.$api.table.get({
            table_name: 'message_center',
            param: {
                message_center_id: id
            }
        });

        if(res.code == 0){
            formAll.value = Object.assign({}, res.data);
        }
        else{
            proxy.$modal.msgError(res.message);
        }
    }
    else{
        let res = await proxy.$api.message_center['get'](id);
        if(res.code == 0){
            formAll.value = Object.assign({}, res.data);
        }
        else{
            proxy.$modal.msgError(res.message);
        }
    }
}

function refresh(){
    if(routerQuery.message_center_id){
        getDetail(routerQuery.message_center_id);
    }
    if(detail.value == 'all'){
        if(routerQuery.message_center_id){
            getDetailAll(routerQuery.message_center_id);
        }
    }
}

refresh();

</script>

<style lang="scss" scoped>
</style>