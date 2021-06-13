<template>
  <BookmarkForm v-if="showCreateFrom" :after-save="afterSave" :book="book"/>
  <a v-on:click="showCreateFrom = true">Add</a>


  <div class="" :key="bookmark.id" v-for="(bookmark, index) in bookmarks">
    <div v-if="editIndex!==index">
      <a  v-on:click="view(bookmark)">{{ bookmark.name }}</a>
      <ul>
        <li :key="tag" v-for="tag in bookmark.tags">
          {{ tag }}
        </li>
      </ul>

      <a  v-on:click="editIndex = index">Edit</a>
      <a  v-on:click="remove(bookmark)">Delete</a>
    </div>
    <div v-if="editIndex===index">
      <BookmarkForm :bookmark="bookmark" :after-save="afterSave"/>&nbsp;&nbsp;&nbsp;&nbsp;
    </div>
==================================
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
      book: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    view: function (bookmark) {
      this.$parent.viewBookmark(bookmark);
    },
    fetchData: function (book) {
      this.book = book;
      fetch(book ? '/v1/bookmark?bookId=' + book.id : '/v1/bookmark')
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
            this.fetchData(this.book);
          });
    },
    afterSave: function (bookmark) {
      this.bookmarks[this.editIndex] = bookmark;
      this.showCreateFrom = false;
      this.editIndex = null;
      this.fetchData(this.book);
    }
  }
}
</script>

<style scoped>

</style>