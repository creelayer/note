<template>
  <div class="edit">
    <div v-if="errors.length">
      <ul>
        <li v-bind:key="error" v-for="error in errors">{{ error }}</li>
      </ul>
    </div>

    <form class="mb-3">
      <input v-model="name" type="text" class="form-control" placeholder="Enter bookmark name" v-if="isInline">
      <div class="mb-3" v-if="!isInline">
        <input v-model="name" type="text" class="form-control" placeholder="Enter bookmark name">
      </div>
      <div class="mb-3" v-if="!isInline">

      </div>
      <div class="mb-3" v-if="!isInline">
        <textarea v-model="body" class="form-control" placeholder="Enter book body" rows="10"/>
      </div>
      <button type="submit" class="btn-primary btn-sm mt-1" v-on:click="submit">Save</button>

    </form>


  </div>
</template>
<style scoped>


</style>
<script>


import Rest from "@/api/Rest";

export default {
  name: "BookForm",
  components: {},
  props: {
    book: null,
    bookmark: null,
    isInline: null
  },
  created() {
    this.fetchTags();
  },
  data() {
    return {
      name: this.bookmark ? this.bookmark.name : null,
      body: this.bookmark ? this.bookmark.body : null,
      tags: this.bookmark ? this.bookmark.tags.map(v => v.name) : null,
      errors: [],
      allTags: [],
    }
  },
  methods: {
    fetchTags: function () {
      Rest.get('/v1/tag')
          .then(res => {
            this.allTags = res.data;
          });
    },
    submit: function (e) {
      let _that = this;
      this.errors = [];
      if (!this.name) {
        this.errors.push('Требуется указать имя.');
      }

      if (this.errors.length !== 0) {
        return;
      }

      let bookmark = {
        bookId: this.bookmark ? this.bookmark.bookId : this.book.id,
        name: this.name,
        body: this.body,
        tags: this.tags
      };

      Rest.post(this.bookmark ? '/v1/bookmark/' + this.bookmark.id : '/v1/bookmark', bookmark)
          .then(() => {
            _that.$parent.fetchData(this.book);
          });
      e.preventDefault();
    }
  }
}
</script>

<style scoped>

</style>