<template>
    <div id="RowId">
        <ColorSpot v-on:SelectSpot="SelectSpot" SpotId="SpotOne"></ColorSpot>
        <ColorSpot v-on:SelectSpot="SelectSpot" SpotId="SpotTwo"></ColorSpot>
        <ColorSpot v-on:SelectSpot="SelectSpot" SpotId="SpotThree"></ColorSpot>
        <ColorSpot v-on:SelectSpot="SelectSpot" SpotId="SpotFour"></ColorSpot>
        <div class="Hints">
            <div class="topRow">
            <HintSpot class="hintSpot" HintId="HintOne"></HintSpot>
            <HintSpot class="hintSpot" HintId="HintTwo"></HintSpot>
            </div>
            <div class="bottomRow">
            <HintSpot class="hintSpot" HintId="HintThree"></HintSpot>
            <HintSpot class="hintSpot" HintId="HintFour"></HintSpot>
            </div>
        </div>
    </div>
</template>

<script>
import ColorSpot from '@/components/ColorSpot.vue';
import HintSpot from '@/components/HintSpot.vue';

export default {
    name: 'BoardRow',
    components: {
        ColorSpot,
        HintSpot,
    },
    Data() {
        return {
        }
    },
    props: ['RowId'],
    methods: {
        SelectSpot(obj) {
            this.$emit("SelectSpot", obj)
        }
    },
}
</script>

<style scoped>
.hintSpot {
    position: relative;
    display: inline-block;
}
</style>