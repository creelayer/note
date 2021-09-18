<template>

  <div class="add-bookmark mb-2" v-on:click="showCreateFrom = !!book">
    <div class="add-bookmark-box">
      <svg class="Icon" focusable="false" viewBox="0 0 32 32">
        <path
            d="M26,14h-8V6c0-1.1-0.9-2-2-2l0,0c-1.1,0-2,0.9-2,2v8H6c-1.1,0-2,0.9-2,2l0,0c0,1.1,0.9,2,2,2h8v8c0,1.1,0.9,2,2,2l0,0c1.1,0,2-0.9,2-2v-8h8c1.1,0,2-0.9,2-2l0,0C28,14.9,27.1,14,26,14z"></path>
      </svg>
    </div>
  </div>

  <div class="" v-if="showCreateFrom">
    <div class=" rounded-3 p-2 mb-1 bookmark">
      <BookmarkForm :book="book" :is-inline="true"/>

      <div class="info">
        <InlineTags :book="book"/>
      </div>

    </div>
  </div>


  <div v-if="bookmarks.length === 0 " class="text-center text-muted mt-4">
    {{ search ? 'No items found...' : 'There is no items yet. You may create your first...' }}
  </div>

  <div class="context-menu" :key="bookmark.id" v-for="(bookmark) in bookmarks">
    <div v-on:click="view(bookmark)"
         class=" rounded-3  p-2 mb-1  bookmark"
         v-bind:class="(currentBookmark && currentBookmark.id === bookmark.id ? 'active':'')">
      <a>{{ bookmark.name }}</a>

      <div class="info">
        <InlineTags :tags="bookmark.tags" :book="bookmark.book"/>
      </div>

    </div>
    <i v-if="bookmark.pined === false" class="bi bi-pin-angle pin float-end" v-on:click="pin(bookmark)"></i>
    <i v-if="bookmark.pined === true" class="bi bi-pin-fill pin float-end" v-on:click="pin(bookmark)"></i>
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

.add-bookmark {
  width: 100%;
  align-items: center;
  box-sizing: border-box;
  color: #6f7782;
  display: inline-flex;
  text-align: center;
  cursor: pointer;
}

.add-bookmark-box {
  border-radius: 6px;
  height: 40px;
  width: 100%;
  margin: 0 0 4px 0;
  align-items: center;
  border: 2px dashed #9ca6af;
  box-sizing: border-box;
  color: #6f7782;
  display: flex;
  fill: #9ca6af;
  justify-content: center;
  position: relative;
}

.Icon {
  flex: 0 0 auto;
  height: 16px;
  width: 16px;
}


.bookmark {
  display: block;
  cursor: pointer;
  min-height: 70px;
  font-size: 0.95em;
  border: 1px solid #dee2e6;
}

.bookmark:hover {
  box-shadow: 0 .125rem .25rem rgba(0, 0, 0, .075) !important;
}

.bookmark.active {
  background-color: #edf8ff;
  border-color: #14aaf5;
}

.bi-pin-angle {
  position: absolute;
  right: 10px;
  bottom: 5px;
  opacity: 0.5;
  cursor: pointer;
}

.bi-pin-angle:hover {
  color: blue;
}

.bi-pin-angle:hover::before {
  content: "\f4ea";
}

.bi-pin-fill {
  position: absolute;
  right: 10px;
  bottom: 5px;
  opacity: 0.5;
  cursor: pointer;
  color: blue;
}

.bi-pin-fill:hover {
  color: blue;
}

.bi-pin-fill:hover::before {
  content: "\f4ed";
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
      currentBookmark: null,
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
      this.currentBookmark = bookmark;
      this.$parent.viewBookmark(bookmark);
    },
    remove: function (bookmark) {
      Rest.del('/v1/bookmark/' + bookmark.id).then(() => {
        this.fetchData();
      })
    },
    pin: function (bookmark) {
      Rest.post('/v1/bookmark/' + bookmark.id + '/pin')
          .then(() => {
            this.fetchData(this.book, this.search);
          });
    },
    fetchData: function (book, search) {
      this.showCreateFrom = false;
      this.book = book;
      this.search = search;
      Rest.get('/v1/search/live?' + this._getSearchUrl(book, search))
          .then(res => {
            this.bookmarks = res.data.content;
          });
    },
    _getSearchUrl: function (book, search) {
      let query = '';
      if (book)
        query += '&bookId=' + book.id;
      if (search)
        query += '&search=' + search;
      return query;
    }
  }
}
</script>