<template>
  <div class="mt-4 mb-4">
    <Search/>
  </div>
  <div class="container-fluid">
    <div class="row">
      <div class="col-2">
        <Books/>
      </div>
      <div class="col-4">
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
  methods: {
    searchResult: function (result) {
      console.log(result);
    },
    fetchBookmarks: function (book) {
      this.$refs.bookmarks.fetchData(book);
    },
    viewBookmark: function (bookmark) {
      this.$refs.view.fetchData(bookmark);
    }
  }
}
</script>

<style scoped>

</style>