<template>
  <div :id="RowId">
      <ColorSpot v-on:SelectSpot="SelectCodeSpot" SpotId="SpotOne"></ColorSpot>
      <ColorSpot v-on:SelectSpot="SelectCodeSpot" SpotId="SpotTwo"></ColorSpot>
      <ColorSpot v-on:SelectSpot="SelectCodeSpot" SpotId="SpotThree"></ColorSpot>
      <ColorSpot v-on:SelectSpot="SelectCodeSpot" SpotId="SpotFour"></ColorSpot>
  </div>
</template>

<script>
import ColorSpot from '@/components/ColorSpot.vue';

export default {
    name: 'Code',
    components: {
        ColorSpot,
    },
    props: ['RowId'],
    methods: {
        SelectCodeSpot(CodeSpot) {
            this.$emit("SelectCodeSpot", CodeSpot)
        }
    }
}
</script>

<style scoped>

</style>