<template>
     
  <base-wrapper
>
<div class="flex-between-start" style="background-color: #5D5FEF; height: 140px;">
    <div class="flex-c-center-start h-full">
      <div class="m-x-20">
          <span style="font-weight: bold; font-size: 20px; color: #FFFFFF;">订单建议</span>
      </div>
      <div class="m-x-20 m-t-4">
        <span style="font-size: 14px; color: #FFFFFF;">主键、区域名称、预估订单量、距离(公里)、推荐指数、建议方向、热力等级。</span>
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
:to="{ path: '/order_suggestion' }"
>
订单建议
</el-breadcrumb-item>
<el-breadcrumb-item
>
订单建议 {{ $t('page.detail') }} 
</el-breadcrumb-item>
</el-breadcrumb>
<base-wrapper
class="p-10"
>
<el-tabs
class="demo-tabs"
>
<el-tab-pane
:label="'订单建议 ' + $t('page.detail') "
>
<el-form
:model='form'
ref='dataFormRef'
label-position='top'
:rules='{"area_name":[{"required": true,"message": $t("page.rule.required",["区域名称"]),"trigger": "blur"}],"order_count":[{"required": true,"message": $t("page.rule.required",["预估订单量"]),"trigger": "blur"}],"distance":[{"required": true,"message": $t("page.rule.required",["距离(公里)"]),"trigger": "blur"}],"recommend_index":[{"required": true,"message": $t("page.rule.required",["推荐指数"]),"trigger": "blur"}],"suggest_direction":[{"required": true,"message": $t("page.rule.required",["建议方向"]),"trigger": "blur"}],"heat_level_enum_heat_level_enum_id_1":[{"required": true,"message": $t("page.rule.required",["热力等级"]),"trigger": "blur"}]}'
label-width='150px'
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

    <el-form-item   label="热力等级" prop="heat_level_enum_heat_level_enum_id_1">
        <base-select clearable v-model="form.heat_level_enum_heat_level_enum_id_1" api="heat_level_enum.list" show_name="level_name"></base-select>
    </el-form-item>

</el-form>
<base-cell
header="所属热力等级"
label-width="180px"
class="m-b-10"
>
<base-cell-item
label="等级名称"
>
{{ formAll.level_name }}
</base-cell-item>
<base-cell-item
label="颜色"
>
{{ formAll.color }}
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
              table_name: 'order_suggestion',
              param: form.value
          });

          proxy.$modal.msgSuccess(res.message);

          if(res.code == 0){
              refresh();
          }
      }
      else{
          let res = await proxy.$api.order_suggestion['update'](form.value);
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
            table_name: 'order_suggestion',
            param: {
                order_suggestion_id: id
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
        let res = await proxy.$api.order_suggestion['get'](id);
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
            table_name: 'order_suggestion',
            param: {
                order_suggestion_id: id
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
        let res = await proxy.$api.order_suggestion['get'](id);
        if(res.code == 0){
            formAll.value = Object.assign({}, res.data);
        }
        else{
            proxy.$modal.msgError(res.message);
        }
    }
}

function refresh(){
    if(routerQuery.order_suggestion_id){
        getDetail(routerQuery.order_suggestion_id);
    }
    if(detail.value == 'all'){
        if(routerQuery.order_suggestion_id){
            getDetailAll(routerQuery.order_suggestion_id);
        }
    }
}

refresh();

</script>

<style lang="scss" scoped>
</style>