<template>
    <div>
        <span class="dot" id="green"></span> <br>
        <span class="dot" id="yellow"></span> <br>
        <span class="dot" id="blue"></span> <br>
        <span class="dot" id="red"></span> <br>
        <span class="dot" id="white"></span> <br>
        <span class="dot" id="black"></span> <br>
    </div>
</template>

<script>

export default {
    name: 'Colors',
    components: {
    }
}
</script>

<style scoped>
.dot {  
    height: 40px;
    width: 40px;
    border-radius: 50%;
    display: inline-block;
    border: groove;
}

#green {
    background-color: greenyellow;
}
#red {  
    background-color: red;
}

#blue {  
    background-color: blue;
}

#white {  
    background-color: white;
}

#yellow {  
    background-color: yellow;
}

#black {  
    background-color: black;
}
</style>