<template>
    <div class="board" :id="BoardId">
        <BoardRow class="row" RowId="1" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="2" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="3" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="4" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="5" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="6" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="7" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="8" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="9" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="10" v-on:SelectSpot="SelectSpot"></BoardRow>
        <Code class="row" RowId="0"></Code>
    </div>
</template>

<script>
import BoardRow from '@/components/BoardRow.vue';
import Code from '@/components/Code.vue';

export default {
    name: 'MastermindBoard',
    components: {
        BoardRow,
        Code,
    },
    methods: {
        SelectSpot(obj) {
            this.$emit("SelectSpot", obj)
        }
    },
    props: ['BoardId'],
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
