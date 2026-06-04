// import request from '@/utils/request';

import service from "@/utils/request";
const BASE_API = "/inviteRecordStatistics";

export default {
invite_record_statistic_31bed8f4_count(data){
  return service({
    url: BASE_API + "/invite_record_statistic_31bed8f4_count",
    method: "post",
    data:data
  });
},
invite_record_statistic_a629efa5_count(data){
  return service({
    url: BASE_API + "/invite_record_statistic_a629efa5_count",
    method: "post",
    data:data
  });
},
invite_record_statistic_72eba71b_count(data){
  return service({
    url: BASE_API + "/invite_record_statistic_72eba71b_count",
    method: "post",
    data:data
  });
},
invite_record_invite_time_datetime_statistic_9e15a676_count(data){
  return service({
    url: BASE_API + "/invite_record_invite_time_datetime_statistic_9e15a676_count",
    method: "post",
    data:data
  });
},
invite_record_invite_time_datetime_statistic_31df2134_count(data){
  return service({
    url: BASE_API + "/invite_record_invite_time_datetime_statistic_31df2134_count",
    method: "post",
    data:data
  });
},
invite_record_invite_time_datetime_statistic_8a084807_count(data){
  return service({
    url: BASE_API + "/invite_record_invite_time_datetime_statistic_8a084807_count",
    method: "post",
    data:data
  });
},
};
