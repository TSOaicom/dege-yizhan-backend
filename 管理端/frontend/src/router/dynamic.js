import i18n from '@/i18n'
const { t } = i18n.global 
export const dynamicRoutes = [{
        path: '/',
        meta: {
            title: t('page.home'),
            isParentView: false
        },
        component: ()=> import('@/views/home/index.vue'),
    }, {
        path: '/user_info',
        meta: {
            title: "用户信息",
            shownot: false
        },
        component: ()=> import('@/views/user_info/index.vue'),
    }, {
        path: '/car_type_enum',
        meta: {
            title: "车辆类型",
            shownot: false
        },
        component: ()=> import('@/views/car_type_enum/index.vue'),
    }, {
        path: '/cert_status_enum',
        meta: {
            title: "认证状态",
            shownot: false
        },
        component: ()=> import('@/views/cert_status_enum/index.vue'),
    }, {
        path: '/user_certification',
        meta: {
            title: "资质认证",
            shownot: false
        },
        component: ()=> import('@/views/user_certification/index.vue'),
    }, {
        path: '/order_suggestion',
        meta: {
            title: "订单建议",
            shownot: false
        },
        component: ()=> import('@/views/order_suggestion/index.vue'),
    }, {
        path: '/heat_level_enum',
        meta: {
            title: "热力等级",
            shownot: false
        },
        component: ()=> import('@/views/heat_level_enum/index.vue'),
    }, {
        path: '/toilet_parking',
        meta: {
            title: "厕所停车",
            shownot: false
        },
        component: ()=> import('@/views/toilet_parking/index.vue'),
    }, {
        path: '/message_center',
        meta: {
            title: "消息中心",
            shownot: false
        },
        component: ()=> import('@/views/message_center/index.vue'),
    }, {
        path: '/message_type_enum',
        meta: {
            title: "消息类型",
            shownot: false
        },
        component: ()=> import('@/views/message_type_enum/index.vue'),
    }, {
        path: '/wallet',
        meta: {
            title: "钱包",
            shownot: false
        },
        component: ()=> import('@/views/wallet/index.vue'),
    }, {
        path: '/member_type_enum',
        meta: {
            title: "会员类型",
            shownot: false
        },
        component: ()=> import('@/views/member_type_enum/index.vue'),
    }, {
        path: '/invite_record',
        meta: {
            title: "邀请记录",
            shownot: false
        },
        component: ()=> import('@/views/invite_record/index.vue'),
    }, {
        path: '/feedback',
        meta: {
            title: "反馈",
            shownot: false
        },
        component: ()=> import('@/views/feedback/index.vue'),
    }, {
        path: '/user_info/detail',
        meta: {
            title: "用户信息",
            shownot: true
        },
        component: ()=> import('@/views/user_info/detail/index.vue'),
    }, {
        path: '/car_type_enum/detail',
        meta: {
            title: "车辆类型",
            shownot: true
        },
        component: ()=> import('@/views/car_type_enum/detail/index.vue'),
    }, {
        path: '/cert_status_enum/detail',
        meta: {
            title: "认证状态",
            shownot: true
        },
        component: ()=> import('@/views/cert_status_enum/detail/index.vue'),
    }, {
        path: '/user_certification/detail',
        meta: {
            title: "资质认证",
            shownot: true
        },
        component: ()=> import('@/views/user_certification/detail/index.vue'),
    }, {
        path: '/order_suggestion/detail',
        meta: {
            title: "订单建议",
            shownot: true
        },
        component: ()=> import('@/views/order_suggestion/detail/index.vue'),
    }, {
        path: '/heat_level_enum/detail',
        meta: {
            title: "热力等级",
            shownot: true
        },
        component: ()=> import('@/views/heat_level_enum/detail/index.vue'),
    }, {
        path: '/toilet_parking/detail',
        meta: {
            title: "厕所停车",
            shownot: true
        },
        component: ()=> import('@/views/toilet_parking/detail/index.vue'),
    }, {
        path: '/message_center/detail',
        meta: {
            title: "消息中心",
            shownot: true
        },
        component: ()=> import('@/views/message_center/detail/index.vue'),
    }, {
        path: '/message_type_enum/detail',
        meta: {
            title: "消息类型",
            shownot: true
        },
        component: ()=> import('@/views/message_type_enum/detail/index.vue'),
    }, {
        path: '/wallet/detail',
        meta: {
            title: "钱包",
            shownot: true
        },
        component: ()=> import('@/views/wallet/detail/index.vue'),
    }, {
        path: '/member_type_enum/detail',
        meta: {
            title: "会员类型",
            shownot: true
        },
        component: ()=> import('@/views/member_type_enum/detail/index.vue'),
    }, {
        path: '/invite_record/detail',
        meta: {
            title: "邀请记录",
            shownot: true
        },
        component: ()=> import('@/views/invite_record/detail/index.vue'),
    }, {
        path: '/feedback/detail',
        meta: {
            title: "反馈",
            shownot: true
        },
        component: ()=> import('@/views/feedback/detail/index.vue'),
    }]