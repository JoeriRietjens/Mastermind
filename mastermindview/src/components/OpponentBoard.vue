<template>
    <div class="board">
        <Code class="row" id="code"></Code>
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
    </div>
</template>

<script>
import BoardRow from '@/components/BoardRow.vue';
import Code from '@/components/Code.vue';

export default {
    name: 'OpponentBoard',
    components: {
        BoardRow,
        Code,
    }
}
</script>

<style scoped>
.board {
    border-style: ridge;
    width: 750px;
    height: 250px;
}

.row {
    position: relative;
    display: inline-block;
    padding: 5px 10px;
}

#code {
    top: -20px;
}
</style>