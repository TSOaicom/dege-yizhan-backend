import {createRouter, createWebHistory} from 'vue-router'
import i18n from '@/i18n'

const {t} = i18n.global

export const constantRouters = [
    {
        path: '/login',
        meta: {
            isParentView: true,
            title: t('menu.login'),
        },
        component: () => import('@/views/login/index.vue'),
    },
    {
        path: '/login_manger',
        meta: {
            title: t('menu.auth'),
            shownot: false
        },
        component: () => import('@/views/login_manger/index.vue'),
    }, {
        path: '/api_setting_manger',
        meta: {
            title: t('menu.api'),
            shownot: false
        },
        component: () => import('@/views/dynamic_api_setting/index.vue'),
    }, {
        path: '/system_setting_manger',
        meta: {
            title: t('menu.api'),
            shownot: false
        },
        component: () => import('@/views/kf_system_setting/index.vue'),
    }
    // ,
    // {
    //     path: "/:pathMatch(.*)*",
    //     component: () => import('@/views/error/404.vue'),
    //     hidden: true
    // }
]

const router = createRouter({
    history: createWebHistory(import.meta.env.VITE_BASE),
    routes: constantRouters,
});

export default router;