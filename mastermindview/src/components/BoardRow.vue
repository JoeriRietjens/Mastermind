<template>
    <div>
        <ColorSpot></ColorSpot>
        <ColorSpot></ColorSpot>
        <ColorSpot></ColorSpot>
        <ColorSpot></ColorSpot>
        <HintSpot></HintSpot>
        <HintSpot></HintSpot>
        <HintSpot></HintSpot>
        <HintSpot></HintSpot>
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

</style>