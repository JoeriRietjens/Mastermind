<template>
    <div>
      <span class="hint"></span>
    </div>
</template>

<script>
export default {
    name: 'HintSpot',
    data() {
      return {
        Color: 'grey',
      }
    },
    watch: {
      Color: function() {
        document.querySelector('#' + this.$parent.$parent.BoardId).querySelector('#' + this.$parent.RowId).querySelector('#' + this.HintId).style.backgroundColor = this.Color;
      }
    }
}
</script>

<style scoped>
.hint {
  height: 10px;
  width: 10px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  border: groove;
}


</style>