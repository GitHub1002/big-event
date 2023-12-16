import { createRouter, createWebHistory } from "vue-router";

// 导入组件
import LoginVue from '@/views/login.vue'
import LayoutVue from '@/views/layout.vue'
import ArticleCategoryVue from '@/views/article/ArticleCategory.vue'
import ArticleManageVue from '@/views/article/ArticleManage.vue'
import UserAvatarVue from'@/views/user/UserAvatar.vue'
import UserInfoVue from '@/views/user/UserInfo.vue'
import UserResetPasswordVue from '@/views/user/UserResetPassword.vue'


// 定义路由关系
const routes = [
    {
        path: '/login',
        name: 'Login',
        component: LoginVue
    },
    {
        path: '/',
        name: 'Layout',
        component: LayoutVue,
        redirect: '/article/category',
        children:[
            { path: '/article/category', component: ArticleCategoryVue},
            { path: '/article/manage', component: ArticleManageVue},
            { path: '/user/userAvatar', component: UserAvatarVue},
            { path: '/user/info', component: UserInfoVue},
            { path: '/user/userResetPassword', component: UserResetPasswordVue},
        ]
    }
]

// 创建路由
const router = createRouter({
    history: createWebHistory(),
    routes: routes
})

// 导出路由
export default router