<template>
  <div :id="RowId">
      <ColorSpot v-on:SelectSpot="CreateCode" SpotId="SpotOne"></ColorSpot>
      <ColorSpot v-on:SelectSpot="CreateCode" SpotId="SpotTwo"></ColorSpot>
      <ColorSpot v-on:SelectSpot="CreateCode" SpotId="SpotThree"></ColorSpot>
      <ColorSpot v-on:SelectSpot="CreateCode" SpotId="SpotFour"></ColorSpot>
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
        CreateCode(ColorSpot) {
            this.$emit("SelectCodeSpot", ColorSpot)
        }
    }
}
</script>

<style scoped>

</style>