// import request from '@/utils/request';

import service from "@/utils/request";
const BASE_API = "/feedbackStatistics";

export default {
feedback_statistic_88d8c4e8_count(data){
  return service({
    url: BASE_API + "/feedback_statistic_88d8c4e8_count",
    method: "post",
    data:data
  });
},
feedback_statistic_567cc485_count(data){
  return service({
    url: BASE_API + "/feedback_statistic_567cc485_count",
    method: "post",
    data:data
  });
},
feedback_statistic_97294634_count(data){
  return service({
    url: BASE_API + "/feedback_statistic_97294634_count",
    method: "post",
    data:data
  });
},
feedback_create_time_datetime_statistic_a0fae0b9_count(data){
  return service({
    url: BASE_API + "/feedback_create_time_datetime_statistic_a0fae0b9_count",
    method: "post",
    data:data
  });
},
feedback_create_time_datetime_statistic_747f6f44_count(data){
  return service({
    url: BASE_API + "/feedback_create_time_datetime_statistic_747f6f44_count",
    method: "post",
    data:data
  });
},
feedback_create_time_datetime_statistic_f2b17184_count(data){
  return service({
    url: BASE_API + "/feedback_create_time_datetime_statistic_f2b17184_count",
    method: "post",
    data:data
  });
},
};
