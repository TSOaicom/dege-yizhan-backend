<template>
     
  <base-wrapper
>
<div class="flex-between-start" style="background-color: #5D5FEF; height: 140px;">
    <div class="flex-c-center-start h-full">
      <div class="m-x-20">
          <span style="font-weight: bold; font-size: 20px; color: #FFFFFF;">用户信息</span>
      </div>
      <div class="m-x-20 m-t-4">
        <span style="font-size: 14px; color: #FFFFFF;">主键、手机号、城市、车牌号、车辆类型、头像、姓名、认证状态、累计活跃天数、连续活跃天数、密码。</span>
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
:to="{ path: '/user_info' }"
>
用户信息
</el-breadcrumb-item>
<el-breadcrumb-item
>
用户信息 {{ $t('page.detail') }} 
</el-breadcrumb-item>
</el-breadcrumb>
<base-wrapper
class="p-10"
>
<el-tabs
class="demo-tabs"
>
<el-tab-pane
:label="'用户信息 ' + $t('page.detail') "
>
<el-form
:model='form'
ref='dataFormRef'
label-position='top'
:rules='{"phone_number":[{"required": true,"message": $t("page.rule.required",["手机号"]),"trigger": "blur"}],"city":[{"required": true,"message": $t("page.rule.required",["城市"]),"trigger": "blur"}],"car_plate":[{"required": true,"message": $t("page.rule.required",["车牌号"]),"trigger": "blur"}],"car_type_enum_car_type_enum_id_1":[{"required": true,"message": $t("page.rule.required",["车辆类型"]),"trigger": "blur"}],"cert_status_enum_cert_status_enum_id_1":[{"required": true,"message": $t("page.rule.required",["认证状态"]),"trigger": "blur"}]}'
label-width='150px'
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

    <el-form-item   label="车辆类型" prop="car_type_enum_car_type_enum_id_1">
        <base-select clearable v-model="form.car_type_enum_car_type_enum_id_1" api="car_type_enum.list" show_name="type_name"></base-select>
    </el-form-item>

    <el-form-item  label="头像" prop="avatar_resource_key">
        <image-upload :limit="5" v-model="form.avatar" :type="true" />
    </el-form-item>

    <el-form-item  label="姓名" prop="real_name">
        <el-input clearable placeholder="姓名" v-model="form.real_name" />
    </el-form-item>

    <el-form-item   label="认证状态" prop="cert_status_enum_cert_status_enum_id_1">
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
header="所属车辆类型"
label-width="180px"
class="m-b-10"
>
<base-cell-item
label="类型名称"
>
{{ formAll.type_name }}
</base-cell-item>
</base-cell>
<base-cell
header="所属认证状态"
label-width="180px"
class="m-b-10"
>
<base-cell-item
label="状态"
>
{{ formAll.status }}
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
<el-tab-pane
:label=" $t('page.view')  +' 资质认证'"
>
<user_certification_component
 :params="{user_info_user_info_id_1:routerQuery.user_info_id}"
 />
</el-tab-pane>
<el-tab-pane
:label=" $t('page.view')  +' 钱包'"
>
<wallet_component
 :params="{user_info_user_info_id_1:routerQuery.user_info_id}"
 />
</el-tab-pane>
<el-tab-pane
:label=" $t('page.view')  +' 邀请记录'"
>
<invite_record_component
 :params="{user_info_user_info_id_1:routerQuery.user_info_id}"
 />
</el-tab-pane>
<el-tab-pane
:label=" $t('page.view')  +' 反馈'"
>
<feedback_component
 :params="{user_info_user_info_id_1:routerQuery.user_info_id}"
 />
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
              table_name: 'user_info',
              param: form.value
          });

          proxy.$modal.msgSuccess(res.message);

          if(res.code == 0){
              refresh();
          }
      }
      else{
          let res = await proxy.$api.user_info['update'](form.value);
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
            table_name: 'user_info',
            param: {
                user_info_id: id
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
        let res = await proxy.$api.user_info['get'](id);
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
            table_name: 'user_info',
            param: {
                user_info_id: id
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
        let res = await proxy.$api.user_info['get'](id);
        if(res.code == 0){
            formAll.value = Object.assign({}, res.data);
        }
        else{
            proxy.$modal.msgError(res.message);
        }
    }
}

function refresh(){
    if(routerQuery.user_info_id){
        getDetail(routerQuery.user_info_id);
    }
    if(detail.value == 'all'){
        if(routerQuery.user_info_id){
            getDetailAll(routerQuery.user_info_id);
        }
    }
}

refresh();

</script>

<style lang="scss" scoped>
</style>