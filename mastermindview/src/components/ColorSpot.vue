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
        selected: false,
      }
    },
    props: ['SpotId'],
    methods: {
      SelectSpot() {
        this.selected = true;
        this.$emit("SelectSpot", this);
      }
    },
    watch: {
      Color: function() {
        console.log(this.$parent.$parent.BoardId)
        document.querySelector('#' + this.$parent.$parent.BoardId).querySelector('#' + this.$parent.RowId).querySelector('#' + this.SpotId).style.backgroundColor = this.Color;
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