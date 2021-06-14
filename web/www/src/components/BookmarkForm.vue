<template>
  <div>
    <div v-if="errors.length">
      <ul>
        <li v-bind:key="error" v-for="error in errors">{{ error }}</li>
      </ul>
    </div>
    <div class="mb-3">
      <input v-model="name" type="text" class="form-control" placeholder="Enter book name">

      <Multiselect
          v-model="tags"
          :options="allTags"
          :mode="'tags'"
          :searchable="true"
          :createTag="true"
          :valueProp="'name'"
          :label="'name'"
      />


      <textarea v-model="body" v-if="showBodyForm === true" class="form-control" placeholder="Enter book body"/>
      <button type="submit" class="btn btn-primary" v-on:click="submit">Save</button>
    </div>
  </div>
</template>

<style src="@vueform/multiselect/themes/default.css"></style>
<script>

import Multiselect from '@vueform/multiselect'


export default {
  name: "BookForm",
  components: {Multiselect},
  props: {
    book: null,
    bookmark: null,
    afterSave: null,
    showBodyForm: null,
    showTagForm: null
  },
  created() {
    this.fetchTags();
  },
  data() {
    return {
      name: this.bookmark ? this.bookmark.name : null,
      body: this.bookmark ? this.bookmark.body : null,
      tags: this.bookmark ? this.bookmark.tags : null,
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
        bookId: this.bookmark ? this.bookmark.book.id : this.book.id,
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