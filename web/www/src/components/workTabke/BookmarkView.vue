<template>

  <div class="view">

    <BookmarkForm v-if="showUpdateFrom"
                  :after-save="afterSave"
                  :bookmark="bookmark"
                  :is-inline="false"/>

    <a v-if="bookmark && !showUpdateFrom" v-on:click="showUpdateFrom = true">Edit</a>

    <div v-if="bookmark && !showUpdateFrom">
      <h1>{{ bookmark.name }}</h1>
      <InlineTags :tags="bookmark.tags" :book-name="bookmark.book.name"/>
      <div class="body">{{ bookmark.body }}</div>
    </div>

  </div>
</template>

<style scoped>

.view{
  /*position: absolute;*/
  /*top: 0;*/
  padding: 24px 20px;
  box-shadow: -0.5rem 0.5rem 1rem rgb(0 0 0 / 2%), inset 1px 0px 0 rgb(0 0 0 / 15%);
  height: 100%;
  width: 100%;
   margin-bottom: -24px;
}

.body {
  margin-top: 10px;
}

</style>


<script>
import BookmarkForm from "@/components/workTabke/BookmarkForm";
import InlineTags from "@/components/tags/Inline.vue";

export default {
  name: "BookmarkView",
  components: {BookmarkForm, InlineTags},
  data() {
    return {
      bookmark: null,
      showUpdateFrom: null
    }
  },
  methods: {
    fetchData: function (bookmark) {
      fetch('/v1/bookmark/' + bookmark.id)
          .then(res => res.json())
          .then(res => {
            this.bookmark = res.data;
          });
    },
    afterSave: function (bookmark) {
      this.showUpdateFrom = false;
      this.$parent.fetchBookmarks({id: bookmark.bookId});
    }
  }
}
</script>

