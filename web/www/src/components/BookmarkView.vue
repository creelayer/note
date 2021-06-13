<template>

  <BookmarkForm v-if="showUpdateFrom"
                :after-save="afterSave"
                :bookmark="bookmark"
                :showBodyForm="true"
                :show-tag-form="true"/>
  <a v-if="bookmark && !showUpdateFrom" v-on:click="showUpdateFrom = true">Edit</a>

  <div v-if="bookmark && !showUpdateFrom">
    <h1>{{ bookmark.name }}</h1>
    <div>{{ bookmark.body }}</div>
    <ul>
      <li :key="tag" v-for="tag in bookmark.tags">
        {{ tag }}
      </li>
    </ul>
  </div>
</template>

<script>
import BookmarkForm from "@/components/BookmarkForm";

export default {
  name: "BookmarkView",
  components: {BookmarkForm},
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

<style scoped>

</style>