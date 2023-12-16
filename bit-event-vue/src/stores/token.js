// 保存用户信息，以给请求头添加 Authorization 属性
import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useTokenStore = defineStore('token', ()=> {
    const token = ref('')

    const setToken = (newToken) => {
        token.value = newToken
    }

    const removeToken = ()=> {
        token.value = ''
    }

    return {
        token, setToken, removeToken
    }
},{
    persist: true
});
