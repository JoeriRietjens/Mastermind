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
        document.querySelector('#' + this.$parent.$parent.BoardId).querySelector('#' + this.$parent.RowId).querySelector('#' + this.HintId).style.backgroundColor = this.Color;
      }
    }
}
</script>

<style scoped>
.hint {
  height: 10px;
  width: 10px;
  background-color: lightgrey;
  border-radius: 50%;
  display: inline-block;
  border: groove;
}


</style>