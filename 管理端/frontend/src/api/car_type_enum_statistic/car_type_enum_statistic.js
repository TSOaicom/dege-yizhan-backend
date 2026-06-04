// import request from '@/utils/request';

import service from "@/utils/request";
const BASE_API = "/carTypeEnumStatistics";

export default {
car_type_enum_car_type_enum_id_status_statistic_cff7ec90_count(data){
  return service({
    url: BASE_API + "/car_type_enum_car_type_enum_id_status_statistic_cff7ec90_count",
    method: "post",
    data:data
  });
},
car_type_enum_car_type_enum_id_status_statistic_54e8df16_count(data){
  return service({
    url: BASE_API + "/car_type_enum_car_type_enum_id_status_statistic_54e8df16_count",
    method: "post",
    data:data
  });
},
car_type_enum_car_type_enum_id_status_statistic_2e7d2440_count(data){
  return service({
    url: BASE_API + "/car_type_enum_car_type_enum_id_status_statistic_2e7d2440_count",
    method: "post",
    data:data
  });
},
};
