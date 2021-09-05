<template>
  <div class=" mt-5 row">
    <div class="col-4"></div>
    <div class="col-4">
      <form @submit.prevent="submit">
        <div class="mb-3">
          <label for="exampleInputEmail1" class="form-label">Email address</label>
          <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" v-model="email">
          <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
        </div>
        <div class="mb-3">
          <label for="exampleInputPassword1" class="form-label">Password</label>
          <input type="password" class="form-control" id="exampleInputPassword1" v-model="password">
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
      </form>
    </div>
    <div class="col-4"></div>
  </div>

</template>

<script>
import Auth from "@/api/Auth"
import Api from "@/api/Rest"
import Router from "@/router/index"

export default {
  name: "Login",
  data() {
    return {
      email: null,
      password: null,
    }
  },
  created() {
    if (Api.getAccessToken()) {
     return  Router.replace("/")
    }
  },
  methods: {
    submit: function () {
      Auth.auth("test", "test").then(token => {
        Api.token = token;
        Router.replace("/")
      })
    }
  }
}
</script>

<style scoped>

</style>