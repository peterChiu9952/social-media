import { createMemoryHistory, createRouter } from "vue-router"
import { store } from "./store/index"

import PostsView from "./view/PostsView.vue"
import LoginView from "./view/LoginView.vue"
import RegisterView from "./view/RegisterView.vue"

const routes = [
    { path: "/", name: "home", redirect: "/posts" },
    { path: "/posts", name: "posts", component: PostsView },
    { path: "/login", name: "login", component: LoginView },
    {
        path: "/logout",
        name: "logout",
        beforeEnter: (to, from) => {
            store.dispatch("clearAuth");
            location.reload();
        }
    },
    { path: "/register", name: "register", component: RegisterView },
]

const router = createRouter({
    history: createMemoryHistory(),
    routes,
})

export default router