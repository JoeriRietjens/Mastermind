<template>
    <div>
        <div class="row">
            <span class="dot" id="green" v-bind:value="SelectedColor" v-on:click="SelectColor"></span>
            <span class="dot" id="yellow" v-bind="SelectedColor" v-on:click="SelectColor"></span>
            <span class="dot" id="blue" v-bind="SelectedColor" v-on:click="SelectColor"></span>
        </div>
        <div class="row">
            <span class="dot" id="red" v-bind="SelectedColor" v-on:click="SelectColor"></span>
            <span class="dot" id="white" v-bind="SelectedColor" v-on:click="SelectColor"></span>
            <span class="dot" id="black" v-bind="SelectedColor" v-on:click="SelectColor"></span>
        </div>
    </div>
</template>

<script>

export default {
    name: 'Colors',
    components: {
    },
    data() {
        return {
            SelectedColor: null,
        }
    },
    methods: {
        SelectColor() {
            this.$emit("SetColor", this.SelectedColor);
        }
    }
}
</script>

<style scoped>
.dot {  
    height: 50px;
    width: 50px;
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

.row {
    display: inline-block;
    position: relative;
}
</style>