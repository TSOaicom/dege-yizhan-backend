// import request from '@/utils/request';

import service from "@/utils/request";
const BASE_API = "/heatLevelEnumStatistics";

export default {
heat_level_enum_heat_level_enum_id_status_statistic_9911c871_count(data){
  return service({
    url: BASE_API + "/heat_level_enum_heat_level_enum_id_status_statistic_9911c871_count",
    method: "post",
    data:data
  });
},
heat_level_enum_heat_level_enum_id_status_statistic_05b8f60f_count(data){
  return service({
    url: BASE_API + "/heat_level_enum_heat_level_enum_id_status_statistic_05b8f60f_count",
    method: "post",
    data:data
  });
},
heat_level_enum_heat_level_enum_id_status_statistic_7c58e626_count(data){
  return service({
    url: BASE_API + "/heat_level_enum_heat_level_enum_id_status_statistic_7c58e626_count",
    method: "post",
    data:data
  });
},
};
