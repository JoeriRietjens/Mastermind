<template>
  <div id="login">
    <h1>Mastermind</h1>
    <form>
      <input
        type="text"
        name="username"
        v-model="input.username"
        placeholder="Username"
      />
      <input
        type="password"
        name="password"
        v-model="input.password"
        placeholder="Password"
      />
      <button type="button" v-on:click="login()">Sign In</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      input: {
        username: "",
        password: "",
      },
    };
  },
  computed: mapState(["socket"]),

  created() {
    console.log(this.$parent.mockAccount.username);
    this.unsubscribe = this.$store.subscribe((mutation, state) => {
      if (mutation.type == "SOCKET_ONOPEN") {
        if (state.socket.socket.isConnected) {
          //action on socket open
        }
      } else if (mutation.type == "SOCKET_ONMESSAGE") {
        var message = state.socket.socket.message;
        var parsedMessage = JSON.parse(message.content);
        switch (message.operation) {
          case "LOGIN":
            console.log("Eeeey je bent ingelogd")
            break;
        }
      }
    });
  },
  beforeDestroy() {
    this.unsubscribe();
  },
  methods: {
    login() {
      if (this.input.username != "" && this.input.password != "") {
        if (
          this.input.username == this.$parent.mockAccount.username &&
          this.input.password == this.$parent.mockAccount.password
        ) {
          this.$emit("authenticated", true);
          this.$router.replace({ name: "Home" });
        } else {
          console.log("The username and / or password is incorrect");
        }
      } else {
        console.log("A username and password must be present");
      }
    },
  },
};
</script>

<style scoped>
#login {
  width: 500px;
  border: 1px solid #cccccc;
  background-color: #ffffff;
  margin: auto;
  margin-top: 200px;
  padding: 20px;
}
</style>