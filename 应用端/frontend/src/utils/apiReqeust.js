


import client from "@/config/aipexbase";

const apiReqeust=(params={})=>{
    const { url, method,data} = params;
    let finalUrl = url;
    if (url.startsWith("/")){
        console.log(111)
        finalUrl = url.substring(1)
    }
    return  client.api
        .call(finalUrl)
        // .method(method)
        .params({...data})

        .headers({
            APP_ID: import.meta.env.VITE_APP_ID,
            APP_TYPE: import.meta.env.VITE_APP_TYPE
        });
}

export default  apiReqeust;