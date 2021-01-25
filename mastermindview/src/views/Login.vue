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
      <p style="white-space: pre-line;">{{ message }}</p>
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
      message: "",
    };
  },
  methods: {

    async axiosLogin(userString){
      const headers = {'Content-Type': 'application/json'}
      return this.axios.post('http://localhost:5001/login/', userString, { headers: headers }).then(response => response.status)
    },

    async login() {
      if (this.input.username != "" && this.input.password != "") {
        // Retrieve user information
        const user = {username: this.input.username, password: this.input.password}
        const userString = JSON.stringify(user)
        var resp = await this.axiosLogin(userString)

        console.log(resp);

        if (resp == 200) {
          this.$emit("authenticated", true);
          this.$router.replace({ name: "Home" });
        } else {
          console.log("The username and / or password is incorrect");
          this.message = "The username and / or password is incorrect";
        }
      } else {
        console.log("A username and password must be present");
        this.message = "A username and password must be present";
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