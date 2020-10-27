<template>
  <div>
      <span class="dot" v-on:click="SelectSpot"></span>
  </div>
</template>

<script>
export default {
    name: 'ColorSpot',
    data() {
      return {
        Color: 'grey',

      }
    },
    methods: {
      SelectSpot() {
        this.$emit("SelectSpot", this);
      }
    }
}
</script>

<style scoped>
.dot {
  height: 40px;
  width: 40px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  border: groove;
}

</style>