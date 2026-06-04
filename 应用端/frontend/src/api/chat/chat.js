import service from "@/utils/request";

class ChatBuilder {
    constructor() {
        this.__param = {}
    }

    conversation_id(conversation_id) {
        this.__param.conversationId = conversation_id;
        return this;
    }

    text(text) {
        this.__param.query = text;
        return this;
    }

    __execute() {
        return service({
            url: "/chat",
            method: 'post',
            data: this.__param
        })
    }

    then(resolve, reject) {
        return this.__execute().then(resolve, reject);
    }

}

export default {
    chat() {
        return new ChatBuilder();
    }
}