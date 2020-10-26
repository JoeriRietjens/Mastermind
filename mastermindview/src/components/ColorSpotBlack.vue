<template>
  <div>
      <span class="dot" v-on:click="SelectColor(Color)"></span>
  </div>
</template>

<script>
export default {
    name: 'ColorSpotBlack',
    data() {
      return {
        Color: 'black',

      }
    },
    methods: {
      SelectColor(color) {
        this.$emit("SetColor", color);
      }
    }
}
</script>

<style scoped>
.dot {  
  height: 50px;
  width: 50px;
  border-radius: 50%;
  display: inline-block;
  border: groove;
  background-color: black;
}


</style>