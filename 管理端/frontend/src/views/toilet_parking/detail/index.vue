<template>
     
  <base-wrapper
>
<div class="flex-between-start" style="background-color: #5D5FEF; height: 140px;">
    <div class="flex-c-center-start h-full">
      <div class="m-x-20">
          <span style="font-weight: bold; font-size: 20px; color: #FFFFFF;">厕所停车</span>
      </div>
      <div class="m-x-20 m-t-4">
        <span style="font-size: 14px; color: #FFFFFF;">主键、厕所名称、纬度、经度、有免费车位、车位数量、开放状态。</span>
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
:to="{ path: '/toilet_parking' }"
>
厕所停车
</el-breadcrumb-item>
<el-breadcrumb-item
>
厕所停车 {{ $t('page.detail') }} 
</el-breadcrumb-item>
</el-breadcrumb>
<base-wrapper
class="p-10"
>
<el-tabs
class="demo-tabs"
>
<el-tab-pane
:label="'厕所停车 ' + $t('page.detail') "
>
<el-form
:model='form'
ref='dataFormRef'
label-position='top'
:rules='{"name":[{"required": true,"message": $t("page.rule.required",["厕所名称"]),"trigger": "blur"}],"latitude":[{"required": true,"message": $t("page.rule.required",["纬度"]),"trigger": "blur"}],"longitude":[{"required": true,"message": $t("page.rule.required",["经度"]),"trigger": "blur"}],"has_parking":[{"required": true,"message": $t("page.rule.required",["有免费车位"]),"trigger": "blur"}],"is_open":[{"required": true,"message": $t("page.rule.required",["开放状态"]),"trigger": "blur"}]}'
label-width='150px'
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
let detail = ref('normally')

function submitForm() {
  proxy.$refs.dataFormRef.validate(async (valid) => {
    if (valid) {

      if(import.meta.env.VITE_APP_MODEL === 'PREVIEW'){
          let res = await proxy.$api.table['update']({
              table_name: 'toilet_parking',
              param: form.value
          });

          proxy.$modal.msgSuccess(res.message);

          if(res.code == 0){
              refresh();
          }
      }
      else{
          let res = await proxy.$api.toilet_parking['update'](form.value);
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
            table_name: 'toilet_parking',
            param: {
                toilet_parking_id: id
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
        let res = await proxy.$api.toilet_parking['get'](id);
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
            table_name: 'toilet_parking',
            param: {
                toilet_parking_id: id
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
        let res = await proxy.$api.toilet_parking['get'](id);
        if(res.code == 0){
            formAll.value = Object.assign({}, res.data);
        }
        else{
            proxy.$modal.msgError(res.message);
        }
    }
}

function refresh(){
    if(routerQuery.toilet_parking_id){
        getDetail(routerQuery.toilet_parking_id);
    }
    if(detail.value == 'all'){
        if(routerQuery.toilet_parking_id){
            getDetailAll(routerQuery.toilet_parking_id);
        }
    }
}

refresh();

</script>

<style lang="scss" scoped>
</style>