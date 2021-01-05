<template>
  <div>
      <span class="dot" v-on:click="SelectSpot" :id="SpotId" :selected="Selected"></span>
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
        if(this.Selected == true){
          this.$el.querySelector('span').classList.add("selectedSpot");
        }
        else{
          this.$el.querySelector('span').classList.remove("selectedSpot");
        }
      }
    },
    mounted: function () {
      this.Color = this.$props.color;
    }
}
</script>

<style scoped>

</style>