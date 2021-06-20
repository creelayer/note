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
        <Multiselect
            v-model="tags"
            :options="allTags"
            :mode="'tags'"
            :searchable="true"
            :createTag="true"
            :valueProp="'name'"
            :label="'name'"
        />
      </div>
      <div class="mb-3" v-if="!isInline">
        <textarea v-model="body" class="form-control" placeholder="Enter book body" rows="10"/>
      </div>
      <button type="submit" class="btn-primary btn-sm mt-1" v-on:click="submit"><i class="bi bi-check2"></i></button>

    </form>


  </div>
</template>

<style src="../../../node_modules/@vueform/multiselect/themes/default.css"></style>
<style scoped>


</style>
<script>

import Multiselect from '@vueform/multiselect'


export default {
  name: "BookForm",
  components: {Multiselect},
  props: {
    book: null,
    bookmark: null,
    afterSave: null,
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
      fetch('/v1/tag')
          .then(res => res.json())
          .then(res => {
            this.allTags = res.data;
          });
    },
    submit: function (e) {
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

      const requestOptions = {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(bookmark)
      };

      fetch(this.bookmark ? '/v1/bookmark/' + this.bookmark.id : '/v1/bookmark', requestOptions)
          .then(res => res.json())
          .then(res => {
            if (this.afterSave) this.afterSave(res.data);
          });

      e.preventDefault();
    }
  }
}
</script>

<style scoped>

</style>