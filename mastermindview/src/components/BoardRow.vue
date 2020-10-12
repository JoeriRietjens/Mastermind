<template>
    <div>
        <ColorSpot></ColorSpot>
        <ColorSpot></ColorSpot>
        <ColorSpot></ColorSpot>
        <ColorSpot></ColorSpot>
        <div class="Hints">
            <div class="topRow">
            <HintSpot class="hint"></HintSpot>
            <HintSpot class="hint"></HintSpot>
            </div>
            <div class="bottomRow">
            <HintSpot class="hint"></HintSpot>
            <HintSpot class="hint"></HintSpot>
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
    }
}
</script>

<style scoped>
.hint {
    position: relative;
    display: inline-block;
}
</style>