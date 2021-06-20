<template>
  <nav class="navbar fixed-top ">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">
        <img src="https://getbootstrap.com/docs/5.0/assets/brand/bootstrap-logo.svg" alt="" width="30" height="24"
             class="d-inline-block align-text-top">
        ohmynote
      </a>
      <Search ref="search"/>
    </div>
  </nav>
</template>

<script>
import Search from "@/components/header/Search";
export default {
name: "Nav",
  components: {Search},
}
</script>

<style scoped>

</style>