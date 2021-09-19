<template>

  <div class="login-page ">

    <div class="card border-light shadow">
      <div class="card-body">

        <h1>Registration</h1>

        <p v-if="errors.length">
          <b>Please correct the following error(s):</b>
        <ul>
          <li :key="index" v-for="(error,index) in errors">{{ error }}</li>
        </ul>
        </p>
        <form @submit="checkForm" @submit.prevent="submit">
          <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Email address</label>
            <input type="email" class="form-control rounded-3" id="exampleInputEmail1" aria-describedby="emailHelp"
                   v-model="username">
            <div id="emailHelp" class="form-text rounded-3">We'll never share your email with anyone else.</div>
          </div>
          <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword1" v-model="password">
          </div>
          <div class="d-grid gap-2 ">
            <button type="submit" class="btn btn-primary rounded-3 mt-3">Register</button>
          </div>
        </form>


      </div>
    </div>


  </div>

</template>


<style scoped>

.login-page {
  background-color: #f5f8f9;
  height: 100%;
  padding-top: 100px;
}

.card {
  margin: 0 auto;
  max-width: 440px;
  width: calc(100% - 16px);
  padding: 44px 38px 38px;
}

</style>

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

      if (!this.password) {
        this.errors.push('Password required.');
      }
    },
    submit: function () {

      if (this.errors.length) {
        return;
      }

      Auth.register(this.username, this.password).then(() => {
        Auth.auth(this.username, this.password).then(data => {
          Api.token = data.data;
          Router.replace("/");
        });
      })
    }
  }
}
</script>