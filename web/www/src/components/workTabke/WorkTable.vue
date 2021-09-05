<template>
  <Nav ref="nav"/>
  <div class="workspace container-fluid">
    <div class="row">
      <div class="pt-4 col-xs-4 col-sm-4 col-md-3 col-xl-2 col-xxl-2">
        <Books/>
      </div>
      <div class="pt-4 col-bookmark col-xs-4 col-sm-4 col-md-3 col-xl-3 col-xxl-3">
        <Bookmarks ref="bookmarks"/>
      </div>
      <div class="col-xs-4 col-sm-4 col-md-6 col-xl-7 col-xxl-7">
        <BookmarkView ref="view"/>
      </div>
    </div>
  </div>
</template>

<script>
import Nav from "@/components/header/Nav";
import Books from "@/components/workTabke/Books";
import Bookmarks from "@/components/workTabke/Bookmarks";
import BookmarkView from "@/components/workTabke/BookmarkView";

export default {
  name: "WorkTable",
  components: {Nav, Books, Bookmarks, BookmarkView},
  data() {
    return {
      // book: null,
      // search: null,
    }
  },
  methods: {
    fetchBookmarks: function (book, search) {
      this.$refs.bookmarks.fetchData(book, search);
    },
    viewBookmark: function (bookmark) {
      this.$refs.view.fetchData(bookmark);
    }
  }
}
</script>

<style>
.workspace {
  padding-top: 56px;
  height: 100%;
}

.workspace > .row {
  height: 100%;
}

.context-menu {
  position: relative;
}

.context-menu:hover .dropdown {
  display: block;
}

.context-menu .dropdown {
  display: none;
  position: absolute;
  top: 4px;
  right: 5px;
}

.context-menu .dropdown-toggle {
  padding: 0;
}

.context-menu .without-caret .dropdown-toggle::after {
  display: none;
}

.context-menu .dropdown-item {
  font-size: 0.9em;
}



</style>