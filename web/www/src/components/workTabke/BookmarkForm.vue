<template>
  <div class="edit">
    <div v-if="errors.length">
      <ul>
        <li v-bind:key="error" v-for="error in errors">{{ error }}</li>
      </ul>
    </div>

    <form class="">


      <input v-model="name" type="text" autofocus class="form-control inline" placeholder="Write new bookmark name"
             v-if="isInline" v-on:blur="submit">


      <div class="mb-3" v-if="!isInline">
        <input v-model="name" type="text" class="form-control" placeholder="Enter bookmark name">
      </div>
      <div class="mb-3" v-if="!isInline">

      </div>
      <div class="mb-3" v-if="!isInline">
        <textarea v-model="body" class="form-control" placeholder="Enter book body" rows="10"/>
      </div>

    </form>


  </div>
</template>
<style scoped>

.inline {
  border: none;
  font-size: 1.1em;
  padding-left: 0;
  outline: none;
}

.form-control:focus {
  outline: none;
  box-shadow: none;
}

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
        return;
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

      Rest.post(this.bookmark ? '/v1/bookmark/' + this.bookmark.id : '/v1/bookmark/add/' + this.book.id, bookmark)
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