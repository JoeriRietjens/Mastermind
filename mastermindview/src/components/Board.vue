<template>
    <div class="board" :id="BoardId">
        <BoardRow class="row" RowId="RowOne" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="RowTwo" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="RowThree" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="RowFour" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="RowFive" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="RowSix" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="RowSeven" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="RowEight" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="RowNine" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" RowId="RowTen" v-on:SelectSpot="SelectSpot"></BoardRow>
        <Code class="row" id="code"></Code>
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
    created: function () {
        this.$store.commit('setPlayerBoard', {state: this.$store.state, board: this})
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