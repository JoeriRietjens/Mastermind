<template>
    <div class="board" :id="BoardId">
        <Code v-on:SelectCodeSpot="SelectCodeSpot" class="row" RowId="0"></Code>
        <BoardRow class="row" RowId="10"></BoardRow>
        <BoardRow class="row" RowId="9"></BoardRow>
        <BoardRow class="row" RowId="8"></BoardRow>
        <BoardRow class="row" RowId="7"></BoardRow>
        <BoardRow class="row" RowId="6"></BoardRow>
        <BoardRow class="row" RowId="5"></BoardRow>
        <BoardRow class="row" RowId="4"></BoardRow>
        <BoardRow class="row" RowId="3"></BoardRow>
        <BoardRow class="row" RowId="2"></BoardRow>
        <BoardRow class="row" RowId="1"></BoardRow>
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