<template>
    <div class="board">
        <BoardRow></BoardRow>
        <BoardRow></BoardRow>
        <BoardRow></BoardRow>
        <BoardRow></BoardRow>
        <BoardRow></BoardRow>
        <BoardRow></BoardRow>
        <BoardRow></BoardRow>
        <BoardRow></BoardRow>
        <BoardRow></BoardRow>
        <BoardRow></BoardRow>
        <Code></Code>
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

</style>