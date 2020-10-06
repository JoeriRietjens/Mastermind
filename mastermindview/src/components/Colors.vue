<template>
    <div>
        <span class="dot" id="green"></span>
        <span class="dot" id="yellow"></span>
        <span class="dot" id="blue"></span>
        <span class="dot" id="red"></span>
        <span class="dot" id="white"></span>
        <span class="dot" id="black"></span>
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
    height: 25px;
    width: 25px;
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