<template>
     
  <base-wrapper
>
<div class="flex-between-start" style="background-color: #5D5FEF; height: 140px;">
    <div class="flex-c-center-start h-full">
      <div class="m-x-20">
          <span style="font-weight: bold; font-size: 20px; color: #FFFFFF;">资质认证</span>
      </div>
      <div class="m-x-20 m-t-4">
        <span style="font-size: 14px; color: #FFFFFF;">主键、用户ID、驾驶证、行驶证、营运证、审核状态、审核备注。</span>
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
:to="{ path: '/user_certification' }"
>
资质认证
</el-breadcrumb-item>
<el-breadcrumb-item
>
资质认证 {{ $t('page.detail') }} 
</el-breadcrumb-item>
</el-breadcrumb>
<base-wrapper
class="p-10"
>
<el-tabs
class="demo-tabs"
>
<el-tab-pane
:label="'资质认证 ' + $t('page.detail') "
>
<el-form
:model='form'
ref='dataFormRef'
label-position='top'
:rules='{"user_info_user_info_id_1":[{"required": true,"message": $t("page.rule.required",["用户ID"]),"trigger": "blur"}],"status_enum_cert_status_enum_id_1":[{"required": true,"message": $t("page.rule.required",["审核状态"]),"trigger": "blur"}]}'
label-width='150px'
>
    <el-form-item   label="用户ID" prop="user_info_user_info_id_1">
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

    <el-form-item   label="审核状态" prop="status_enum_cert_status_enum_id_1">
        <base-select clearable v-model="form.status_enum_cert_status_enum_id_1" api="cert_status_enum.list" show_name="status"></base-select>
    </el-form-item>

    <el-form-item  label="审核备注" prop="remark">
        <el-input clearable placeholder="审核备注" v-model="form.remark" />
    </el-form-item>

</el-form>
<base-cell
header="所属用户信息"
label-width="180px"
class="m-b-10"
>
<base-cell-item
label="手机号"
>
{{ formAll.phone_number }}
</base-cell-item>
<base-cell-item
label="城市"
>
{{ formAll.city }}
</base-cell-item>
<base-cell-item
label="车牌号"
>
{{ formAll.car_plate }}
</base-cell-item>
<base-cell-item
label="车辆类型"
>
{{ formAll.car_type_enum_car_type_enum_id_1 }}
</base-cell-item>
<base-cell-item
label="头像"
>
{{ formAll.avatar_resource_key }}
</base-cell-item>
<base-cell-item
label="姓名"
>
{{ formAll.real_name }}
</base-cell-item>
<base-cell-item
label="认证状态"
>
{{ formAll.cert_status_enum_cert_status_enum_id_1 }}
</base-cell-item>
<base-cell-item
label="累计活跃天数"
>
{{ formAll.active_days }}
</base-cell-item>
<base-cell-item
label="连续活跃天数"
>
{{ formAll.continue_active_days }}
</base-cell-item>
<base-cell-item
label="密码"
>
{{ formAll.password }}
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
              table_name: 'user_certification',
              param: form.value
          });

          proxy.$modal.msgSuccess(res.message);

          if(res.code == 0){
              refresh();
          }
      }
      else{
          let res = await proxy.$api.user_certification['update'](form.value);
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
            table_name: 'user_certification',
            param: {
                user_certification_id: id
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
        let res = await proxy.$api.user_certification['get'](id);
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
            table_name: 'user_certification',
            param: {
                user_certification_id: id
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
        let res = await proxy.$api.user_certification['get'](id);
        if(res.code == 0){
            formAll.value = Object.assign({}, res.data);
        }
        else{
            proxy.$modal.msgError(res.message);
        }
    }
}

function refresh(){
    if(routerQuery.user_certification_id){
        getDetail(routerQuery.user_certification_id);
    }
    if(detail.value == 'all'){
        if(routerQuery.user_certification_id){
            getDetailAll(routerQuery.user_certification_id);
        }
    }
}

refresh();

</script>

<style lang="scss" scoped>
</style>