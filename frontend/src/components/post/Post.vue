<script setup>
import { ref } from "vue";
import { useStore } from "vuex";
import { getCommentsByPostId, createComment } from "../../api/comment";
import { deletePost, updatePost } from "../../api/post";

const props = defineProps({
    post: {
        postId: Number,
        userId: Number,
        username: String,
        content: String,
        createdAt: Date,
    },
});
const emit = defineEmits(["refresh"]);

const store = useStore();

const showComment = ref(false);
const showEditDialog = ref(false);
const newComment = ref("");
const editedPost = ref(props.post.content);
const comments = ref([]);
const commentInputField = ref();
const postInputField = ref();
const isAuthor = props.post.userId === store.state.userId;

const getComments = () => {
    getCommentsByPostId(props.post.postId).then((res) => {
        comments.value = res.data;
    });
};

const handleCreatComment = async () => {
    const formValidate = await commentInputField.value.validate();
    if (formValidate.valid) {
        createComment(props.post.postId, newComment.value).then(() => {
            newComment.value = "";
            getComments();
        });
    }
};

const handleDeletePost = () => {
    deletePost(props.post.postId).then(() => {
        emit("refresh");
    });
};

const handleEditPost = async () => {
    const formValidate = await postInputField.value.validate();
    if (formValidate.valid) {
        updatePost(props.post.postId, editedPost.value).then(() => {
            showEditDialog.value = false;
            emit("refresh");
        });
    }
};

const initialize = () => {
    getComments();
};

initialize();
</script>

<template>
    <v-card
        style="width: 500px"
        prepend-icon="mdi-account"
        variant="outlined"
        :subtitle="props.post.createdAt"
        :title="props.post.username"
    >
        <v-card-text>{{ props.post.content }}</v-card-text>
        <template v-slot:append v-if="isAuthor">
            <v-btn
                icon="mdi-pencil"
                size="small"
                @click="showEditDialog = true"
            ></v-btn>
            <v-btn
                icon="mdi-delete"
                size="small"
                @click="handleDeletePost"
            ></v-btn>
        </template>
        <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn
                icon="mdi-comment-text"
                size="small"
                @click="showComment = !showComment"
            ></v-btn>
        </v-card-actions>

        <v-expand-transition>
            <div v-show="showComment">
                <v-divider></v-divider>
                <v-list lines="one">
                    <v-list-item
                        v-for="comment in comments"
                        :key="comment.commentId"
                        :title="comment.content"
                        :subtitle="comment.username"
                    ></v-list-item>
                </v-list>
                <v-card-actions v-if="store.state.isLogin">
                    <v-textarea
                        label="content"
                        variant="outlined"
                        v-model="newComment"
                        type="text"
                        :rules="[(v) => !!v || 'Content is required']"
                        ref="commentInputField"
                    ></v-textarea>
                    <v-btn @click="handleCreatComment">Comment</v-btn>
                </v-card-actions>
            </div>
        </v-expand-transition>
    </v-card>

    <v-dialog v-model="showEditDialog" width="500px">
        <v-card prepend-icon="mdi-pencil" title="Edit Post">
            <v-textarea
                label="content"
                variant="outlined"
                v-model="editedPost"
                type="text"
                :rules="[(v) => !!v || 'Content is required']"
                ref="postInputField"
            ></v-textarea>
            <v-btn @click="handleEditPost">submit</v-btn>
        </v-card>
    </v-dialog>
</template>

<style scoped></style>
