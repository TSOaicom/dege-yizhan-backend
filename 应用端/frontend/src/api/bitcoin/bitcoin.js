import service from "@/utils/request";

const BASE_API = "/api";

class BiteBuilder {
    constructor() {
        this.__param = {};
    }

    vsCurrency(vsCurrency) {
        this.__param.vsCurrency = vsCurrency
        return this;
    }

    days(days) {
        this.__param.days = days
        return this;
    }

    interval(interval) {
        this.__param.interval = interval
        return this;
    }

    order(order) {
        this.__param.order = order
        return this;
    }

    perPage(perPage) {
        this.__param.perPage = perPage
        return this;
    }

    page(page) {
        this.__param.page = page
        return this;
    }

    sparkline(sparkline) {
        this.__param.sparkline = sparkline
        return this;
    }

    __execute() {

    }

    then(resolve, reject) {
        return this.__execute().then(resolve, reject);
    }
}

class TrendBuilder extends BiteBuilder {
    __execute() {
        const {vsCurrency, days, interval} = this.__param;
        const url = `${BASE_API}/historytrend`;
        return service({
            url: url,
            method: 'post',
            data: {
                vsCurrency,
                days,
                interval
            }
        })
    }
}

class MarketDataBuilder extends BiteBuilder {
    __execute() {
        const {vsCurrency, order, perPage, page, sparkline} = this.__param;
        const url = `${BASE_API}/marketdata`;
        return service({
            url: url,
            method: 'post',
            data: {
                vsCurrency,
                order,
                perPage,
                page,
                sparkline
            }
        })
    }
}



export default {
    historytrend: () => {
        return new TrendBuilder()
    },
    marketdata: () => {
        return new MarketDataBuilder()
    }
}

