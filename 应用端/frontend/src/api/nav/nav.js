class NavBuilder {
    constructor() {
        this.__param = {};
        this.__plantform = "";
    }


    platform(platform) {
        this.__plantform = platform
        return this;
    }

    lng(lng) {
        this.__param.longitude = lng
        return this;
    }

    lat(lat) {
        this.__param.latitude = lat
        return this;
    }

    address(address) {
        this.__param.address = address
        return this;
    }

    __execute(){

    }

    then(resolve, reject) {
        return this.__execute().then(resolve, reject);
    }

    isWeChat() {
        return /MicroMessenger/i.test(window.navigator.userAgent);
    }
}

class BaiduNav extends NavBuilder {
    __execute() {
        console.log("开始导航")
        const {latitude, longitude, address} = this.__param;
        return new Promise((resolve, reject) => {
            // #ifdef H5 ||  APP-PLUS || APP-HARMONY
            if (this.isWeChat()) {
                window.location.href = `https://map.baidu.com/poi/${encodeURIComponent(address)}/@${longitude},${latitude},19z`;
                return
            }
            uni.getSystemInfo({
                success: (res) => {
                    if (res.platform === "android" || (res.system && res.system.toLowerCase().includes("harmony"))) {
                        window.location.href = "baidumap://map/marker?location=" + latitude + "," + longitude + "&title=" + address + "&content=" + address + "&traffic=on";
                    } else {
                        window.location.href = "https://api.map.baidu.com/marker?location=" + latitude + "," + longitude + "&title=" + address + "&content=地点&output=html&src=webapp.baidu.openAPIdemo";
                    }
                    resolve()
                },
                fail: (err) => {
                    reject(err);
                }
            })
            // #endif
            // #ifdef MP
            uni.openLocation({
                latitude: latitude,
                longitude: longitude,
                name: address,
                address: address,
                success: () => {
                   resolve()
                },
                fail: (err) => reject(err)
            })
            // #endif
        })
    }

}

class AmapNav extends NavBuilder {
    __execute() {
        const {latitude, longitude, address} = this.__param;
        return new Promise((resolve, reject)=>{
            // #ifdef H5 ||  APP-PLUS || APP-HARMONY
            if (super.isWeChat()) {
                window.location.href = `https://uri.amap.com/marker?position=${longitude},${latitude}&name=${encodeURIComponent(address)}`;
                return
            }
            uni.getSystemInfo({
                success: (res) => {
                    if (res.platform === "android" || (res.system && res.system.toLowerCase().includes("harmony"))) {
                        window.location.href = "androidamap://viewMap?sourceApplication=appname&poiname=" + address + "&lat=" + latitude + "&lon=" + longitude + "&dev=0";
                    } else if (res.platform === "ios") {
                        window.location.href = "iosamap://viewMap?sourceApplication=appname&poiname=" + address + "&lat=" + latitude + "&lon=" + longitude + "&dev=0";
                    } else {
                        window.location.href = "https://uri.amap.com/marker?position=" + longitude + "," + latitude + "&name=" + address;
                    }
                    resolve()
                },
                fail: (err) => {
                    reject(err);
                }
            });
            // #endif
            // #ifdef MP
            uni.openLocation({
                latitude: latitude,
                longitude: longitude,
                name: address,
                address: address,
                success: () => {
                    console.log("导航已打开")
                    resolve()
                },fail: (err) => reject(err)
            });
            // #endif
        })
    }
}


class QqmapNav extends NavBuilder {
    __execute() {
        const {latitude, longitude, address} = this.__param;
       return new Promise((resolve, reject)=>{
           // #ifdef H5 ||  APP-PLUS || APP-HARMONY
           if (this.isWeChat()) {
               window.location.href = `https://apis.map.qq.com/uri/v1/marker?marker=coord:${latitude},${longitude};title:${encodeURIComponent(address)};addr:${encodeURIComponent(address)}`;
               return
           }
           uni.getSystemInfo({
               success: (res) => {
                   if (res.platform === "android" || (res.system && res.system.toLowerCase().includes("harmony"))) {
                       window.location.href = "qqmap://map/marker?marker=coord:" + latitude + "," + longitude + ";title:" + address + ";addr:" + address + "&referer=key";
                   } else {
                       window.location.href = `https://apis.map.qq.com/uri/v1/marker?marker=coord:(${latitude},${longitude})&title:${address}&addr:${address}`
                   }
                   resolve()
               },
               fail: (err) => {
                   reject(err);
               }
           });
           // #endif
           // #ifdef MP
           uni.openLocation({
               latitude: latitude,
               longitude: longitude,
               name: address,
               address: address,
               success: () => {
                   console.log("导航已打开")
                   resolve()
               },fail: (err) => reject(err)
           });
           // #endif
       })
    }
}


export default {
    baidu: ()=>{
        return new BaiduNav()
    },
    amap: ()=>{
        return new AmapNav()
    },
    qqmap: ()=>{
        return new QqmapNav()
    },
}

