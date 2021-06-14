<template>



  <div class="d-grid gap-2">
    <button v-on:click="showCreateFrom = true" class="btn btn-light mb-2" type="button">+ Add</button>
  </div>
  <BookmarkForm v-if="showCreateFrom" :after-save="afterSave" :book="book"/>


  <div class="" :key="bookmark.id" v-for="(bookmark, index) in bookmarks">
    <div v-if="editIndex!==index" class="border rounded-3 shadow-sm p-2 mb-2 ">
      <a v-on:click="view(bookmark)">{{ bookmark.name }}</a>
      <ul>
        <li :key="tag" v-for="tag in bookmark.tags">
          {{ tag }}
        </li>
      </ul>


      <div v-if="editIndex!==index" class="dropdown without-caret float-end">
        <button class="btn btn-sm dropdown-toggle" type="button"  data-bs-toggle="dropdown" aria-expanded="false">
          <i class="bi bi-three-dots-vertical"></i>
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
          <li><a class="dropdown-item" href="#" v-on:click="editIndex = index">Edit</a></li>
          <li><a class="dropdown-item" href="#" v-on:click="remove(book)">Delete</a></li>
        </ul>
      </div>

    </div>
    <div v-if="editIndex===index">
      <BookmarkForm :bookmark="bookmark" :after-save="afterSave"/>&nbsp;&nbsp;&nbsp;&nbsp;
    </div>

  </div>

</template>

<script>

import BookmarkForm from "@/components/BookmarkForm";

export default {
  name: "Bookmarks",
  components: {BookmarkForm},
  data() {
    return {
      showCreateFrom: false,
      editIndex: null,
      bookmarks: [],
      book: null,
      search: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    view: function (bookmark) {
      this.$parent.viewBookmark(bookmark);
    },
    fetchData: function () {
      fetch('/v1/search/live?' + this._getSearchUrl())
          .then(res => res.json())
          .then(res => {
            this.bookmarks = res.data.content;
          });
    },
    remove: function (bookmark) {
      const requestOptions = {
        method: "DELETE",
        headers: {"Content-Type": "application/json"},
      };
      fetch('/v1/bookmark/' + bookmark.id, requestOptions)
          .then(res => res.json())
          .then(() => {
            this.fetchData();
          });
    },
    afterSave: function (bookmark) {
      this.bookmarks[this.editIndex] = bookmark;
      this.showCreateFrom = false;
      this.editIndex = null;
    },
    _getSearchUrl: function () {
      let query = '';
      if (this.book)
        query += '&bookId=' + this.book.id;
      if (this.search)
        query += '&s=' + this.search;
      return query;
    }
  }
}
</script>

<style scoped>

</style>