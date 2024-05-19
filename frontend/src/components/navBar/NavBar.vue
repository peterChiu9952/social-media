<script setup>
import { useRouter } from "vue-router";
import { useStore } from "vuex";

const router = useRouter();
const store = useStore();

const goToPosts = () => {
    router.push("/");
};
const goToLogin = () => {
    router.push("/login");
};
const goToRegister = () => {
    router.push("/register");
};
const logout = () => {
    router.push("/logout");
};
</script>

<template>
    <v-app-bar :elevation="2">
        <v-app-bar-title>Social Media</v-app-bar-title>
        <template v-slot:append>
            <v-btn @click="goToPosts">Posts</v-btn>
            <v-btn v-if="!store.state.isLogin" @click="goToLogin">Login</v-btn>
            <v-btn v-if="!store.state.isLogin" @click="goToRegister"
                >Register</v-btn
            >
            <v-btn v-if="store.state.isLogin" @click="logout">Logout</v-btn>
        </template>
    </v-app-bar>
</template>

<style scoped></style>
