<template>

  <div class="container">
    <form v-on:submit.prevent="live">
      <input class="form-control" id="search" type="search" placeholder="Search..." autocomplete="off"
             v-on:keyup="live"
             v-on:change="live"
             v-model="search">
    </form>
  </div>

</template>

<script>
export default {
  name: "Search",
  data() {
    return {
      book: null,
      search: '',
      minSearchLen: 1,
      timer: null,
      delay: 150,
    }
  },
  methods: {
    live: function () {

      if (this.timer) {
        clearTimeout(this.timer);
        this.timer = null;
      }

      if (!this.search.length || this.search.length < this.minSearchLen) {
        this.$parent.search = null;
        return;
      }

      this.timer = setTimeout(() => {
        this.$parent.search = this._clean(this.search);
      }, this.delay);
    },
    _clean: function (s) {
      let re = /[^а-я\w\s]/gi;
      return s.replace(re, '');
    }
  }
}
</script>
