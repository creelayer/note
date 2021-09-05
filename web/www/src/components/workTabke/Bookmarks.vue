<template>


  <div class="d-grid gap-2">
    <button v-on:click="showCreateFrom = true" :disabled="!book" class="btn btn-light mb-2" type="button" >+ Add</button>
  </div>

  <BookmarkForm v-if="showCreateFrom" :book="book" :is-inline="true"/>


  <div class="context-menu" :key="bookmark.id" v-for="(bookmark) in bookmarks">
    <div v-on:click="view(bookmark)"
         class="border rounded-3 shadow-sm p-2 mb-2  bookmark">
      <a>{{ bookmark.name }}</a>

      <div class="info">
        <InlineTags :bookmark="bookmark"/>
        <i class="bi bi-eye float-end"></i>
      </div>

    </div>

    <div class="dropdown without-caret float-end">
      <button class="btn btn-sm dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
        <i class="bi bi-three-dots-vertical"></i>
      </button>
      <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
        <li><a class="dropdown-item" href="#" v-on:click="remove(bookmark)">Delete</a></li>
      </ul>
    </div>

  </div>

</template>

<style scoped>

.bookmark {
  display: block;
  cursor: pointer;
  min-height: 70px;
  font-size: 0.85em;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Helvetica, Arial, sans-serif;
}

.bi-eye {
  position: absolute;
  right: 10px;
  bottom: 5px;
  opacity: 0.5;
}

</style>

<script>
import BookmarkForm from "@/components/workTabke/BookmarkForm";
import InlineTags from "@/components/tags/Inline";
import Rest from "@/api/Rest"


export default {
  name: "Bookmarks",
  components: {BookmarkForm, InlineTags},
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
    remove: function (bookmark) {
      Rest.del('/v1/bookmark/' + bookmark.id).then(() => {
        this.fetchData();
      })
    },
    fetchData: function (book, search) {
      this.showCreateFrom = false;
      this.book = book;
      Rest.get('/v1/search/live?' + this._getSearchUrl(book, search))
          .then(res => {
            this.bookmarks = res.data.content;
          });
    },
    // afterSave: function (bookmark) {
    //   this.bookmarks[this.editIndex] = bookmark;
    //   this.showCreateFrom = false;
    //   this.editIndex = null;
    // },
    _getSearchUrl: function (book, search) {
      let query = '';
      if (book)
        query += '&bookId=' + book.id;
      if (search)
        query += '&s=' + search;
      return query;
    }
  }
}
</script>