<template>
    <div class="board" :id="BoardId">
        <Code class="row" id="code"></Code>
        <BoardRow class="row" RowId="RowOne" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="RowOne" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="RowOne" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="RowOne" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="RowOne" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="RowOne" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="RowOne" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="RowOne" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="RowOne" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="RowOne" v-on:SelectSpot="SelectSpot"></BoardRow>
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
        }
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