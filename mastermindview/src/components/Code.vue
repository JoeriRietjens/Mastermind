<template>
  <div>
      <ColorSpot></ColorSpot>
      <ColorSpot></ColorSpot>
      <ColorSpot></ColorSpot>
      <ColorSpot></ColorSpot>
  </div>
</template>

<script>
import ColorSpot from '@/components/ColorSpot.vue';

export default {
    name: 'Code',
    components: {
        ColorSpot,
    },
    methods: {
        //CreateCode(selectedColor) {
            //this.$emit(this.SelectSpot, obj)
        //}
    }
}
</script>

<style scoped>

</style>