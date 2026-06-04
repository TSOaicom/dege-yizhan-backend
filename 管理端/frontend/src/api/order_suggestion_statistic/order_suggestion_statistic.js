// import request from '@/utils/request';

import service from "@/utils/request";
const BASE_API = "/orderSuggestionStatistics";

export default {
order_suggestion_statistic_b2e54e19_count(data){
  return service({
    url: BASE_API + "/order_suggestion_statistic_b2e54e19_count",
    method: "post",
    data:data
  });
},
order_suggestion_statistic_025d1cf0_count(data){
  return service({
    url: BASE_API + "/order_suggestion_statistic_025d1cf0_count",
    method: "post",
    data:data
  });
},
order_suggestion_statistic_b0be95c4_count(data){
  return service({
    url: BASE_API + "/order_suggestion_statistic_b0be95c4_count",
    method: "post",
    data:data
  });
},
order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_fa3c8c86_count(data){
  return service({
    url: BASE_API + "/order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_fa3c8c86_count",
    method: "post",
    data:data
  });
},
order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_f66eba9a_count(data){
  return service({
    url: BASE_API + "/order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_f66eba9a_count",
    method: "post",
    data:data
  });
},
order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_e974660b_count(data){
  return service({
    url: BASE_API + "/order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_e974660b_count",
    method: "post",
    data:data
  });
},
};
