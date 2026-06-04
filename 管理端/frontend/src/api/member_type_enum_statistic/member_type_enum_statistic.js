// import request from '@/utils/request';

import service from "@/utils/request";
const BASE_API = "/memberTypeEnumStatistics";

export default {
member_type_enum_member_type_enum_id_status_statistic_0d9c3cae_count(data){
  return service({
    url: BASE_API + "/member_type_enum_member_type_enum_id_status_statistic_0d9c3cae_count",
    method: "post",
    data:data
  });
},
member_type_enum_member_type_enum_id_status_statistic_691c8685_count(data){
  return service({
    url: BASE_API + "/member_type_enum_member_type_enum_id_status_statistic_691c8685_count",
    method: "post",
    data:data
  });
},
member_type_enum_member_type_enum_id_status_statistic_4f5a8e3f_count(data){
  return service({
    url: BASE_API + "/member_type_enum_member_type_enum_id_status_statistic_4f5a8e3f_count",
    method: "post",
    data:data
  });
},
};
