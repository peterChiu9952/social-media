<script setup>
import { ref } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { login } from "../api/auth";

const router = useRouter();

const username = ref("");
const password = ref("");
const form = ref();

const store = useStore();

const onLogin = async () => {
    const formValidate = await form.value.validate();
    if (formValidate.valid) {
        login(username.value, password.value).then((res) => {
            store.dispatch("setAuth", {
                token: res.data.token,
                isLogin: true,
                userId: res.data.userId,
            });
            router.push("/");
        });
    }
};
</script>

<template>
    <div style="width: 600px">
        <v-form @submit.prevent ref="form">
            <v-text-field
                v-model="username"
                :rules="[(v) => !!v || 'username is required']"
                label="username"
            ></v-text-field>
            <v-text-field
                v-model="password"
                :rules="[(v) => !!v || 'password is required']"
                label="password"
            ></v-text-field>
            <v-btn class="mt-2" type="submit" block @click="onLogin"
                >Login</v-btn
            >
        </v-form>
    </div>
</template>

<style scoped></style>
