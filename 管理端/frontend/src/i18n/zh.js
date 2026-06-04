export default {
    login: {
        login_title: "登录",
        enter_user_name: "请输入用户名",
        enter_pass_wd: "请输入密码",
        login_btn: "登录",
        login_default_msg: "默认账户密码：admin / 123456",
        logout_btn: "退出登录"
    },
    document: {
        help_btn: "帮助文档",
    },
    menu: {
        login: "登录",
        auth: "登录信息",
        api: "API配置",
        system: "系统配置"
    },
    component: {
        upload: {
            image: {
                start: "请上传",
                size: "大小不超过",
                format: "格式为",
                end: "的文件",
                preview: "预览"
            },
            file: {
                choose: "选取文件",
                start: "请上传",
                size: "大小不超过",
                format: "格式为",
                end: "的文件",
                del: "删除"
            }
        }
    },
    page: {
        home: "首页",
        detail: "详情",
        view: "查看",
        home_help: {
            title: "快速入门",
            description: "欢迎！本视频将带您了解管理后台(Admin)的核心功能，帮助您快速上手。",
        },
        op: {
            title: "操作",
            add: "添加",
            edit: "编辑",
            detail: "详情",
            cancel: "取消",
            submit: "确定",
            delete: "删除",
            del_msg: "您确定删除这条数据吗？",
            passwd: "修改密码",
            choose: "选择",
        },
        search_op: {
            add: "添加",
            batch_del: "批量删除",
            export_excel: "导出Excel",
            import_excel: "导入Excel/PDF",
            download_excel_template: "下载Excel模版",
            filters: "高级搜索",
            search: "搜索"
        },
        select_op: {
            yes: "是",
            no: "否"
        },
        rule: {
            required: "{0} 不能为空"
        },
        upload_msg: {
            upload_info_left: "将文件拖到此处，或",
            upload_info_right: "点击上传",
            upload_tip: "提示：仅允许导入“xls”、“xlsx”、“pdf”格式文件！",
            upload_notice: "注意：PDF文件处理时间会比较长！！！"
        },
        api: {
            title: "API配置",
            description: "API配置，可以配置自己的API",
            columns: {
                id: "主键",
                keyName: "API标识",
                description: "API描述",
                protocol: "协议",
                method: "API请求方法",
                url: "API请求地址",
                token: "token",
                header: "API请求头",
                bodyType: "API请求体类型",
                bodyTemplate: "API请求体模版",
                op: "操作"
            }
        },
        auth: {
            title: "登录管理",
            description: "新用户的默认密码为:123456",
            change_new_passwd_title: "新密码",
            change_new_passwd_placeholder: "请输入密码",
            user_name_and_phone: "手机号/用户名不能为空",
            password_length_rule_text: "密码长度3-18位",
            user_name_and_phone_search_placeholder: "手机号/用户名",
            columns: {
                login_id: "主键",
                wx_open_id: "微信绑定ID",
                phone_number: "手机号/用户名",
                user_name: "用户昵称",
                relevance_id: "绑定业务ID",
                relevance_table: "绑定业务",
                op: "操作"
            }
        },
        system: {
            title: "系统配置",
            description: "系统配置",
            save: "保存配置",
            save_msg: "保存成功",
            mock: {
                title: "Mock配置",
                show_default_account: "是否显示默认登录账号",
                wechat: "是否开启微信登录Mock",
                weapp: "是否开启微信小程序登录Mock",
                email: "是否开启邮箱登录Mock",
                phone: "是否开启手机登录Mock",
                google: "是否开启Google登录Mock",
                wx_pay_mock_enable: "是否开启mock支付（没有申请支付的情况下，true/false）",
                wx_pay_is_test: "微信支付是否开启测试（开启后支付金额统一为0.01，正式上线请关闭）"
            },
            wechat: {
                title: "微信授权登录配置",
                app_id: "微信服务号APPID",
                app_secret: "微信服务号SECRET",
                pay: {
                    title: "微信支付",
                    wechat_app_id: "微信小程序appid",
                    wechat_app_secret: "微信小程序secret",
                    wx_pay_pay_back_url: "微信支付回调url",
                    wx_pay_mch_serial_no: "微信支付证书序列号",
                    wx_pay_app_id: "微信支付对应的appid",
                    wx_pay_mch_id: "微信支付商户号",
                    wx_pay_api_v3_key: "微信支付apiv3密钥",
                    wx_pay_wechat_enable: "是否开启微信支付",
                    wx_pay_mp_app_id: "微信公众号的APPID",
                    wx_pay_mp_app_secret: "微信公众号的Secret",
                    wechat_mp_redirect_uri: "后端重定向的url",
                    wechat_mp_frontend_redirect_uri: "前端重定向的url",
                    wx_pay_private_key: "微信支付私钥内容",
                }
            },
            email: {
                title: "邮箱登录配置",
                host: "SMTP服务器地址",
                port: "端口号",
                user: "发件邮箱",
                passwd: "授权码/密码",
                code_template: "验证码模版",
                code_template_msg: "验证码模版,可以在合适的位置放入变量"
            },
            google: {
                title: "Google OAuth登录配置",
                client_id: "Client ID",
                client_secret: "Client Secret",
                callback_uri: "Callback URI",
                redirect_uri: "Redirect URI"
            },
            stripe: {
                title: "Stripe支付配置",
                key: "Stripe Key",
                webhook_secret: "Webhook Secret"
            },
            pay: {}
        }
    }
}