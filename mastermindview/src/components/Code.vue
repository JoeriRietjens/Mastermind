<template>
  <div>
      <ColorSpot v-on:SelectSpot="CreateCode"></ColorSpot>
      <ColorSpot v-on:SelectSpot="CreateCode"></ColorSpot>
      <ColorSpot v-on:SelectSpot="CreateCode"></ColorSpot>
      <ColorSpot v-on:SelectSpot="CreateCode"></ColorSpot>
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
        CreateCode(ColorSpot) {
            this.$emit("SelectCodeSpot", ColorSpot)
        }
    }
}
</script>

<style scoped>

</style>