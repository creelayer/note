<template>
  <div>
    <div v-if="errors.length">
      <ul>
        <li v-bind:key="error" v-for="error in errors">{{ error }}</li>
      </ul>
    </div>
    <div class="mb-3">
      <input v-model="name" type="text" class="form-control" placeholder="Enter book name">
      <button type="submit" class="btn btn-primary" v-on:click="submit">Save</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "BookForm",
  props: {
    book: null,
    afterSave: null,
  },
  data() {
    return {
      name: this.book ? this.book.name : null,
      errors: [],
    }
  },
  methods: {
    submit: function (e) {

      this.errors = [];
      if (!this.name) {
        this.errors.push('Требуется указать имя.');
      }

      if (this.errors.length !== 0) {
        return;
      }

      let book = this.book ? this.book : {id: null, name: ''};
      book.name = this.name;

      const requestOptions = {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(book)
      };

      fetch(book.id ? '/v1/book/' + book.id : '/v1/book', requestOptions)
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