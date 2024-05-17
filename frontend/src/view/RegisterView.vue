<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { register } from "../api/user";

const router = useRouter();

const username = ref("");
const email = ref("");
const password = ref("");
const coverImage = ref("");
const biography = ref("");

const form = ref();

const onReguster = async () => {
    const formValidate = await form.value.validate();
    if (formValidate.valid) {
        register(username.value, email.value, password.value, coverImage.value, biography.value)
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
                v-model="username"
                :rules="[(v) => !!v || 'username is required']"
                label="username"
            ></v-text-field>
            <v-text-field
                v-model="email"
                :rules="[(v) => !!v || 'email is required']"
                label="email"
            ></v-text-field>
            <v-text-field
                v-model="password"
                :rules="[(v) => !!v || 'password is required']"
                label="password"
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
