<template>

  <div class="container">
    <form v-on:submit.prevent="live">
      <input class="form-control" id="search" type="search" placeholder="Search..."
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
      search: '',
      minSearchLen: 2,
      timer: null,
      delay: 100,
    }
  },
  methods: {
    live: function () {

      if (this.timer) {
        clearTimeout(this.timer);
        this.timer = null;
      }

      if (!this.search.length || this.search.length < this.minSearchLen) {
        return;
      }

      this.timer = setTimeout(() => {
        fetch('/v1/search/live?s=' + this._clean(this.search))
            .then(res => res.json())
            .then(res => {
              this.$parent.searchResult(res);
            });

      }, this.delay);
    },
    _clean: function (s) {
      let re = /[^а-я\w\s]/gi;
      return s.replace(re, '');
    }
  }
}
</script>
