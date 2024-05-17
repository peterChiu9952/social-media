// import store from '@/store';
import { store } from "../store/index"
import axios from 'axios'
// import router from '../router'
// import { tip, toLogin } from './utils'

const host = "http://localhost:8080"; //local server


const errorHandle = (status, msg) => {
    switch (status) {
        case 400:
            console.log(msg);
            break;

        case 401:
            if (router.currentRoute == 'Login') {
                console.log("未登入");
            } else {
                store.dispatch('setAuth', {
                    token: '',
                    isLogin: false,
                    userId: '',
                })

                console.log('登入過期，請重新登入');
                setTimeout(() => {
                    toLogin();
                }, 1000);
            }
            break;

        case 403:
            console.log('權限不足');
            break;

        case 404:
            console.log(msg);
            break;

        default:
            console.error(msg);
    }
}

const instance = axios.create({
    baseUrl: '/api/v1/'
})

instance.interceptors.request.use((config) => {
    const token = store.state.token;
    token && (config.headers.Authorization = 'Bearer ' + token);
    return config;
}, (error) => {
    return Promise.reject(error);
});

instance.interceptors.response.use((response) => {
    return response;
}, (error) => {
    const { response } = error;

    if (response) {
        errorHandle(response.status, response.data.error);
        return Promise.reject(error);
    } else {
        if (!window.navigator.onLine) {
            console.log('無法連線');
        } else {
            return Promise.reject(error);
        }
    }
});

export default function (method, url, data = null) {
    url = host + url;

    method = method.toLowerCase();
    if (method == 'post') {
        return instance.post(url, data);
    } else if (method == 'get') {
        return instance.get(url, { params: data });
    } else if (method == 'delete') {
        return instance.delete(url, { params: data });
    } else if (method == 'put') {
        return instance.put(url, data);
    } else {
        console.error('unKnow method' + method);
        return false;
    }
}