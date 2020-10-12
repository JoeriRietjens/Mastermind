<template>
    <div class="board">
        <BoardRow class="row"></BoardRow>
        <BoardRow class="row"></BoardRow>
        <BoardRow class="row"></BoardRow>
        <BoardRow class="row"></BoardRow>
        <BoardRow class="row"></BoardRow>
        <BoardRow class="row"></BoardRow>
        <BoardRow class="row"></BoardRow>
        <BoardRow class="row"></BoardRow>
        <BoardRow class="row"></BoardRow>
        <BoardRow class="row"></BoardRow>
        <Code class="row"></Code>
    </div>
</template>

<script>
import BoardRow from '@/components/BoardRow.vue';
import Code from '@/components/Code.vue'

export default {
    name: 'MastermindBoard',
    components: {
        BoardRow,
        Code,
    }
}
</script>

<style scoped>
.board {border-style: ridge;}

.row {
    position: relative;
    display: inline-block;
}

</style>