<script setup>
import { ref } from "vue";

const commnets = [
    {
        commentId: 1,
        userId: 1,
        content: "comment",
        createdAt: "1999/5/2",
    },
    {
        commentId: 2,
        userId: 1,
        content: "comment",
        createdAt: "1999/5/2",
    },
    {
        commentId: 3,
        userId: 1,
        content: "comment",
        createdAt: "1999/5/2",
    },
];

const showComment = defineModel(false);
</script>

<template >
    <v-card-text>
        {{  }}
    </v-card-text>
    <v-card-actions>
        <v-textarea label="Label" variant="outlined"></v-textarea>
        <v-btn
            icon="mdi-comment-text"
            size="small"
            @click="showComment = !showComment"
        ></v-btn>
    </v-card-actions>
</template>

<style scoped></style>
