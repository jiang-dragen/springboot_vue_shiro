import axios from "axios";
import ElementUI from 'element-ui';
import router from './router';

axios.defaults.baseURL = "http://localhost:8888"

//前置拦截
axios.interceptors.request.use(config =>{
    return config;
})

//后置拦截
axios.interceptors.response.use(response =>{
    let res = response.data;
    if (res.code == 200){
        return response;
    }else if (res.code == 401){
        ElementUI.Message.error(res.message , {duration:3*1000})
        router.push("/login");
        return Promise.reject(res.message) //拒绝后续操作
    }else {
        ElementUI.Message.error(res.message , {duration:3*1000})
        return Promise.reject(res.message)
    }
})