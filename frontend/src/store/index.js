import { createStore } from 'vuex'

export const store = createStore({
    state() {
        return {
            token: "",
            isLogin: false,
            userId: "",
        }
    },
    mutations: {
        SET_AUTH(state, options) {
            state.token = options.token;
            state.isLogin = options.isLogin;
            state.userId = options.userId;
        }
    },
    actions: {
        setAuth(context, options) {
            context.commit('SET_AUTH', {
                token: options.token,
                isLogin: options.isLogin,
                userId: options.userId
            });
        }
    }
})