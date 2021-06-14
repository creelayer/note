<template>
  <div class="mt-4 mb-4">
    <Search ref="search"/>
  </div>
  <div class="container-fluid">
    <div class="row">
      <div class="col-2">
        <Books/>
      </div>
      <div class="col-2">
        <Bookmarks ref="bookmarks"/>
      </div>
      <div class="col-6">
        <BookmarkView ref="view"/>
      </div>
    </div>
  </div>
</template>
<script>
import Books from "@/components/Books";
import Bookmarks from "@/components/Bookmarks";
import BookmarkView from "@/components/BookmarkView";
import Search from "@/components/header/Search";

export default {
  name: "WorkTable",
  components: {Search, Books, Bookmarks, BookmarkView},
  data() {
    return {
      book: null,
      search: null,
    }
  },
  watch: {
    book: function (val) {
      this.$refs.bookmarks.book = val;
      this.fetchBookmarks();
    },
    search: function (val) {
      this.$refs.bookmarks.search = val;
      this.fetchBookmarks();
    }
  },
  methods: {
    fetchBookmarks: function () {
      this.$refs.bookmarks.fetchData();
    },
    viewBookmark: function (bookmark) {
      this.$refs.view.fetchData(bookmark);
    }
  }
}
</script>

<style scoped>

</style>