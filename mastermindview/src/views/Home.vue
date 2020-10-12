<template>
  <div class="home">
    <div class="homeElements">
      <h2> Your own bord </h2>
      <Board class="board"></Board>
      <h2> Your opponents board </h2>
      <OpponentBoard class="board"></OpponentBoard>
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
import OpponentBoard from '@/components/OpponentBoard.vue';

export default {
  name: 'Home',
  components: {
    Board,
    Colors,
    OpponentBoard,
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
  top: -350px;
}

.board {
    border-style: ridge;
    width: 750px;
    height: 250px;
}

.row {
    position: relative;
    display: inline-block;
    padding: 5px 10px;
}

#code {
    top: -20px;
}
</style>