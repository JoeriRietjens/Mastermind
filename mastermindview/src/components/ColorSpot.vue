<template>
  <div>
      <span class="dot" v-on:click="SelectSpot" :id="SpotId" :selected="selected"></span>
  </div>
</template>

<script>
export default {
    name: 'ColorSpot',
    data() {
      return {
        Color: 'grey',
        Selected: false
      }
    },
    props: ['SpotId', 'color', 'Button'],
    methods: {
      SelectSpot() {
        this.$emit("SelectSpot", this);
      }
    },
    watch: {
      Color: function() {
        this.$el.querySelector('span').style.backgroundColor = this.Color;
      },
      Selected: function() {
        this.$el.querySelector('span').style.border.Color = rgb(255, 255, 255);
      }
    },
    mounted: function () {
      this.Color = this.$props.color;
    }
}
</script>

<style scoped>

</style>