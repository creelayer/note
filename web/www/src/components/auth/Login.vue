<template>
  <div class=" mt-5 row">
    <div class="col-4"></div>
    <div class="col-4">
      <p v-if="errors.length">
        <b>Please correct the following error(s):</b>
      <ul>
        <li :key="index" v-for="(error,index) in errors">{{ error }}</li>
      </ul>
      </p>
      <form @submit="checkForm" @submit.prevent="submit">
        <div class="mb-3">
          <label for="exampleInputEmail1" class="form-label">Email address</label>
          <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                 v-model="username">
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
      username: null,
      password: null,
      errors: [],
    }
  },
  created() {
    if (Api.getAccessToken()) {
      return Router.replace("/")
    }
  },
  methods: {
    checkForm: function () {
      if (!this.username) {
        this.errors.push('Name required.');
      }
    },
    submit: function () {

      if (this.errors.length) {
        return;
      }

      Auth.auth(this.username, this.password).then(data => {
        Api.token = data.data;
        Router.replace("/")
      })
    }
  }
}
</script>

<style scoped>

</style>