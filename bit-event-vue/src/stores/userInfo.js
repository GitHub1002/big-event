// 获取用户信息，以方便在导航栏使用
import { defineStore } from 'pinia'
import { ref } from 'vue'

const useUserInfoStore = defineStore('userInfo', ()=>{
    const info = ref({})

    const setInfo = (newInfo) => {
        info.value = newInfo
    }

    const removeInfo = () => {
        info.value = {}
    }

    return {info, setInfo, removeInfo}
},{
    persist:true
})

export default useUserInfoStore;