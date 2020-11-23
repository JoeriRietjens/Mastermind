<template>
    <div>
        <ColorSpot class="colorSpot" color='LIME' SpotId="Lime" Button='true' v-on:SelectSpot="SelectColor"/>
        <ColorSpot class="colorSpot" color='YELLOW' SpotId="Yellow" Button='true' v-on:SelectSpot="SelectColor"/>
        <ColorSpot class="colorSpot" color='BLUE' SpotId="Blue" Button='true' v-on:SelectSpot="SelectColor"/>
        <ColorSpot class="colorSpot" color='RED' SpotId="Red" Button='true' v-on:SelectSpot="SelectColor"/>
        <ColorSpot class="colorSpot" color='WHITE' SpotId="White" Button=true v-on:SelectSpot="SelectColor"/>
        <ColorSpot class="colorSpot" color='BLACK' SpotId="Black" Button=true v-on:SelectSpot="SelectColor"/>
    </div>
</template>

<script>
import ColorSpot from '@/components/ColorSpot.vue';


export default {
    name: 'Colors',
    components: {
        ColorSpot,
    },
    data() {
        return {
        }
    },
    methods: {
        SelectColor(spot) {
            this.$emit("SetColor", spot.$data.Color);
        }
    }
}
</script>

<style scoped>
.colorSpot {  
    display: inline-block;
}
</style>