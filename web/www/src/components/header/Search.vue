<template>

  <div class="container">
    <form v-on:submit.prevent="live">
      <input class="form-control form-control-sm" id="search" type="search" placeholder="Search..." autocomplete="off"
             ref="search"
             v-on:keyup="live"
             v-on:change="live"
             v-model="search">
    </form>
  </div>

</template>

<script>

import Debounce from "debounce";

export default {
  name: "Search",
  data() {
    return {
      book: null,
      search: '',
      minSearchLen: 2,
    }
  },
  created: function () {
    document.addEventListener('keydown', function () {
      if (document.activeElement.classList.contains("mce-edit-focus")) {
        return;
      }
      if (document.activeElement.tagName === 'TEXTAREA' || document.activeElement.tagName === 'INPUT') {
        return;
      }
      document.getElementById('search').focus();
    });
  },
  methods: {
    live: Debounce(function () {

      this.search = this._clean(this.search)

      if (!this.search.length || this.search.length < this.minSearchLen) {
        this.$parent.$parent.fetchBookmarks(this.book, null);
        return;
      }

      this.$parent.$parent.fetchBookmarks(this.book, this.search);


    }, 100),
    _clean: function (s) {
      let re = /[^а-яєії\w\s]/gi;
      return s.replace(re, '');
    }
  }
}
</script>
