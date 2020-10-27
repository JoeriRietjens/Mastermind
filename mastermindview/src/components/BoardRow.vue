<template>
    <div>
        <ColorSpot v-on:SelectSpot="SelectSpot"></ColorSpot>
        <ColorSpot v-on:SelectSpot="SelectSpot"></ColorSpot>
        <ColorSpot v-on:SelectSpot="SelectSpot"></ColorSpot>
        <ColorSpot v-on:SelectSpot="SelectSpot"></ColorSpot>
        <div class="Hints">
            <div class="topRow">
            <HintSpot class="hintSpot"></HintSpot>
            <HintSpot class="hintSpot"></HintSpot>
            </div>
            <div class="bottomRow">
            <HintSpot class="hintSpot"></HintSpot>
            <HintSpot class="hintSpot"></HintSpot>
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
    methods: {
        SelectSpot(obj) {
            this.$emit("SelectSpot", obj)
        }
    }
}
</script>

<style scoped>
.hintSpot {
    position: relative;
    display: inline-block;
}
</style>