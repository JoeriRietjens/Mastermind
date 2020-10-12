<template>
    <div>
        <ColorSpot></ColorSpot>
        <ColorSpot></ColorSpot>
        <ColorSpot></ColorSpot>
        <ColorSpot></ColorSpot>
        <div class="Hints">
            <HintSpot class="left"></HintSpot>
            <HintSpot class="right"></HintSpot>
            <HintSpot class="left"></HintSpot>
            <HintSpot class="right"></HintSpot>
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
.left {
    float: left;
}
</style>