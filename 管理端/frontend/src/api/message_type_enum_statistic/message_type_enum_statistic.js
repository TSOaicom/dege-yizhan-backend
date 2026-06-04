// import request from '@/utils/request';

import service from "@/utils/request";
const BASE_API = "/messageTypeEnumStatistics";

export default {
message_type_enum_message_type_enum_id_status_statistic_48318e25_count(data){
  return service({
    url: BASE_API + "/message_type_enum_message_type_enum_id_status_statistic_48318e25_count",
    method: "post",
    data:data
  });
},
message_type_enum_message_type_enum_id_status_statistic_0a19315b_count(data){
  return service({
    url: BASE_API + "/message_type_enum_message_type_enum_id_status_statistic_0a19315b_count",
    method: "post",
    data:data
  });
},
message_type_enum_message_type_enum_id_status_statistic_9669aff3_count(data){
  return service({
    url: BASE_API + "/message_type_enum_message_type_enum_id_status_statistic_9669aff3_count",
    method: "post",
    data:data
  });
},
};
