<template>
  <div class="edit">
    <div v-if="errors.length">
      <ul>
        <li v-bind:key="error" v-for="error in errors">{{ error }}</li>
      </ul>
    </div>
    <div>
      <input v-model="name" type="text" class="form-control" placeholder="Enter book name" v-on:blur="submit">
    </div>
  </div>
</template>

<style scoped>

</style>

<script>
import Rest from "@/api/Rest"

export default {
  name: "BookForm",
  props: {
    book: null,
  },
  data() {
    return {
      name: this.book ? this.book.name : null,
      errors: [],
    }
  },
  methods: {
    submit: function (e) {
      let _that = this;
      this.errors = [];
      if (!this.name) {
        this.errors.push('Требуется указать имя.');
      }

      if (this.errors.length !== 0) {
        return;
      }

      let book = this.book ? this.book : {id: null, name: ''};
      book.name = this.name;

      Rest.post(book.id ? '/v1/book/' + book.id : '/v1/book', book)
          .then(() => {
            _that.$parent.fetchData();
          });
      e.preventDefault();
    }
  }
}
</script>