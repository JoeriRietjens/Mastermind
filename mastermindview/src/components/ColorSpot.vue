<template>
  <div>
      <span class="dot" id="ColorSpot" v-on:click="SelectSpot" selected="false"></span>
  </div>
</template>

<script>
export default {
    name: 'ColorSpot',
    data() {
      return {
        Color: 'grey',
        SelectedNr: '',
      }
    },
    methods: {
      SelectSpot() {
        this.$emit("SelectSpot", this);
      }
    },
    watch: {
      Color: function() {
          document.querySelector(':focus').style.backgroundColor = this.Color;
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