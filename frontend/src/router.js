import { createMemoryHistory, createRouter } from 'vue-router'

import PostsView from "./view/PostsView.vue";
import LoginView from "./view/LoginView.vue"
import RegisterView from "./view/RegisterView.vue"

const routes = [
    { path: '/', component: PostsView },
    { path: '/login', component: LoginView },
    { path: '/register', component: RegisterView },
]

const router = createRouter({
    history: createMemoryHistory(),
    routes,
})

export default router