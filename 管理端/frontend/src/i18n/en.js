export default {
    login: {
        login_title: "Login",
        enter_user_name: "enter user name",
        enter_pass_wd: "enter password",
        login_btn: "Sign in",
        login_default_msg: "Default credentials: admin / 123456",
        logout_btn: "Log out"
    },
    document: {
        help_btn: "Help Docs",
    },
    menu: {
        login: "Login",
        auth: "Authentication",
        api: "API Configuration",
        system: "System Configuration"
    },
    component: {
        upload: {
            image: {
                start: "Please upload",
                size: "max size ",
                format: " format",
                end: " files",
                preview: "Preview"
            },
            file: {
                choose: "Select File",
                start: "Please upload",
                size: "max size",
                format: "format",
                end: " files ",
                del: "Remove"
            }
        }
    },
    page: {
        home: "Home",
        detail: "Detail",
        view: "View",
        home_help: {
            title: "Getting Started",
            description: "Welcome! This video will guide you through the Admin panel's core functionality, enabling you to become proficient quickly."
        },
        op: {
            title: "Operation",
            add: "Add",
            edit: "Edit",
            detail: "Detail",
            cancel: "Cancel",
            submit: "Submit",
            delete: "Del",
            del_msg: "Are you sure you want to delete this record?",
            passwd: "Password Change",
            choose: "Choose",
        },
        search_op: {
            add: "Add",
            batch_del: "Batch Delete",
            export_excel: "Export to Excel",
            import_excel: "Import Excel/PDF",
            download_excel_template: "Download Excel Template",
            filters: "Filters",
            search: "Search"
        },
        select_op: {
            yes: "Yes",
            no: "No"
        },
        rule: {
            required: "{0} is required."
        },
        upload_msg: {
            upload_info_left: "Drag file here or ",
            upload_info_right: "click to upload",
            upload_tip: "Tip: Only 'xls', 'xlsx', and 'pdf' file formats are allowed!",
            upload_notice: "Note: Processing PDF files may take longer!"
        },
        api: {
            title: "API Configuration",
            description: "Configure and manage your own API settings here.",
            columns: {
                id: "Unique Key",
                keyName: "API Key Name",
                description: "API Description",
                protocol: "Protocol",
                method: "Request Method",
                url: "Request URL",
                token: "API Token",
                header: "Request Headers",
                bodyType: "Request Body Type",
                bodyTemplate: "Request Body Template",
                op: "Operation"
            }
        },
        auth: {
            title: "Authentication Management",
            description: "The default password for new users is: 123456",
            change_new_passwd_title: "New Password",
            change_new_passwd_placeholder: "Please enter your new password",
            user_name_and_phone: "Mobile number or username cannot be empty.",
            password_length_rule_text: "Password length should be 3-18 characters",
            user_name_and_phone_search_placeholder: "Mobile number or username",
            columns: {
                login_id: "Primary Key",
                wx_open_id: "WeChat OpenID",
                phone_number: "Phone/Username",
                user_name: "Nickname",
                relevance_id: "Bound Business ID",
                relevance_table: "Bound Service",
                op: "Operation"
            }
        },
        system: {
            title: "System Configuration",
            description: "System Configuration",
            save: "Save Configuration",
            save_msg: "Success",
            mock: {
                title: "Mock Configuration",
                show_default_account: "Show the default account on the login page",
                wechat: "Enable WeChat login mock",
                weapp: "Enable WeChat Mini Program login mock",
                email: "Enable email login mock",
                phone: "Enable phone login mock",
                google: "Enable Google login mock",
                wx_pay_mock_enable: "Enable mock payment (when no payment account applied, true/false)",
                wx_pay_is_test: "Enable WeChat Pay test mode (amount will be 0.01 when enabled, disable for production)"
            },
            wechat: {
                title: "WeChat OAuth Configuration",
                app_id: "WeChat Official Account APPID",
                app_secret: "WeChat Official Account SECRET",
                pay: {
                    title: "WeChat Pay",
                    wechat_app_id: "WeChat Mini Program AppID",
                    wechat_app_secret: "WeChat Mini Program Secret",
                    wx_pay_pay_back_url: "WeChat Pay callback URL",
                    wx_pay_mch_serial_no: "WeChat Pay certificate serial number",
                    wx_pay_app_id: "AppID used for WeChat Pay",
                    wx_pay_mch_id: "WeChat Pay merchant ID",
                    wx_pay_api_v3_key: "WeChat Pay API v3 key",
                    wx_pay_wechat_enable: "Whether to enable WeChat Pay",
                    wx_pay_mp_app_id: "WeChat Official Account AppID",
                    wx_pay_mp_app_secret: "WeChat Official Account Secret",
                    wechat_mp_redirect_uri: "Backend redirect URL",
                    wechat_mp_frontend_redirect_uri: "Frontend redirect URL",
                    wx_pay_private_key: "WeChat Pay private key content",
                }
            },
            email: {
                title: "Email Login Configuration",
                host: "SMTP Server Address",
                port: "Port Number",
                user: "Sender Email Address",
                passwd: "Authorization Code / Password",
                code_template: "Verification Code Template",
                code_template_msg: "Verification code template; you can insert variables in the appropriate positions"
            },
            google: {
                title: "Google OAuth Login Configuration",
                client_id: "Client ID",
                client_secret: "Client Secret",
                callback_uri: "Callback URI",
                redirect_uri: "Redirect URI"
            },
            stripe: {
                title: "Stripe Payment Configuration",
                key: "Stripe Key",
                webhook_secret: "Webhook Secret"
            }
        }
    }
}