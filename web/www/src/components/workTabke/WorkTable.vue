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
      book: null,
      search: null,
    }
  },
  watch: {
    book: function (book) {
      this.$refs.bookmarks.book = book;
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

.tags {
  margin: 10px 1px;
  padding: 0;
  position: relative;
  display: block;
  overflow: hidden;
  list-style: none;
}

.tags .tag {
  margin-right: 6px;
  font-weight: normal;
  background-color: #e5e4e4;
  float: left;
  padding: 1px 6px;
  color: #FFFFFF;;
  font-size: 11px;
  border-radius: 8px;
}

.context-menu .dropdown-item {
  font-size: 0.9em;
}

.multiselect-tag {
  background: #e5e4e4;
  color: black;
  opacity: 0.6;
}

</style>