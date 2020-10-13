<template>
    <div class="board">
        <BoardRow class="row" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" v-on:SelectSpot="SelectSpot"></BoardRow>
        <BoardRow class="row" v-on:SelectSpot="SelectSpot"></BoardRow>
        <Code class="row" id="code"></Code>
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
    },
    methods: {
        SelectSpot(obj) {
            this.$emit("SelectSpot", obj)
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