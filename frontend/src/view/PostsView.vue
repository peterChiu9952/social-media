<script setup>
import { ref, onMounted } from "vue";
import { useStore } from "vuex";
import Post from "../components/post/Post.vue";
import { getPosts, createPost } from "../api/post";

const posts = ref([]);
const showEditDialog = ref(false);
const newPostContent = ref("");
const inputField = ref();

const store = useStore();

const handleCreatePost = async () => {
    const formValidate = await inputField.value.validate();
    if (formValidate.valid) {
        createPost(newPostContent.value).then(() => {
            showEditDialog.value = false;
            newPostContent.value = "";
            refresh();
        });
    }
};

const refresh = () => {
    getPosts().then((res) => {
        posts.value = res.data.map((post) => ({
            ...post,
            createdAt: new Date(post.createdAt).toLocaleString(),
        }));
    });
};

refresh();
</script>

<template>
    <v-btn v-if="store.state.isLogin" @click="showEditDialog = true"
        >New Post</v-btn
    >
    <v-dialog v-model="showEditDialog" width="500px">
        <v-card prepend-icon="mdi-pencil" title="create Post">
            <v-textarea
                label="content"
                variant="outlined"
                v-model="newPostContent"
                type="text"
                :rules="[(v) => !!v || 'Content is required']"
                ref="inputField"
            ></v-textarea>
            <v-btn @click="handleCreatePost">Create</v-btn>
        </v-card>
    </v-dialog>

    <div v-for="post in posts" :key="post.postId">
        <Post :post="post" @refresh="refresh" />
    </div>
</template>

<style scoped></style>
