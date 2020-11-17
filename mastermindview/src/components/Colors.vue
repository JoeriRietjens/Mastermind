<template>
    <div>
        <ColorSpotGreen class="colorSpot" id="green" v-on:SetColor="SelectColor"/>
        <ColorSpotYellow class="colorSpot" id="yellow" v-on:SetColor="SelectColor"/>
        <ColorSpotBlue class="colorSpot" id="blue" v-on:SetColor="SelectColor"/>
        <ColorSpotRed class="colorSpot" id="red" v-on:SetColor="SelectColor"/>
        <ColorSpotWhite class="colorSpot" id="white" v-on:SetColor="SelectColor"/>
        <ColorSpotBlack class="colorSpot" id="black" v-on:SetColor="SelectColor"/>
    </div>
</template>

<script>
import ColorSpotGreen from '@/components/ColorSpotGreen.vue';
import ColorSpotYellow from '@/components/ColorSpotYellow.vue';
import ColorSpotBlue from '@/components/ColorSpotBlue.vue';
import ColorSpotRed from '@/components/ColorSpotRed.vue';
import ColorSpotWhite from '@/components/ColorSpotWhite.vue';
import ColorSpotBlack from '@/components/ColorSpotBlack.vue';


export default {
    name: 'Colors',
    components: {
        ColorSpotRed,
        ColorSpotYellow,
        ColorSpotGreen,
        ColorSpotBlue,
        ColorSpotWhite,
        ColorSpotBlack,
    },
    data() {
        return {
            SelectedColor: null,
        }
    },
    methods: {
        SelectColor(color) {
            this.SelectedColor = color;
            this.$emit("SetColor", color);
        }
    }
}
</script>

<style scoped>
.colorSpot {  
    display: inline-block;
}


</style>