<template>
    <div>
      <span class="hint" :id="HintId"></span>
    </div>
</template>

<script>
export default {
    name: 'HintSpot',
    props: ['HintId'],
    data() {
      return {
        Color: 'grey',
      }
    },
    watch: {
      Color: function() {
        this.$el.querySelector('span').style.backgroundColor = this.Color;
      }
    }
}
</script>

<style scoped>
.hint {
  height: 10px;
  width: 10px;
  background-color: rgb(70, 7, 7);
  border-radius: 50%;
  display: inline-block;
  position: relative;
  left: -2px;
  margin-left: 4px;
  border: 1px groove rgb(10, 5, 5);
  box-shadow: inset 0px 0px 2px black;
}
</style>