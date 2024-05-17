<script setup>
import NavBar from "./components/navBar/NavBar.vue";
import LoginView from "./view/LoginView.vue";
</script>
<template>
    <v-layout class="rounded rounded-md">
        <NavBar />

        <v-main
            class="d-flex flex-column align-center justify-center w-100"
            style="min-height: 300px"
        >
            <RouterView />
        </v-main>
    </v-layout>
</template>

<style scoped></style>
