<template>
    <div :id="RowId">
        <ColorSpot v-on:SelectSpot="SelectSpot" SpotId="SpotOne" ref="ColorSpot"></ColorSpot>
        <ColorSpot v-on:SelectSpot="SelectSpot" SpotId="SpotTwo" ref="ColorSpot"></ColorSpot>
        <ColorSpot v-on:SelectSpot="SelectSpot" SpotId="SpotThree" ref="ColorSpot"></ColorSpot>
        <ColorSpot v-on:SelectSpot="SelectSpot" SpotId="SpotFour" ref="ColorSpot"></ColorSpot>
        <div class="Hints">
            <div class="topRow">
            <HintSpot class="hintSpot" HintId="HintOne" ref="ColorSpot"></HintSpot>
            <HintSpot class="hintSpot" HintId="HintTwo" ref="ColorSpot"></HintSpot>
            </div>
            <div class="bottomRow">
            <HintSpot class="hintSpot" HintId="HintThree" ref="ColorSpot"></HintSpot>
            <HintSpot class="hintSpot" HintId="HintFour" ref="ColorSpot"></HintSpot>
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

.custom-select .selected {
    background-color: #0a0a0a;
  border-radius: 6px;
  border: 1px solid #666666;
  color: #fff;
  padding-left: 1em;
  cursor: pointer;
  user-select: none;
}
</style>