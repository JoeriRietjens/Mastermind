<template>
    <div class="board" :id="BoardId">
        <Code v-on:SelectCodeSpot="SelectCodeSpot" class="row" RowId="code"></Code>
        <BoardRow class="row" RowId="RowOne"></BoardRow>
        <BoardRow class="row" RowId="RowOne"></BoardRow>
        <BoardRow class="row" RowId="RowOne"></BoardRow>
        <BoardRow class="row" RowId="RowOne"></BoardRow>
        <BoardRow class="row" RowId="RowOne"></BoardRow>
        <BoardRow class="row" RowId="RowOne"></BoardRow>
        <BoardRow class="row" RowId="RowOne"></BoardRow>
        <BoardRow class="row" RowId="RowOne"></BoardRow>
        <BoardRow class="row" RowId="RowOne"></BoardRow>
        <BoardRow class="row" RowId="RowOne"></BoardRow>
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
    },
    props: ['BoardId'],
    methods: {
        SelectSpot() {
            this.$emit("SelectSpot", null);
        },
        SelectCodeSpot(SelectCodeSpot) {
            this.$emit("SelectCodeSpot", SelectCodeSpot)
        }
    },
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