<template>
  <div class="home">
    <Board></Board>
    <Board></Board>
    <Colors></Colors>
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
