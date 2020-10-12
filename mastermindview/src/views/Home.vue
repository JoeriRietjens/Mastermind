<template>
  <div class="home">
    <div class="homeElements">
    <Board class="board"></Board>
    <Board class="board"></Board>
    </div>
    <div class="homeElements colors">
    <Colors></Colors>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Board from '@/components/Board.vue';
import Colors from '@/components/Colors.vue';

export default {
  name: 'Home',
  components: {
    Board,
    Colors,
  }
}
</script>

<style scoped>
.board {
  margin: 10px;
}

.homeElements {
  position: relative;
  display: inline-block;
}

.colors {
  top: -100px;
}
</style>