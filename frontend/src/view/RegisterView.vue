<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { register } from "../api/user";

const router = useRouter();

const mobile = ref("");
const email = ref("");
const password = ref("");
const biography = ref("");

const form = ref();

const onReguster = async () => {
    const formValidate = await form.value.validate();
    if (formValidate.valid) {
        register(mobile.value, email.value, password.value, biography.value)
        .then(() => {
            router.push("/");
        });
    }
};
</script>

<template>
    <div style="width: 600px">
        <v-form @submit.prevent ref="form">
            <v-text-field
                v-model="mobile"
                :rules="[(v) => /^09\d{8}$/.test(v) || 'Must be a valid Taiwan mobile number']"
                label="mobile"
                type="text"
                autofocus
            ></v-text-field>
            <v-text-field
                v-model="email"
                :rules="[(v) => /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(v) || 'Must be a valid email']"
                label="email"
                type="email"
            ></v-text-field>
            <v-text-field
                v-model="password"
                :rules="[(v) => !!v || 'Password is required']"
                label="password"
                type="password"
            ></v-text-field>
            <v-textarea
                v-model="biography"
                label="biography"
            ></v-textarea>
            <v-btn class="mt-2" type="submit" block @click="onReguster"
                >Register</v-btn
            >
        </v-form>
    </div>
</template>

<style scoped></style>
