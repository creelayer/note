<template>

  <div class="login-page ">

    <div class="card border-light shadow">
      <div class="card-body">


        <div class="d-grid gap-2">
          <button class="btn btn-google-login btn-outline-light rounded-3" type="button">

            <svg class=" GoogleSignInButton-logo--sparse GoogleSignInButton-logo" viewBox="0 0 18 18">
              <path
                  d="M17.64,9.20454545 C17.64,8.56636364 17.5827273,7.95272727 17.4763636,7.36363636 L9,7.36363636 L9,10.845 L13.8436364,10.845 C13.635,11.97 13.0009091,12.9231818 12.0477273,13.5613636 L12.0477273,15.8195455 L14.9563636,15.8195455 C16.6581818,14.2527273 17.64,11.9454545 17.64,9.20454545 L17.64,9.20454545 Z"
                  fill="#4285F4"></path>
              <path
                  d="M9,18 C11.43,18 13.4672727,17.1940909 14.9563636,15.8195455 L12.0477273,13.5613636 C11.2418182,14.1013636 10.2109091,14.4204545 9,14.4204545 C6.65590909,14.4204545 4.67181818,12.8372727 3.96409091,10.71 L0.957272727,10.71 L0.957272727,13.0418182 C2.43818182,15.9831818 5.48181818,18 9,18 L9,18 Z"
                  fill="#34A853"></path>
              <path
                  d="M3.96409091,10.71 C3.78409091,10.17 3.68181818,9.59318182 3.68181818,9 C3.68181818,8.40681818 3.78409091,7.83 3.96409091,7.29 L3.96409091,4.95818182 L0.957272727,4.95818182 C0.347727273,6.17318182 0,7.54772727 0,9 C0,10.4522727 0.347727273,11.8268182 0.957272727,13.0418182 L3.96409091,10.71 L3.96409091,10.71 Z"
                  fill="#FBBC05"></path>
              <path
                  d="M9,3.57954545 C10.3213636,3.57954545 11.5077273,4.03363636 12.4404545,4.92545455 L15.0218182,2.34409091 C13.4631818,0.891818182 11.4259091,0 9,0 C5.48181818,0 2.43818182,2.01681818 0.957272727,4.95818182 L3.96409091,7.29 C4.67181818,5.16272727 6.65590909,3.57954545 9,3.57954545 L9,3.57954545 Z"
                  fill="#EA4335"></path>
            </svg>

            Login with Google
          </button>
        </div>


        <span class="SeparatorRow LoginDefaultView-separatorRow"><span class="SeparatorRow-horizontalLine"></span><span
            class="SeparatorRow-label LoginDefaultView-separatorRowLabel">or</span><span
            class="SeparatorRow-horizontalLine"></span></span>


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
            <button type="submit" class="btn btn-primary rounded-3 mt-3">Login</button>
          </div>
        </form>


        <div class="">
          Don't have an account?
          <a href="/create-account">Sign up</a>
        </div>



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

.btn-google-login {
  border-color: #ced4da;
  position: relative;
  color: black;
  padding: .575rem .75rem;
}

.GoogleSignInButton-logo {
  width: 18px;
  height: 18px;
  float: left;
  margin-top: 4px;
  position: absolute;
  left: 20px;
}

.SeparatorRow {
  align-items: center;
  display: flex;
  text-align: center;
}

.SeparatorRow-horizontalLine {
  border-top: 1px solid #e8ecee;
  flex: 1 1 auto;
  margin-top: 8px;
  min-width: 1px;
  padding-top: 8px;
}

.LoginDefaultView-separatorRowLabel {
  color: #cbd4db !important;
  font-size: 16px;
  margin: 30px 10px;
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
    },
    submit: function () {

      if (this.errors.length) {
        return;
      }

      Auth.auth(this.username, this.password).then(data => {
        Api.token = data.data;
        Router.replace("/")
      }).catch(data => {
        console.log(data);
      })
    }
  }
}
</script>